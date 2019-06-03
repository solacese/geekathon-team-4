package com.solace.fmcg;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;

import com.google.gson.Gson;
import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

public class SapSimulator extends Thread {

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
  private Random rand = new Random();

  public SapSimulator(String region, String myId, String host, String vpn, String user, String password) {
    super();
    this.region = region;
    this.myId = myId;
    this.host = host;
    this.vpn = vpn;
    this.user = user;
    this.password = password;

    pdb.FillSamples();
    rand.setSeed(Instant.now().toEpochMilli());
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

      connection.start();

      System.out.println(String.valueOf(this.getId()) + " connected");

      while (true) {
        // Every n sec send some random "product update"
        Thread.sleep(18000);

        int numberItems = rand.nextInt(3) + 1; // number from 1 to 3
        List<Product> li = new ArrayList<Product>();
        for (int i = 0; i < numberItems; i++) {
          Product p = pdb.getProductRandom();
          Double factor = rand.nextDouble() * 0.1 + 0.95; // 0.95 .. 1.05
          Double oldPrice = p.getPrice();
          Double newPrice = Math.ceil(oldPrice * factor * 100.0) / 100.0;
          // System.out.println("Old: " + oldPrice + " new: " + newPrice);
          p.setPrice(newPrice); // Set in db
          li.add(p);
        }
        ProductList pli = new ProductList("000000000", myId, li);
        String jsonString = new Gson().toJson(pli);
        TextMessage message = session.createTextMessage(jsonString);

        MessageProducer messageProducer = session.createProducer(session.createTopic(region + "/prodinfo/sap/" + myId));
        messageProducer.send(message, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
            Message.DEFAULT_TIME_TO_LIVE);
        messageProducer.close();
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
