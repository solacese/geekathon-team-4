package com.solace.fmcg;

public class Item {
  private String productId;
  private String text;
  private Double quantity;
  private String currency;
  private Double price;

  public Item(String productId, String text, Double quantity, String currency, Double price) {
    super();
    this.productId = productId;
    this.text = text;
    this.quantity = quantity;
    this.currency = currency;
    this.price = price;
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

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public String getcurrency() {
    return currency;
  }

  public void setcurrency(String currency) {
    this.currency = currency;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
