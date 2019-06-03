package com.solace.fmcg;

public class Product {

  public enum UnitType {
    PC, KG, M, M2, M3, L
  }

  public enum StatusType {
    NEW, UPD, DEL
  }

  private String productId;
  private String text;
  private String category;
  private String size;
  private String color;
  private String other;
  private UnitType unit;
  private String currency;
  private Double price;
  StatusType status;
  private Integer popularity;

  public Product(String productId, String text, String category, String size, String color, String other, UnitType unit,
      String currency, Double price, StatusType status, Integer popularity) {
    super();
    this.productId = productId;
    this.text = text;
    this.category = category;
    this.size = size;
    this.color = color;
    this.other = other;
    this.unit = unit;
    this.currency = currency;
    this.price = price;
    this.status = status;
    this.popularity = popularity;
  }
  
  public String getProductId() {
    return productId;
  }
  public void setProductId(String productId) {
    this.productId = productId;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getSize() {
    return size;
  }
  public void setSize(String size) {
    this.size = size;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public String getOther() {
    return other;
  }
  public void setOther(String other) {
    this.other = other;
  }
  public UnitType getUnit() {
    return unit;
  }
  public void setUnit(UnitType unit) {
    this.unit = unit;
  }
  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }
  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = price;
  }
  public StatusType getStatus() {
    return status;
  }
  public void setStatus(StatusType status) {
    this.status = status;
  }
  public Integer getPopularity() {
    return popularity;
  }
  public void setPopularity(Integer popularity) {
    this.popularity = popularity;
  }
}
