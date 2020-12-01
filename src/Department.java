//Department.java
//Krystian Lewandowski
/*This program will allow the user to instantiate a Department object with the following parameters:
* 1.Name of the department
* 2.The Manager of the department, not assigned by default
* 3.An arrayList of Workers who work in the department, empty by default*/

import java.util.ArrayList;

public class Department {
    private String departmentName;
    //every department requires 1 manager
    private Manager departmentManager;
    //every department can have many workers
    private ArrayList<Employee> departmentWorkers;

    public Department(String departmentName, Manager departmentManager) {
        setDepartmentName(departmentName);
        setDepartmentManager(departmentManager);
    }

    //setters and getters
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Manager getDepartmentManager() {
        if(this.departmentManager==null)
            return new Manager();
        else
            return departmentManager;
    }

    public void setDepartmentManager(Manager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public ArrayList<Employee> getDepartmentWorkers() {
        return departmentWorkers;
    }

    //method to add an employee to a department invokable only by the manager of the department
    public void addEmployee(Worker employee){
        this.departmentWorkers.add(employee);
        employee.setDepartment(this.getDepartmentName());
        employee.setPosition();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADD METHOD TO REMOVE AN EMPLOYEE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Override
    public String toString() {
        String output ="Department Name: " +getDepartmentName() + "\nDepartment Manager: " + getDepartmentManager().getName() + "\n\nDepartment Employees:\n\n";

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
