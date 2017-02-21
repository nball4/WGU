package WGU.nball4.IMS.model;

import WGU.nball4.IMS.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by nicholas on 2/16/2017.
 */
public class Inventory {

    // Instance Variables
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private MainApp mainApp;


    //Getters and setters
    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }


    //constructor
    public Inventory() {
    }


    //other methods

    //Add product to products list
    public void addProduct(Product product){

        this.products.add(product);
    }

    // used methods in ProductEditDialogController for this
    public boolean removeProduct(int int1){

        try {
            this.products.remove(int1);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to remove part");
            return false;
        }
    }

    // used methods in ProductEditDialogController for this
    public Product lookupProduct(int int1){

        return this.products.get(int1);

    }

    // used methods in ProductEditDialogController for this
    public void updatePart(int int1){


    }

}
