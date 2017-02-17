package WGU.nball4.IMS.view;
import WGU.nball4.IMS.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    //called by main application to give a reference back to itself.

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("nball4 - Inventory Management System");
        alert.setHeaderText("About");
        alert.setContentText("Author - Nicholas Ball \nWGU- nball4");

        alert.showAndWait();
    }

}