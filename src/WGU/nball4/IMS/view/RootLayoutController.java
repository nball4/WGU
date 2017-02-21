package WGU.nball4.IMS.view;
import WGU.nball4.IMS.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    private Stage primaryStage;

    //called by main application to give a reference back to itself.

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    // shows the about page from menu bar.
    @FXML private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(primaryStage);
        alert.setTitle("nball4 - Inventory Management System");
        alert.setHeaderText("About");
        alert.setContentText("Author - Nicholas Ball \nWGU- nball4");

        alert.showAndWait();
    }

    public void setDialogStage(Stage dialogStage) {
        this.primaryStage = dialogStage;

    }

}