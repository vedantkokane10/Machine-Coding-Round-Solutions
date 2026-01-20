package mypackage;


public abstract  class  ParkingPlan{
    protected double basePrice;
    protected double incrementalCharge;
    protected int cappedHours;
    protected double maxPriceForCappedHours;
    protected double baseHours;
    protected double  hours;

    protected double getTotalCharge(int count){
        System.err.println(this.hours);
        double total = this.basePrice;
        if(hours > baseHours){
            total += ((this.hours - this.baseHours) * this.incrementalCharge);
        }

        if(count == 1){
            total = total - (total * 0.1);
        }
        else{
            if(count % 2 != 0){
                total = total - (total * 0.05);
            }
        }

        if(this.hours <= this.cappedHours && total > this.maxPriceForCappedHours){
            total = this.maxPriceForCappedHours;
        }

        return total;
    }
    
}