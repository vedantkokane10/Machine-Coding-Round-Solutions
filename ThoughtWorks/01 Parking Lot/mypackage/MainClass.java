package mypackage;

import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        List<Integer> customerIds = new ArrayList<>();
        
        System.out.println("=== PARKING LOT MANAGEMENT SYSTEM ===\n");
        
        
        // Test Case 1: Customer Creation
        System.out.println("--- Test Case 1: Creating Customers ---");
        int customer1 = parkingLot.createCustomer();
        customerIds.add(customer1);
        
        int customer2 = parkingLot.createCustomer();
        customerIds.add(customer2);
        
        int customer3 = parkingLot.createCustomer();
        customerIds.add(customer3);
        System.out.println();
        
        
        // Test Case 2: Basic Plan Testing
        System.out.println("--- Test Case 2: Basic Plan (Customer 0) ---");
        System.out.println("Adding Basic Plan with 6 hours");
        parkingLot.addPlan(customerIds.get(0), 1, 6);
        parkingLot.getTotalCost(customerIds.get(0));
        System.out.println("Expected: Base $2 + (6-2)*$1 = $6, with 10% first booking discount = $5.40");
        System.out.println();
        
        
        // Test Case 3: Premium Plan Testing
        System.out.println("--- Test Case 3: Premium Plan (Customer 1) ---");
        System.out.println("Adding Premium Plan with 10 hours");
        parkingLot.addPlan(customerIds.get(1), 2, 10);
        parkingLot.getTotalCost(customerIds.get(1));
        System.out.println("Expected: Base $5 + (10-3)*$2 = $19, with 10% first booking discount = $17.10");
        System.out.println();
        
        
        // Test Case 4: VIP Plan Testing
        System.out.println("--- Test Case 4: VIP Plan (Customer 2) ---");
        System.out.println("Adding VIP Plan with 8 hours");
        parkingLot.addPlan(customerIds.get(2), 3, 8);
        parkingLot.getTotalCost(customerIds.get(2));
        System.out.println("Expected: Base $10 + (8-4)*$3 = $22, with 10% first booking discount = $19.80");
        System.out.println();
        
        
        // Test Case 5: Capped Hours Testing (Basic Plan)
        System.out.println("--- Test Case 5: Capped Hours Test (Basic Plan) ---");
        int customer4 = parkingLot.createCustomer();
        System.out.println("Adding Basic Plan with 5 hours (at cap)");
        parkingLot.addPlan(customer4, 1, 5);
        parkingLot.getTotalCost(customer4);
        System.out.println("Expected: Base $2 + (5-2)*$1 = $5, capped at $10, with 10% discount = $4.50");
        System.out.println();
        
        
        // Test Case 6: Multiple Bookings (Odd Count Discount)
        System.out.println("--- Test Case 6: Multiple Bookings Test ---");
        System.out.println("Customer 0 - Second Booking (Even, no discount)");
        parkingLot.removePlan(customerIds.get(0));
        parkingLot.addPlan(customerIds.get(0), 1, 4);
        parkingLot.getTotalCost(customerIds.get(0));
        System.out.println("Expected: Base $2 + (4-2)*$1 = $4, no discount (even count)");
        System.out.println();
        
        System.out.println("Customer 0 - Third Booking (Odd, 5% discount)");
        parkingLot.removePlan(customerIds.get(0));
        parkingLot.addPlan(customerIds.get(0), 1, 6);
        parkingLot.getTotalCost(customerIds.get(0));
        System.out.println("Expected: Base $2 + (6-2)*$1 = $6, with 5% odd booking discount = $5.70");
        System.out.println();
        
        
        // Test Case 7: Remove Plan Testing
        System.out.println("--- Test Case 7: Remove Plan Test ---");
        parkingLot.removePlan(customerIds.get(1));
        System.out.println();
        
        
        // Test Case 8: Error Handling - Invalid Customer
        System.out.println("--- Test Case 8: Invalid Customer ID ---");
        parkingLot.addPlan(9999, 1, 5);
        parkingLot.getTotalCost(9999);
        parkingLot.removePlan(9999);
        System.out.println();
        
        
        // Test Case 9: Error Handling - Invalid Plan
        System.out.println("--- Test Case 9: Invalid Plan Type ---");
        parkingLot.addPlan(customerIds.get(1), 5, 3);
        System.out.println();
        
        
        // Test Case 10: Remove Non-existent Plan
        System.out.println("--- Test Case 10: Remove Non-existent Plan ---");
        parkingLot.removePlan(customerIds.get(1));
        System.out.println();
        
        
        // Test Case 11: Premium Plan Capping
        System.out.println("--- Test Case 11: Premium Plan Capping Test ---");
        int customer5 = parkingLot.createCustomer();
        System.out.println("Adding Premium Plan with 8 hours (at cap)");
        parkingLot.addPlan(customer5, 2, 8);
        parkingLot.getTotalCost(customer5);
        System.out.println("Expected: Base $5 + (8-3)*$2 = $15, capped at $20, with 10% discount = $13.50");
        System.out.println();
        
        
        // Test Case 12: VIP Plan Exceeding Cap
        System.out.println("--- Test Case 12: VIP Plan Exceeding Cap ---");
        int customer6 = parkingLot.createCustomer();
        System.out.println("Adding VIP Plan with 15 hours (exceeds cap of 12)");
        parkingLot.addPlan(customer6, 3, 15);
        parkingLot.getTotalCost(customer6);
        System.out.println("Expected: Base $10 + (15-4)*$3 = $43, exceeds cap, with 10% discount = $38.70");
        System.out.println();
        
        
        // Test Case 13: Minimum Hours (Base Price Only)
        System.out.println("--- Test Case 13: Minimum Hours Test ---");
        int customer7 = parkingLot.createCustomer();
        System.out.println("Adding Basic Plan with 2 hours (base only)");
        parkingLot.addPlan(customer7, 1, 2);
        parkingLot.getTotalCost(customer7);
        System.out.println("Expected: Base $2 only, with 10% discount = $1.80");
        System.out.println();
        
        
        // Test Case 14: Plan Switching
        System.out.println("--- Test Case 14: Plan Switching Test ---");
        System.out.println("Customer 2 - Switching from VIP to Basic");
        parkingLot.removePlan(customerIds.get(2));
        parkingLot.addPlan(customerIds.get(2), 1, 3);
        parkingLot.getTotalCost(customerIds.get(2));
        System.out.println("Expected: Base $2 + (3-2)*$1 = $3, even booking (no discount) = $3.00");
        System.out.println();
        
        System.out.println("=== ALL TEST CASES COMPLETED ===");
    }
}

// javac mypackage/*.java