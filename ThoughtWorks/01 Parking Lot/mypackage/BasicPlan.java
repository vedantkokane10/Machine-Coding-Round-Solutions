package mypackage;

public class BasicPlan extends ParkingPlan{

     public BasicPlan(double hours){
        this.basePrice = 2;
        this.incrementalCharge = 1;
        this.cappedHours = 5;
        this.maxPriceForCappedHours = 10;
        this.baseHours = 2;
        this.hours = hours;
    }

}
