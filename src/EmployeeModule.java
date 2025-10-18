//temp main function to test EmployeeUser & EmployeeUserDatabase & AdminRule
public class EmployeeModule {
    public static void main(String[] args) {
        // 1. Create a database and load existing employees
        EmployeeUserDatabase database = new EmployeeUserDatabase("Employee.txt");
        database.readFromFile(); // load data from file if it exists

        // 2. Create admin object to manage employees
        AdminRule admin = new AdminRule(database);

        // 3. Add new employees
        admin.addEmployee("E001", "Hager", "hager@email.com", "Cairo", "0100000000");
        admin.addEmployee("E002", "Omar", "omar@email.com", "Giza", "0111111111");

        // 4. Display all employees
        System.out.println("All Employees:");
        EmployeeUser[] employees = admin.getListOfEmployees();
        for (EmployeeUser e : employees) {
            System.out.println(e.lineRepresentation());
        }

        // 5. Remove an employee by ID
        admin.removeEmployee("E001");

        // 6. Display updated list
        System.out.println("\nAfter removing E001:");
        employees = admin.getListOfEmployees();
        for (EmployeeUser e : employees) {
            System.out.println(e.lineRepresentation());
        }

        // 7. Save all changes to the file
        admin.logout();
        System.out.println("\nAll data saved to Employee.txt successfully!");
    }
}
