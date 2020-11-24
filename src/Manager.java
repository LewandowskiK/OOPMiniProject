import java.util.GregorianCalendar;

public class Manager extends Employee{
    private int pointOnSalaryScale;
    private String position;
    private Department department;

        //manager requires a department to manage
    public Manager(String name, String address, String eircode, GregorianCalendar dateOfEmployment, int pointOnSalaryScale) {
        super(name, address, eircode, dateOfEmployment);
        //set the position as manager of department
        setPointOnSalaryScale(pointOnSalaryScale);
    }

    public int getPointOnSalaryScale() {
        return pointOnSalaryScale;
    }

    public void setPointOnSalaryScale(int pointOnSalaryScale) {
        this.pointOnSalaryScale = pointOnSalaryScale;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition() {
        this.position = "Manager of " +getDepartment().getDepartmentName();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String toString() {
        return super.toString();
    }
}
