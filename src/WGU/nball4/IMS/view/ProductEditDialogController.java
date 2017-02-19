package WGU.nball4.IMS.view;


import WGU.nball4.IMS.MainApp;
import WGU.nball4.IMS.model.Part;
import WGU.nball4.IMS.model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import WGU.nball4.IMS.MainApp;
import java.util.Optional;


/**
 * Created by nicholas on 2/13/2017.
 */
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

    //Getters
    public Label getaddProductLabel(){return this.addProductLabel;}

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

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        allPartsTable.setItems(mainApp.getPartData());}

    public ProductEditDialogController(){}

    @FXML private void handleOk() {

        if (isInputValid()) {
            product.setName(addProductNameTextField.getText());
            product.setPrice(Double.parseDouble(addProductPriceTextField.getText()));
            product.setInStock(Integer.parseInt(addProductInventoryTextField.getText()));
            product.setMax(Integer.parseInt(addProductMaxTextField.getText()));
            product.setMin(Integer.parseInt(addProductMinTextField.getText()));
            product.setProductID(Integer.parseInt(addProductIDTextField.getText()));
            product.setParts(currentPartsTable.getItems());
            okClicked = true;


            dialogStage.close();
        }
    }

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

    @FXML private void handleDeletePart(){


        int selectedIndex = currentPartsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
    }

    @FXML private void handleCancel(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You will lose any progress if you select OK!");

        Optional<ButtonType> results = alert.showAndWait();
        if (results.get() == ButtonType.OK) {

            dialogStage.close();
        }
    }

    public void setDialogStage(Stage dialogStage) {this.dialogStage = dialogStage;}

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

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";


        if (addProductNameTextField.getText() == null || addProductNameTextField.getText().length() == 0) {
            errorMessage += "No valid Product name!\n";
        }
        if (addProductIDTextField.getText() == null || addProductIDTextField.getText().length() == 0) {
            errorMessage += "No valid Product ID!\n";
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
        for(Part part : currentPartsTable.getItems()){
            if (Integer.parseInt(addProductPriceTextField.getText()) < part.getPrice()){
                errorMessage += "Product Price lower than an individual part!\n";

            }
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

    @FXML private void searchAllParts(){
        for (Part part : allPartsTable.getItems()){

            if (part.getName().equals(productsAllPartsSearchTextField.getText())){

                allPartsTable.getSelectionModel().select(part);
                allPartsTable.scrollTo(part);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(dialogStage);
                alert.setTitle("Part Found");
                alert.setHeaderText("Part Found!");
                alert.setContentText("Would you like to add the part to the current Product?");
                Optional<ButtonType> results = alert.showAndWait();
                if (results.get() == ButtonType.OK) {

                    currentPartsTable.getItems().add(part);

                }

            }


        }
    }


}


