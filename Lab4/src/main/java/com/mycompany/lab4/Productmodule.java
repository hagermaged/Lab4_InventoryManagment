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
    { System.out.println("Create new product:");
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
    Product p1 = new Product(id,pname,mname,sname,q,p);
    System.out.println("------------------------------------------------");
    System.out.println("Quantity of the product is:"+p1.getQuantity());
    System.out.println("------------------------------------------------");
    System.out.println("Set the quantity to be:");   
    int nq = scan.nextInt();
    p1.setQuantity(nq);
    System.out.println("New quantity is:"+p1.getQuantity());
    System.out.println("------------------------------------------------");
    System.out.println("Searchkey is:"+p1.getSearchKey());
    System.out.println("------------------------------------------------");
    String product = p1.lineRepresentation();
    System.out.println("Product's Data:"+product);
    
   
    }
}
