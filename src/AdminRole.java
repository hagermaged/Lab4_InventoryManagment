import java.io.*;
import java.util.*;

public class AdminRole {
    private EmployeeUserDatabase database;

    // constructor
    public AdminRole(EmployeeUserDatabase database) {
        this.database = database;
    }

    // method 1 : adds new employee to a file named Employee.txt
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        this.database.insertRecord(employee);
        // logout();
    }

    // method 2 : returns an array of employees in the file
    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> records = new ArrayList<>();
        records = database.returnAllRecords();
        return records.toArray(new EmployeeUser[0]); //converting the arrayList to an array 
        //employeeUser[0] making an array of size zero the toArray will increase its size
    }

    //method 3 : remove employee by id 
    public void removeEmployee(String key){
        if(database.contains(key)){ //if the records contains an employee with this id
            database.deleteRecord(key);
            // logout(); //save new records to the file
        }
        else{
            System.out.println("There's no employee with this id.");
        }
    }

    //method 4 : save all the data in file
    public void logout(){
    database.saveToFile();
    }
}
