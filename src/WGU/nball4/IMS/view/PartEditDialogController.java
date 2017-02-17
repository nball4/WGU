package WGU.nball4.IMS.view;


import WGU.nball4.IMS.MainApp;
import WGU.nball4.IMS.model.InHouse;
import WGU.nball4.IMS.model.Outsourced;
import WGU.nball4.IMS.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;



/**
 * Created by nicholas on 2/13/2017.
 */
public class PartEditDialogController {

    //Variables from fxml
    @FXML private RadioButton addPartInHouseRadio;
    @FXML private RadioButton addPartOutsourcedRadio;
    @FXML private Label addPartCompanyNameLabel;
    @FXML private TextField addPartCompanyNameTextField;
    @FXML private TextField addPartIDTextField;
    @FXML private TextField addPartPartNameTextField;
    @FXML private TextField addPartPriceTextField;
    @FXML private TextField addPartMinTextField;
    @FXML private TextField addPartMaxTextField;
    @FXML private TextField addPartInventoryTextField;
    @FXML private Label addPartLabel;

    public Label getAddPartLabel() {
        return addPartLabel;
    }

    //Variables
    private Stage dialogStage;
    private InHouse inHouse;
    private Outsourced outSourced;
    private boolean okClicked = false;
    private MainApp mainApp;



    //Initializes the controller class - automatically called
    @FXML
    private void initialize() {
    }

    @FXML
    private void handleOk() {

        try {


            if (isInputValid()) {
                if (addPartInHouseRadio.isSelected()) {

                    this.inHouse = new InHouse();
                    inHouse.setName(addPartPartNameTextField.getText());
                    inHouse.setInStock(Integer.parseInt(addPartInventoryTextField.getText()));
                    inHouse.setMachineID(Integer.parseInt(addPartCompanyNameTextField.getText()));
                    inHouse.setMin(Integer.parseInt(addPartMinTextField.getText()));
                    inHouse.setMax(Integer.parseInt(addPartMaxTextField.getText()));
                    inHouse.setPrice(Double.parseDouble(addPartPriceTextField.getText()));
                    inHouse.setPartID(Integer.parseInt(addPartIDTextField.getText()));

                }

                if (addPartOutsourcedRadio.isSelected()) {


                    this.outSourced = new Outsourced();
                    outSourced.setName(addPartPartNameTextField.getText());
                    outSourced.setInStock(Integer.parseInt(addPartInventoryTextField.getText()));
                    outSourced.setCompanyName(addPartCompanyNameTextField.getText());
                    outSourced.setMin(Integer.parseInt(addPartMinTextField.getText()));
                    outSourced.setMax(Integer.parseInt(addPartMaxTextField.getText()));
                    outSourced.setPrice(Double.parseDouble(addPartPriceTextField.getText()));
                    outSourced.setPartID(Integer.parseInt(addPartIDTextField.getText()));

                }

                if (addPartInHouseRadio.isSelected()) {
                    mainApp.getPartData().add(inHouse);
                }
                if (addPartOutsourcedRadio.isSelected()) {
                    mainApp.getPartData().add(outSourced);
                }

                okClicked = true;
                this.dialogStage.close();
            }

        }

        catch (Exception e){
            isInputValid();
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setPart(Part part) {

        if (part instanceof InHouse) {

            this.inHouse = (InHouse) part;

            addPartInHouseRadio.setSelected(true);
            setRadio();
            addPartCompanyNameTextField.setText(Integer.toString(((InHouse) part).getMachineID()));
            addPartIDTextField.setText(Integer.toString(part.getPartID()));
            addPartPartNameTextField.setText(part.getName());
            addPartInventoryTextField.setText(Integer.toString(part.getInStock()));
            addPartMaxTextField.setText(Integer.toString(part.getMax()));
            addPartMinTextField.setText(Integer.toString(part.getMin()));
            addPartPriceTextField.setText(Double.toString(part.getPrice()));
            mainApp.getPartData().remove(part);
        }
        if (part instanceof Outsourced) {

            this.outSourced = (Outsourced) part;
            setRadio();
            addPartOutsourcedRadio.setSelected(true);
            addPartCompanyNameTextField.setText(Integer.toString(((Outsourced) part).getCompanyId()));
            addPartIDTextField.setText(Integer.toString(part.getPartID()));
            addPartPartNameTextField.setText(part.getName());
            addPartInventoryTextField.setText(Integer.toString(part.getInStock()));
            addPartMaxTextField.setText(Integer.toString(part.getMax()));
            addPartMinTextField.setText(Integer.toString(part.getMin()));
            addPartPriceTextField.setText(Double.toString(part.getPrice()));
            mainApp.getPartData().remove(part);

        }




    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;}

    public boolean isOkClicked() {
        return okClicked;
    }

    public void radioSelect(ActionEvent event) {
     setRadio();

    }

    private void setRadio(){
         if (addPartInHouseRadio.isSelected()) {
             addPartCompanyNameLabel.setText("Machine ID");
             addPartCompanyNameTextField.setVisible(true);
             addPartOutsourcedRadio.setSelected(false);


         }
         if (addPartOutsourcedRadio.isSelected()) {
             addPartCompanyNameLabel.setText("Company Name");
             addPartCompanyNameTextField.setVisible(true);
             addPartInHouseRadio.setSelected(false);
         }

     }

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
}


