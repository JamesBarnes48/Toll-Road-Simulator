/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author james
 */
public class TollRoadMain {


    public static void main(String[] args) throws FileNotFoundException, InsufficientAccountBalanceException, CustomerNotFoundException{
        // TODO code application logic here
        //flag used to set the output stream to the output.txt file on the 2nd iteration
        boolean flag = false;
        
        //loop runs the program twice, first outputting to console, then to output.txt
        for(int i = 0; i < 2; i++)
        {
            //setting the output stream to output.txt
            if (flag == true)
            {
                File file = new File("output.txt");
                FileOutputStream fstream = new FileOutputStream(file);
                PrintStream pstream = new PrintStream(fstream);
                System.setOut(pstream);
            }
            //creating instance of this class so methods can be called
            TollRoadMain program = new TollRoadMain();

            //running initialise method and assigning the return value to a TollRoad object
            //in order for it to be passed into simulateFromFile()
            TollRoad road = program.initialiseTollRoadFromFile();

            //running simulate method using pre-made road as an argument
            System.out.println("£" + program.simulateFromFile(road) + " made from this road");
            
            
            //setting flag to true for iteration 2
            flag = true;
        }
        
    }
    
    //creates a new tollroad, reads through customerData.txt and populates the road with CustomerAccounts
    private TollRoad initialiseTollRoadFromFile() throws FileNotFoundException
    {
        //creating new road
        TollRoad theRoad = new TollRoad();
        
        //Setting up my file reader which is a scanner object and setting its delimiter
        //delimiter sets an attribute of the Scanner so it stops reading when it reaches a comma or a #
        System.out.println(new File(".").getAbsolutePath());
        Scanner theReader = new Scanner(new File("customerData.txt"));
        theReader.useDelimiter("[,#]+");
        
        //creates CustomerAccount objects from read data and adds to array
        while (theReader.hasNext())
        {
            
            //Reads each part of the document until a comma/hash and assigns it to its corresponding
            //variable. Once all parameters have been read a new CustomerAccount object is made from them
            //and added to the array list
            String tempType = theReader.next();
            String tempReg  = theReader.next();
            String tempFirst = theReader.next();
            String tempLast = theReader.next();
            String tempManufacturer = theReader.next();
            int tempVehicleVar = Integer.parseInt(theReader.next());
            int tempBalance = Integer.parseInt(theReader.next());
            String tempDiscount = theReader.next();
            
            theRoad.tiedAccounts.add(new CustomerAccount(tempFirst, tempLast, tempType, tempReg, tempManufacturer, tempVehicleVar, tempBalance, tempDiscount));
            
        }
        
        //returns our new TollRoad object to be used in simulateFromFile
        return theRoad;
    }
    
    //takes a toll road as an argument and simulates a days worth of transactions on it
    //the transactions are read in from transactions.txt and the transactions are then
    //carried out for that toll road, altering the customer accounts and money made accordingly
    private int simulateFromFile(TollRoad simRoad) throws FileNotFoundException, InsufficientAccountBalanceException, CustomerNotFoundException
    {
        //reads transactions.txt and executes the transaction before reading the next
        Scanner transactionReader = new Scanner(new File("transactions.txt"));
        transactionReader.useDelimiter("[,$]+");
        
        
        while (transactionReader.hasNext())
        {
            String instruction = transactionReader.next();
            String regNum = transactionReader.next();
            
            //addFunds instructions have an additional variable on the end so this accounts for that
            //embedded in several try-catch loops to allow specific exceptions to be handled
            try
            {
                if (instruction.equals("addFunds"))
                {
                    int amount = Integer.parseInt(transactionReader.next());

                    //executing the addFunds instruction
                    try
                    {
                        simRoad.findCustomer(regNum).addFunds(amount);
                        System.out.println(regNum + ":" + amount + " added successfully");
                    }
                    catch(Exception e)
                    {
                        throw new CustomerNotFoundException(simRoad.findCustomer(regNum).getCustomerVehicle().getRegNum() + ": addFunds failed. CustomerAccount does not exist");
                    }
                }
                else if (instruction.equals("makeTrip"))
                {
                    simRoad.chargeCustomer(regNum);
                    System.out.println(regNum + ": Trip made successfully");
                    
                }
            }
            //catches any exceptions thrown in the simulation and handles them
            catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
        }
        //returns the money made so it can be printed in the main method
        return (simRoad.getMoneyMade());
    }
}
