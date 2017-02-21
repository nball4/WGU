package WGU.nball4.IMS.model;

import WGU.nball4.IMS.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by nicholas on 2/13/2017.
 */
public class Product {


    // Instance Variables
    private ObservableList<Part> parts = FXCollections.observableArrayList();
    private String name;
    private int productID;
    private double price;
    private int inStock;
    private int min;
    private int max;
    private static int count = 0;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;}

    //Getters and Setters
    public ObservableList<Part> getParts() {
        return parts;
    }

    public void setParts(ObservableList<Part> parts) {
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

    public void setProductID() {

            this.productID = ++count;

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

    public Product(Part part, String name, double price, int inStock, int min, int max) {
        addPart(part);
        this.name = name;
        setProductID();
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
