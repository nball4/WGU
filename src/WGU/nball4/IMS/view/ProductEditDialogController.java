package WGU.nball4.IMS.view;


import WGU.nball4.IMS.MainApp;
import WGU.nball4.IMS.model.InHouse;
import WGU.nball4.IMS.model.Outsourced;
import WGU.nball4.IMS.model.Part;
import WGU.nball4.IMS.model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import WGU.nball4.IMS.MainApp;


/**
 * Created by nicholas on 2/13/2017.
 */
public class ProductEditDialogController {


    //Variables from fxml
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, String> nameColumn;
    @FXML private TableColumn<Part, String> partIDColumn;
    @FXML private TableColumn<Part, String> inventoryColumn;
    @FXML private TableColumn<Part, String> priceColumn;


    //Variables
    private Stage dialogStage;
    private boolean okClicked = false;
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        allPartsTable.setItems(mainApp.getPartData());}



    //Initializes the controller class - automatically called
    @FXML
    private void initialize() {
        partIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getPartID())));
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        inventoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.toString(cellData.getValue().getInStock())));
        priceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Double.toString(cellData.getValue().getPrice())));

    }

    @FXML
    private void handleOk() {


    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setProduct(Product product) {



    }



    public boolean isOkClicked() {
        return okClicked;
    }

/**
    private boolean isInputValid() {
        String errorMessage = "";

        if (!addPartInHouseRadio.isSelected() & !addPartOutsourcedRadio.isSelected()){
            errorMessage += "Please Select In House or Outsourced!\n";
        }

        if (addPartPartNameTextField.getText() == null || addPartPartNameTextField.getText().length() == 0) {
            errorMessage += "No valid Part name!\n";
        }
        if (addPartIDTextField.getText() == null || addPartIDTextField.getText().length() == 0) {
            errorMessage += "No valid Part ID!\n";
        }
        if (addPartInventoryTextField.getText() == null || addPartInventoryTextField.getText().length() == 0) {
            errorMessage += "No valid Part inventory amount!\n";
        }
        if (addPartPriceTextField.getText() == null || addPartPriceTextField.getText().length() == 0) {
            errorMessage += "No valid Part price!\n";
        }
        if (addPartMinTextField.getText() == null || addPartMinTextField.getText().length() == 0) {
            errorMessage += "No valid Minimum inventory!\n";
        }
        if (addPartMaxTextField.getText() == null || addPartMaxTextField.getText().length() == 0) {
            errorMessage += "No valid Maximum inventory!\n";
        }
        if (addPartInHouseRadio.isSelected()){
            if (addPartCompanyNameTextField.getText() == null || addPartCompanyNameTextField.getText().length() == 0) {
                errorMessage += "No valid Machine ID!\n";
            }
        }
        if (addPartOutsourcedRadio.isSelected()){
            if (addPartCompanyNameTextField.getText() == null || addPartCompanyNameTextField.getText().length() == 0) {
                errorMessage += "No valid Company Name!\n";
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
 */
}


