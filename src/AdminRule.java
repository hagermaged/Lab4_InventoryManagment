import java.io.*;
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

    //method 2 : returns an array of employees in the file
    public EmployeeUser[] getListOfEmployees(){
        Scanner reader = new Scanner("Employee.txt");
        EmployeeUserDatabase data = new EmployeeUserDatabase("Employee.txt");
        String line;
        while(reader.hasNextLine()){
            line = reader.nextLine();
        }
    }
}
