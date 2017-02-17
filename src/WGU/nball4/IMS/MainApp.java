package WGU.nball4.IMS;

import java.io.IOException;
import WGU.nball4.IMS.model.InHouse;
import WGU.nball4.IMS.model.Inventory;
import WGU.nball4.IMS.model.Part;
import WGU.nball4.IMS.model.Product;
import WGU.nball4.IMS.view.ProductEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import WGU.nball4.IMS.view.RootLayoutController;
import WGU.nball4.IMS.view.PartOverviewController;
import WGU.nball4.IMS.view.PartEditDialogController;

public class MainApp extends Application {

    //variables
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Part> partData = FXCollections.observableArrayList();
    private Inventory inventory = new Inventory();

    public Inventory getInventory(){ return inventory;}
    public ObservableList<Part> getPartData(){return partData;}


    // Sample data
    public MainApp(){
        partData.add(new InHouse(12,"abc",12,12,12,12,123));
        partData.add(new InHouse(123,"abcefg",122,122,122,122,1234));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WGU C482 - IMS");

        initRootLayout();

        showPartOverview();


    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
           // scene.getStylesheets().add(MainApp.class.getResource("util/IMS.css").toExternalForm());

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void showPartOverview() {
        try {
            // Load part overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PartOverview.fxml"));
            VBox partOverview = loader.load();


            // Set part overview into the top center of root layout.
            rootLayout.setCenter(partOverview);

            // Give the controller access to the main app.
            PartOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPartEditDialog(Part part) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PartEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
           // scene.getStylesheets().add(MainApp.class.getResource("util/IMS.css").toExternalForm());

            // Set the part into the controller.
            PartEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.getAddPartLabel().setText("Edit Part");
            controller.setPart(part);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showPartNewDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PartEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //scene.getStylesheets().add(MainApp.class.getResource("util/IMS.css").toExternalForm());

            // Set the part into the controller.
            PartEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);



            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public boolean showProductEditDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // scene.getStylesheets().add(MainApp.class.getResource("util/IMS.css").toExternalForm());

            // Set the part into the controller.
            ProductEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            //controller.setProduct(product);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
