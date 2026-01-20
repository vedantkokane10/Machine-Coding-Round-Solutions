package mypackage;

public class PremiumPlan extends ParkingPlan{

    public PremiumPlan(double hours){
        this.basePrice = 5;
        this.incrementalCharge = 2;
        this.cappedHours = 8;
        this.maxPriceForCappedHours = 20;
        this.baseHours = 3;
        this.hours = hours;
    }

}
