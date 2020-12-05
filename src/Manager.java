//Manager.java
//Krystian Lewandowski
/*This program will allow the user to instantiate a Manager Object with the following parameters:
* 1.Name of the Manager
* 2.Address of the manager
* 3.Eircode of the manager
* 4.Date of employment
* The manager will then be assigned to a Department*/

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Manager extends Employee implements Serializable {

    private String position;
    private Department department;

    //no argument constructor, used when creating a department
    public Manager() {
        super();
    }

    //manager requires a department to manage
    public Manager(String name, String address, String eircode, GregorianCalendar dateOfEmployment) {
        super(name, address, eircode, dateOfEmployment);
        setPosition();
    }

    public String getPosition() {
        return position;
    }


    public void setPosition() {
        if(getDepartment()!=null)
            this.position = "Manager of " + getDepartment().getDepartmentName() + " Department";
        else
            this.position = "Manager";
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        setPosition();
    }

    public String toString() {
        return super.toString();
    }
}
