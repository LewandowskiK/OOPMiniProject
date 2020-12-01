//addManagerGUI.java
//Krystian Lewandowski
/*This program has all the code necessary to create a manager on the system, done through a GUI */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class addManagerGUI extends JFrame implements ActionListener {
    //text areas for name,address,eircode,(day,year)of employment
    JTextArea[] textAreas = new JTextArea[5];
    //combo box for the month of employment
    JComboBox monthList = new JComboBox(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
    //submit button for listener
    JButton submit;

    public addManagerGUI(){
        //set title to 'Add Manager'
        super("Add Manager");

        //set border layout
        setLayout(new BorderLayout(0,0));

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //set size of the window
        setSize(320,270);

        //a panel that will contain the form needed to create a new Manager Object
        JPanel formPanel = new JPanel();

        //null layout for absolute positioning
        formPanel.setLayout(null);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(10,10,190,20);
        formPanel.add(departmentLabel);

        //user method returns an array of String
        JComboBox departmentList = new JComboBox(initializeDepartments());
        departmentList.setBounds(100,10,200,20);
        formPanel.add(departmentList);

        //create JLabels and JTextAreas for the user to fill out, the text areas are assigned names for more meaningful error messages
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10,40,(int)nameLabel.getPreferredSize().getWidth(),(int)nameLabel.getPreferredSize().getHeight());
        formPanel.add(nameLabel);

        JTextArea nameArea = new JTextArea(1,20);
        textAreas[0] = nameArea;
        textAreas[0].setName("Name Field");
        textAreas[0].setBounds(100,40,200,20);
        formPanel.add(textAreas[0]);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10,70,(int)addressLabel.getPreferredSize().getWidth(),(int)addressLabel.getPreferredSize().getHeight());
        formPanel.add(addressLabel);

        JTextArea addressArea = new JTextArea(1,20);
        textAreas[1] = addressArea;
        textAreas[1].setName("Address Field");
        textAreas[1].setBounds(100,70,200,20);
        formPanel.add(textAreas[1]);

        JLabel eircodeLabel = new JLabel("Eircode(DDDDDDD):");
        eircodeLabel.setBounds(10,100,(int)eircodeLabel.getPreferredSize().getWidth(),(int)eircodeLabel.getPreferredSize().getHeight());
        formPanel.add(eircodeLabel);

        JTextArea eircodeArea = new JTextArea(1,20);
        textAreas[2] = eircodeArea;
        textAreas[2].setName("Eircode Field");
        textAreas[2].setBounds(100,100,200,20);
        formPanel.add(textAreas[2]);



        JLabel dateOfEmploymentLabel = new JLabel("Date Of Employment(leave blank for today):");
        dateOfEmploymentLabel.setBounds(10,130,(int)dateOfEmploymentLabel.getPreferredSize().getWidth(),(int)dateOfEmploymentLabel.getPreferredSize().getHeight());
        formPanel.add(dateOfEmploymentLabel);


        //indent for the below JLabels and JTextAreas initialized
        int leftIndent = 10;

        JLabel dayOfEmploymentLabel = new JLabel("Day");
        dayOfEmploymentLabel.setBounds(leftIndent,160,(int)dayOfEmploymentLabel.getPreferredSize().getWidth(),(int)dayOfEmploymentLabel.getPreferredSize().getHeight());
        formPanel.add(dayOfEmploymentLabel);

        //set the width to 14px, the size of two digits, the user can enter more but they will not be displayed on the screen and will show an error message
        leftIndent += dayOfEmploymentLabel.getPreferredSize().getWidth() + 5;
        JTextArea dayOfEmploymentArea = new JTextArea();
        textAreas[3] = dayOfEmploymentArea;
        textAreas[3].setName("Day Field");
        textAreas[3].setBounds(leftIndent,160,14,16);
        formPanel.add(textAreas[3]);

        leftIndent += 19;
        JLabel monthOfEmploymentLabel = new JLabel("Month");
        monthOfEmploymentLabel.setBounds(leftIndent,160,(int)monthOfEmploymentLabel.getPreferredSize().getWidth(),(int)dayOfEmploymentLabel.getPreferredSize().getHeight());
        formPanel.add(monthOfEmploymentLabel);

        leftIndent += monthOfEmploymentLabel.getPreferredSize().getWidth() + 5;

        monthList.setBounds(leftIndent,160,(int)monthList.getPreferredSize().getWidth(),16);
        formPanel.add(monthList);

        leftIndent += (int)monthList.getPreferredSize().getWidth() + 5;
        JLabel yearOfEmploymentLabel = new JLabel("Year");
        yearOfEmploymentLabel.setBounds(leftIndent,160,(int)yearOfEmploymentLabel.getPreferredSize().getWidth(),(int)dayOfEmploymentLabel.getPreferredSize().getHeight());
        formPanel.add(yearOfEmploymentLabel);

        leftIndent += yearOfEmploymentLabel.getPreferredSize().getWidth() + 5;
        JTextArea yearOfEmploymentArea = new JTextArea();
        textAreas[4] = yearOfEmploymentArea;
        textAreas[4].setName("Year Field");
        textAreas[4].setBounds(leftIndent,160,28,16);
        formPanel.add(textAreas[4]);

        //add a submit and cancel button
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(10,190,(int)submit.getPreferredSize().getWidth(),(int)submit.getPreferredSize().getHeight());
        formPanel.add(submit);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(220,190,(int)cancel.getPreferredSize().getWidth(),(int)cancel.getPreferredSize().getHeight());
        formPanel.add(cancel);

        //set window to not resizable
        setResizable(false);

        //add the panel to the frame
        add(formPanel);

        //set default close operation and visibility of the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        repaint();

        //if there are no departments to assign a manager to do below
        if(initializeDepartments()[0].equals("No Departments Available")){
            JOptionPane.showMessageDialog(null,"Please create at least one department before assigning a manager","No Departments Created",JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    public String[] initializeDepartments(){
        //initialize a string array of size departments.size(), ie however-many department objects are instantiated
        String[] tempDepartmentNames = new String[Driver.departments.size()];
        int counter = 0;
        for(Department department:Driver.departments){
            //if the manager name is the default constructor Manager name, there is no one assigned yet
            if(department.getDepartmentManager().getName().equals("No manager assigned")){

                tempDepartmentNames[counter]=department.getDepartmentName();
                counter++;
            }
        }
        if(counter==0){
            return new String[]{"No Departments Available"};
        }
        //if the counter is the size of the global departments array return all the departments
        else if(counter==Driver.departments.size()){
            return tempDepartmentNames;
        }
        else {
            //make a new array of size counter, ie the amount of departments with no manager
            String[] departmentNames = new String[counter];
            counter = 0;
            //populate the array
            for(String name : tempDepartmentNames)
                if(name!=null){
                    departmentNames[counter] = name;
                    counter++;
                }
            return departmentNames;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            if(validFields()){
                Driver.managers.add(new Manager(textAreas[0].getText(),textAreas[1].getText(),textAreas[2].getText(),getDate()));
            }
        }
        //cancel button pressed
        else {
            JOptionPane.showMessageDialog(null,"Exiting this window!","Confirmation",JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private boolean validFields(){
        boolean result = false;
        //a string that will hold all of the errors found
        String errors = "";
        for(JTextArea textArea:textAreas){
            if(textArea.getName().equals("Year Field")){
                if(textArea.getName().equals("Year Field"))
                    if(!validYear())
                        errors += "Invalid date was entered into the Year Field\n";
                if(validYear() && !validDay()){
                    errors += "Invalid date was entered into the Year Field\n";
                }
            }
            else if(textArea.getText().equals("")){
                errors += textArea.getName() + " has been left blank\n";
            }
            if(!textArea.getText().equals("") && textArea.getName().equals("Name Field") && !validName()){
                errors += "Invalid name entered\n";
            }
            if(!textArea.getText().equals("") && textArea.getName().equals("Eircode Field") && !validEircode()){
                errors += "Invalid eircode entered\n";
            }
        }





        //if no errors were generated then all inputs were valid
        if(errors.equals("")){
            result = true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Following error message was generated:\n\n" + errors,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private boolean validDay(){
        int i;
        String yearText = textAreas[3].getText();
        for(i = 0; i < yearText.length(); i++){
            if(!Character.isDigit(yearText.charAt(i)))
                break;
        }
        if(i == yearText.length()){
            int dayNo = Integer.parseInt(yearText);
            //if the month has 31 days check
            if(monthList.getSelectedIndex()==0||monthList.getSelectedIndex()==2||monthList.getSelectedIndex()==4||monthList.getSelectedIndex()==6||monthList.getSelectedIndex()==8||monthList.getSelectedIndex()==10){
                if(dayNo > 0 && dayNo <= 31)
                    return true;
            }
            //if the month has 30 days check
            else if(monthList.getSelectedIndex()==3||monthList.getSelectedIndex()==5||monthList.getSelectedIndex()==7||monthList.getSelectedIndex()==9||monthList.getSelectedIndex()==11){
                if(dayNo > 0 && dayNo <= 30)
                    return true;
            }
            //else, the month is february, check for valid year, then leap year and respond appropriately
            else if(validYear()){
                    if(Integer.parseInt(textAreas[4].getText())/4==0){
                        if(dayNo > 0 && dayNo <=29)
                            return true;
                    }
                    else{
                        if(dayNo > 0 && dayNo <=28)
                            return true;
                    }
            }
        }
        return false;
    }

    private boolean validYear(){
        int i;
        String yearText = textAreas[4].getText();
        if(yearText.length()==4){
            for(i = 0; i < yearText.length(); i++){
                if(!Character.isDigit(yearText.charAt(i)))
                    break;
            }
            if(i==yearText.length()){
                return true;
            }
        }
        return false;
    }

    private boolean validName(){
        if(!textAreas[0].getText().equals("")){
            String nameText = textAreas[0].getText();
            int i;
            for(i = 0; i < nameText.length(); i++){
                //if the name contains something that is not a letter or a ' or a space break
                if(!Character.isLetter(nameText.charAt(i)) && nameText.charAt(i)!='\'' && nameText.charAt(i)!=' ')
                    break;
            }
            if(i==nameText.length())
                return true;
        }
        return false;
    }

    private boolean validEircode(){
    /*Eircode allowed characters chart found here under chapter 1.5.4
    *http://www.ossiansmyth.ie/wp-content/uploads/2015/04/Eircode-Address-File-Product-Guide-Edition-1-Version-8-PUBLISHED.pdf
    *
    *Pos | Allowed characters
    *1   | A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
    *2   | 0-9
    *3   | 0-9 with the exception of W for D6W
    *4   | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
    *5   | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
    *6   | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
    *7   | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
    **/
    //in order to make this easier, i decided to store the allowed letters in a string so that i can use the .contains()
    //otherwise i could have used a for loop for each character but that is not as efficient :P
        String allowedLetters = "ACDEFHKNPRTVWXY";
        String eircodeText = textAreas[2].getText();
        if(eircodeText.length()==7){
            //perform all of the requirement checks above in one if statement
            //substring used instead of charAt() because contains wants char 'String' to compare to
            if(allowedLetters.contains(eircodeText.substring(0,1)) && Character.isDigit(eircodeText.charAt(1)) &&
            (Character.isDigit(eircodeText.charAt(1)) || (eircodeText.charAt(0)=='D' && eircodeText.charAt(1)=='6' && eircodeText.charAt(2)=='W')) &&
            (Character.isDigit(eircodeText.charAt(3)) || allowedLetters.contains(eircodeText.substring(3,4))) &&
            (Character.isDigit(eircodeText.charAt(4)) || allowedLetters.contains(eircodeText.substring(4,5))) &&
            (Character.isDigit(eircodeText.charAt(5)) || allowedLetters.contains(eircodeText.substring(5,6))) &&
            (Character.isDigit(eircodeText.charAt(6)) || allowedLetters.contains(eircodeText.substring(6,7)))){
                return true;
            }

        }
        return false;
    }

    private GregorianCalendar getDate(){
        return null;
    }
}
