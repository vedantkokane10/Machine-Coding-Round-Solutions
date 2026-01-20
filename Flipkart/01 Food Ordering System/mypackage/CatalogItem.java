package mypackage;

import java.util.*;


public class CatalogItem {
    private String name;
    private double price;
    private int quantity;

    CatalogItem(String name, double price, int quantity){
        this.name = name.toLowerCase();
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }


}
