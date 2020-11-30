//Driver.java
//Krystian Lewandowski
/*This program will act as the driver of the entire project*/

import java.util.ArrayList;

public class Driver {

    //create 3 global arrays for each of the instantiable classes
    public static ArrayList<Department> departments = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static ArrayList<Worker> workers = new ArrayList<>();

    public static void main(String[] args) {
        //start the program
        new LoginGUI();
    }
}
