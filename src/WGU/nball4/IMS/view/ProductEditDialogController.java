package WGU.nball4.IMS.view;


import WGU.nball4.IMS.MainApp;
import WGU.nball4.IMS.model.Part;
import WGU.nball4.IMS.model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class ProductEditDialogController {

    //Variables from fxml
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, String> productnameColumn;
    @FXML private TableColumn<Part, String> productIDColumn;
    @FXML private TableColumn<Part, String> productinventoryColumn;
    @FXML private TableColumn<Part, String> productpriceColumn;
    @FXML private TableView<Part> currentPartsTable;
    @FXML private TableColumn<Part, String> productsnameColumn;
    @FXML private TableColumn<Part, String> productspartIDColumn;
    @FXML private TableColumn<Part, String> productsinventoryColumn;
    @FXML private TableColumn<Part, String> productspriceColumn;
    @FXML private TextField addProductIDTextField;
    @FXML private TextField addProductNameTextField;
    @FXML private TextField addProductPriceTextField;
    @FXML private TextField addProductInventoryTextField;
    @FXML private TextField addProductMaxTextField;
    @FXML private TextField addProductMinTextField;
    @FXML private Label addProductLabel;
    @FXML private TextField productsAllPartsSearchTextField;


    //Variables
    private Stage dialogStage;
    private boolean okClicked = false;
    private MainApp mainApp;
    private Product product;


    //Initializes the controller class - automatically called
    @FXML private void initialize() {
        productIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getPartID())));
        productnameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        productinventoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getInStock())));
        productpriceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Double.toString(cellData.getValue().getPrice())));

        productspartIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getPartID())));
        productsnameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        productsinventoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getInStock())));
        productspriceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Double.toString(cellData.getValue().getPrice())));

    }

    public ProductEditDialogController(){}

    //Handlers

    // attempts to save a product either new or edited after validation
    // then adds the new part to the inventory product list. Closes window
    @FXML private void handleOk() {

        try {
            if (isInputValid()) {
                product.setName(addProductNameTextField.getText());
                product.setPrice(Double.parseDouble(addProductPriceTextField.getText()));
                product.setInStock(Integer.parseInt(addProductInventoryTextField.getText()));
                product.setMax(Integer.parseInt(addProductMaxTextField.getText()));
                product.setMin(Integer.parseInt(addProductMinTextField.getText()));
                product.setProductID();
                product.setParts(currentPartsTable.getItems());
                okClicked = true;


                dialogStage.close();
            }
        }catch (Exception e){
              /*
            isInputValid();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Whoops");
            alert.setHeaderText("It looks like something broke :(");
            alert.setContentText("I did not handel this exception. Sorry");
            alert.showAndWait();
            */
        }
    }

    // adds the select part from the all partstable to the current products parts table
    @FXML private void handleAddPart() {

        try {
            if (allPartsTable.getSelectionModel().getSelectedItem() == null) {

                throw new Exception();
            } else {
                Part tempPart;
                tempPart = allPartsTable.getSelectionModel().getSelectedItem();
                currentPartsTable.getItems().add(tempPart);
            }
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("No Selection");
            alert.setHeaderText("Please select a Part");
            alert.setContentText("Please select a part before selecting add");

            alert.showAndWait();
        }
    }

    // deleted the current select part from the current products parts table
    @FXML private void handleDeletePart(){


        int selectedIndex = currentPartsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("You will remove the part from the product!");

            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK){

                currentPartsTable.getItems().remove(currentPartsTable.getSelectionModel().getSelectedItem());
            }

        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
    }

    //confirms cancellation with alert then closes the window
    @FXML private void handleCancel(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You will lose any progress if you select OK!");

        Optional<ButtonType> results = alert.showAndWait();
        if (results.get() == ButtonType.OK) {

            dialogStage.close();
        }
    }

    //returns ok clicked status back to the main app that made the part edit dialog window
    public boolean isOkClicked() {
        return okClicked;
    }

    // listens to the most of the TextFields and links to the calls that resets it
    @FXML private void handleOnClicked(MouseEvent e){

        changeTextFieldTextBack();

    }

    // After the validation checks this is called to reset the border around the textFields if they are selected
    @FXML private void changeTextFieldTextBack(){

        if (addProductInventoryTextField.isFocused())
            addProductInventoryTextField.setStyle("");
        if (addProductMinTextField.isFocused())
            addProductMinTextField.setStyle("");
        if (addProductPriceTextField.isFocused())
            addProductPriceTextField.setStyle("");
        if (addProductMaxTextField.isFocused())
            addProductMaxTextField.setStyle("");
        if (addProductNameTextField.isFocused())
            addProductNameTextField.setStyle("");



    }

    // different tests for validation of data
    private boolean isInputValid() {
        int partsPriceTotal = 0;
        String errorMessage = "";

        //create new array list to parseInt for NFE
        ArrayList<TextField> tempTFAL = new ArrayList<>();
        tempTFAL.add(addProductInventoryTextField);
        tempTFAL.add(addProductMaxTextField );
        tempTFAL.add(addProductMinTextField);

        // Check Inv, min, Max and price for ability to be parsed
        for (TextField tf: tempTFAL){
            try{
                Integer.parseInt(tf.getText());
            }catch (NumberFormatException nfe){
                errorMessage += "Not a valid input for: "+tf.getPromptText();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(errorMessage);

                alert.showAndWait();
                tf.setStyle("-fx-border-color:red;");


            }
        }
        try{
            Double.parseDouble(addProductPriceTextField.getText());
        }catch (NumberFormatException nfe){
            errorMessage += "Not a valid input for: Price" ;
            addProductPriceTextField.setStyle("-fx-border-color:red;");

        }

        //check if any fields are empty
        if (addProductNameTextField.getText() == null || addProductNameTextField.getText().length() == 0) {
            errorMessage += "No valid Product name!\n";
        }
        if (addProductInventoryTextField.getText() == null || addProductInventoryTextField.getText().length() == 0) {
            errorMessage += "No valid Product inventory amount!\n";
        }
        if (addProductPriceTextField.getText() == null || addProductPriceTextField.getText().length() == 0) {
            errorMessage += "No valid Product price!\n";
        }
        if (addProductMinTextField.getText() == null || addProductMinTextField.getText().length() == 0) {
            errorMessage += "No valid Minimum inventory!\n";
        }
        if (addProductMaxTextField.getText() == null || addProductMaxTextField.getText().length() == 0) {
            errorMessage += "No valid Maximum inventory!\n";
        }
        if (currentPartsTable.getItems().isEmpty()){
            errorMessage += "No Part added to product!\n";
        }


        //Check if price of product is lower then any individual part
        for(Part part : currentPartsTable.getItems()){
            if (Double.parseDouble(addProductPriceTextField.getText()) < part.getPrice()){
                errorMessage += "Product Price lower than an individual part!\n";

            }
        }
        //Check if price of product is lower then all parts added together
        for(Part part : currentPartsTable.getItems()){
            partsPriceTotal += part.getPrice();
            System.out.println(partsPriceTotal);
        }
        if (Integer.parseInt(addProductInventoryTextField.getText()) < partsPriceTotal){
            errorMessage += "Product Price is lower than the total price of all parts.!\n";
        }

        //check if Max field is populated and if it is lower than the Min field
        if (addProductMaxTextField.getText() == null || addProductMaxTextField.getText().length() == 0) {
            errorMessage += "Maximum inventory is not set!\n";
            addProductMaxTextField.setStyle("-fx-border-color:red;");

        } else if (Integer.parseInt(addProductMaxTextField.getText()) < Integer.parseInt(addProductMinTextField.getText())) {
            errorMessage += "Max inventory value cannot be lower than Minimum!\n";
            addProductMaxTextField.setStyle("-fx-border-color:red;");
            addProductMinTextField.setStyle("-fx-border-color:red;");
        }

        //check if max and inventory fields are populated then see if the current inventory is higher than Max
        if (addProductMaxTextField.getText() == null || addProductMaxTextField.getText().length() == 0) {
            errorMessage += "Maximum inventory is not set!\n";
            addProductMaxTextField.setStyle("-fx-border-color:red;");

        } else if (addProductInventoryTextField.getText() == null || addProductInventoryTextField.getText().length() == 0) {
            errorMessage += "No valid Part inventory amount!\n";
            addProductInventoryTextField.setStyle("-fx-border-color:red;");
        } else if (Integer.parseInt(addProductInventoryTextField.getText()) > Integer.parseInt(addProductMaxTextField.getText())) {
            errorMessage += "Current inventory cannot be higher than Max!\n";
            addProductMaxTextField.setStyle("-fx-border-color:red;");
            addProductInventoryTextField.setStyle("-fx-border-color:red;");
        }





        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    //Searchs the all parts table for end users input from search textfield
    @FXML private void searchAllParts(){
        int counter = 0;
        Part tempPart;

        for (Part part : allPartsTable.getItems()) {
            if (part.getName().equals(productsAllPartsSearchTextField.getText())) {
                counter++;
            }
        }

        if (counter > 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Part Found");
            alert.setHeaderText("More than one Part was found");
            alert.setContentText("Please select the correct Part then add to Product using add button");


            for (Part part1 : allPartsTable.getItems()) {
                if (part1.getName().equals(productsAllPartsSearchTextField.getText())) {

                    allPartsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    allPartsTable.getSelectionModel().select(part1);
                    allPartsTable.scrollTo(allPartsTable.getItems().indexOf(part1)-counter+1);


                }


            }

        }
        if (counter == 1) {
            for (Part part1 : allPartsTable.getItems()) {
                if (part1.getName().equals(productsAllPartsSearchTextField.getText())) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.initOwner(dialogStage);
                    alert1.setTitle("Part Found");
                    alert1.setHeaderText("Part Found!");
                    alert1.setContentText("Would you like to add the part to the current Product?");
                    Optional<ButtonType> results1 = alert1.showAndWait();

                    if (results1.get() == ButtonType.OK) {

                        currentPartsTable.getItems().add(part1);

                    }

                }
            }
        }
        if (counter == 0) {
            System.out.println(counter);
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.initOwner(dialogStage);
            alert2.setTitle("Part NOT Found");
            alert2.setHeaderText("Part was NOT found!");
            alert2.setContentText("Please try searching again.");
            alert2.showAndWait();


        }
    }



    //Getters

    // gets the add product label so it can be changed to edit
    public Label getaddProductLabel(){return this.addProductLabel;}


    //Setters

    //sets the mainapp object to give reference back to one that called it.
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        SortedList<Part> sortedList = new SortedList<>(mainApp.getPartData());
        allPartsTable.setItems(sortedList);
        sortedList.comparatorProperty().bind(allPartsTable.comparatorProperty());





    }

    //this sets the dialog page
    public void setDialogStage(Stage dialogStage) {this.dialogStage = dialogStage;}

    // this sets the product information from end users selection. if edit button was pressed.
    // if new was presses then it was passed a blank new product
    public void setProduct(Product product) {

        this.product = product;
        if(product.getName() != null) {
            currentPartsTable.setItems(product.getParts());
            addProductIDTextField.setText(Integer.toString(product.getProductID()));
            addProductNameTextField.setText(product.getName());
            addProductPriceTextField.setText(Double.toString(product.getPrice()));
            addProductInventoryTextField.setText(Integer.toString(product.getInStock()));
            addProductMaxTextField.setText(Integer.toString(product.getMax()));
            addProductMinTextField.setText(Integer.toString(product.getMin()));
        }

    }




}






