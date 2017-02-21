package WGU.nball4.IMS.view;


import WGU.nball4.IMS.model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import WGU.nball4.IMS.MainApp;
import WGU.nball4.IMS.model.Part;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.Optional;


public class InventoryOverviewController {

    //Variables from fxml
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, String> nameColumn;
    @FXML private TableColumn<Part, String> partIDColumn;
    @FXML private TableColumn<Part, String> inventoryColumn;
    @FXML private TableColumn<Part, String> priceColumn;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> productnameColumn;
    @FXML private TableColumn<Product, String> productIDColumn;
    @FXML private TableColumn<Product, String> productinventoryColumn;
    @FXML private TableColumn<Product, String> productpriceColumn;
    @FXML private TextField imsMainPartsSearchField;
    @FXML private TextField imsMainProductSearchField;

    // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;

    //called by the main application to give a reference back to itself
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        // Add observable list data to the table sorted by name alphabetically

        partTable.setItems(mainApp.getPartData().sorted(Comparator.comparing(Part::getName)));
        productTable.setItems(mainApp.getInventory().getProducts().sorted(Comparator.comparing(Product::getName)));

    }
    //this sets the dialog page

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    //constructor
    public InventoryOverviewController() {
    }

    //Initializes the controller class - automatically called
    @FXML private void initialize() {
        // Initialize the parts table with the columns.
        partIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getPartID())));
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        inventoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getInStock())));
        priceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Double.toString(cellData.getValue().getPrice())));

        // Initialize the products table with the columns
        productIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getProductID())));
        productnameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        productinventoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getInStock())));
        productpriceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Double.toString(cellData.getValue().getPrice())));

    }

    //handles the delete button for the parts section of the main window. asks for confirmation before delete
    @FXML private void handleDeletePart() {
        int selectedIndex = partTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Cancel Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("You will lose any progress if you select OK!");

            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK){

                mainApp.getPartData().remove(selectedIndex);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.initOwner(dialogStage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
    }

    // handles the new part button and calls the new part method from mainapp
    @FXML private void handleNewPart () {

      mainApp.showPartNewDialog();

    }

    // handles the edit part button and calls the edit part method from mainapp
    @FXML private void handleEditPart() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            mainApp.showPartEditDialog(selectedPart);



        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
    }

    //handles the delete button for the products section of the main window. asks for confirmation before delete
    @FXML private void handleDeleteProduct() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (productTable.getSelectionModel().getSelectedItem().getParts().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(dialogStage);
                alert.setTitle("Cancel Confirmation");
                alert.setHeaderText("Are you sure?");
                alert.setContentText("You will lose any progress if you select OK!");

                Optional<ButtonType> results = alert.showAndWait();
                if (results.get() == ButtonType.OK){

                    productTable.getItems().remove(selectedIndex);
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Warning");
                alert.setHeaderText("Product has parts associated");
                alert.setContentText("You cannot delete a product that has parts associated with it.");

                alert.showAndWait();
            }


        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }
    }

    // handles the new product button and calls the new EDIT product method from mainapp
    @FXML private void handleNewProduct() {

        Product tempProduct = new Product();
        boolean okClicked =  mainApp.showProductEditDialog((tempProduct));
        if (okClicked) {
            mainApp.getInventory().addProduct(tempProduct);
        }



    }

    // handles the edit product button and calls the EDIT product method from mainapp
    @FXML private void handleEditProduct() {

        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            mainApp.showProductEditDialog(selectedProduct);


        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }

    }

    // handles the PARTS search button and pops up dialog if none are found or more than one result for the same was returned. highlights found results
    @FXML private void handleSearchButton() {

        int counter = 0;
        Part tempPart;

        for (Part part : partTable.getItems()) {
            if (part.getName().equals(imsMainPartsSearchField.getText())) {
                counter++;
            }
        }

        if (counter > 1) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Part Found");
            alert.setHeaderText("More than one Part was found");
            alert.setContentText("Please select the correct Part then complete edit or delete action");
            alert.showAndWait();

            for (Part part1 : partTable.getItems()) {
                if (part1.getName().equals(imsMainPartsSearchField.getText())) {

                    partTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    partTable.getSelectionModel().select(part1);
                    partTable.scrollTo(partTable.getItems().indexOf(part1)-counter+1);


                }


            }

        }
        if (counter == 1) {
            for (Part part1 : partTable.getItems()) {
                if (part1.getName().equals(imsMainPartsSearchField.getText())) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Part Found");
                    alert1.setHeaderText("Part Found!");
                    alert1.setContentText("Would you like to Edit the part?");
                    Optional<ButtonType> results1 = alert1.showAndWait();

                    if (results1.get() == ButtonType.OK) {

                        mainApp.showPartEditDialog(part1);

                    }

                }
            }
        }
        if (counter == 0) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Part NOT Found");
            alert2.setHeaderText("Part was NOT found!");
            alert2.setContentText("Please try searching again.");
            alert2.showAndWait();


        }
    }

    // handles the PRODUCTS search button and pops up dialog if none are found or more than one result for the same was returned. highlights found results
    @FXML private void handleSearchProducts() {

        int counter = 0;

        for (Product product : productTable.getItems()) {
            if (product.getName().equals(imsMainProductSearchField.getText())) {
                counter++;
            }
        }

        if (counter > 1) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Product Found");
            alert.setHeaderText("More than one Product by the same name was found");
            alert.setContentText("Please select the correct Product then complete edit or delete action");
            alert.showAndWait();

            for (Product product : productTable.getItems()) {
                if (product.getName().equals(imsMainProductSearchField.getText())) {

                    productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    productTable.getSelectionModel().select(product);
                    productTable.scrollTo(productTable.getItems().indexOf(product)-counter+1);


                }


            }

        }
        if (counter == 1) {
            for (Product product : productTable.getItems()) {
                if (product.getName().equals(imsMainProductSearchField.getText())) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Product Found");
                    alert1.setHeaderText("Product Found!");
                    alert1.setContentText("Would you like to Edit the product?");
                    Optional<ButtonType> results1 = alert1.showAndWait();

                    if (results1.get() == ButtonType.OK) {

                        mainApp.showProductEditDialog(product);

                    }

                }
            }
        }
        if (counter == 0) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Product NOT Found");
            alert2.setHeaderText("Product was NOT found!");
            alert2.setContentText("Please try searching again.");
            alert2.showAndWait();


        }
    }

    //calls to close the stage.
    @FXML private void handleExit(){
        mainApp.getPrimaryStage().close();
    }


    }

