//Worker.java
//Krystian Lewandowski
/*This program will allow the user to instantiate a Worker Object with the following parameters:
 * 1.Name of the worker
 * 2.Address of the worker
 * 3.Eircode of the worker
 * 4.Date of employment
 * The worker will then be assigned to a Department*/

import java.util.GregorianCalendar;

public class Worker extends Employee {
    private String position;
    private String department;

    public Worker(String name, String address, String eircode, GregorianCalendar dateOfEmployment, String position) {
        super(name, address, eircode, dateOfEmployment);
        setPosition();
    }

    //Setters and getters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(){
        //when the worker is assigned to a department, his position changes to reflect that e.g. 'Worker of Engineering Department'
        if(getDepartment()!=null)
            this.position = "Worker of " + getDepartment() + " Department";
        else
            this.position = "Worker";
    }
}
