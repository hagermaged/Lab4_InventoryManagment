import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct> {

    // Constructor
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    // Method 1: Create CustomerProduct object from a line in file 
    @Override
    public CustomerProduct createRecordForm(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 4) {
                System.out.println("Invalid line format: " + line);
                return null;
            }

            String customerSSN = parts[0];
            String productID = parts[1];
            String dateStr = parts[2];
            boolean paid = Boolean.parseBoolean(parts[3]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate purchaseDate = LocalDate.parse(dateStr, formatter);

            CustomerProduct record = new CustomerProduct(customerSSN, productID, purchaseDate);
            record.setPaid(paid);
            return record;

        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    // Method 2: Get the search key for a CustomerProduct record
    @Override
    public String getSearchKey(CustomerProduct record) {
        return record.getSearchKey(); // uses the same format "SSN,ProductID,DD-MM-YYYY"
    }

    // Method 3: Save all records to file
    @Override
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.fileName))) {
            for (CustomerProduct record : records) {
                writer.println(record.lineRepresentation());
            }
            System.out.println("Data saved successfully to " + this.fileName);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}