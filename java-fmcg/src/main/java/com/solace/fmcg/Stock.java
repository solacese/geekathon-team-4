package com.solace.fmcg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {

  private Map<String, Item> stock = new HashMap<String, Item>();

  public void initFromProductDatabase(ProductDatabase pdb) {
    stock.clear();

    for (int i = 0; i < pdb.getNumberProducts(); i++) {
      Product p = pdb.getProductByIndex(i);
      Item it = new Item(p.getProductId(), p.getText(), 0.0, p.getCurrency(), p.getPrice());
      stock.put(p.getProductId(), it);
    }
  }

  // Returns inventory list of changed items
  public List<Item> storeItems(List<Item> li) {
    List<Item> inventoryList = new ArrayList<Item>();

    for (Item entry : li) {
      Item stockItem = stock.get(entry.getProductId());
      stockItem.setQuantity(stockItem.getQuantity() + entry.getQuantity());
      inventoryList.add(stockItem);
    }
    return inventoryList;
  }

  // Returns inventory list of changed items
  // Attention: li is modified if not sufficient stock!
  public List<Item> retrieveItems(List<Item> li) {
    List<Item> inventoryList = new ArrayList<Item>();

    for (Item entry : li) {
      Item stockItem = stock.get(entry.getProductId());

      Double sqty = stockItem.getQuantity();
      Double eqty = entry.getQuantity();
      if (sqty < eqty) {
        eqty = sqty;
        entry.setQuantity(eqty);
      }
      stockItem.setQuantity(stockItem.getQuantity() - entry.getQuantity());
      inventoryList.add(stockItem);
    }
    return inventoryList;
  }

  // Fake top up, return a list with inventory change
  public List<Item> topUp() {
    List<Item> inventoryList = new ArrayList<Item>();

    for (Map.Entry<String, Item> entry : stock.entrySet()) {
      if (entry.getValue().getQuantity() < 3.0) {
        entry.getValue().setQuantity(10.0);
        inventoryList.add(entry.getValue());
      }
    }
    return inventoryList;
  }
  
  // Return reference to a stock item
  public Item getItemById (String id) {
    return stock.get(id);
  }
}
