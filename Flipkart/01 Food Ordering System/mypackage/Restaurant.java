package mypackage;

import java.util.*;

public class Restaurant {
    // A Restaurant can have -> Name, Email, GST, Phone, catalog
    
    private String name;
    private String email;
    private String gst;
    private String phoneNo;
    private Map<String, CatalogItem> catalog;
    private long id;

    Restaurant(String name, String email, String gst, String phoneNo, long id){
        this.name = name;
        this.email = email;
        this.gst = gst;
        this.phoneNo = phoneNo;
        this.catalog = new HashMap<>();
        this.id = id;

        System.out.println("Registerd Restaurant, id - " + id);
    }

    public boolean addItem(String name, double price, int quantity){
        name = name.toLowerCase();
        if(this.catalog.containsKey(name)){
            System.err.println("Item with name " + name + "already exists.");
            return false;
        }
        catalog.put(name, new CatalogItem(name, price, quantity));
        return true;
    }
    
    public CatalogItem getItemFromCatalog(String name){
        CatalogItem item = this.catalog.getOrDefault(name, null);
        return item;
    }

    public void updateItemPrice(String name, double newPrice){
        name = name.toLowerCase();
        CatalogItem obj = this.catalog.getOrDefault(name, null);
        if(obj == null){
            return;
        }
        obj.setPrice(newPrice);
    }
    
    public void updateItemQuantity(String name, int quantity){
        name = name.toLowerCase();
        CatalogItem obj = this.catalog.getOrDefault(name, null);
        if(obj == null){
            return;
        }
        obj.setQuantity(quantity);
    }

    void setName(String name){
        this.name = name;
    }

    void setEmail(String email){
        this.email = email;
    }

    void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

    String getName(){
        return this.name;
    }

    String getEmail(){
        return this.email;
    }

    String getPhoneNo(){
        return this.phoneNo;
    }

    long getId(){
        return this.id;
    }

    boolean searchCatalog(String itemName){
        itemName = itemName.toLowerCase();
        List<CatalogItem> list = new ArrayList<>();
        Set<String> allItems = catalog.keySet();
        for(String x:allItems){
            if(x.contains(itemName)){
                list.add(this.catalog.get(x));
            }
        }

        if(list.size() == 0){
            System.err.println("No item found named - " + itemName);
            return false;
        }

        list.sort((a,b) -> Double.compare(a.getPrice(), b.getPrice()));
        System.err.println("Item \t\t Price \t\t  Quantity");
        for(CatalogItem item:list){
            System.err.println(item.getName() + "\t\t" +  item.getPrice() + "\t\t" + item.getQuantity());
        }

        
        return true;
    }

    boolean itemExists(String itemName, int quantity){
        itemName = itemName.toLowerCase();
        CatalogItem item = this.catalog.getOrDefault(itemName, null);
        if(item == null){
            return false;
        }
        if(item.getQuantity() < quantity){
            return false;
        }
        return true;
    }

    double calculatePrice(String itemName, int quantity){
        if(itemExists(itemName, quantity) == false){
            return -1;
        }
        itemName = itemName.toLowerCase();
        CatalogItem item = this.catalog.getOrDefault(itemName, null);
        if(item == null){
            return -1;
        }
        double ans = quantity * item.getPrice();
        int newStockQuantity = item.getQuantity() - quantity;
        updateItemQuantity(itemName, newStockQuantity);
        return ans;
    }

    int getItemQuantity(String itemName){
        itemName = itemName.toLowerCase();
        CatalogItem item = this.catalog.getOrDefault(itemName, null);
        if(item == null){
            return -1;
        }
        return item.getQuantity();
    }

    double getItemPrice(String itemName){
        itemName = itemName.toLowerCase();
        CatalogItem item = this.catalog.getOrDefault(itemName, null);
        if(item == null){
            return -1;
        }
        return item.getPrice();
    }
}
