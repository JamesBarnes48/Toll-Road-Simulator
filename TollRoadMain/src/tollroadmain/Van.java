/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
public class Van extends Vehicle{
    
    //variables unique to vans
    //payload stores the weight of the payload in kilograms
    private int payload;
    
    //variable accessor methods
    @Override
    public int getVehicleVar()
    {
        return payload;
    }
    
    @Override
    public int calculateBasicTripCost()
    {
        if (payload <= 600)
        {
            return 500;
        }
        else if (payload <= 800 && payload > 600)
        {
            return 750;
        }
        else
        {
            return 1000;
        }
    }
    
    @Override
    public String getVehicleType()
    {
        return "Van";
    }
    
    //constructor
    public Van(int payload, String regNum, String manufacturer)
    {
        this.payload = payload;
        this.regNum = regNum;
        this.manufacturer = manufacturer;
    }
}
