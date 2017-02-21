package WGU.nball4.IMS.view;


import WGU.nball4.IMS.MainApp;
import WGU.nball4.IMS.model.InHouse;
import WGU.nball4.IMS.model.Outsourced;
import WGU.nball4.IMS.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Float.NaN;


/**
 * Created by nicholas on 2/13/2017.
 */
public class PartEditDialogController {

    //Variables from fxml
    @FXML
    private RadioButton addPartInHouseRadio;
    @FXML
    private RadioButton addPartOutsourcedRadio;
    @FXML
    private Label addPartCompanyNameLabel;
    @FXML
    private TextField addPartCompanyNameTextField;
    @FXML
    private TextField addPartIDTextField;
    @FXML
    private TextField addPartPartNameTextField;
    @FXML
    private TextField addPartPriceTextField;
    @FXML
    private TextField addPartMinTextField;
    @FXML
    private TextField addPartMaxTextField;
    @FXML
    private TextField addPartInventoryTextField;
    @FXML
    private Label addPartLabel;



    //Variables
    private Stage dialogStage;
    private InHouse inHouse;
    private Outsourced outSourced;
    private boolean okClicked = false;
    private MainApp mainApp;

    // Initializes the controller class - automatically called
    @FXML private void initialize() {
    }

    // attempts to save a part either new or edited after validation, then adds the new part to the partData list. Closes window
    @FXML
    private void handleOk() {

        try {


            if (isInputValid()) {
                if (addPartInHouseRadio.isSelected()) {
                    mainApp.getPartData().remove(inHouse);
                    this.inHouse = new InHouse();
                    inHouse.setName(addPartPartNameTextField.getText());
                    inHouse.setInStock(Integer.parseInt(addPartInventoryTextField.getText()));
                    inHouse.setMachineID(Integer.parseInt(addPartCompanyNameTextField.getText()));
                    inHouse.setMin(Integer.parseInt(addPartMinTextField.getText()));
                    inHouse.setMax(Integer.parseInt(addPartMaxTextField.getText()));
                    inHouse.setPrice(Double.parseDouble(addPartPriceTextField.getText()));
                    inHouse.setPartID();
                }


                if (addPartOutsourcedRadio.isSelected()) {

                    mainApp.getPartData().remove(inHouse);
                    this.outSourced = new Outsourced();
                    outSourced.setName(addPartPartNameTextField.getText());
                    outSourced.setInStock(Integer.parseInt(addPartInventoryTextField.getText()));
                    outSourced.setCompanyName(addPartCompanyNameTextField.getText());
                    outSourced.setMin(Integer.parseInt(addPartMinTextField.getText()));
                    outSourced.setMax(Integer.parseInt(addPartMaxTextField.getText()));
                    outSourced.setPrice(Double.parseDouble(addPartPriceTextField.getText()));
                    outSourced.setPartID();

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
        }catch (Exception e){}
/*
        catch(Exception e)

    {
        isInputValid();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Whoops");
        alert.setHeaderText("It looks like something broke :(");
        alert.setContentText("I did not handel this exception. Sorry");
        alert.showAndWait();

    }
*/
}


    //confirms cancellation with alert then closes the window
    @FXML private void handleCancel(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You will lose any progress if you select OK!");

        Optional<ButtonType> results = alert.showAndWait();
        if (results.get() == ButtonType.OK){

            dialogStage.close();
        }



    }

    //gets the label so it can be set to say add or Modify
    public Label getAddPartLabel() {
        return addPartLabel;
    }

    //this sets the dialog page
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    // this sets the part information from end users select. if edit button was pressed.
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

        }
        if (part instanceof Outsourced) {

            this.outSourced = (Outsourced) part;
            addPartOutsourcedRadio.setSelected(true);
            setRadio();
            addPartCompanyNameTextField.setText(((Outsourced) part).getCompanyName());
            addPartIDTextField.setText(Integer.toString(part.getPartID()));
            addPartPartNameTextField.setText(part.getName());
            addPartInventoryTextField.setText(Integer.toString(part.getInStock()));
            addPartMaxTextField.setText(Integer.toString(part.getMax()));
            addPartMinTextField.setText(Integer.toString(part.getMin()));
            addPartPriceTextField.setText(Double.toString(part.getPrice()));


        }




    }

    //sets the mainapp object to give reference back to one that called it.
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;}

    //returns ok clicked status back to the main app that made the part edit dialog window
    public boolean isOkClicked() {
        return okClicked;
    }

    //listens to radio button.
    public void radioSelect(ActionEvent event) {
     setRadio();

    }

    // if either radio is selected, this sets the information needed
    private void setRadio(){
         if (addPartInHouseRadio.isSelected()) {
             addPartCompanyNameLabel.setText("Machine ID");
             addPartCompanyNameTextField.setPromptText("Machine ID");
             addPartCompanyNameTextField.setVisible(true);
             addPartOutsourcedRadio.setSelected(false);


         }
         if (addPartOutsourcedRadio.isSelected()) {
             addPartCompanyNameLabel.setText("Company Name");
             addPartCompanyNameTextField.setPromptText("Company Name");
             addPartCompanyNameTextField.setVisible(true);
             addPartInHouseRadio.setSelected(false);
         }

     }

    // listens to the most of the TextFields and links to the calls that resets it
    @FXML private void handleOnClicked(MouseEvent e){

        changeTextFieldTextBack();

    }

     // After the validation checks this is called to reset the border around the textFields if they are selected
    @FXML private void changeTextFieldTextBack(){

        if (addPartInventoryTextField.isFocused())
            addPartInventoryTextField.setStyle("");
        if (addPartMinTextField.isFocused())
            addPartMinTextField.setStyle("");
        if (addPartPriceTextField.isFocused())
            addPartPriceTextField.setStyle("");
        if (addPartMaxTextField.isFocused())
            addPartMaxTextField.setStyle("");

        if (addPartPartNameTextField.isFocused())
            addPartPartNameTextField.setStyle("");
        if (addPartCompanyNameTextField.isFocused())
            addPartCompanyNameTextField.setStyle("");



    }

     // different tests for validation of data
    private boolean isInputValid() {

        //declare string for error message
        String errorMessage = "";

        //create new array list to parseInt for NFE
        ArrayList<TextField> tempTFAL = new ArrayList<>();
        tempTFAL.add(addPartInventoryTextField);
        tempTFAL.add(addPartMaxTextField);
        tempTFAL.add(addPartMinTextField);

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
            Double.parseDouble(addPartPriceTextField.getText());
        }catch (NumberFormatException nfe){
            errorMessage += "Not a valid input for: Price" ;
            addPartPriceTextField.setStyle("-fx-border-color:red;");

        }

        //check if radio was selected.

        if (!addPartInHouseRadio.isSelected() & !addPartOutsourcedRadio.isSelected()){
            errorMessage += "Please Select In House or Outsourced!\n";
        }

        //check if any fields are empty

        if (addPartPartNameTextField.getText() == null || addPartPartNameTextField.getText().length() == 0) {
            errorMessage += "No valid Part name!\n";
            addPartPartNameTextField.setStyle("-fx-border-color:red;");
        }
        if (addPartInventoryTextField.getText() == null || addPartInventoryTextField.getText().length() == 0) {
            errorMessage += "No valid Part inventory amount!\n";
            addPartInventoryTextField.setStyle("-fx-border-color:red;");
        }

        if (addPartPriceTextField.getText() == null || addPartPriceTextField.getText().length() == 0) {
            errorMessage += "No valid Part price!\n";
            addPartPriceTextField.setStyle("-fx-border-color:red;");
        }

        if (addPartMinTextField.getText() == null || addPartMinTextField.getText().length() == 0) {
            errorMessage += "No valid Minimum inventory!\n";
            addPartMinTextField.setStyle("-fx-border-color:red;");
        }

        if (addPartInHouseRadio.isSelected()){
            if (addPartCompanyNameTextField.getText() == null || addPartCompanyNameTextField.getText().length() == 0) {
                errorMessage += "No valid Machine ID!\n";
                addPartCompanyNameTextField.setStyle("-fx-border-color:red;");
            }
        }


        if (addPartOutsourcedRadio.isSelected()){
            if (addPartCompanyNameTextField.getText() == null || addPartCompanyNameTextField.getText().length() == 0) {
                errorMessage += "No valid Company Name!\n";
                addPartCompanyNameTextField.setStyle("-fx-border-color:red;");
            }
        }

        if (addPartMaxTextField.getText() == null || addPartMaxTextField.getText().length() == 0) {
            errorMessage += "Maximum inventory is not set!\n";
            addPartMaxTextField.setStyle("-fx-border-color:red;");
        }
        //Test machine ID is not really numbers not a string
        try {
            if (addPartInHouseRadio.isSelected()) {
                if (Integer.parseInt(addPartCompanyNameTextField.getText()) == NaN ) {
                    errorMessage += "No valid Machine ID!\n";
                    addPartCompanyNameTextField.setStyle("-fx-border-color:red;");
                }
            }
        }catch (Exception e){
            errorMessage += "Not a valid Machine ID" ;
            addPartCompanyNameTextField.setStyle("-fx-border-color:red;");
        }

        //check if Max field is populated and if it is lower than the Min field
        if (addPartMaxTextField.getText() == null || addPartMaxTextField.getText().length() == 0) {
            errorMessage += "Maximum inventory is not set!\n";
            addPartMaxTextField.setStyle("-fx-border-color:red;");

        } else if (Integer.parseInt(addPartMaxTextField.getText()) < Integer.parseInt(addPartMinTextField.getText())) {
            errorMessage += "Max inventory value cannot be lower than Minimum!\n";
            addPartMaxTextField.setStyle("-fx-border-color:red;");
            addPartMinTextField.setStyle("-fx-border-color:red;");
        }

        //check if max and inventory fields are populated then see if the current inventory is higher than Max
        if (addPartMaxTextField.getText() == null || addPartMaxTextField.getText().length() == 0) {
            errorMessage += "Maximum inventory is not set!\n";
            addPartMaxTextField.setStyle("-fx-border-color:red;");

        } else if (addPartInventoryTextField.getText() == null || addPartInventoryTextField.getText().length() == 0) {
            errorMessage += "No valid Part inventory amount!\n";
            addPartInventoryTextField.setStyle("-fx-border-color:red;");
        } else if (Integer.parseInt(addPartInventoryTextField.getText()) > Integer.parseInt(addPartMaxTextField.getText())) {
            errorMessage += "Current inventory cannot be higher than Maximum!\n";
            addPartMaxTextField.setStyle("-fx-border-color:red;");
            addPartInventoryTextField.setStyle("-fx-border-color:red;");
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


