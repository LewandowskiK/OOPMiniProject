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
        return departmentManager;
    }

    public void setDepartmentManager(Manager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public ArrayList<Employee> getDepartmentWorkers() {
        return departmentWorkers;
    }

    //method to add an employee to a department invokable only by the manager of the department
    public void addEmployee(Employee employee){
        this.departmentWorkers.add(employee);
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADD METHOD TO REMOVE AN EMPLOYEE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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
            output+="No employees in this department yet";
        }


        return output;
    }
}