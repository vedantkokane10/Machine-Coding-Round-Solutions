package mypackage;

public class OrderDetails {
    private Customer user;
    private Restaurant restaurant;
    private boolean completed;
    private boolean cancelled;
    private String itemName;
    private double totalPrice;
    private int quantity;
    private long id;

    public OrderDetails(long id, Customer user, Restaurant restaurant, String itemName, double totalPrice, int quantity) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.completed = false;
        this.cancelled = false;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    void getOrderDetails(){
        String details = "Order ID - " + this.id + "\n";
        details += "Customer Name - " + this.user.getName() + "\n";
        details += "Customer Phone - " + this.user.getPhone() + "\n";
        details += "Customer Email - " + this.user.getEmail() + "\n";
        details += "Restaurant Name - " + this.restaurant.getName() + "\n";
        details += "Restaurant Phone - " + this.restaurant.getPhoneNo() + "\n";
        details += "Item - " + this.itemName + "\n";
        details += "Quantity - " + this.quantity + "\n";
        details += "Total Price - " + this.totalPrice + "\n";
        String status = this.completed == true ? "Completed" : "Preparing";
        if(this.cancelled){
            status = "Cancelled";
        }
        details += "Status - " + status + "\n";
        System.err.println(details);
    }
    
    boolean cancelOrder(){
        if(this.completed == true){
            return false;
        }
        int currentQuantity = this.restaurant.getItemQuantity(this.itemName);
        this.restaurant.updateItemQuantity(this.itemName, currentQuantity + this.quantity);
        this.completed = false;
        this.cancelled = true;
        return true;
    }

    Long getUserId(){
        return this.user.getId();
    }

    boolean getCurrentOrderStatus(){
        return this.completed;
    }

    boolean completeOrder(){
        if(this.cancelled == true){
            System.out.println("Order cannot be completed as it is already cancelled");
            return false;
        }
        this.completed = true;
        this.cancelled = false;
        return true;
    }
}
