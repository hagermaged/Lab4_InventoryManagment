public class EmployeeUser {

    //variables of the class
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    //constructor
    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber){
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //getters
    public String getEmployeeId(){
        return this.employeeId;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    //method 1 : return the data of the user comma seperated
    public String lineRepresentation(){
        return getEmployeeId() + "," +  getName() + "," + getEmail() + "," + getAddress() + "," + getPhoneNumber() ;
    }

    //method 2 : return employee id
    public String getSearchKey(){
        return getEmployeeId();
    }
}