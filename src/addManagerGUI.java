//addManagerGUI.java
//Krystian Lewandowski
/*This program has all the code necessary to create a manager on the system, done through a GUI */

import javax.swing.*;
import java.awt.*;

public class addManagerGUI extends JFrame {
    public addManagerGUI(){
        //set title to 'Add Manager'
        super("Add Manager");

        //set border layout
        setLayout(new BorderLayout(0,0));

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //set size of the window
        setSize(260,400);

        //a panel that will contain the form needed to create a new Manager Object
        JPanel formPanel = new JPanel();

        //null layout for absolute positioning
        formPanel.setLayout(null);



        //only make a new manager if there is a department for him to manage
        //method below returns null if all the departments have a manager

            JComboBox departmentList = new JComboBox(initializeDepartments());
            departmentList.setBounds(10,10,100,20);
            formPanel.add(departmentList);

        if(initializeDepartments()[0].equals("No Departments Available")){
            //create 2 error message labels to display to the user
            JLabel errorMessage = new JLabel("No departments without a manager exist");
            errorMessage.setBounds(10,30,250,16);
            JLabel errorMessageLine2 = new JLabel("If you want to hire a new manager, either fire a current one or create a new department");
            errorMessageLine2.setBounds(10,50,400,16);
            //make the window smaller
            setSize(350,200);
            //add the two lines of the error message
            formPanel.add(errorMessage);
            formPanel.add(errorMessageLine2);
        }

        //set window to not resizable
        setResizable(false);

        //add the panel to the frame
        add(formPanel);

        //set default close operation and visibility of the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    public String[] initializeDepartments(){
        //initialize a string array of size departments.size(), ie however-many department objects are instantiated
        String[] tempDepartmentNames = new String[Driver.departments.size()];
        int counter = 0;
        for(Department department:Driver.departments){
            if(department.getDepartmentManager()==null){
                tempDepartmentNames[0]=department.getDepartmentName();
                counter++;
            }
        }

        if(counter==0)
            return new String[]{"No Departments Available"};
        //if the counter is the size of the global departments array return all the departments
        else if(counter==Driver.departments.size()){
            return tempDepartmentNames;
        }
        else
        {
            //make a new array of size counter, ie the amount of departments with no manager
            String[] departmentNames = new String[counter];
            counter = 0;
            for(String name : tempDepartmentNames)
                if(name!=null){
                    departmentNames[counter] = name;
                    counter++;
                }
            return departmentNames;
        }
    }
}
