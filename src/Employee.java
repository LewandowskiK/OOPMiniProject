//Employee.java
//Krystian Lewandowski
/*This program is an abstract class from which the Worker and Manager classes inherit
* This program will do the calculations and checks such as validation of the eircode and
* calculation of the salary of the employee*/

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public abstract class Employee {
    private int EmployeeID;
    private String name;
    private String address;
    private String eircode;
    private GregorianCalendar dateOfEmployment;
    private int pointOnSalaryScale;
    private static int IDCounter = 0;

    // no argument constructor, only used when creating a department
    public Employee() {
        this("No manager assigned",null,null, null);
    }

    // 5-argument constructor
    public Employee(String name, String address, String eircode, GregorianCalendar dateOfEmployment) {
        setEmployeeID();
        setName(name);
        setAddress(address);
        setEircode(eircode);
        setDateOfEmployment(dateOfEmployment);
        setPointOnSalaryScale();
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
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADD EIRCODE VALIDATION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

    //method to calculate the salary of an employee
    //similar to exercise1 lab sheet10
    public int getPointOnSalaryScale() {
        return pointOnSalaryScale;
    }

    //this mutator calculates the point on the salary scale for the manager used in the salary calculation
    public void setPointOnSalaryScale() {
        if(getDateOfEmployment()!=null){
            //retrieve todays date and employment date
            GregorianCalendar todaysDate = new GregorianCalendar();
            GregorianCalendar employmentDate = getDateOfEmployment();
            SimpleDateFormat getDay = new SimpleDateFormat("dd");
            SimpleDateFormat getMonth = new SimpleDateFormat("MM");
            SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
            //Date comparison
            //if todays month <= employment dates month
            if(Integer.parseInt(getMonth.format(employmentDate.getTime()))>=Integer.parseInt(getMonth.format(todaysDate.getTime()))){
                //if the two months are the same
                if(Integer.parseInt(getMonth.format(employmentDate.getTime()))==Integer.parseInt(getMonth.format(todaysDate.getTime()))){
                    //check the days
                    if(Integer.parseInt(getDay.format(employmentDate.getTime()))>=Integer.parseInt(getDay.format(todaysDate.getTime()))){
                        //this employee was hired either today or earlier this month, therefore a full year has passed (+1)
                        this.pointOnSalaryScale = Integer.parseInt(getYear.format(todaysDate.getTime())) - Integer.parseInt(getYear.format(employmentDate.getTime())) + 1;
                    }
                    //a full year hasn't passed therefore
                    this.pointOnSalaryScale = Integer.parseInt(getYear.format(todaysDate.getTime())) - Integer.parseInt(getYear.format(employmentDate.getTime()));
                }
                //set the point on salary scale to the full year since it has already passed (+1)
                this.pointOnSalaryScale = Integer.parseInt(getYear.format(todaysDate.getTime())) - Integer.parseInt(getYear.format(employmentDate.getTime())) + 1;
            }
            //a full year has not passed therefore
            this.pointOnSalaryScale = Integer.parseInt(getYear.format(todaysDate.getTime())) - Integer.parseInt(getYear.format(employmentDate.getTime()));
        }
        else
            this.pointOnSalaryScale = 0;
    }


    //existence of accessor infers existence of mutator
    //abstract method to distinguish between a manager and a worker
    public abstract String getPosition();

    private int calculateSalary(int pointOnSalaryScale){
        int salary = 0;
        if(this.getPosition().toLowerCase().contains("manager")){
            //manager starting salary at 58000 + 5000 per year employed up to 8 years
            if(pointOnSalaryScale>8)
                pointOnSalaryScale = 8;
            salary = 58000 + 5000*pointOnSalaryScale;
        }
        else {
            //worker starting salary at 50000 + 4000 per year employed up to 8 years
            salary = 50000 + 4000*pointOnSalaryScale;
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
