package com.solace.fmcg;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class NameAddressDatabase {

  private ArrayList<NameAddress> db = new ArrayList<NameAddress>();
  Random rand = new Random();

  public NameAddressDatabase() {
    rand.setSeed(Instant.now().toEpochMilli());
  }

  public void FillCustomerSamples() {
    db.clear();
    db.add(new NameAddress("100000000","Xaviera Mccoy","Ap #159-5395 Congue, St.","San Jose","41987","United States"));
    db.add(new NameAddress("100000001","Kelly Williams","Ap #748-6007 Lorem, Av.","Tampa","45245","United States"));
    db.add(new NameAddress("100000002","Berk B. Rodgers","777-3617 Non St.","Biloxi","72194","United States"));
    db.add(new NameAddress("100000003","Maile J. Austin","Ap #147-7556 Vestibulum Ave","Miami","84354","United States"));
    db.add(new NameAddress("100000004","Jocelyn F. Manning","929-9578 Convallis, Rd.","Newport News","19016","United States"));
    db.add(new NameAddress("100000005","Lana Berry","569-5751 Tellus Road","Southaven","91484","United States"));
    db.add(new NameAddress("100000006","Emmanuel Mckee","P.O. Box 117, 9559 Ante Ave","Rock Springs","76931","United States"));
    db.add(new NameAddress("100000007","Logan Thompson","147-111 Ut, Rd.","Hillsboro","12677","United States"));
    db.add(new NameAddress("100000008","Zahir Norris","722-1918 Dis Rd.","Bellevue","90825","United States"));
    db.add(new NameAddress("100000009","Sade L. Chaney","9994 Proin St.","Butte","98557","United States"));
    db.add(new NameAddress("100000010","Kathleen H. Gamble","P.O. Box 506, 2407 Dignissim Road","Sioux City","40405","United States"));
    db.add(new NameAddress("100000011","Julie Mathews","P.O. Box 696, 2885 Blandit Rd.","Norfolk","54288","United States"));
    db.add(new NameAddress("100000012","Roary David","9455 Magna Ave","Glendale","47122","United States"));
    db.add(new NameAddress("100000013","Aladdin Z. Hayes","3429 Vestibulum Road","Tacoma","53211","United States"));
    db.add(new NameAddress("100000014","Adara Hughes","Ap #814-3720 Ultrices Rd.","Gaithersburg","45608","United States"));
    db.add(new NameAddress("100000015","Xyla Gilmore","Ap #276-5847 Penatibus Ave","Billings","69945","United States"));
    db.add(new NameAddress("100000016","Neil Mullins","341-8520 Sapien Rd.","Henderson","50888","United States"));
    db.add(new NameAddress("100000017","Maite D. Whitley","Ap #532-229 Nunc Rd.","Naperville","77540","United States"));
    db.add(new NameAddress("100000018","Solomon Bowers","P.O. Box 274, 647 Massa. Av.","Grand Rapids","32443","United States"));
    db.add(new NameAddress("100000019","Tiger D. Weeks","5574 Ante Ave","Sacramento","44852","United States"));
    db.add(new NameAddress("100000020","Ashton K. Britt","298-928 Tincidunt. Rd.","Burlington","17861","United States"));
    db.add(new NameAddress("100000021","Thaddeus L. Burton","Ap #585-6509 Egestas. Av.","Wilmington","94875","United States"));
    db.add(new NameAddress("100000022","Reuben Sawyer","P.O. Box 595, 5171 Lorem, St.","Missoula","65367","United States"));
    db.add(new NameAddress("100000023","Oleg L. Collins","308-4730 In, Avenue","Saint Paul","70486","United States"));
    db.add(new NameAddress("100000024","Signe Chen","Ap #145-994 A St.","Pocatello","18957","United States"));
    db.add(new NameAddress("100000025","Buckminster M. Ayala","P.O. Box 232, 1624 Sed Street","Detroit","26581","United States"));
    db.add(new NameAddress("100000026","Judah O. Barker","2186 Sagittis St.","Jacksonville","50040","United States"));
    db.add(new NameAddress("100000027","Blaze L. Welch","P.O. Box 383, 4042 Ac Rd.","Lawton","15403","United States"));
    db.add(new NameAddress("100000028","Kay Peck","P.O. Box 949, 9904 Convallis St.","Davenport","57897","United States"));
    db.add(new NameAddress("100000029","Jamalia P. Beach","1417 Amet Avenue","Bridgeport","87041","United States"));
    db.add(new NameAddress("100000030","Cole G. Baxter","P.O. Box 686, 1351 Curabitur Rd.","Kearney","36670","United States"));
    db.add(new NameAddress("100000031","Eliana S. Knapp","892-6861 Lacinia Road","Minneapolis","29550","United States"));
    db.add(new NameAddress("100000032","Ashely N. Lawrence","P.O. Box 108, 7162 In Ave","Southaven","80632","United States"));
    db.add(new NameAddress("100000033","Latifah E. Mueller","483 Vel Rd.","Jonesboro","44788","United States"));
    db.add(new NameAddress("100000034","Lawrence Lyons","875-8123 Augue St.","Chandler","43459","United States"));
    db.add(new NameAddress("100000035","Bradley I. Riggs","2056 Ac Street","Casper","39596","United States"));
    db.add(new NameAddress("100000036","Amir B. Nguyen","8464 Tempus Road","Tucson","23752","United States"));
    db.add(new NameAddress("100000037","Declan Q. Hurley","159-4719 Fringilla. Rd.","Cedar Rapids","14219","United States"));
    db.add(new NameAddress("100000038","Leigh Gray","1021 Luctus Rd.","Norfolk","20700","United States"));
    db.add(new NameAddress("100000039","Patrick F. Ewing","9625 Donec Ave","Tampa","52734","United States"));
    db.add(new NameAddress("100000040","Lionel Hopkins","Ap #217-7190 Tellus Avenue","Fort Worth","68593","United States"));
    db.add(new NameAddress("100000041","Gary Q. Schneider","P.O. Box 506, 5214 Faucibus St.","Bozeman","65667","United States"));
    db.add(new NameAddress("100000042","Chiquita W. Boyd","8067 Urna. St.","San Diego","65472","United States"));
    db.add(new NameAddress("100000043","Bertha R. Blair","2233 Mauris St.","New Haven","95482","United States"));
    db.add(new NameAddress("100000044","Igor Charles","Ap #783-9846 Nulla. Road","Lawton","14559","United States"));
    db.add(new NameAddress("100000045","Deborah Reese","Ap #452-9947 Volutpat St.","Lansing","96057","United States"));
    db.add(new NameAddress("100000046","Noble Sullivan","365-9261 Dolor. Street","Houston","51500","United States"));
    db.add(new NameAddress("100000047","Tanya Tucker","700-8354 Aliquet St.","Augusta","25332","United States"));
    db.add(new NameAddress("100000048","Mariam Le","774-4957 Venenatis Rd.","Phoenix","86430","United States"));
    db.add(new NameAddress("100000049","India Herring","P.O. Box 220, 3052 Ac Avenue","Allentown","19227","United States"));
  }  

  public void FillShopSamples() {
    db.clear();
    db.add(new NameAddress("200000000","Ut Erat Associates","Ap #773-9496 Tellus, Rd.","Fort Wayne","47173","United States"));
    db.add(new NameAddress("200000001","Auctor Limited","401-6370 Congue, St.","Augusta","78664","United States"));
    db.add(new NameAddress("200000002","Purus Maecenas Corporation","Ap #845-3120 Erat Rd.","Clarksville","83187","United States"));
    db.add(new NameAddress("200000003","Nisl Sem Associates","P.O. Box 162, 4214 Habitant Rd.","Bellevue","44588","United States"));
    db.add(new NameAddress("200000004","Pharetra Nam Corporation","Ap #467-3173 Vestibulum St.","Memphis","29992","United States"));
    db.add(new NameAddress("200000005","Ut Sem Industries","5021 Diam Road","Kansas City","67382","United States"));
    db.add(new NameAddress("200000006","Mi Ac Consulting","961-8130 Metus Avenue","Chattanooga","52267","United States"));
    db.add(new NameAddress("200000007","Lacus Nulla Tincidunt PC","9773 Mauris Ave","Reno","44995","United States"));
    db.add(new NameAddress("200000008","Sem Industries","P.O. Box 228, 4593 At Rd.","Savannah","53043","United States"));
    db.add(new NameAddress("200000009","Pharetra Felis Eget Associates","897-9078 Turpis Street","College","65180","United States"));
    db.add(new NameAddress("200000010","Donec Luctus Trading","4839 Egestas Ave","Hilo","31147","United States"));
    db.add(new NameAddress("200000011","Non LLP","P.O. Box 393, 8074 Arcu. Avenue","Los Angeles","76369","United States"));
    db.add(new NameAddress("200000012","Sit Amet Associates","Ap #111-8476 Cursus Street","South Bend","30402","United States"));
    db.add(new NameAddress("200000013","Dis Parturient Inc.","150-4134 Parturient Av.","Boise","90288","United States"));
    db.add(new NameAddress("200000014","Magna Suspendisse Corp.","6419 Arcu. Avenue","New Haven","25815","United States"));
    db.add(new NameAddress("200000015","Magna Malesuada Vel Corporation","824-5075 Donec Ave","Bozeman","19594","United States"));
    db.add(new NameAddress("200000016","Dictum Eu Foundation","P.O. Box 573, 1715 Sed St.","Anchorage","69639","United States"));
    db.add(new NameAddress("200000017","Scelerisque Neque Nullam Corporation","P.O. Box 932, 9256 Nec Rd.","Phoenix","86884","United States"));
    db.add(new NameAddress("200000018","Mollis Duis Inc.","3634 Feugiat Street","Colchester","40901","United States"));
    db.add(new NameAddress("200000019","Arcu Curabitur Ut Incorporated","P.O. Box 714, 3778 A Avenue","Bellevue","36607","United States"));
    db.add(new NameAddress("200000020","Magna Corp.","Ap #151-2883 Integer Av.","Green Bay","60787","United States"));
    db.add(new NameAddress("200000021","Volutpat Nunc Sit Corporation","3035 Leo. Avenue","Vancouver","29138","United States"));
    db.add(new NameAddress("200000022","Eleifend Cras Trading","Ap #148-519 Mollis Rd.","Boston","78167","United States"));
    db.add(new NameAddress("200000023","Pellentesque Tellus Trading","9575 Aliquam St.","Tallahassee","76262","United States"));
    db.add(new NameAddress("200000024","Arcu Vestibulum Ltd","2335 Sollicitudin Rd.","Houston","55398","United States"));
  }

  public void FillWarehouseSamples() {
    db.clear();
    db.add(new NameAddress("300000000","Faucibus Id Foundation","6936 Arcu. Rd.","Lafayette","34560","United States"));
    db.add(new NameAddress("300000001","Cursus Et Magna Corporation","Ap #447-8513 Varius St.","Hartford","63287","United States"));
    db.add(new NameAddress("300000002","Id Risus Quis Company","P.O. Box 774, 9443 Amet, Road","Tulsa","53679","United States"));
    db.add(new NameAddress("300000003","Porttitor Eros Industries","8522 Placerat. Road","Billings","88227","United States"));
    db.add(new NameAddress("300000004","Est Ac Associates","Ap #673-8091 Rutrum. St.","Kailua","41984","United States"));
    db.add(new NameAddress("300000005","Ut Sagittis Limited","187-4293 A, St.","Cedar Rapids","14351","United States"));
    db.add(new NameAddress("300000006","Mauris Ut Foundation","677-2313 Mi St.","Tallahassee","74673","United States"));
    db.add(new NameAddress("300000007","Urna Trading","P.O. Box 899, 2220 Magna. St.","Reno","84234","United States"));
    db.add(new NameAddress("300000008","Orci Ltd","9892 Bibendum St.","Evansville","57845","United States"));
    db.add(new NameAddress("300000009","Magna LLP","P.O. Box 391, 7975 Vestibulum Av.","Montpelier","95999","United States"));
    db.add(new NameAddress("300000010","Sed Associates","P.O. Box 286, 2266 Vestibulum Street","Indianapolis","92102","United States"));
    db.add(new NameAddress("300000011","Vulputate Risus Consulting","7138 Dictum Rd.","Grand Island","56537","United States"));
    db.add(new NameAddress("300000012","Maecenas Malesuada Incorporated","P.O. Box 680, 7890 Egestas Ave","Green Bay","72965","United States"));
    db.add(new NameAddress("300000013","Est Mauris Corporation","Ap #186-2693 Amet, Avenue","New Haven","88255","United States"));
    db.add(new NameAddress("300000014","Hendrerit Company","487-6720 Aliquam Road","Fayetteville","73809","United States"));
    db.add(new NameAddress("300000015","Vitae Sodales Nisi Limited","357-2376 Rutrum St.","Frederick","79994","United States"));
    db.add(new NameAddress("300000016","Aliquet Phasellus Fermentum Trading","7544 Pellentesque Avenue","Rockford","75115","United States"));
    db.add(new NameAddress("300000017","Vel Nisl LLP","P.O. Box 121, 6663 Sagittis Avenue","Anchorage","67463","United States"));
    db.add(new NameAddress("300000018","Parturient Limited","5628 Lacinia. Road","Portland","79438","United States"));
    db.add(new NameAddress("300000019","Arcu Vestibulum Ante Company","845-1082 Ante Av.","Southaven","32233","United States"));
    db.add(new NameAddress("300000020","Orci Luctus Et Ltd","581-9381 Nam Av.","Aurora","63504","United States"));
    db.add(new NameAddress("300000021","Sem Ut Cursus Associates","772-8657 Aliquam Ave","Miami","78330","United States"));
    db.add(new NameAddress("300000022","Egestas Fusce Incorporated","443-2165 Elit Avenue","Birmingham","20996","United States"));
    db.add(new NameAddress("300000023","Erat Semper Rutrum Corporation","Ap #858-9742 Volutpat St.","Waterbury","31036","United States"));
    db.add(new NameAddress("300000024","Nec Leo Morbi Incorporated","951-1228 Auctor St.","Wilmington","19962","United States"));
  }

  public void FillSapSamples() {
    db.clear();
    db.add(new NameAddress("400000000","Corporate SAP Instance","","","",""));
    db.add(new NameAddress("400000001","Corporate SAP Instance","","","",""));
    db.add(new NameAddress("400000002","Corporate SAP Instance","","","",""));
    db.add(new NameAddress("400000003","Corporate SAP Instance","","","",""));
    db.add(new NameAddress("400000004","Corporate SAP Instance","","","",""));
  }


  public NameAddress getNameAddressRandom() {
    return db.get(rand.nextInt(db.size()));
  }

  public NameAddress getNameAddressById(String id) {
    for (NameAddress el : db)
      if (el.getId().equals(id))
        return el;
    return null;
  }
  
  public NameAddress getNameAddressByIndex(int ix) {
    return db.get(ix);
  }
  
  // Just for testing...
  public static void main(String... args) throws Exception {

    NameAddressDatabase pdb = new NameAddressDatabase();
    pdb.FillCustomerSamples();

    for (int i = 0; i < 10; i++) {
      NameAddress p = pdb.getNameAddressRandom();
      System.out.println(p.getName() + ", " + p.getCity());
    }
    
    System.out.println("Now direct access");
    NameAddress p1=pdb.getNameAddressById("100000000");
    System.out.println(p1.getName());
    NameAddress p2=pdb.getNameAddressById("100000049");
    System.out.println(p2.getName());
  }
}
