package WGU.nball4.IMS.model;

import javafx.beans.property.StringProperty;

/**
 * Created by nicholas on 2/13/2017.
 */
public abstract class Part {


    // Instance Variables
    private String name;
    private int partID;
    private double price;
    private int inStock;
    private int min;
    private int max;
    private static int count = 0;

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID() {
        this.partID = ++count;
    }
}

