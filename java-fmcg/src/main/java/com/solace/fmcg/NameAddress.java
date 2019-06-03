package com.solace.fmcg;

public class NameAddress {

  private String id;
  private String name;
  private String street;
  private String city;
  private String zip;
  private String country;
  
  public NameAddress(String id, String name, String street, String city, String zip, String country) {
    super();
    this.id = id;
    this.name = name;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.country = country;
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getStreet() {
    return street;
  }
  
  public void setStreet(String street) {
    this.street = street;
  }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getZip() {
    return zip;
  }
  
  public void setZip(String zip) {
    this.zip = zip;
  }
  
  public String getCountry() {
    return country;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
}
