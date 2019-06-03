package com.solace.fmcg;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InventoryList {
  private String utcDateTime;
  private String locDateTime;
  private String referenceId;
  private String senderId;
  private List<Item> items;

  public InventoryList(String referenceId, String senderId, List<Item> items) {
    super();
    setCurrentDateTime();

    this.referenceId = referenceId;
    this.senderId = senderId;
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

  public String getreferenceId() {
    return referenceId;
  }

  public void setreferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
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
    i.add(new Item("productId1", "Hotpants", 100.0, "USD", 12.25));
    i.add(new Item("productId2", "Shovel", 50.0, "USD", 24.25));

    InventoryList il1 = new InventoryList("list-id","100000000", i);

    String jsonStringPretty = new GsonBuilder().setPrettyPrinting().create().toJson(il1);
    System.out.println(jsonStringPretty);

    String jsonString1 = new Gson().toJson(il1);
    System.out.println(jsonString1);

    InventoryList il2 = new Gson().fromJson(jsonString1, InventoryList.class);

    String jsonString2 = new Gson().toJson(il2);
    System.out.println(jsonString2);
  }
}
