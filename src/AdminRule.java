import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminRule {
    private EmployeeUserDatabase database;

    // constructor
    public AdminRule(EmployeeUserDatabase database) {
        this.database = database;
    }

    // method 1 : adds new employee to a file named Employee.txt
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("Employee.txt", true); // true to append
            EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            writer.write(employee.lineRepresentation() + "\n");
        } catch (IOException e) {
            System.out.println("Can not write in the file.");
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    // method 2 : returns an array of employees in the file
    public EmployeeUser[] getListOfEmployees() {
        Scanner reader = null;
        ArrayList<EmployeeUser> list = new ArrayList<>();

        try {
            reader = new Scanner(new File("Employee.txt")); // Fix: scan FILE not string
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.trim().isEmpty()) {
                    list.add(database.createRecordForm(line));
                }
            }
            return list.toArray(new EmployeeUser[0]); // Convert to array and return

        } catch (FileNotFoundException e) {
            System.out.println("Employee file not found.");
            return new EmployeeUser[0]; // Return empty array if file doesn't exist
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
