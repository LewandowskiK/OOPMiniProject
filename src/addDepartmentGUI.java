//addDepartmentGUI.java
//Krystian Lewandowski
/*This program has all the code required to create a department in the system through a GUI*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class addDepartmentGUI extends JFrame {
    static JTextArea departmentNameArea;
    JLabel errorLabel;
    JButton submit;


    //Parent allows to exit to the CEOGUI
    public addDepartmentGUI(){
        //set title to 'Add Department'
        super("Add Department");
        //set border layout
        setLayout(new BorderLayout(0,0));

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //set size of the window
        setSize(280,200);

        //set window to not resizable
        setResizable(false);

        //create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(280,100);

        //add a message label to the main Panel
        JLabel messageLabel = new JLabel("Please enter a name for the department");
        mainPanel.add(messageLabel);
        //set the location of the message label
        messageLabel.setBounds(10,10,(int)messageLabel.getPreferredSize().getWidth(),(int)messageLabel.getPreferredSize().getHeight());

        //initialize the departmentName textArea
        departmentNameArea = new JTextArea(1,22);
        mainPanel.add(departmentNameArea);
        //set location of the textArea
        departmentNameArea.setBounds(10,31,(int)departmentNameArea.getPreferredSize().getWidth(),(int)departmentNameArea.getPreferredSize().getHeight());

        //initialize the errorLabel, text set if there is an error in the naming
        errorLabel = new JLabel("");
        mainPanel.add(errorLabel);
        //set the location of the error label
        errorLabel.setLocation(10,52);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(280,20);

        //initialize the submit button, add an action listener, and set a mnemonic 'S'
        ButtonEventHandler handler = new ButtonEventHandler();
        submit = new JButton("Submit");
        submit.setMnemonic(KeyEvent.VK_S);
        submit.addActionListener(handler);
        buttonPanel.add(submit);
        //set the bounds of the button to sit flush with the edge of the panel and about 1/2 of its width in size
        submit.setBounds(0,0,130,20);

        //initialize a cancel button, add an action listener, and set a mnemonic 'C'
        JButton cancel = new JButton("Cancel");
        cancel.setMnemonic(KeyEvent.VK_C);
        cancel.addActionListener(handler);
        buttonPanel.add(cancel);

        //set the bounds of the cancel button so that it sits flush with the other side of the screen
        cancel.setBounds(150,0,130,20);

        add(mainPanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);


        //set default close operation and visibility of the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public class ButtonEventHandler implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==submit){
                if(departmentNameArea.getText().length()>0){
                    JOptionPane.showMessageDialog(null,"Department added! You can add another or exit this window.","Confirmation",JOptionPane.INFORMATION_MESSAGE);
                    //assume that the department does not exist yet
                    boolean doesNotExist = true;
                    //check the global departments array for a duplicate value

                    for(Department department:Driver.departments){
                        if(department.getDepartmentName().equals(departmentNameArea.getText())){
                            //display error message if a department with that name already exists
                            JOptionPane.showMessageDialog(null,"Department with this name already exists!","Department already exists",JOptionPane.ERROR_MESSAGE);
                            //the department exists, do not add a new one
                            doesNotExist = false;
                            //reset departmentNameArea text
                            departmentNameArea.setText("");
                            //repaint the window
                            repaint();
                        }
                    }
                    //a department with that name does not exist yet, it is safe to add it to the array
                    if(doesNotExist)
                        //add this new department to the global array of departments in the Driver class
                        Driver.departments.add(new Department(departmentNameArea.getText(),null));

                    //reset departmentNameArea text
                    departmentNameArea.setText("");
                }
                else
                    errorLabel.setText("Please enter a department name!");
            }
            //if the source of the ActionEvent is not the submit button, it is the cancel button
            else{
                JOptionPane.showMessageDialog(null,"Exiting this window.","Confirmation",JOptionPane.INFORMATION_MESSAGE);
                //close this window
                dispose();
            }
        }
    }
}
