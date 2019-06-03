package com.solace.fmcg;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class CustomerSimulator extends Thread {

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
  private NameAddressDatabase shopdb = new NameAddressDatabase();
  private NameAddressDatabase custdb = new NameAddressDatabase();
  private Random rand = new Random();

  public CustomerSimulator(String region, String myId, String host, String vpn, String user, String password) {
    super();
    this.region = region;
    this.myId = myId;
    this.host = host;
    this.vpn = vpn;
    this.user = user;
    this.password = password;

    pdb.FillSamples();
    custdb.FillCustomerSamples();
    shopdb.FillShopSamples();
    rand.setSeed(Instant.now().toEpochMilli());
  }

  // Product update from SAP -> update local database
  private class ProdinfoListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
      try {
        // Get the product info message
        String orderMsgText = ((TextMessage) message).getText();
        ProductList pli = new Gson().fromJson(orderMsgText, ProductList.class);

        // Update the internal database with new prices
        List<Product> productListItems = pli.getItems();
        for (Product element : productListItems) {
          String pid = element.getProductId();
          Double newPrice = element.getPrice();

          // Price in local product db
          Product p = pdb.getProductById(pid);
          p.setPrice(newPrice);
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
      Thread.sleep(rand.nextInt(3000) + 1000); // Sleep a bit until infrastructure is ready ;-)

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

      MessageConsumer prodinfoConsumer = session.createConsumer(session.createTopic(region + "/prodinfo/sap/>"));
      prodinfoConsumer.setMessageListener(new ProdinfoListener());

      connection.start();

      System.out.println(String.valueOf(this.getId()) + " connected");

      while (true) {
        // Every n sec send some order message to shop
        // TODO: Parameterize the destination shop
        int numberItems = rand.nextInt(3) + 1; // number from 1 to 3
        List<Item> li = new ArrayList<Item>();
        for (int i = 0; i < numberItems; i++) {
          Product p = pdb.getProductRandomWeightedByPopularity();
          li.add(
              new Item(p.getProductId(), p.getText(), new Double(rand.nextInt(3) + 1), p.getCurrency(), p.getPrice()));
        }
        OrderDeliveryList oli = new OrderDeliveryList("000000000", custdb.getNameAddressById(myId),
            shopdb.getNameAddressById("200000000"), li);
        String jsonString = new Gson().toJson(oli);
        TextMessage message = session.createTextMessage(jsonString);
        //BytesMessage message = session.createBytesMessage();
        //message.writeBytes(jsonString.getBytes());

        MessageProducer messageProducer = session
            .createProducer(session.createTopic(region + "/order/cust/" + myId + "/shop/" + "200000000"));
        messageProducer.send(message, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
            Message.DEFAULT_TIME_TO_LIVE);
        messageProducer.close();

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
