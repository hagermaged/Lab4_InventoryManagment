import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {

    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    //Constructor
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false; // Default: not paid unless updated
    }

    // Getters
    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    //Paid methods
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // Returns data as comma-separated values (for saving to file)
    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid;
    }

    // Returns formatted search key
    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }
}