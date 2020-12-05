//Department.java
//Krystian Lewandowski
/*This program will allow the user to instantiate a Department object with the following parameters:
* 1.Name of the department
* 2.The Manager of the department, not assigned by default
* 3.An arrayList of Workers who work in the department, empty by default*/

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An instantiable class which defines a department in a company.
 * All validation is done in the file that creates the department.
 * A Department can only have one manager but many Workers.
 * @author Krystian Lewandowski
 */
public class Department implements Serializable {
    private String departmentName;
    //every department requires 1 manager
    private Manager departmentManager;
    //every department can have many workers
    private ArrayList<Employee> departmentWorkers = new ArrayList<>();

    /**
     * Department 2 argument constructor. Calls the 2 parameters from the GUI file associated with creating the department
     * By default a no argument constructor is used to add aa manager to the department which is later assigned properly
     * @param departmentName This is the name of the department
     * @param departmentManager This is the manager object of the department
     */
    public Department(String departmentName, Manager departmentManager) {
        setDepartmentName(departmentName);
        setDepartmentManager(departmentManager);
    }

    //setters and getters

    /**
     * Accessor to return the name of the department
     * @return a string value specifying the name of the department
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Mutator method to set the name of the department
     * @param departmentName the name of the department
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    /**
     * Accessor to return the details of the departments Manager
     * @return a Manager object, the manager of the department
     * Returns the Managers name,address,eircode and date of employment
     */
    public Manager getDepartmentManager() {
        if(this.departmentManager==null)
            return new Manager();
        else
            return departmentManager;
    }

    /**
     * Mutator to set the manager of the department
     * @param departmentManager a Manager Object, the manager of the department
     */
    public void setDepartmentManager(Manager departmentManager) {
        this.departmentManager = departmentManager;
    }

    /**
     * Accessor to return the employees that work in this department
     * @return an array of Employee containing all of the Workers in the department
     */
    public ArrayList<Employee> getDepartmentWorkers() {
        return departmentWorkers;
    }

    /**
     * Method to add an employee to the departmentWorkers array
     * @param employee an employee that will be added to the department
     */
    //method to add an employee to a department invokable only by the manager of the department
    public void addEmployee(Worker employee){
        this.departmentWorkers.add(employee);
    }

    /**
     * Method to get the state of the department object
     * @return a string value specifying the state of the department object
     */
    @Override
    public String toString() {
        String output ="Department Name: " + getDepartmentName() + "\nDepartment Manager: " + getDepartmentManager().getName() + "\n\nDepartment Employees:\n\n";

        //loop through the departmentWorkers to add them all to the output
        if(getDepartmentWorkers()!=null){
            for(Employee employee: getDepartmentWorkers()){
                output += employee + "\n";
            }
        }
        //if there are no employees assigned print out the message below
        else{
            output += "No employees in this department yet";
        }

        return output;
    }
}
