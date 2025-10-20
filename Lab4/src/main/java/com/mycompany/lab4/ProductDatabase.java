
import java.io.*;
import java.util.*;

public  class ProductDatabase extends Database<Product> {


    public ProductDatabase(String filename) {
       super(filename);
    }
    
@Override
public Product createRecordFrom(String line)
{ 
  String [] record = line.split(",");
  if(record.length !=6)
  {
  System.out.println("Invalid line:"+line);
  return null;
  }
  int quantity = Integer.parseInt(record[4]);
  float price = Float.parseFloat(record[5]);
  Product p = new Product(record[0],record[1],record[2],record[3],quantity,price);
return p;
}
@Override
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(super.fileName))) {
            for (Product p: super.records) {
                pw.println(p.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + super.fileName);
        }
    }
@Override    
public String getSearchKey(Product product)  
{
return product.getSearchKey();
}
 
}
