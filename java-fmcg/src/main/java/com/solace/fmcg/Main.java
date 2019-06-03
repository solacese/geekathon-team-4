package com.solace.fmcg;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String... args) throws Exception {
    List<Thread> lt = new ArrayList<Thread>();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.out.println("New shutdown hook ran!");
        for (Thread element : lt) {
          element.interrupt();
        }
        try {
          System.out.println("Waiting 10s");
          Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
      }
    });

    String envHost = System.getenv("FMCG_HOST");
    String envVPN = System.getenv("FMCG_VPN");
    String envUser = System.getenv("FMCG_USER");
    String envPassword = System.getenv("FMCG_PASSWORD");
    String envRegion = System.getenv("FMCG_REGION");
    if (envRegion == null || envRegion.isEmpty())
      envRegion = "us1"; // Backwards compatibility

    // Add some customers
    NameAddressDatabase customers = new NameAddressDatabase();
    customers.FillCustomerSamples();
    for (int i = 0; i < 1; i++) {
      NameAddress nameAddr = customers.getNameAddressByIndex(i);
      Thread t = new CustomerSimulator(envRegion, nameAddr.getId(), envHost, envVPN, envUser, envPassword);
      t.start();
      lt.add(t);
    }

    // Add some shops
    NameAddressDatabase shops = new NameAddressDatabase();
    shops.FillShopSamples();
    for (int i = 0; i < 1; i++) {
      NameAddress nameAddr = shops.getNameAddressByIndex(i);
      Thread t = new ShopSimulator(envRegion, nameAddr.getId(), envHost, envVPN, envUser, envPassword);
      t.start();
      lt.add(t);
    }

    /*
     * // Add some warehouses NameAddressDatabase warehouses = new
     * NameAddressDatabase(); warehouses.FillWarehouseSamples(); for (int i = 0; i <
     * 0; i++) { NameAddress nameAddr = warehouses.getNameAddressByIndex(i);
     * Thread t = new WarehouseSimulator(envZone, nameAddr.getId(), envHost,
     * envVPN, envUser, envPassword); t.start(); lt.add(t); }
     */

    // Add some SAP
    NameAddressDatabase saps = new NameAddressDatabase();
    saps.FillSapSamples();
    for (int i = 0; i < 1; i++) {
      NameAddress nameAddr = saps.getNameAddressByIndex(i);
      Thread t = new SapSimulator(envRegion, nameAddr.getId(), envHost, envVPN, envUser, envPassword);
      t.start();
      lt.add(t);
    }
  }
}
