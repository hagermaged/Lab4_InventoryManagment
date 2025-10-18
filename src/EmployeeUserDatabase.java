import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records = new ArrayList<>();
    private String fileName;

    // constructor
    public EmployeeUserDatabase(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) { // check if the given name is valid
            System.out.println("File name is incorrect.");
        } else {
            this.fileName = fileName;
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
        this.records.clear(); //clear the previous records (when calling more than once the records duplicates)
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

    // method 2 : takes a string line and return an EmpolyeeUser object
    public EmployeeUser createRecordForm(String line) {
        String[] data = line.split(","); // split the line into an array of strings
        String employeeId = data[0];
        String name = data[1];
        String email = data[2];
        String address = data[3];
        String phoneNumber = data[4];
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        return employee;
    }

    // method 3 : return the records
    public ArrayList<EmployeeUser> returnAllRecords() {
        return this.records;
    }

    // method 4 : search by employee id
    public boolean contains(String key) {
        ArrayList<EmployeeUser> list = returnAllRecords(); // getting the records
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmployeeId().equals(key))
                return true;
        }
        return false;
    }

    // method 5 : given the employee id search and return the employeeUser
    public EmployeeUser getRecord(String key) {
        ArrayList<EmployeeUser> list = returnAllRecords();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmployeeId().equals(key)) {
                return list.get(i);
            }
        }
        System.out.println("This employee ID cannot be found.");
        return null;

    }

    // method 6 : insert an employeeUser object in the list
    public void insertRecord(EmployeeUser record) {
        this.records.add(record);
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
    public void saveToFile() {
        FileWriter writer = null;
        ArrayList<EmployeeUser> list = returnAllRecords();
        try {
            writer = new FileWriter(getFileName(), false); // opens the file -> delete its content -> start writing
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).lineRepresentation() + "\n"); // line representation is a method in
                                                                       // EmployeeUser class
            }
        } catch (IOException e) {
            System.out.println("Error writing in the file.");
        }
        // In saveToFile() - need proper exception handling for close()
        finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}
