package com.solace.fmcg;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderDeliveryList {
  private String utcDateTime;
  private String locDateTime;
  private String referenceId;
  private NameAddress sender;
  private NameAddress receiver;
  private List<Item> items;

  public OrderDeliveryList(String referenceId, NameAddress sender, NameAddress receiver, List<Item> items) {
    super();
    setCurrentDateTime();
    
    this.referenceId = referenceId;
    this.items = items;
    this.referenceId = referenceId;
    this.sender = sender;
    this.receiver = receiver;
    this.items = items;
  }

  public void setCurrentDateTime() {
    ZonedDateTime ldtZoned = ZonedDateTime.now();
    ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
    this.locDateTime = ldtZoned.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    this.utcDateTime = utcZoned.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  public String getUtcDateTime() {
    return utcDateTime;
  }

  public void setUtcDateTime(String utcDateTime) {
    this.utcDateTime = utcDateTime;
  }

  public String getLocDateTime() {
    return locDateTime;
  }

  public void setLocDateTime(String locDateTime) {
    this.locDateTime = locDateTime;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public NameAddress getSender() {
    return sender;
  }

  public void setSender(NameAddress sender) {
    this.sender = sender;
  }

  public NameAddress getReceiver() {
    return receiver;
  }

  public void setReceiver(NameAddress receiver) {
    this.receiver = receiver;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public static void main(String... args) throws Exception {
    // Only for testing...
    List<Item> i = new ArrayList<Item>();
    i.add(new Item("productId1", "Hotpants", 2.0, "USD", 12.25));
    i.add(new Item("productId2", "Shovel", 1.0, "USD", 24.25));

    OrderDeliveryList il1 = new OrderDeliveryList("list-id", 
        new NameAddress("100000000", "sendername", "senderstreet", "sendercity", "senderzip", "sendercountry"),
        new NameAddress("100000001", "receivername", "receiverstreet", "receivercity", "receiverzip",
            "receivercountry"),
        i);

    String jsonStringPretty = new GsonBuilder().setPrettyPrinting().create().toJson(il1);
    System.out.println(jsonStringPretty);

    String jsonString1 = new Gson().toJson(il1);
    System.out.println(jsonString1);

    OrderDeliveryList il2 = new Gson().fromJson(jsonString1, OrderDeliveryList.class);

    String jsonString2 = new Gson().toJson(il2);
    System.out.println(jsonString2);

  }
}
