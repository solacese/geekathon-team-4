package com.example.demo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

import ch.example.fmcg.Item;
import ch.example.fmcg.NameAddressDatabase;
import ch.example.fmcg.OrderDeliveryList;
import ch.example.fmcg.Product;
import ch.example.fmcg.ProductDatabase;
import ch.example.fmcg.ProductList;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ScsFmcgApplication {

  private static final Logger log = LoggerFactory.getLogger(ScsFmcgApplication.class);

  private ProductDatabase pdb = new ProductDatabase();
  private NameAddressDatabase shopdb = new NameAddressDatabase();
  private NameAddressDatabase custdb = new NameAddressDatabase();
  private Random rand = new Random();

  @Value("${customer.identifier}")
  private String myId;

  @Value("${customer.region}")
  private String region;

  public static void main(String[] args) {
    SpringApplication.run(ScsFmcgApplication.class, args);
  }

  @PostConstruct
  private void init() {
    pdb.FillSamples();
    custdb.FillCustomerSamples();
    shopdb.FillShopSamples();
    rand.setSeed(Instant.now().toEpochMilli());
  }

  @StreamListener(Processor.INPUT)
  public void receiveProductUpdate(ProductList pli) {
    // Update the internal database with new prices
    List<Product> productListItems = pli.getItems();
    for (Product element : productListItems) {
      String pid = element.getProductId();
      Double newPrice = element.getPrice();
      log.info("Received Product Price Update: " + element.toString());

      // Price in local product db
      Product p = pdb.getProductById(pid);
      p.setPrice(newPrice);
    }
  }

  @InboundChannelAdapter(channel = Processor.OUTPUT, poller = @Poller(fixedRate = "30000"))
  public OrderDeliveryList sendOrder() {
    int numberItems = rand.nextInt(3) + 1; // number from 1 to 3
    List<Item> li = new ArrayList<Item>();
    for (int i = 0; i < numberItems; i++) {
      Product p = pdb.getProductRandomWeightedByPopularity();
      li.add(new Item(p.getProductId(), p.getText(), new Double(rand.nextInt(3) + 1), p.getCurrency(), p.getPrice()));
    }
    OrderDeliveryList oli = new OrderDeliveryList(generateOrderNumber(9), custdb.getNameAddressById(myId),
        shopdb.getNameAddressById("200000000"), li);
    log.info("Sending New Order: " + oli.getReferenceId());
    return oli;
  }

  // Create random number for order number; not realistic - just for demo purposes
  private String generateOrderNumber(int charLength) {
    return String.valueOf(charLength < 1 ? 0
        : new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1) + (int) Math.pow(10, charLength - 1));
  }
}
