import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("     Inventory Management System");
        System.out.println("===================================");
        System.out.println("Select mode:");
        System.out.println("1. Test Mode (automatic tests)");
        System.out.println("2. Menu Mode (manual input)");
        System.out.print("Choice: ");
        int mode = input.nextInt();
        input.nextLine();

        if (mode == 1) {
            runTestMode();
        } else if (mode == 2) {
            runMenuMode();
        } else {
            System.out.println("Invalid choice.");
        }

        input.close();
    }

    // ======================== TEST MODE ========================
    private static void runTestMode() {
        System.out.println("\n=== Running Test Mode ===");

        // Admin setup
        EmployeeUserDatabase empDB = new EmployeeUserDatabase("Employees.txt");
        AdminRule admin = new AdminRule(empDB);
        empDB.readFromFile();

        System.out.println("\n--- Test 1: Add Employee ---");
        admin.addEmployee("E2001", "Mariam", "mariam@gmail.com", "Cairo", "01000000000");
        admin.logout();
        System.out.println("Employee added successfully.");

        System.out.println("\n--- Test 2: Remove Employee ---");
        admin.removeEmployee("E2001");
        admin.logout();

        // Employee setup
        EmloyeeRole emp = new EmloyeeRole();

        System.out.println("\n--- Test 3: Add Product ---");
        emp.addProduct("P1001", "Laptop", "HP", "SupplierA", 3, 15000.0f);

        System.out.println("\n--- Test 4: Purchase Product ---");
        boolean purchaseResult = emp.purchaseProduct("123456789", "P1001", LocalDate.of(2025, 10, 16));
        System.out.println("Purchase result: " + (purchaseResult ? "Success" : "Failed"));

        System.out.println("\n--- Test 5: Purchase Nonexistent Product ---");
        boolean purchaseResult2 = emp.purchaseProduct("123456789", "PX999", LocalDate.of(2025, 10, 16));
        System.out.println("Purchase result (nonexistent): " + (purchaseResult2 ? "Unexpected success" : "Failed as expected"));

        System.out.println("\n--- Test 6: Apply Payment ---");
        boolean paid = emp.applyPayment("123456789", LocalDate.of(2025, 10, 16));
        System.out.println("Payment result: " + (paid ? "Success" : "Failed"));

        System.out.println("\n--- Test 7: Return Product (within 14 days) ---");
        double refunded = emp.returnProduct("123456789", "P1001",
                LocalDate.of(2025, 10, 16), LocalDate.of(2025, 10, 20));
        System.out.println(refunded > 0 ? "Returned successfully. Price: " + refunded : "Return failed.");

        System.out.println("\n--- Test 8: Return Product (after 14 days) ---");
        double refundedLate = emp.returnProduct("123456789", "P1001",
                LocalDate.of(2025, 10, 1), LocalDate.of(2025, 10, 20));
        System.out.println(refundedLate == -1 ? "Return failed as expected." : "Unexpected success.");

        emp.logout();
        System.out.println("\n=== All tests finished ===");
    }

    // ======================== MENU MODE ========================
    private static void runMenuMode() {
        Scanner input = new Scanner(System.in);
        int roleChoice;

        System.out.println("\n=== Menu Mode ===");
        System.out.println("1. Admin");
        System.out.println("2. Employee");
        System.out.print("Choice: ");
        roleChoice = input.nextInt();
        input.nextLine();

        if (roleChoice == 1) {
            runAdminMenu(input);
        } else if (roleChoice == 2) {
            runEmployeeMenu(input);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // -------------------- ADMIN MENU --------------------
    private static void runAdminMenu(Scanner input) {
        EmployeeUserDatabase empDB = new EmployeeUserDatabase("Employees.txt");
        AdminRule admin = new AdminRule(empDB);
        empDB.readFromFile();

        int choice;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Remove Employee");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = input.nextLine();
                    System.out.print("Name: ");
                    String name = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();
                    System.out.print("Address: ");
                    String address = input.nextLine();
                    System.out.print("Phone: ");
                    String phone = input.nextLine();
                    admin.addEmployee(id, name, email, address, phone);
                    System.out.println("Employee added.");
                    break;

                case 2:
                    EmployeeUser[] list = admin.getListOfEmployees();
                    System.out.println("\n--- Employees ---");
                    for (EmployeeUser e : list) {
                        System.out.println(e.lineRepresentation());
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to remove: ");
                    String removeId = input.nextLine();
                    admin.removeEmployee(removeId);
                    break;

                case 4:
                    admin.logout();
                    System.out.println("Data saved. Logout successful.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    // -------------------- EMPLOYEE MENU --------------------
    private static void runEmployeeMenu(Scanner input) {
        EmloyeeRole emp = new EmloyeeRole();
        int choice;

        do {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Purchase Product");
            System.out.println("4. Return Product");
            System.out.println("5. Apply Payment");
            System.out.println("6. Logout");
            System.out.print("Choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Product ID: ");
                    String pid = input.nextLine();
                    System.out.print("Name: ");
                    String pname = input.nextLine();
                    System.out.print("Manufacturer: ");
                    String manu = input.nextLine();
                    System.out.print("Supplier: ");
                    String supp = input.nextLine();
                    System.out.print("Quantity: ");
                    int qty = input.nextInt();
                    System.out.print("Price: ");
                    float price = input.nextFloat();
                    input.nextLine();
                    emp.addProduct(pid, pname, manu, supp, qty, price);
                    System.out.println("Product added successfully.");
                    break;

                case 2:
                    Product[] products = emp.getListOfProducts();
                    System.out.println("\n--- Products ---");
                    for (Product p : products) {
                        System.out.println(p.lineRepresentation());
                    }
                    break;

                case 3:
                    System.out.print("Customer SSN: ");
                    String ssn = input.nextLine();
                    System.out.print("Product ID: ");
                    String prodId = input.nextLine();
                    System.out.print("Purchase Date (dd-MM-yyyy): ");
                    String dateStr = input.nextLine();
                    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate pDate = LocalDate.parse(dateStr, f);
                    boolean result = emp.purchaseProduct(ssn, prodId, pDate);
                    System.out.println(result ? "Purchase successful." : "Purchase failed.");
                    break;

                case 4:
                    System.out.print("Customer SSN: ");
                    String ssnR = input.nextLine();
                    System.out.print("Product ID: ");
                    String prodR = input.nextLine();
                    System.out.print("Purchase Date (dd-MM-yyyy): ");
                    String datePR = input.nextLine();
                    System.out.print("Return Date (dd-MM-yyyy): ");
                    String dateRR = input.nextLine();
                    LocalDate d1 = LocalDate.parse(datePR, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalDate d2 = LocalDate.parse(dateRR, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    double ref = emp.returnProduct(ssnR, prodR, d1, d2);
                    System.out.println(ref > 0 ? "Return successful. Amount: " + ref : "Return failed.");
                    break;

                case 5:
                    System.out.print("Customer SSN: ");
                    String ssnP = input.nextLine();
                    System.out.print("Purchase Date (dd-MM-yyyy): ");
                    String datePP = input.nextLine();
                    LocalDate payDate = LocalDate.parse(datePP, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    boolean paid = emp.applyPayment(ssnP, payDate);
                    System.out.println(paid ? "Payment applied." : "Payment failed.");
                    break;

                case 6:
                    emp.logout();
                    System.out.println("Data saved. Logout successful.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }
}