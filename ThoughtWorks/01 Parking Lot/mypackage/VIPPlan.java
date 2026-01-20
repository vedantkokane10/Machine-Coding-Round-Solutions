package mypackage;

public class VIPPlan extends ParkingPlan{

    public VIPPlan(double hours){
        this.basePrice = 10;
        this.incrementalCharge = 3;
        this.cappedHours = 12;
        this.maxPriceForCappedHours = 30;
        this.baseHours = 4;
        this.hours = hours;
    }

    
}
