/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author james
 */
public class CustomerAccount implements Comparable<CustomerAccount> {

    private String firstName;
    private String lastName;
    private int accBalance;
    private DiscountType discount;
    
    //setting the static type of the vehicle
    Vehicle customerVehicle;

    private int compare(String regNum, String regNum0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //enum to determine the discount applied to the account
    private static enum DiscountType {
        NONE,
        STAFF,
        FRIENDS_AND_FAMILY;
    }
    
    
    //ACCESSOR METHODS
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getDiscount()
    {
        return discount.name();
    }
    
    public int getAccBalance()
    {
        return accBalance;
    }
    
    public Vehicle getCustomerVehicle()
    {
        return customerVehicle;
    }
    
    public String getAll()
    {
        return (customerVehicle.getVehicleType() + "\t" + customerVehicle.getRegNum() + "\t" + firstName +  "\t" + lastName + "\t" + customerVehicle.getManufacturer() + "\t" + customerVehicle.getVehicleVar() + "\t" + accBalance + "\t" + discount.name());
    }
    
    //DISCOUNT SETTING METHODS
    //sets the discount to STAFF
    private void activateStaffDiscount()
    {
        this.discount = DiscountType.STAFF;
    }
    
    //sets the discount to FRIENDS_AND_FAMILY
    private void activateFriendsAndFamilyDiscount()
    {
        this.discount = DiscountType.FRIENDS_AND_FAMILY;
    }
    
    //sets the discount to NONE
    private void deactivateDiscount()
    {
        this.discount = DiscountType.NONE;
    }
    
    //applies the given discount to given trip cost, used for ease of interpretation
    private int applyDiscount(DiscountType theDiscount, int cost)
    {
        if (theDiscount.equals(DiscountType.STAFF))
        {
            //50% discount
            return (cost/2);
        }
        else if (theDiscount.equals(DiscountType.FRIENDS_AND_FAMILY))
        {
            //10% discount
            return (cost-(cost/10));
        }
        return cost;
    }
    
    //FUNCTIONAL METHODS
    //adds funds to the user's account
    public void addFunds(int amount)
    {
        this.accBalance += amount;
    }
    
    //simulates a trip
    public int makeTrip() throws InsufficientAccountBalanceException
    {
        int tripCost = customerVehicle.calculateBasicTripCost();
        tripCost = applyDiscount(discount, tripCost);
        
        //checking to see if account has sufficient funds
        //if an InsufficientAccountBalanceException is thrown here it is carried down to the try-catch in simulateFromFile to be handled
        if ((accBalance - tripCost) < 0)
        {
            throw new InsufficientAccountBalanceException(this.customerVehicle.getRegNum() + ": makeTrip failed. Insufficient funds");
        }
        else
        {
            this.accBalance -= tripCost;
            return tripCost;
        }
    }
    
    //CONSTRUCTOR METHODS
    //constructor
    public CustomerAccount(String firstName, String lastName, String vehicleType, String regNum, String manufacturer, int vehicleVar, int accBalance, String discountType)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accBalance = accBalance;
        
        //calls separate method to set dynamic type of customerVehicle
        setVehicleType(vehicleType, vehicleVar, regNum, manufacturer);
        
        //sets discount to NONE by default
        deactivateDiscount();
        //calls separate method to set the discount if there is one
        setDiscount(discountType);
    }
    
    //uses a string to determine which discount to apply, if any
    private void setDiscount(String discountType)
    {
        if (discountType.equals("FRIENDS_AND_FAMILY"))
        {
            activateFriendsAndFamilyDiscount();
        }
        else if (discountType.equals("STAFF"))
        {
            activateStaffDiscount();
        }
    }
    //uses a string to determine which Vehicle subclass to set as dynamic type
    //the additional integer variable for each subclass is passed in as an argument in each case
    private void setVehicleType(String vehicleType, int vehicleVar, String regNum, String manufacturer)
    {
        //tried using a switch statement here but it wouldn't work, hence the repeated if statements
        if(vehicleType.equals("Car"))
        {
            this.customerVehicle = new Car(vehicleVar, regNum, manufacturer);
        }
        else if(vehicleType.equals("Truck"))
        {
            this.customerVehicle = new Truck(vehicleVar, regNum, manufacturer);
        }
        else if(vehicleType.equals("Van"))
        {
            this.customerVehicle = new Van(vehicleVar, regNum, manufacturer);
        }
    }
    
    
    //COMPARISON METHODS
    //overriding the compareTo method so we can compare registration numbers
    @Override
    public int compareTo(CustomerAccount otherAccount)
    {
        return compare(this.customerVehicle.getRegNum(), otherAccount.customerVehicle.getRegNum());
    }
    
}
