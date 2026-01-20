package mypackage;

import java.util.*;


public class ParkingLot {
    Map<Integer, Customer> mp = new HashMap<>(); // customer-mapping
    Map<Integer, String> plans = new HashMap<>(); //plan-mapping

    private int customerCountForGeneratingId;

    public ParkingLot() {
        plans.put(1, "Basic");
        plans.put(2, "Premium");
        plans.put(3, "VIP");
    }

    public int createCustomer(){
        try {
            int id = this.customerCountForGeneratingId;
            this.customerCountForGeneratingId = this.customerCountForGeneratingId  + 1;
            this.mp.put(id, new Customer(id));
            
            System.err.println("Successfully created a customer id:" + id);
            return id;
        } 
        catch (Exception e) {
            System.err.println("Error while creating customer " + e.getMessage());
            return -1;
        }
    }


    public boolean addPlan(int customerId, int plan, double hours){
        try {
            if(this.mp.containsKey(customerId) == false){
                System.err.println("Customer with id:" + customerId + " does not exists.");
                return false;
            }
            
            switch (plan) {
                case 1:
                    BasicPlan newBasicPlan = new BasicPlan(hours);
                    System.err.println(newBasicPlan.hours);
                    this.mp.get(customerId).updatePlanType(newBasicPlan);
                    break;
                case 2:
                    PremiumPlan newPremiumPlan = new PremiumPlan(hours);
                    this.mp.get(customerId).updatePlanType(newPremiumPlan);
                    break;
                case 3:
                    VIPPlan newVIPPlan = new VIPPlan(hours);
                    this.mp.get(customerId).updatePlanType(newVIPPlan);
                    break;
                default:
                    System.err.println("Please select a valid plan (Basic:1, Premium:2, VIP:3)");
                    return false;
            }
            this.mp.get(customerId).updateBookingCount();
            System.err.println("Successfully added " + plans.get(plan) + " plan for customer id: " + customerId);
            return true;
        } 
        catch (Exception e) {
            System.err.println("Error while adding plan for customer id: " + customerId);
            return false;
        }
    }


    public boolean removePlan(int customerId){
        try {
            if(this.mp.containsKey(customerId) == false){
                System.err.println("Customer with id:" + customerId + " does not exists.");
                return false;
            }
            
            int plan = 1;

            if(this.mp.get(customerId).getPlanType() == null){
                System.err.println("Customer with id:" + customerId + " currently does not have any plans.");
                return false;
            }
            else{
                if(this.mp.get(customerId).getPlanType() instanceof BasicPlan){
                    plan = 1;
                }
                else if(this.mp.get(customerId).getPlanType() instanceof PremiumPlan){
                    plan = 2;
                }
                else if(this.mp.get(customerId).getPlanType() instanceof VIPPlan){
                    plan = 3;
                }

                mp.get(customerId).removePlanType();
                
            }

            System.err.println("Successfully removed " + plans.get(plan) + " for customer id: " + customerId);
            return true;
        } 
        catch (Exception e) {
            System.err.println("Error while removing plan for customer id: " + customerId);
            return false;
        }
    }


    public double getTotalCost(int customerId){
        try {
            if(this.mp.containsKey(customerId) == false){
                System.err.println("Customer with id:" + customerId + " does not exists.");
                return 0.0;
            }

            Customer c1 = mp.get(customerId);


            double totalCost = c1.getTotalCharge();

            System.err.println("Total cost for customer id: " + customerId + " is - $" + totalCost);
            return totalCost;
        } 
        catch (Exception e) {
            System.err.println("Error while calculating total cost for customer id: " + customerId);
            return 0.0;
        }
    }
}
