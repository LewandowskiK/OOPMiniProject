import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public abstract class Employee {
    private int EmployeeID;
    private String name;
    private String address;
    private String eircode;
    private GregorianCalendar dateOfEmployment;
    private static int IDCounter = 0;

    // 5-argument constructor
    public Employee(String name, String address, String eircode, GregorianCalendar dateOfEmployment) {
        setEmployeeID();
        setName(name);
        setAddress(address);
        setEircode(eircode);
        setDateOfEmployment(dateOfEmployment);
    }

    //setters and getters
    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID() {
        incrementID();
        this.EmployeeID = IDCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADD EIRCODE VALIDATION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.eircode = eircode;
    }

    public GregorianCalendar getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(GregorianCalendar dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    //Called when assigning a new ID
    private void incrementID(){
        IDCounter++;
    }

    //Called when removing an employee from the system
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! PROBABLY NOT NEEDED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void decrementID(){
        IDCounter--;
    }

    //abstract method to calculate the salary of an employee
    //similar to exercise1 lab sheet10
    public abstract int getPointOnSalaryScale();

    //abstract method to distinguish between a manager and a worker
    public abstract String getPosition();

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! TEST, NOT SURE IF THIS WILL WORK !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private int calculateSalary(int pointOnSalaryScale){
        int salary = 0;
        if(this.getPosition().toLowerCase().contains("manager")){
            //calculate manager salary
        }
        else {
            //calculate worker salary
        }
        return salary;
    }

    //custom toString method
    @Override
    public String toString() {
        //Date format -> day-month-year
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return "EmployeeID: " + getEmployeeID() + "\nEmployee name: " + getName() + "\nEmployee Position: " + getPosition() +  "\nEmployee address: " + getAddress() + " \nEmployee eircode: " + getEircode() +
                "\nEmployee date of employment: " + dateFormat.format(getDateOfEmployment().getTime()) + "\nEmployee Salary: " + calculateSalary(getPointOnSalaryScale());
    }
}
