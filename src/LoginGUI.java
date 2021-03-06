//LoginGUI.java
//Krystian Lewandowski
/*This program will allow the user to log in to the system as a CEO or Manager*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame implements WindowListener{
    //declaration of global components and variables needed for functionality
    private final JTextArea usernameField;
    private final JPasswordField passwordField;
    JLabel attemptLabel;
    private int attemptCounter = 3;

    public LoginGUI(){
        //set window Title to 'Login'
        super("Login");

        //set border layout
        setLayout(new BorderLayout(0,0));

        //set size of window
        setSize(220,140);

        //set window to not resizable
        setResizable(false);

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //add username and password labels and fields in the panel
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextArea(1, 10);
        usernameField.setLineWrap(true);

        //get size in pixels for label field for more fine formatting
        Dimension LabelDimension = usernameLabel.getPreferredSize();
        Dimension FieldDimension = usernameField.getPreferredSize();

        //make a panel with no layout for absolute positioning
        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(null);
        layoutPanel.setSize(220,120);

        //add labels and fields with absolute positioning
        layoutPanel.add(usernameLabel);
        usernameLabel.setBounds(10,10,LabelDimension.width,LabelDimension.height);
        layoutPanel.add(usernameField);
        usernameField.setBounds(15 + LabelDimension.width,10,FieldDimension.width,FieldDimension.height);


        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(10);

        layoutPanel.add(passwordLabel);
        passwordLabel.setBounds(10,15 + LabelDimension.height,LabelDimension.width,LabelDimension.height);
        layoutPanel.add(passwordField);
        passwordField.setBounds(15 + LabelDimension.width,15 + LabelDimension.height,FieldDimension.width,FieldDimension.height);

        //add label that displays attempts remaining, only visible after one incorrect attempt
        attemptLabel = new JLabel("");
        layoutPanel.add(attemptLabel);
        attemptLabel.setBounds(10,20+LabelDimension.height*2,150,LabelDimension.height);

        //add login button in the bottom section of the window with a mnemonic 'RETURN' for login
        //and a ButtonEventHandler - handler
        JButton loginButton = new JButton("Login");
        loginButton.setMnemonic(KeyEvent.VK_ENTER);
        ButtonEventHandler handler = new ButtonEventHandler();
        loginButton.addActionListener(handler);

        //add JPanel and JButton to JFrame in the CENTER and SOUTH portions of the JFrame
        add(layoutPanel,BorderLayout.CENTER);
        add(loginButton,BorderLayout.SOUTH);

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
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}

    private class ButtonEventHandler implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            //username 'ceo' not case sensitive, password 'CEO' case sensitive
            if(usernameField.getText().toLowerCase().equals("ceo") && encrypt(passwordField.getPassword()).equals("HJT")){
                new CEOGUI();
                dispose();
            }
            //username 'manager' not case sensitive, password 'Manager' case sensitive
            else if(encrypt(passwordField.getPassword()).equals("Rfsfljw") && usernameField.getText().toLowerCase().equals("manager")){
                if(Driver.managers.size()>0){
                    //create the GUI
                    new ManagerGUI();
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please register departments/assign managers before logging in","No managers authorised",JOptionPane.INFORMATION_MESSAGE);
                    //reset the text fields
                    usernameField.setText("");
                    passwordField.setText("");
                    repaint();
                }
            }
            else{
                //decrement attemptCounter and display error message and attempts remaining
                attemptCounter--;
                attemptLabel.setText(attemptCounter + " attempts remaining");
                if(attemptCounter==1){
                    attemptLabel.setText(attemptCounter + " attempt remaining");
                }
                else if(attemptCounter==0){
                    JOptionPane.showMessageDialog(null,"Too many incorrect attempts!!!  Exiting System!","Authorisation Error",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                JOptionPane.showMessageDialog(null,"Invalid username/password combination!","Invalid username/password combination",JOptionPane.ERROR_MESSAGE);

                //reset the text fields
                usernameField.setText("");
                passwordField.setText("");
                repaint();
            }
        }
    }

    //user made method to encrypt the password received from the user, since .getPassword returns a char array the input is a char[]
    private String encrypt(char[] password){
        String encryptedPassword = "";

        //simple encryption method - caesar cypher - learned in RAD first year translated/amended from python to java language
        //for each character the ASCII value will be increased by 5,then the character is brought back to char from the ascii value and stored to a string then returned for comparison

        for(char c : password){
            int ascii = c;
            ascii+=5;
            encryptedPassword += (char)ascii;
        }
        return encryptedPassword;
    }
}
