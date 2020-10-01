/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
public class Car extends Vehicle {
    
    //variables unique to cars
    private int numberOfSeats;
    
    //variable accessor methods
    @Override
    public int getVehicleVar()
    {
        return numberOfSeats;
    }
    
    @Override
    public int calculateBasicTripCost()
    {
        if (numberOfSeats < 5)
        {
            return 500;
        }
        else
        {
            return 600;
        }
    }
    
    @Override
    public String getVehicleType()
    {
        return "Car";
    }
    
    //constructor
    public Car(int numberOfSeats, String regNum, String manufacturer)
    {
        this.numberOfSeats = numberOfSeats;
        this.regNum = regNum;
        this.manufacturer = manufacturer;
        
    }
}
