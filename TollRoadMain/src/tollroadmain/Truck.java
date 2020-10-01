/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
public class Truck extends Vehicle {
    
    //variables unique to trucks
    private int numTrailers;
    
    //variable accessor methods
    @Override
    public int getVehicleVar()
    {
        return numTrailers;
    }
    
    @Override
    public int calculateBasicTripCost()
    {
        if (numTrailers == 0 || numTrailers == 1)
        {
            return 1250;
        }
        else
        {
            return 1500;
        }
    }
    
    @Override
    public String getVehicleType()
    {
        return "Truck";
    }
    
    //constructor
    public Truck(int numTrailers, String regNum, String manufacturer)
    {
        this.numTrailers = numTrailers;
        this.regNum = regNum;
        this.manufacturer = manufacturer;
    }
}
