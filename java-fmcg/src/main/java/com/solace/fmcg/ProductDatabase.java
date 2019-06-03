package com.solace.fmcg;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import com.solace.fmcg.Product.StatusType;
import com.solace.fmcg.Product.UnitType;


public class ProductDatabase {

  private ArrayList<Product> db = new ArrayList<Product>();
  private Random rand = new Random();
 
  public ProductDatabase() {
    rand.setSeed(Instant.now().toEpochMilli());
  }
  
  public void FillSamples() {
    db.add(new Product("100000000","Pullover","Clothing","S","Red","",UnitType.PC,"USD",25.00,StatusType.UPD,3));
    db.add(new Product("100000001","Pullover","Clothing","M","Green","",UnitType.PC,"USD",25.00,StatusType.UPD,2));
    db.add(new Product("100000002","Pullover","Clothing","L","Blue","",UnitType.PC,"USD",25.00,StatusType.UPD,4));
    db.add(new Product("100000003","Pullover","Clothing","XL","Black","",UnitType.PC,"USD",25.00,StatusType.UPD,5));
    db.add(new Product("100000004","Pullover","Clothing","XXL","White","",UnitType.PC,"USD",25.00,StatusType.UPD,3));
    db.add(new Product("100000005","Tshirt","Clothing","S","Red","",UnitType.PC,"USD",7.00,StatusType.UPD,5));
    db.add(new Product("100000006","Tshirt","Clothing","M","Green","",UnitType.PC,"USD",7.00,StatusType.UPD,5));
    db.add(new Product("100000007","Tshirt","Clothing","L","Blue","",UnitType.PC,"USD",7.00,StatusType.UPD,2));
    db.add(new Product("100000008","Tshirt","Clothing","XL","Black","",UnitType.PC,"USD",7.00,StatusType.UPD,3));
    db.add(new Product("100000009","Tshirt","Clothing","XXL","White","",UnitType.PC,"USD",7.00,StatusType.UPD,5));
    db.add(new Product("100000010","Pants Long","Clothing","S","Blue","",UnitType.PC,"USD",34.00,StatusType.UPD,1));
    db.add(new Product("100000011","Pants Long","Clothing","M","Blue","",UnitType.PC,"USD",45.00,StatusType.UPD,3));
    db.add(new Product("100000012","Pants Long","Clothing","L","Blue","",UnitType.PC,"USD",34.00,StatusType.UPD,2));
    db.add(new Product("100000013","Pants Long","Clothing","XL","Black","",UnitType.PC,"USD",56.00,StatusType.UPD,1));
    db.add(new Product("100000014","Pants Long","Clothing","XXL","Black","",UnitType.PC,"USD",67.00,StatusType.UPD,4));
    db.add(new Product("100000015","Socks","Clothing","S","Black","",UnitType.PC,"USD",9.50,StatusType.UPD,4));
    db.add(new Product("100000016","Socks","Clothing","M","Black","",UnitType.PC,"USD",9.50,StatusType.UPD,1));
    db.add(new Product("100000017","Socks","Clothing","L","Black","",UnitType.PC,"USD",9.50,StatusType.UPD,3));
    db.add(new Product("100000018","Socks","Clothing","XL","Black","",UnitType.PC,"USD",9.50,StatusType.UPD,2));
    db.add(new Product("100000019","Socks","Clothing","XXL","Black","",UnitType.PC,"USD",9.50,StatusType.UPD,2));
    db.add(new Product("100000020","Knife","Kitchen","","","Vegetables",UnitType.PC,"USD",3.00,StatusType.UPD,4));
    db.add(new Product("100000021","Knife","Kitchen","","","Steak",UnitType.PC,"USD",4.00,StatusType.UPD,1));
    db.add(new Product("100000022","Knife","Kitchen","S","","",UnitType.PC,"USD",23.00,StatusType.UPD,5));
    db.add(new Product("100000023","Knife","Kitchen","M","","",UnitType.PC,"USD",34.00,StatusType.UPD,3));
    db.add(new Product("100000024","Knife","Kitchen","L","","",UnitType.PC,"USD",44.00,StatusType.UPD,3));
    db.add(new Product("100000025","Fork","Kitchen","S","","",UnitType.PC,"USD",10.00,StatusType.UPD,4));
    db.add(new Product("100000026","Fork","Kitchen","M","","",UnitType.PC,"USD",11.00,StatusType.UPD,1));
    db.add(new Product("100000027","Fork","Kitchen","L","","",UnitType.PC,"USD",12.00,StatusType.UPD,4));
    db.add(new Product("100000028","Spoon","Kitchen","S","","",UnitType.PC,"USD",10.00,StatusType.UPD,1));
    db.add(new Product("100000029","Spoon","Kitchen","M","","",UnitType.PC,"USD",11.00,StatusType.UPD,5));
    db.add(new Product("100000030","Spoon","Kitchen","L","","",UnitType.PC,"USD",12.00,StatusType.UPD,1));
    db.add(new Product("100000031","Plate","Kitchen","S","White","",UnitType.PC,"USD",6.00,StatusType.UPD,5));
    db.add(new Product("100000032","Plate","Kitchen","M","White","",UnitType.PC,"USD",8.00,StatusType.UPD,5));
    db.add(new Product("100000033","Plate","Kitchen","L","White","",UnitType.PC,"USD",10.00,StatusType.UPD,2));
    db.add(new Product("100000034","Glass","Kitchen","","Water","",UnitType.PC,"USD",7.00,StatusType.UPD,3));
    db.add(new Product("100000035","Glass","Kitchen","","","White Wine",UnitType.PC,"USD",9.00,StatusType.UPD,4));
    db.add(new Product("100000036","Glass","Kitchen","","","Read Wine",UnitType.PC,"USD",11.00,StatusType.UPD,5));
    db.add(new Product("100000037","Notebook","Office","S","","Lines",UnitType.PC,"USD",4.00,StatusType.UPD,4));
    db.add(new Product("100000038","Notebook","Office","M","","Plain",UnitType.PC,"USD",6.00,StatusType.UPD,1));
    db.add(new Product("100000039","Notebook","Office","L","","",UnitType.PC,"USD",8.00,StatusType.UPD,3));
    db.add(new Product("100000040","Scissors","Office","S","","Stainless",UnitType.PC,"USD",23.00,StatusType.UPD,5));
    db.add(new Product("100000041","Scissors","Office","M","","Stainless",UnitType.PC,"USD",27.00,StatusType.UPD,1));
    db.add(new Product("100000042","Scissors","Office","L","","Stainless",UnitType.PC,"USD",31.00,StatusType.UPD,5));
    db.add(new Product("100000043","Cable Mains","Doityourself","5m","","",UnitType.PC,"USD",3.00,StatusType.UPD,2));
    db.add(new Product("100000044","Cable Mains","Doityourself","10m","","",UnitType.PC,"USD",5.00,StatusType.UPD,5));
    db.add(new Product("100000045","Cable Mains","Doityourself","20m","","",UnitType.PC,"USD",7.00,StatusType.UPD,4));
    db.add(new Product("100000046","Tube Alu","Doityourself","15mm x 10m","","",UnitType.PC,"USD",23.00,StatusType.UPD,1));
    db.add(new Product("100000047","Tube Alu","Doityourself","30mm x 10m","","",UnitType.PC,"USD",28.00,StatusType.UPD,3));
    db.add(new Product("100000048","Tube Alu","Doityourself","50mm x 10m","","",UnitType.PC,"USD",32.00,StatusType.UPD,2));
    db.add(new Product("100000049","Board","Doityourself","40x60cm","","Wood",UnitType.PC,"USD",12.00,StatusType.UPD,4));
    db.add(new Product("100000050","Board","Doityourself","40x80cm","","Wood",UnitType.PC,"USD",15.00,StatusType.UPD,1));
    db.add(new Product("100000051","Board","Doityourself","40x100cm","","Wood",UnitType.PC,"USD",18.00,StatusType.UPD,5));
  }

  public Product getProductRandom() {
    return db.get(rand.nextInt(db.size()));
  }

  // Popularity is 1..5
  public Product getProductRandomWeightedByPopularity() {
    Product p = null;

    for (int i = 5; i > 0; i--) {
      p = db.get(rand.nextInt(db.size()));
      if (p.getPopularity() >= i)
        break;
    }
    return p;
  }

  public Product getProductById(String id) {
    for (Product el : db)
      if (el.getProductId().equals(id))
        return el;
    return null;
  }
  
  public Product getProductByIndex(int ix) {
    return db.get(ix);
  }
  
  public int getNumberProducts() {
    return db.size();
  }
  
  // Just for testing...
  public static void main(String... args) throws Exception {

    ProductDatabase pdb = new ProductDatabase();
    pdb.FillSamples();

    for (int i = 0; i < 10; i++) {
      Product p = pdb.getProductRandom();
      System.out.println(p.getText()+" "+p.getPopularity());
    }
 
    System.out.println("Now direct access");
    Product p1=pdb.getProductById("100000000");
    System.out.println(p1.getText());
    Product p2=pdb.getProductById("100000051");
    System.out.println(p2.getText());
  }
}
