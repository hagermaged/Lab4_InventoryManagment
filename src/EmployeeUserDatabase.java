import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class EmployeeUserDatabase extends Database<EmployeeUser> {
    
    // constructor
    public EmployeeUserDatabase(String fileName) {
        super(fileName);
    }

    // method 1 : store the data in the file in an arrayList - from Database
    
    // method 2 : takes a string line and return an EmpolyeeUser object 
    @Override
    public EmployeeUser createRecordForm(String line) {
        //checking if the line is empty
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Line is empty.");
            return null;
        }

        String[] data = line.split(",");
        if (data.length < 5) {
            System.out.println("Invalid line format: " + line);
            return null;
        }

        String employeeId = data[0].trim();
        String name = data[1].trim();
        String email = data[2].trim();
        String address = data[3].trim();
        String phoneNumber = data[4].trim();

        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        return employee;
    }

    // method 3 : return the records - from Database

    //method getSearchKey to return the id of a given EmployeeUser
    public String getSearchKey(EmployeeUser employee){
        return employee.getSearchKey();
    }

    // method 4 : search by employee id - in database

    // method 5 : given the employee id search and return the employeeUser - in database

    // method 6 : insert an employeeUser object in the list - in database

    // method 7 : delete the record by its id - in database

    // method 8 : delete the data in the file and insert data in arraylist
    public void saveToFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getFileName(), false); // opens the file -> delete its content -> start writing
            for (int i = 0; i < records.size(); i++) {
                writer.write(records.get(i).lineRepresentation() + "\n"); // line representation is a method in
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
