
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author KimoStore
 */
public class EmloyeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmloyeeRole() {
        productsDatabase = new ProductDatabase("products.txt");
        customerProductDatabase = new CustomerProductDatabase("customersproducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        Product newproduct = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(newproduct);
        productsDatabase.saveToFile();
    }

    public Product[] getListOfProducts() {
        ArrayList<Product> list = productsDatabase.returnAllRecords();
        return list.toArray(new Product[0]);
    }

     public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        return list.toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product p = productsDatabase.getRecord(productID);
        if (p == null || p.getQuantity() <= 0) {
            return false;
        }

        p.setQuantity(p.getQuantity() - 1);
        productsDatabase.saveToFile();

        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(cp);
        customerProductDatabase.saveToFile();

        return true;
    }
    
    public double returnProduct(String customerSSN, String productID,
                            LocalDate purchaseDate, LocalDate returnDate) {
   
    if (returnDate.isBefore(purchaseDate))
        return -1;

   
    Product p = productsDatabase.getRecord(productID);
    if (p == null)
        return -1;

  
    String key = customerSSN + "," + productID + "," +
            String.format("%02d-%02d-%04d",
                    purchaseDate.getDayOfMonth(),
                    purchaseDate.getMonthValue(),
                    purchaseDate.getYear());

    
    if (!customerProductDatabase.contains(key))
        return -1;

 
    long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
    if (daysBetween > 14)
        return -1;

    
    p.setQuantity(p.getQuantity() + 1);
    productsDatabase.saveToFile();
    customerProductDatabase.deleteRecord(key);
    customerProductDatabase.saveToFile();

    return p.getPrice();
}
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
    
    ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();

    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String purchaseDateStr = purchaseDate.format(formatter);

    
    for (CustomerProduct cp : list) {
       
        if (cp.getCustomerSSN().equals(customerSSN) &&
            cp.getPurchaseDate().format(formatter).equals(purchaseDateStr)) {

            
            if (cp.isPaid()) {
                return false; 
            }

            
            cp.setPaid(true);

            
            customerProductDatabase.saveToFile();

            
            return true;
        }
    }

    
    return false;
}
    
    
    public void logout() {
    productsDatabase.saveToFile();
    customerProductDatabase.saveToFile();
    System.out.println("All data saved successfully. Logged out.");
}
}
