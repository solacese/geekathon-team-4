package com.solace.fmcg;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.solace.fmcg.Product.StatusType;
import com.solace.fmcg.Product.UnitType;

public class ProductList {
  private String utcDateTime;
  private String locDateTime;
  private String referenceId;
  private String senderId;
  private List<Product> items;

  public ProductList(String referenceId, String senderId, List<Product> items) {
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

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public List<Product> getItems() {
    return items;
  }

  public void setItems(List<Product> items) {
    this.items = items;
  }

  public List<Product> getProducts() {
    return items;
  }

  public void setProducts(List<Product> products) {
    this.items = products;
  }

  public static void main(String... args) throws Exception {
    // Only for testing...
    List<Product> i = new ArrayList<Product>();
    i.add(new Product("productId1", "Hotpants", "Clothing", "M", "Yellow", "Fancy", UnitType.PC, "USD", 12.25,
        StatusType.NEW, 3));
    i.add(new Product("productId2", "Shovel", "Garden", "1.2m", "Grey", "Strong", UnitType.PC, "USD", 24.25,
        StatusType.UPD, 2));

    ProductList il1 = new ProductList("list-id","100000000", i);

    String jsonStringPretty = new GsonBuilder().setPrettyPrinting().create().toJson(il1);
    System.out.println(jsonStringPretty);

    String jsonString1 = new Gson().toJson(il1);
    System.out.println(jsonString1);

    ProductList il2 = new Gson().fromJson(jsonString1, ProductList.class);

    String jsonString2 = new Gson().toJson(il2);
    System.out.println(jsonString2);
  }
}
