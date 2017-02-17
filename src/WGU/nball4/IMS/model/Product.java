package WGU.nball4.IMS.model;

import WGU.nball4.IMS.MainApp;

import java.util.ArrayList;

/**
 * Created by nicholas on 2/13/2017.
 */
public class Product {


    // Instance Variables
    private ArrayList<Part> parts;
    private String name;
    private int productID;
    private double price;
    private int inStock;
    private int min;
    private int max;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;}

    //Getters and Setters
    public ArrayList<Part> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    //Constructors

    public Product() {
    }

    public Product(ArrayList<Part> parts, String name, int productID, double price, int inStock, int min, int max) {
        this.parts = parts;
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    public void addPart(Part part){

        this.parts.add(part);

    }

    public boolean removePart(int int1) {
        try {
            this.parts.remove(int1);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to remove part");
            return false;
        }
    }

    public Part lookupPart(int int1){

        return this.parts.get(int1);
    }

    public void updatePart(int int1){

        int count = 0;
        for (Part part: parts){
            if (part.getPartID() == int1){
                for (Part part1 : this.mainApp.getPartData()){
                    if (part1.getPartID() == int1) {
                        parts.set(count, part1);
                    }
                }
            }
            count++;

        }

    }
}
