import java.util.*;
import java.io.*;

public abstract class Database<T> {
    protected ArrayList<T> records; // T stands for type (determined later)
    protected String fileName;

    // constructor
    public Database(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) { // check if the given name is valid
            System.out.println("File name is incorrect.");
        } else {
            this.fileName = fileName;
            this.records = new ArrayList<>();
        }
    }

    // getter for file name
    public String getFileName() {
        return this.fileName;
    }

    // method 1 : store the data in the file in an arrayList
    public void readFromFile() {
        File file = null;
        Scanner reader = null;
        this.records.clear(); // clear the previous records (when calling more than once the records
                              // duplicates)
        try {
            file = new File(this.fileName); // opens the file with the name
            reader = new Scanner(file); // variable reader to read from the file
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                // create an object from the line and inserting the object into the arrayList
                insertRecord(createRecordForm(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, can not read from it.");

        } finally { // works in both cases
            if (reader != null) {
                reader.close();
            }
        }
    }

    // method 2 : takes a string line and return an EmpoyeeUser/Product (depends on
    // the child)
    public abstract T createRecordForm(String line);

    // method 3 : return the records
    public ArrayList<T> returnAllRecords() {
        return this.records;
    }

    // implemented in subclasses,used in contains
    public abstract String getSearchKey(T record);

    // search in records by the key
    public boolean contains(String key) {
        for (T record : records) {
            if (getSearchKey(record).equals(key))
                return true;
        }
        return false;
    }

    // method 5 : given the employee id search and return the employeeUser
    public T getRecord(String key) {
        for (T record : records) {
            if (getSearchKey(record).equals(key))
                return record;
        }
        System.out.println("This employee ID cannot be found.");
        return null;

    }

    // method 6 : insert an employeeUser object in the list
    public void insertRecord(T record) {
        if(!contains(getSearchKey(record))){
            records.add(record);
        }
        else{
            System.out.println("Can not add, ID is repeated.");
        }
    }

    // method 7 : delete the record by its id
    public void deleteRecord(String key) {
        if (contains(key)) {
            this.records.remove(getRecord(key));
        } else {
            System.out.println("Cannot delete, ID is not found.");
        }
    }

    // method 8 : delete the data in the file and insert data in arraylist
    public abstract void saveToFile() ;
}
