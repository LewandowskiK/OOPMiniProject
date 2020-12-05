//Driver.java
//Krystian Lewandowski
/*This program will act as the driver of the entire project*/

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Driver{

    //create 3 global arrays for each of the instantiable classes
    public static ArrayList<Department> departments = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static ArrayList<Worker> workers = new ArrayList<>();

    public static void main(String[] args) {
        if(JOptionPane.showConfirmDialog(null,"Would you like to read data from file?","Read From File?",JOptionPane.YES_NO_CANCEL_OPTION)==0){
            readFromFile();
            //increment ID counter for new additions
            for(Manager manager : managers){
                Employee.incrementID();
            }
            for(Worker worker: workers){
                Employee.incrementID();
            }
        }
        //pre programmed values to reset the files to default values, probably not needed
        /*departments.add(new Department("Engineering",new Manager()));
        departments.add(new Department("Software",new Manager()));
        departments.add(new Department("Accounting",new Manager()));
        departments.add(new Department("Public Realtions",new Manager()));
        managers.add(new Manager("John O'Mahony","23 Rockstreet Tipperrary","E34V598",new GregorianCalendar(2012, Calendar.MAY,18)));
        managers.add(new Manager("Jack Manning","8 Mainstreet Tipperrary","E34EV43",new GregorianCalendar(2014, Calendar.JANUARY,8)));
        managers.add(new Manager("Robin Ridderbos","12 ShellDrive Tipperrary","F91V2X8",new GregorianCalendar(2012, Calendar.FEBRUARY,18)));
        managers.add(new Manager("Scott Drew","12 Outerway Kerry","V92XVN3",new GregorianCalendar(2015, Calendar.MAY,18)));
        departments.get(0).setDepartmentManager(managers.get(0));
        departments.get(1).setDepartmentManager(managers.get(1));
        departments.get(2).setDepartmentManager(managers.get(2));
        departments.get(3).setDepartmentManager(managers.get(3));
        workers.add(new Worker("Jack O'Roarke","18 Rockstreet Tipperrary","E34V128",new GregorianCalendar(2013, Calendar.JANUARY,18)));
        workers.get(0).setDepartment(departments.get(0).getDepartmentName());
        workers.add(new Worker("John O'Reiley","23 Waydrive Tipperrary","E34VE89",new GregorianCalendar(2017, Calendar.OCTOBER,8)));
        workers.get(1).setDepartment(departments.get(0).getDepartmentName());
        workers.add(new Worker("Liam O'Roarke","45 Offroad Tipperrary","E34VBC8",new GregorianCalendar(2018, Calendar.FEBRUARY,6)));
        workers.get(2).setDepartment(departments.get(0).getDepartmentName());
        workers.add(new Worker("Robert Robinson","56 Lakeview Tipperrary","E34E168",new GregorianCalendar(2019, Calendar.AUGUST,9)));
        workers.get(3).setDepartment(departments.get(0).getDepartmentName());
        workers.add(new Worker("James Kings","25 Wellsroad Tipperrary","E34VEV8",new GregorianCalendar(2018, Calendar.OCTOBER,13)));
        workers.get(4).setDepartment(departments.get(0).getDepartmentName());
        workers.add(new Worker("Jack Manning","1 LakeView Tipperrary","E34E108",new GregorianCalendar(2016, Calendar.MARCH,23)));
        workers.get(5).setDepartment(departments.get(0).getDepartmentName());
        departments.get(0).addEmployee(workers.get(0));
        departments.get(0).addEmployee(workers.get(1));
        departments.get(0).addEmployee(workers.get(2));
        departments.get(0).addEmployee(workers.get(3));
        departments.get(0).addEmployee(workers.get(4));
        departments.get(0).addEmployee(workers.get(5));
        workers.add(new Worker("Robert Rake","18 Rockstreet Galway","H91VE28",new GregorianCalendar(2013, Calendar.FEBRUARY,19)));
        workers.get(6).setDepartment(departments.get(1).getDepartmentName());
        workers.add(new Worker("John Doe","23 Ridgeview Galway","H91V189",new GregorianCalendar(2012, Calendar.APRIL,1)));
        workers.get(7).setDepartment(departments.get(1).getDepartmentName());
        workers.add(new Worker("Robin Ridderbos","2 Lakeside Way Galway","H91V1B8",new GregorianCalendar(2018, Calendar.JULY,12)));
        workers.get(8).setDepartment(departments.get(1).getDepartmentName());
        workers.add(new Worker("Michael Downing","18 Mainstreet Galway","H91V1A8",new GregorianCalendar(2020, Calendar.JUNE,6)));
        workers.get(9).setDepartment(departments.get(1).getDepartmentName());
        workers.add(new Worker("Cathal Carol","9 Mainstreet Galway","H91V1B5",new GregorianCalendar(2016, Calendar.AUGUST,8)));
        workers.get(10).setDepartment(departments.get(1).getDepartmentName());
        workers.add(new Worker("Karl Himmey","18 Lakeview Galway","H91V1E8",new GregorianCalendar(2018, Calendar.JANUARY,17)));
        workers.get(11).setDepartment(departments.get(1).getDepartmentName());
        departments.get(1).addEmployee(workers.get(6));
        departments.get(1).addEmployee(workers.get(7));
        departments.get(1).addEmployee(workers.get(8));
        departments.get(1).addEmployee(workers.get(9));
        departments.get(1).addEmployee(workers.get(10));
        departments.get(1).addEmployee(workers.get(11));
        workers.add(new Worker("Fiona Manning","18 Mainway Kildare","R51V128",new GregorianCalendar(2015, Calendar.DECEMBER,13)));
        workers.get(12).setDepartment(departments.get(2).getDepartmentName());
        workers.add(new Worker("Anna Ivovitch","9 Hillside Kildare","R51V128",new GregorianCalendar(2019, Calendar.SEPTEMBER,5)));
        workers.get(13).setDepartment(departments.get(2).getDepartmentName());
        workers.add(new Worker("Mick O'Roarke","7 Mainstreet Kildare","R51V128",new GregorianCalendar(2017, Calendar.NOVEMBER,3)));
        workers.get(14).setDepartment(departments.get(2).getDepartmentName());
        workers.add(new Worker("Micheal Midge","18 Jocksthorough Kildare","R51V128",new GregorianCalendar(2018, Calendar.SEPTEMBER,19)));
        workers.get(15).setDepartment(departments.get(2).getDepartmentName());
        workers.add(new Worker("Mike Urge","18 Rockstreet Kildare","R51V128",new GregorianCalendar(2012, Calendar.NOVEMBER,29)));
        workers.get(16).setDepartment(departments.get(2).getDepartmentName());
        workers.add(new Worker("Dixie Normus","18 Bonetown Kildare","R51V128",new GregorianCalendar(2016, Calendar.MAY,25)));
        workers.get(17).setDepartment(departments.get(2).getDepartmentName());
        departments.get(2).addEmployee(workers.get(12));
        departments.get(2).addEmployee(workers.get(13));
        departments.get(2).addEmployee(workers.get(14));
        departments.get(2).addEmployee(workers.get(15));
        departments.get(2).addEmployee(workers.get(16));
        departments.get(2).addEmployee(workers.get(17));
        workers.add(new Worker("Harry Vieux","18 Rockstreet Dublin22","D22V128",new GregorianCalendar(2011, Calendar.OCTOBER,18)));
        workers.get(18).setDepartment(departments.get(3).getDepartmentName());
        workers.add(new Worker("Tom Greaney","7 Sidestreet Dublin24","D24V128",new GregorianCalendar(2010, Calendar.MAY,14)));
        workers.get(19).setDepartment(departments.get(3).getDepartmentName());
        workers.add(new Worker("John O'Roarke","5 Shopsroad Dublin10","D10V128",new GregorianCalendar(2009, Calendar.JANUARY,13)));
        workers.get(20).setDepartment(departments.get(3).getDepartmentName());
        workers.add(new Worker("Jimmy Clarke","7 Randomway Dublin9","D09V128",new GregorianCalendar(2013, Calendar.MAY,17)));
        workers.get(21).setDepartment(departments.get(3).getDepartmentName());
        workers.add(new Worker("Ben Tennisson","26 Newtroad Dublin4","D04V128",new GregorianCalendar(2019, Calendar.MARCH,3)));
        workers.get(22).setDepartment(departments.get(3).getDepartmentName());
        workers.add(new Worker("George Flannel","6 Buildsville Dublin9","D10V128",new GregorianCalendar(2020, Calendar.MAY,18)));
        workers.get(23).setDepartment(departments.get(3).getDepartmentName());
        departments.get(3).addEmployee(workers.get(18));
        departments.get(3).addEmployee(workers.get(19));
        departments.get(3).addEmployee(workers.get(20));
        departments.get(3).addEmployee(workers.get(21));
        departments.get(3).addEmployee(workers.get(22));
        departments.get(3).addEmployee(workers.get(23));*/
        //start the program
        new LoginGUI();
    }

    public static void saveToFile(){
        try {
            //only save if array size is greater than 0
            if(departments.size()>0){
                File departmentFile = new File("Departments_File.data");
                //if the file already exists delete it, otherwise it will corrupt
                if(departmentFile.exists()){
                    departmentFile.delete();
                }
                FileOutputStream departmentOutputStream = new FileOutputStream(departmentFile);
                ObjectOutputStream departmentsOutStream = new ObjectOutputStream(departmentOutputStream);
                for(Department department : departments){
                    departmentsOutStream.writeObject(department);                }
                JOptionPane.showMessageDialog(null,"Successfully saved " + departments.size() + " Departments to file","Success",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"No departments Exist","No Departments Found",JOptionPane.INFORMATION_MESSAGE);
            }
            //only save if array size is greater than 0
            if(managers.size()>0){
                File managerFile = new File("Managers_File.data");
                //if the file already exists delete it, otherwise it will corrupt
                if(managerFile.exists()){
                    managerFile.delete();
                }
                FileOutputStream managersOutputStream = new FileOutputStream(managerFile);
                ObjectOutputStream managersOutStream = new ObjectOutputStream(managersOutputStream);
                for(Manager manager : managers){
                    managersOutStream.writeObject(manager);
                }
                JOptionPane.showMessageDialog(null,"Successfully saved " + managers.size() + " Departments to file","Success",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"No managers Exist","No Managers Found",JOptionPane.INFORMATION_MESSAGE);
            }
            //only save if the array size is greater than 0
            if(workers.size()>0){
                File workerFile = new File("Workers_File.data");
                //if the file already exists delete it, otherwise it will corrupt
                if(workerFile.exists()){
                    workerFile.delete();
                }
                FileOutputStream workersOutputStream = new FileOutputStream(workerFile);
                ObjectOutputStream workersOutStream = new ObjectOutputStream(workersOutputStream);
                for(Worker worker : workers){
                    workersOutStream.writeObject(worker);
                }
                JOptionPane.showMessageDialog(null,"Successfully saved " + workers.size() + " Workers to file","Success",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"No workers Exist","No Workers Found",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"An error has occurred when saving to a file!","Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void readFromFile(){
        //try to read from the departments file
        try {
            File departmentFile = new File("Departments_File.data");
            FileInputStream departmentInFlow = new FileInputStream(departmentFile);
            ObjectInputStream departmentInputFlow = new ObjectInputStream(departmentInFlow);
            while (true){
                //once all departments in the file are read and added an End Of File Exception (EOF) gets thrown
                departments.add((Department)departmentInputFlow.readObject());
            }
        }
        catch (EOFException e){
            JOptionPane.showMessageDialog(null,"Departments read from file","Read Complete",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Departments File could not be found!", "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Departments File could not be read!", "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert object to the appropriate class!","Problem Converting Object Read From File!",JOptionPane.ERROR_MESSAGE);

        }
        catch (ClassCastException cce) {
            cce.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert the object to the appropriate class!","Problem Converting Object!",JOptionPane.ERROR_MESSAGE);
        }
        //if an un defined error is caught
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Uncaught Error occurred!","Catastrophic Error",JOptionPane.ERROR_MESSAGE);
        }
        //try to read the managers file
        try {
            File managersFile = new File("Managers_File.data");
            FileInputStream managerInFlow = new FileInputStream(managersFile);
            ObjectInputStream managerInputFlow = new ObjectInputStream(managerInFlow);
            while (true){
                //once all managers in the file are read and added an End Of File Exception (EOF) gets thrown
                managers.add((Manager)managerInputFlow.readObject());
            }
        }
        catch (EOFException e){
            JOptionPane.showMessageDialog(null,"Managers read from file","Read Complete",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Managers File could not be found!", "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Managers File could not be read!", "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert object to the appropriate class!","Problem Converting Object Read From File!",JOptionPane.ERROR_MESSAGE);

        }
        catch (ClassCastException cce) {
            cce.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert the object to the appropriate class!","Problem Converting Object!",JOptionPane.ERROR_MESSAGE);
        }
        //if an un defined error is caught
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Uncaught Error occurred!","Catastrophic Error",JOptionPane.ERROR_MESSAGE);
        }

        try {
            File workersFile = new File("Workers_File.data");
            FileInputStream workerInFlow = new FileInputStream(workersFile);
            ObjectInputStream workerInputFlow = new ObjectInputStream(workerInFlow);
            while (true){
                //once all departments in the file are read and added an End Of File Exception (EOF) gets thrown
                workers.add((Worker) workerInputFlow.readObject());
            }
        }
        catch (EOFException e){
            JOptionPane.showMessageDialog(null,"Workers read from file","Read Complete",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Workers File could not be found!", "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Workers File could not be read!", "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert object to the appropriate class!","Problem Converting Object Read From File!",JOptionPane.ERROR_MESSAGE);

        }
        catch (ClassCastException cce) {
            cce.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert the object to the appropriate class!","Problem Converting Object!",JOptionPane.ERROR_MESSAGE);
        }
        //if an un defined error is caught
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Uncaught Error occurred!","Catastrophic Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}