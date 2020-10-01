/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
public abstract class Vehicle {
    //variables common to all vehicles
    protected String regNum;
    protected String manufacturer;
    
    //variable accessor methods
    public String getRegNum()
    {
        return regNum;
    }
    
    public String getManufacturer()
    {
        return manufacturer;
    }
    
    //will be used differently based on the specific vehicle
    abstract int calculateBasicTripCost();
    
    //used to get information about the type of vehicle held by a customer
    abstract String getVehicleType();
    
    //used to get the integer variable unique to each vehicle
    abstract int getVehicleVar();
}
