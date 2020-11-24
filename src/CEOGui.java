import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADDITIONAL FUNCTIONALITY TO BE CODED LATER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class CEOGui extends JFrame {

    //global JButton array for button listener
    JButton[] jButtons = new JButton[5];

    public CEOGui(){
        //set title to 'welcome CEO'
        super("Welcome CEO");

        //set border layout
        setLayout(new BorderLayout(0,0));

        //make window appear in center of screen
        setLocationRelativeTo(null);

        //set size of the window
        setSize(260,200);

        //set window to not resizable
        setResizable(false);

        //create a JPanel that will display the options available to the CEO
        JPanel optionsPanel = new JPanel();

        //set layout for absolute positioning
        optionsPanel.setLayout(null);

        JLabel textLabel = new JLabel("What would you like to do?");
        optionsPanel.add(textLabel);
        //set a position for the text label 10px down width calculation explained below
        /*this.getWidth retrieves the width of the window, it is then divided by 2 to get to the centre of the window.
        * the preferred width of the textLabel is then taken away from that to centre the label on the window ,
        * 10 is added because of the margin of error added by type-casting from double to int and then dividing by 2 which gives an int*/
        textLabel.setBounds(((this.getWidth()/2)-(10+(int)textLabel.getPreferredSize().getWidth()/2)),10,(int)textLabel.getPreferredSize().getWidth(),(int)textLabel.getPreferredSize().getHeight());

        //initialise a buttonEventHandler handler
        ButtonEventHandler handler = new ButtonEventHandler();

        //create and add the 'add manager' JButton to the global array, set a mnemonic 'A' and add the buttonEventHandler
        JButton addManager = new JButton("Add Manager");
        jButtons[0] = addManager;
        jButtons[0].setMnemonic(KeyEvent.VK_A);
        jButtons[0].addActionListener(handler);
        optionsPanel.add(jButtons[0]);
        //place the JButton  31px (10 + 16[preferred label height] + 5) down
        //width calculation similar to textLabel calculation above
        jButtons[0].setBounds(((this.getWidth()/2)-(10+(int)jButtons[0].getPreferredSize().getWidth()/2)),31,(int)jButtons[0].getPreferredSize().getWidth(),(int)jButtons[0].getPreferredSize().getHeight());

        //add the options panel to the Center of the JFrame
        add(optionsPanel,BorderLayout.CENTER);

        //set default close operation and visibility of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        //initialise window for testing
        Employee[] allEmployees = new Employee[5];
        new CEOGui();
    }

    private class ButtonEventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==jButtons[0]){
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPLEMENT A GUI FOR ADDING A MANAGER AND HIS DEPARTMENT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //temporarily done through pre-programmed values
                Manager manager = new Manager("Liam","Tralee","V92EIR2",new GregorianCalendar(),5);
                Department department = new Department("Engineering",manager);
                manager.setDepartment(department);
                manager.setPosition();

                JOptionPane.showMessageDialog(null,manager);
                JOptionPane.showMessageDialog(null,department);
                System.exit(0);
            }
        }
    }
}
