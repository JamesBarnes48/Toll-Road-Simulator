/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

/**
 *
 * @author james
 */
public class TollRoad {
    //variable tracking the money made by an individual road
    int moneyMade;
    
    //array list holding all CustomerAccouunt objects tied to an instance of a toll road
    ArrayList<CustomerAccount> tiedAccounts = new ArrayList<>();
    
    //VARIABLE ACCESSOR METHODS
    public int getMoneyMade()
    {
        return moneyMade;
    }
    
    public ArrayList<CustomerAccount> getTiedAccounts()
    {
        return tiedAccounts;
    }
    
    //FUNCTIONAL METHODS
    //adds a new customer to the toll road
    public void addCustomer(CustomerAccount acc)
    {
        tiedAccounts.add(acc);
    }
    
    //finds a customer account tied to this road and returns it if possible, if not customer not found exception thrown
    public CustomerAccount findCustomer(String targetRegNum) throws CustomerNotFoundException
    {
            for (int i = 0; i < tiedAccounts.size(); i++)
            {
                CustomerAccount tempAcc = tiedAccounts.get(i);
                String tempRegNum = tempAcc.getCustomerVehicle().getRegNum();
                if (tempRegNum.equals(targetRegNum))
                {
                    return tempAcc;
                }
            }
            throw new CustomerNotFoundException("Error: Customer Not Found");
    }
    
    //finds the customer account passed in and calls the makeTrip method on it
    public void chargeCustomer(String regNum) throws CustomerNotFoundException, InsufficientAccountBalanceException
    {
        CustomerAccount chargedAcc = findCustomer(regNum);
        moneyMade += chargedAcc.makeTrip();
    }
}
