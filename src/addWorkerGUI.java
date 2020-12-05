//addWorkerGUI.java
//Krystian Lewandowski
/*This GUI allows the user to add an employee to a department*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class addWorkerGUI extends JFrame implements ActionListener {

    JTextArea[] textAreas = new JTextArea[5];
    //combo box for the month of employment
    JComboBox monthList = new JComboBox(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
    JButton submit;
    String departmentName;

    public addWorkerGUI(String selectedDepartmentName){
        //set title to 'Add Worker'
        super("Add Worker");

        departmentName = selectedDepartmentName;

        //set border layout
        setLayout(new BorderLayout(0,0));

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //set size of the window
        setSize(360,270);

        //a panel that will contain the form needed to create a new Worker Object
        JPanel formPanel = new JPanel();

        //null layout for absolute positioning
        formPanel.setLayout(null);

        JLabel departmentLabel = new JLabel("Please fill out this form to add a worker to the Department");
        departmentLabel.setBounds(10,10,340,20);
        formPanel.add(departmentLabel);

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

        JLabel eircodeLabel = new JLabel("Eircode:");
        eircodeLabel.setBounds(10,100,(int)eircodeLabel.getPreferredSize().getWidth(),(int)eircodeLabel.getPreferredSize().getHeight());
        formPanel.add(eircodeLabel);

        JTextArea eircodeArea = new JTextArea(1,20);
        textAreas[2] = eircodeArea;
        textAreas[2].setName("Eircode Field");
        textAreas[2].setBounds(100,100,200,20);
        formPanel.add(textAreas[2]);



        JLabel dateOfEmploymentLabel = new JLabel("Date Of Employment:");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            if(areValidFields() && isValidDate()){
                Worker worker = new Worker(textAreas[0].getText(),textAreas[1].getText(),textAreas[2].getText(),getDate());
                worker.setDepartment(departmentName);
                Driver.workers.add(worker);

                int index = 0;
                for(Department department : Driver.departments){
                    if(department.getDepartmentName().equals(departmentName)){
                        //if the name of this department is the name of the selected department break and output message
                        JOptionPane.showMessageDialog(null,"Worker added to the " + department.getDepartmentName() + " Department","Employee added successfully!",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    //otherwise increment the index
                    index++;
                }
                //add the employee to the department
                Driver.departments.get(index).addEmployee(worker);

                //reset the text areas,monthList and repaint the window
                for(JTextArea textArea : textAreas){
                    textArea.setText("");
                }
                monthList.setSelectedIndex(0);
                repaint();
            }
        }
        //cancel button selected
        else {
            JOptionPane.showMessageDialog(null,"Exiting this window","Confirmation",JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    //user defined methods start below
    //these methods are a duplicate of the methods in addManagerGUI
    private boolean areValidFields(){
        boolean result = false;
        //a string that will hold all of the errors found
        String errors = "";
        for(JTextArea textArea:textAreas){

            //if both year or day were entered wrong just output 'invalid date entered'
            if(textArea.getName().equals("Year Field")){
                if(!isValidYear()||(isValidYear() && !isValidDay()))
                    errors += "Invalid date was entered\n";
            }
            else if(textArea.getText().equals("")){
                errors += textArea.getName() + " has been left blank\n";
            }

            if(!textArea.getText().equals("") && textArea.getName().equals("Name Field") && !isValidName()){
                errors += "Invalid name entered\n";
            }
            if(!textArea.getText().equals("") && textArea.getName().equals("Eircode Field") && !isValidEircode()){
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

    //this method validates the day entered in the form ie. all numeric
    private boolean isValidDay(){
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
            else if(isValidYear()){
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

    //this method validates the year entered in the form ie. all numeric
    private boolean isValidYear(){
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

    //this method validates the name entered into the form ie. name is alpha
    private boolean isValidName(){
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

    //this method validates the eircode entered, the address mightn't actually exist but it follows the validation steps
    //outlined by the respective authority
    private boolean isValidEircode(){
        /*Eircode allowed characters chart found here under chapter 1.5.4
         *http://www.ossiansmyth.ie/wp-content/uploads/2015/04/Eircode-Address-File-Product-Guide-Edition-1-Version-8-PUBLISHED.pdf
         *
         *Pos | Allowed characters
         * 1  | A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
         * 2  | 0-9
         * 3  | 0-9 with the exception of W for D6W
         * 4  | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
         * 5  | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
         * 6  | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
         * 7  | 0-9, A,C,D,E,F,H,K,N,P,R,T,V,W,X,Y
         **/
        //in order to make this easier, i decided to store the allowed letters in a string so that i can use the .contains()
        //otherwise i could have used a for loop for each character but that is not as efficient :P
        String allowedLetters = "ACDEFHKNPRTVWXY";
        String eircodeText = textAreas[2].getText();
        if(eircodeText.length()==7){
            //perform all of the requirement checks above in one if statement
            //substring used instead of charAt() because contains wants a charArray or 'String' to compare to
            if(allowedLetters.contains(eircodeText.substring(0,1)) && Character.isDigit(eircodeText.charAt(1)) &&
                    (Character.isDigit(eircodeText.charAt(2)) || (eircodeText.charAt(0)=='D' && eircodeText.charAt(1)=='6' && eircodeText.charAt(2)=='W')) &&
                    (Character.isDigit(eircodeText.charAt(3)) || allowedLetters.contains(eircodeText.substring(3,4))) &&
                    (Character.isDigit(eircodeText.charAt(4)) || allowedLetters.contains(eircodeText.substring(4,5))) &&
                    (Character.isDigit(eircodeText.charAt(5)) || allowedLetters.contains(eircodeText.substring(5,6))) &&
                    (Character.isDigit(eircodeText.charAt(6)) || allowedLetters.contains(eircodeText.substring(6,7)))){
                return true;
            }
        }
        return false;
    }

    //this method checks that all the date details are correct and that the date is either today or before today
    private boolean isValidDate(){
        //this only gets called if no errors were present in the form
        int employmentDay = Integer.parseInt(textAreas[3].getText());
        int employmentMonth = monthList.getSelectedIndex();
        int employmentYear = Integer.parseInt(textAreas[4].getText());
        GregorianCalendar todaysDate = new GregorianCalendar();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int todaysDay = Integer.parseInt(dayFormat.format(todaysDate.getTime()));
        int todaysMonth = Integer.parseInt(monthFormat.format(todaysDate.getTime()));
        int todaysYear = Integer.parseInt(yearFormat.format(todaysDate.getTime()));

        if(employmentYear<=todaysYear){
            //if it is the same year, the month has to be earlier than or the same as todays month, otherwise it doesnt matter
            if((employmentYear==todaysYear && employmentMonth<=todaysMonth)||(employmentYear<todaysYear)){
                //similarly to above, if the month and year are the same, the day has to be earlier or the same day as today, otherwise it doesnt matter
                if((employmentYear==todaysYear && employmentMonth==todaysMonth && employmentDay<=todaysDay) || (employmentMonth<todaysMonth)){
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null,"The date has to be either today or in the past","Invalid date",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    //this method puts the separate fields together
    //only invoked if the date is valid and either today or in the past
    private GregorianCalendar getDate(){
        return new GregorianCalendar(Integer.parseInt(textAreas[4].getText()),monthList.getSelectedIndex(),Integer.parseInt(textAreas[3].getText()));
    }
}