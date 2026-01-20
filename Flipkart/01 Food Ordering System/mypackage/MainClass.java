package mypackage;

import java.util.*;

public class MainClass{
    private boolean addNewRestaurant(Map<String, Restaurant> restaurants, Scanner sc){
        String name = sc.next().toLowerCase();
        if(restaurants.containsKey(name)){
            System.err.println("Restaurant with name " + name + " already exists.");
            return false;
        }
        String gst = sc.next();
        String email = sc.next();
        String phone = sc.next();
        Random ran = new Random();
        Long id = Math.abs(ran.nextLong());
        Restaurant newRestaurant = new Restaurant(name, email, gst, phone, id);
        restaurants.put(name, newRestaurant);
        System.err.println("Restaurant Registered!!! RestaurantId: " + id);
        return true;
    }

    private boolean addNewCustomer(Map<Long, Customer> customers, Scanner sc){
        String name = sc.next().toLowerCase();
        String email = sc.next();
        String phone = sc.next();
        Random ran = new Random();
        Long id = Math.abs(ran.nextLong());
        Customer newCustomer = new Customer(name, email, phone, id);
        customers.put(id, newCustomer);
        System.err.println("User Registered!!! UserId: " + id);
        return true;
    }

    private boolean addNewItem(Map<String, Restaurant> restaurants, Scanner sc){
        String restaurantName = sc.next().toLowerCase();
        if(!restaurants.containsKey(restaurantName)){
            System.err.println("Restaurant with name " + restaurantName + " does not exists.");
            return false;
        }
        String itemName = sc.next().toLowerCase();
        double price = sc.nextDouble();
        int quantity = sc.nextInt();

        itemName = itemName.replace('_', ' ');
        Restaurant restaurant = restaurants.get(restaurantName);
        boolean result = restaurant.addItem(itemName, price, quantity);
        if(!result){
            System.out.println(itemName + " already exists try updating price or quantity.");
            return false;
        }
        return true;
    }

    private boolean searchItem(Map<String, Restaurant> restaurants, Scanner sc){
        String restaurantName = sc.next().toLowerCase();
        String itemName = sc.next().toLowerCase();
        Restaurant restaurant = restaurants.getOrDefault(restaurantName, null);
        if(restaurant == null){
            System.out.println("No Restaurant found with name - " + restaurantName);
            return false;
        }
        
        if(!restaurant.searchCatalog(itemName)){
            System.err.println("Search for a different item");
            return false;
        }

        return true;
    }

    boolean placeOrder(Map<String, Restaurant> restaurants, Map<Long, Customer> customers, Map<Long, OrderDetails> orders, Scanner sc){
        Long userId = sc.nextLong();
        String restaurantName = sc.next().toLowerCase();
        String itemName = sc.next().toLowerCase();
        int quantity = sc.nextInt();
        itemName = itemName.toLowerCase();
        if(customers.getOrDefault(userId, null) == null){
            System.out.println("Invalid user id");
            return false;
        }
        if(restaurants.getOrDefault(restaurantName, null) == null){
            System.out.println("Invalid Restaurant");
            return false;
        }
        Customer customer = customers.get(userId);
        Restaurant restaurant = restaurants.get(restaurantName);

        if(restaurant.itemExists(itemName, quantity) == false){
            System.out.println("Item with name " + itemName + " does not exists");
            return false;
        }

        double totalPrice = restaurant.calculatePrice(itemName, quantity);
        if(totalPrice == -1.0){
            System.out.println("Insufficient " + itemName + " quantity, Sorry!");
            return false;
        }
        Random ran = new Random();
        Long orderId = (Math.abs(ran.nextLong()));

        OrderDetails order = new OrderDetails(orderId, customer, restaurant, itemName, totalPrice, quantity);
        orders.put(orderId, order);
        System.err.println("Order placed successfully orderId: " + orderId);
        return true;
    }

    boolean cancelOrder(Map<Long, OrderDetails> orders, Scanner sc){
        Long orderId = sc.nextLong();
        OrderDetails order = orders.getOrDefault(orderId, null);
        if(order == null){
            System.out.println("No orders found with id: " + orderId);
            return false;
        }
        if(order.cancelOrder() == false){
            System.out.println("Cannot cancel order with id:" + orderId + " as it is already completed");
            return false;
        }

        System.out.println("Order with id: " + orderId + " cancelled successfully");
        return true;
    }

    boolean getOrderDetails(Map<Long, OrderDetails> orders, Scanner sc){
        Long orderId = sc.nextLong();
        OrderDetails order = orders.getOrDefault(orderId, null);
        if(order == null){
            System.out.println("No orders found with id: " + orderId);
            return false;
        }

        order.getOrderDetails();
        return true;
    }

    boolean getOrderDetailsForUser(Map<Long, OrderDetails> orders, Scanner sc){
        Long userId = sc.nextLong();
        List<OrderDetails> temp = new ArrayList<>();
        Set<Long> orderIds = orders.keySet();
        for(Long x:orderIds){
            OrderDetails order = orders.get(x);
            if(order.getUserId().equals(userId)){
                temp.add(order);
            }
        }
        
        if(temp.size() == 0){
            System.out.println("No orders found for user with id - " + userId);
            return false;
        }

        for(OrderDetails x:temp){
            x.getOrderDetails();
        }

        return true;
    }

    void printRestaurants(Map<String, Restaurant> restaurants){
        Set<String> s1 = restaurants.keySet();
        for(String x:s1){
            System.err.println(restaurants.get(x).getName());
        }
    }

    boolean orderCompleted(Map<Long, OrderDetails> orders,Scanner sc){
        Long orderId = sc.nextLong();
        OrderDetails order = orders.getOrDefault(orderId, null);
        if(order == null){
            System.out.println("Invalid id no order found");
            return false;
        }
        boolean currentStatus = order.getCurrentOrderStatus();
        if(currentStatus == true){
            System.err.println("Order with id: " + orderId + " already completed");
            return false;
        }
        boolean completeOrder = order.completeOrder();
        if(completeOrder == true){
            System.out.println("Order with id: " + orderId + " completed successfully");
        }
        return true;    
    }

    boolean updateItem(Map<String, Restaurant> restaurants, Scanner sc){
        String restaurantName = sc.next().toLowerCase();
        String itemName = sc.next().toLowerCase();
        Restaurant restaurant = restaurants.getOrDefault(restaurantName, null);
        if(restaurant == null){
            System.out.println("Invalid Restaurant");
            return false;
        }

        if(restaurant.itemExists(itemName, -1) == false){
            System.out.println("Item does not exists");
        } 

        System.out.println("Enter 1 to update item quanity or 2 to update item's price");
        int choice = sc.nextInt();
        if(choice == 1){
            System.out.println("Item's current quantity is - " + restaurant.getItemQuantity(itemName) + " enter new quantity");
            int newQuantity = sc.nextInt();
            restaurant.updateItemQuantity(itemName, newQuantity);
            System.out.println("Updated item's quantity successfully.");
        }
        else if(choice == 2){
            System.out.println("Item's current price is - " + restaurant.getItemPrice(itemName) + " enter new price");
            double newPrice = sc.nextDouble();
            restaurant.updateItemPrice(itemName, newPrice);
            System.out.println("Updated item's price successfully.");
        }
        else{
            System.out.println("wrong choice entered");
            return false;
        }
        return true;
    }

    private void Menu(boolean  exit, Map<String, Restaurant> restaurants, Map<Long, Customer> customers, Map<Long, OrderDetails> orders, Scanner sc ){
        while(exit){
            System.out.println("------------------------- Welcome -------------------------");
            System.out.println("1. Register Restaurant");
            System.out.println("2. Register Customer");
            System.out.println("3. Add Item to Catalog of restaurant");
            System.out.println("4. Search item in restaurant");
            System.out.println("5. Place order");
            System.out.println("6. Cancel order");
            System.out.println("7. Get Order details");
            System.out.println("8. Get all Order details");
            System.out.println("9. Update item of a Catalog");
            System.out.println("10. Completed Order");
            System.out.println("0. Exit");
            
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:
                    this.addNewRestaurant(restaurants, sc);
                    break;
                case 2:
                    this.addNewCustomer(customers, sc);
                    break;
                case 3:
                    addNewItem(restaurants, sc);
                    break;
                case 4:
                    searchItem(restaurants, sc);
                    break;
                case 5:
                    placeOrder(restaurants, customers, orders, sc);
                    break;
                case 6:
                    cancelOrder(orders, sc);
                    break;
                case 7:
                    getOrderDetails(orders, sc);
                    break;
                case 8:
                    getOrderDetailsForUser(orders, sc);
                    break;
                case 9:
                    updateItem(restaurants, sc);
                    break;
                case 10:
                    orderCompleted(orders, sc);
                    break;
                case 0:
                    exit = false;
                    break;
                
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        boolean exit = true;

        Map<String, Restaurant> restaurants = new HashMap<>();
        Map<Long, Customer> customers = new HashMap<>();
        Map<Long, OrderDetails> orders = new HashMap<>();
        Scanner sc = new Scanner(System.in);    

        MainClass obj = new MainClass();
        obj.Menu(exit, restaurants, customers, orders, sc);
        

        
    }
}