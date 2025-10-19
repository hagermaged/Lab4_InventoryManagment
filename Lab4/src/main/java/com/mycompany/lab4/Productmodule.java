/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.util.*;

/**
 *
 * @author orignal store
 */
public class Productmodule {
    public static void main(String []args)
    { System.out.println("Create new product :");
        Scanner scan = new Scanner(System.in);
    System.out.println("ID:");
    String id = scan.nextLine();
    System.out.println("Product Name:");
    String pname = scan.nextLine();
    System.out.println("Manufacturer Name:");
    String mname = scan.nextLine();    
    System.out.println("Supplier Name:");
    String sname = scan.nextLine();
    System.out.println("Quantity :");
    int q = scan.nextInt();
    System.out.println("Price:");
    float p = scan.nextFloat();
    scan.nextLine();

  
    Product po = new Product(id,pname,mname,sname,q,p);
   
   ProductDatabase db = new ProductDatabase("Products.txt");

        // Step 2: Create sample products
        Product p1 = new Product("P1001", "Laptop", "Apple", "TechSupplier", 5, 1500.00f);
        Product p2 = new Product("P1002", "Phone", "Samsung", "GadgetWorld", 10, 899.99f);
        Product p3 = new Product("P1003", "Headphones", "Sony", "AudioHub", 20, 199.50f);
        db.readFromFile();
        // Step 3: Insert products into database
        db.insertRecord(p1);
        db.insertRecord(po);
        db.insertRecord(p2);
        db.insertRecord(p3);

        // Step 4: Save to file
        db.saveToFile();
        System.out.println("Products saved to file.");

   // Step 5: Create new database to test reading
        ProductDatabase db2 = new ProductDatabase("Products.txt");
        db2.readFromFile();

        // Step 6: Retrieve and print all records
        ArrayList<Product> products = db2.returnAllRecords();
        System.out.println("Products read from file:");
        //for (Product product : products) {
            //System.out.println(product);
       // }
        //delete 
   ProductDatabase db3 = new ProductDatabase("Products.txt");
   db3.readFromFile();
       System.out.println("Enter the ID for the product to be deleted");
    String key = scan.nextLine();
   db3.deleteRecord(key);
   db3.saveToFile();
   //get record by id
   ProductDatabase db4 = new ProductDatabase("Products.txt");
   db4.readFromFile();
    System.out.println("Enter the ID for the required product");
    String key1 = scan.nextLine();
   Product required = db4.getRecord(key1);
   System.out.println("Product Name is :"+required.getProductName());
    }
}
