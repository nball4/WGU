package WGU.nball4.IMS.view;


import WGU.nball4.IMS.model.Product;
import javafx.beans.property.SimpleObjectProperty;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Alert.AlertType;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import WGU.nball4.IMS.MainApp;
        import WGU.nball4.IMS.model.Part;
        import WGU.nball4.IMS.model.InHouse;

public class PartOverviewController {

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

    // Reference to the main application.
    private MainApp mainApp;

    //called by the main application to give a reference back to itself
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        // Add observable list data to the table
        partTable.setItems(mainApp.getPartData());
    }

    //constructor
    public PartOverviewController() {
    }

    //Initializes the controller class - automatically called
    @FXML
    private void initialize() {
        // Initialize the parts table with the columns.
        partIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getPartID())));
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        inventoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getInStock())));
        priceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Double.toString(cellData.getValue().getPrice())));

    }

    @FXML
    private void handleDeletePart() {
        int selectedIndex = partTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            partTable.getItems().remove(selectedIndex);
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

    @FXML
    private void handleNewPart () {

      mainApp.showPartNewDialog();

    }

    @FXML
    private void handleEditPart() {
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

    @FXML
    private void handleDeleteProduct() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
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

    @FXML
    private void handleNewProduct() {


        mainApp.showProductEditDialog();


    }

    @FXML
    private void handleEditProduct() {
        /**
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
         */
    }


}