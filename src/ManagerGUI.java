//ManagerGUI.java
//Krystian Lewandowski
/*This program will allow the user to act as the manager and manage a department selected with the JComboBox at the top
* of the GUI*/

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

public class ManagerGUI extends JFrame implements ActionListener, WindowListener {

    //JComboBox for departments with managers initialized using a user defined method
    JComboBox managedDepartmentsList = new JComboBox(initializeManagers());

    //JButton array for button listener
    JButton[] jButtons = new JButton[5];

    public ManagerGUI(){
        //set title to 'Welcome Manager'
        super("Welcome Manager");

        //set border layout
        setLayout(new BorderLayout(0,0));

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //set size of the window
        setSize(260,240);

        //set window to not resizable
        setResizable(false);

        //make a new JPanel mainPanel
        JPanel mainPanel = new JPanel();

        //add a JLabel and setBounds
        JLabel departmentLabel = new JLabel("Department: ");
        departmentLabel.setBounds(10,10,(int)departmentLabel.getPreferredSize().getWidth(),(int)departmentLabel.getPreferredSize().getHeight());
        mainPanel.add(departmentLabel);

        //create a JMenu for 'Log out' option
        JMenu logout = new JMenu("Log Out");
        //anonymous menu listener generated by JCreator with some user made code to log out
        logout.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //if the user says he wants to log out the code executes else, nothing happens
                if(JOptionPane.showConfirmDialog(null,"Would you like to log out?")==0){
                    dispose();
                    new LoginGUI();
                }
            }
            //required for MenuListener, no functionality
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });

        //create a JMenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.lightGray);
        menuBar.add(logout);

        //add the JComboBox to the mainPanel and setBounds
        managedDepartmentsList.setBounds(10 + (int)departmentLabel.getPreferredSize().getWidth() + 5,10,(int)managedDepartmentsList.getPreferredSize().getWidth(),(int)managedDepartmentsList.getPreferredSize().getHeight());
        mainPanel.add(managedDepartmentsList);

        //create and add a 'add employee' JButton to the array, set a mnemonic 'E' and add the listener
        JButton addEmployee = new JButton("Add Employee");
        jButtons[0] = addEmployee;
        jButtons[0].setMnemonic(KeyEvent.VK_E);
        jButtons[0].addActionListener(this);
        mainPanel.add(jButtons[0]);

        //create and add a 'remove employee' JButton to the array, set a mnemonic 'R' and add the listener
        JButton removeEmployee = new JButton("Remove Employee");
        jButtons[1] = removeEmployee;
        jButtons[1].setMnemonic(KeyEvent.VK_R);
        jButtons[1].addActionListener(this);
        mainPanel.add(jButtons[1]);

        //create and add a 'list employees' JButton to the array, set a mnemonic 'L' and add the listener
        JButton listEmployees = new JButton("List Employees");
        jButtons[2] = listEmployees;
        jButtons[2].setMnemonic(KeyEvent.VK_L);
        jButtons[2].addActionListener(this);
        mainPanel.add(jButtons[2]);


        //add the main panel to the window
        add(mainPanel,BorderLayout.CENTER);

        //add Window Listener and visibility of the window
        addWindowListener(this);
        setVisible(true);
    }

    //window Listener code to the main windows to ask the user if s/he wants to save the data to file
    //duplicated in LoginGUI, ManagerGUI and CEOGUI
    public void windowOpened(WindowEvent e){}
    public void windowClosing(WindowEvent e){
        if(JOptionPane.showConfirmDialog(null,"Would you like to save all the data to file?","Save to File?",JOptionPane.YES_NO_OPTION)==0){
            Driver.saveToFile();
        }
        dispose();
    }
    public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowActivated(WindowEvent e){ }
    public void windowDeactivated(WindowEvent e){}

    //actionListener
    public void actionPerformed(ActionEvent e) {
        Department selectedDepartment = Driver.departments.get(getDepartmentIndex());
        if(e.getSource()==jButtons[0]){
            //call to a user-defined method that gets the name of the selected item in the combo box
            new addWorkerGUI(selectedDepartment.getDepartmentName());
        }
        else if(e.getSource()==jButtons[1]){
            //user Defined method
            removeEmployee(getDepartmentIndex());
        }
        else{
            listEmployees();
        }

    }

    //this GUI appears only if a manager has been added to the global managers array so no check to see if there are managers present required here
    private String[] initializeManagers(){
        String[] list = new String[Driver.managers.size()];
        int counter = 0;
        for(Manager manager : Driver.managers){
            if(manager.getDepartment()!=null){
                list[counter] = manager.getDepartment().getDepartmentName();
                counter++;
            }
        }
        //this can be done like this because once you remove a department, the manager associated with that department is removed as well
        //therefore we dont have to re-make an array of the proper size to return that as a String[]
        return list;
    }

    //a user defined method to remove an employee from the current department
    private void removeEmployee(int departmentIndex){
        //if the department has employees assigned
        if(Driver.departments.get(departmentIndex).getDepartmentWorkers().size()>0){
            String allEmployees = "";
            for(int i = 0; i < Driver.departments.get(departmentIndex).getDepartmentWorkers().size(); i++){
                allEmployees += "\nIndex: " + (i+1) + "    Name: " + Driver.departments.get(departmentIndex).getDepartmentWorkers().get(i).getName();
            }

            String input = JOptionPane.showInputDialog(null,"Please input which worker to remove: \n" + allEmployees,"Select Worker",JOptionPane.PLAIN_MESSAGE);
            while(input!=null && !validIndex(Driver.departments.get(departmentIndex).getDepartmentWorkers().size(),input)){
                input = JOptionPane.showInputDialog(null,"Invalid Input!\n\nPlease input which worker to remove: \n" + allEmployees,"Select Worker",JOptionPane.PLAIN_MESSAGE);
            }

            //-1 to get the actual index
            int workerIndex = Integer.parseInt(input)-1;

            //if the user confirms he wants to remove the manager
            if(JOptionPane.showConfirmDialog(null,"You are about to remove the following Worker:\n\n" + Driver.departments.get(departmentIndex).getDepartmentWorkers().get(workerIndex) + "\n\nProceed?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION)==0){

                String workerName = Driver.departments.get(departmentIndex).getDepartmentWorkers().get(workerIndex).getName();
                int globalWorkerIndex = 0;
                for(Worker worker : Driver.workers){
                    if(worker.getName().equals(workerName)){
                        //we found the worker in the global array
                        break;
                    }
                    //else increment the index
                    globalWorkerIndex++;
                }
                //remove worker from the department and global array
                Driver.departments.get(departmentIndex).getDepartmentWorkers().remove(workerIndex);
                Driver.workers.remove(globalWorkerIndex);
            }
        }
        else{
            //else throw a message
            JOptionPane.showMessageDialog(null,"Please add Workers to the Department before attempting to remove","No Workers Found",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listEmployees(){
        String output = "";
        for(Employee employee : Driver.departments.get(getDepartmentIndex()).getDepartmentWorkers()){
            output += employee + "\n\n";
        }
        JOptionPane.showMessageDialog(null,output,"Listing employees in " + Driver.departments.get(getDepartmentIndex()).getDepartmentName() + "Department",JOptionPane.INFORMATION_MESSAGE);
    }

    //method to determine whether the user has inputted an available index in department query
    //duplicate of the method in CEOGUI
    private boolean validIndex(int maxIndex, String input){
        boolean valid = false;
        int i;
        for(i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i)))
                break;
        }

        if(i==input.length()){
            int index = Integer.parseInt(input);
            //if the index selected is not within the maxIndex(amount of departments queried) loop does not validate
            if(index<=maxIndex && index>0)
                valid = true;
        }

        return valid;
    }

    //a user defined method to get the index of the selected department
    private int getDepartmentIndex(){
        String departmentName = getNameOfDepartment(managedDepartmentsList.getSelectedIndex());

        int departmentIndex = 0;
        for(Department department : Driver.departments){
            if(department.getDepartmentName().equals(departmentName)){
                //department was found
                break;
            }
            //otherwise iterate the index
            departmentIndex++;
        }
        return departmentIndex;
    }

    //a user defined method that returns the name of the selected department in the JComboBox
    private String getNameOfDepartment(int index){
        int counter = 0;
        for(String name : initializeManagers()){
            if(counter==index){
                return name;
            }
            counter++;
        }
        //the program wont ever get here but the compiler doesnt like the return in the for-each loop
        return null;
    }
}
