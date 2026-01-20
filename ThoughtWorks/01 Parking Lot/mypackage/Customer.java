package mypackage;

public class Customer {
    protected int id;
    protected ParkingPlan planType; 
    protected int bookingCount;

    public Customer(int id) {
        this.id = id;
        this.bookingCount = 0;
        this.planType = null;
    }

    protected void updateBookingCount(){
        this.bookingCount++;
    }

    protected void removePlanType(){
        this.planType = null;
    }


    protected void updatePlanType(ParkingPlan newPlanType){
        System.out.println("Plan added - " + newPlanType.hours);
        this.planType = newPlanType;
    }
    
    public int getBookingCount(){
        return this.bookingCount;
    }

    public int getId(){
        return this.id;
    }

    public ParkingPlan getPlanType(){
        return this.planType;
    }


    public double getTotalCharge(){
        
        return this.planType.getTotalCharge(this.bookingCount);
    }
}
