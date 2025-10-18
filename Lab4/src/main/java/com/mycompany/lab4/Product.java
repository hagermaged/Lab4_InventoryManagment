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
public class Product {
private final String productID;
private String productName;
private String manufacturerName;
private String supplierName;
private int quantity;
private float price;
//constructor

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID = productID;
        //id must be unique
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

//setters 


//increase quantity
//decrease quantity
//linerepresntation
//gersearchkey

   

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }
//getters
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }
//increase quantity by one
//public void increasequantity()
//{this.quantity +=1;}
//decrease quantity by one
//public void decreasequantity()
//{this.quantity -=1;}
public String lineRepresentation()
{
String quantity = String.valueOf(this.quantity);
String price = String.valueOf(this.price);
String record = String.join(",",this.productID,this.productName,this.manufacturerName,this.supplierName,quantity,price);
return record;
}    
public String getSearchKey()  
{
return this.productID;
}
}






