package com.solace.fmcg;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;

import com.google.gson.Gson;
import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

public class ShopSimulator extends Thread {

  private String region;
  private String myId;
  private String host;
  private String vpn;
  private String user;
  private String password;
  private Connection connection;
  private Session session;
  private TopicSubscriber topicSubscriber;
  private ProductDatabase pdb = new ProductDatabase();
  private Stock stock = new Stock();
  private Random rand = new Random();

  public ShopSimulator(String region, String myId, String host, String vpn, String user, String password) {
    super();
    this.region = region;
    this.myId = myId;
    this.host = host;
    this.vpn = vpn;
    this.user = user;
    this.password = password;

    pdb.FillSamples();
    rand.setSeed(Instant.now().toEpochMilli());
    stock.initFromProductDatabase(pdb); // All 0 quantity now
  }

  // Order from customer -> deliver items and send inventory update
  private class OrderListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
      try {
        // Get the order message
        String orderMsgText = null;
        if (message instanceof TextMessage) {
          orderMsgText = ((TextMessage) message).getText();
        }
        else if (message instanceof BytesMessage) {
          BytesMessage bytesMessage = (BytesMessage) message;
          byte[] data = new byte[(int) bytesMessage.getBodyLength()];
          bytesMessage.readBytes(data);
          orderMsgText = new String(data);
        }
        OrderDeliveryList ol = new Gson().fromJson(orderMsgText, OrderDeliveryList.class);

        MessageProducer deliveryMessageProducer = session
            .createProducer(session.createTopic(region + "/delivery/shop/" + myId + "/cust/" + ol.getSender().getId()));
        MessageProducer inventoryMessageProducer = session
            .createProducer(session.createTopic(region + "/inventory/shop/" + myId));

        // Top up the stock (if required) and send inventory update
        List<Item> inventoryListItems1 = stock.topUp();
        if (inventoryListItems1.size() > 0) {
          InventoryList il1 = new InventoryList("000000000", myId, inventoryListItems1);
          String inventoryMessageTextT = new Gson().toJson(il1);
          TextMessage inventoryMessageT = session.createTextMessage(inventoryMessageTextT);
          inventoryMessageProducer.send(inventoryMessageT, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
              Message.DEFAULT_TIME_TO_LIVE);
        }

        // Get requested items from stock and send a delivery message
        List<Item> inventoryListItems2 = stock.retrieveItems(ol.getItems());
        NameAddress tmprcv = ol.getReceiver(); // swap sender and receiver
        ol.setReceiver(ol.getSender());
        ol.setSender(tmprcv);
        String deliveryMsgText = new Gson().toJson(ol);
        TextMessage deliveryMessage = session.createTextMessage(deliveryMsgText);
        deliveryMessageProducer.send(deliveryMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
            Message.DEFAULT_TIME_TO_LIVE);

        // Send an inventory update (if required)
        if (inventoryListItems2.size() > 0) {
          InventoryList il2 = new InventoryList("000000000", myId, inventoryListItems2);
          String inventoryMessageText2 = new Gson().toJson(il2);
          TextMessage inventoryMessage2 = session.createTextMessage(inventoryMessageText2);
          inventoryMessageProducer.send(inventoryMessage2, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
              Message.DEFAULT_TIME_TO_LIVE);
        }

        deliveryMessageProducer.close();
        inventoryMessageProducer.close();

      } catch (Exception ex) {
        System.out.println("Error processing order message.");
        ex.printStackTrace();
      }
    }
  }

  // Product update from SAP -> update local database
  private class ProdinfoListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
      try {
        // Get the product info message
        String orderMsgText = ((TextMessage) message).getText();
        ProductList pli = new Gson().fromJson(orderMsgText, ProductList.class);
        List<Product> productListItems = pli.getItems();

        // Update the internal database with new prices
        for (Product element : productListItems) {
          String pid = element.getProductId();
          Double newPrice = element.getPrice();

          // Price in local product db
          Product p = pdb.getProductById(pid);
          p.setPrice(newPrice);

          // Price in local store
          Item it = stock.getItemById(pid);
          it.setPrice(newPrice);
        }
      } catch (Exception ex) {
        System.out.println("Error processing product update message.");
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void run() {

    System.out.println(String.valueOf(this.getId()) + " started");

    try {
      SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
      connectionFactory.setHost(host);
      if (vpn != null && !vpn.isEmpty())
        connectionFactory.setVPN(vpn);
      if (user != null && !user.isEmpty())
        connectionFactory.setUsername(user);
      if (password != null && !password.isEmpty())
        connectionFactory.setPassword(password);
      connectionFactory.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      connectionFactory.setDirectTransport(false);
      connectionFactory.setDynamicDurables(false);

      connection = connectionFactory.createConnection();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      MessageConsumer orderConsumer = session
          .createConsumer(session.createTopic(region + "/order/cust/*/shop/" + myId));
      orderConsumer.setMessageListener(new OrderListener());

      MessageConsumer prodinfoConsumer = session.createConsumer(session.createTopic(region + "/prodinfo/sap/>"));
      prodinfoConsumer.setMessageListener(new ProdinfoListener());

      connection.start();

      System.out.println(String.valueOf(this.getId()) + " connected");

      while (true) {
        // Every n seconds do something useful ;-)
        Thread.sleep(10000);
      }

    } catch (InterruptedException e) {
    } catch (Exception e) {
      System.out.println("Exception received.");
      e.printStackTrace();
    } finally {
      try {
        System.out.println(String.valueOf(this.getId()) + " shutting down ...");
        connection.stop();
        topicSubscriber.close();
        session.close();
        connection.close();
      } catch (Exception e) {
      }
    }
  }
}
