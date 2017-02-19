package WGU.nball4.IMS;

import java.io.IOException;
import java.util.Random;

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
    private Inventory inventory;

    //Getters
    public Inventory getInventory(){ return inventory;}
    public ObservableList<Part> getPartData(){return partData;}


    // Sample data
    public MainApp(){
        partData.add(new InHouse(1,"one",1,1,1,1,1));
        partData.add(new InHouse(1,"two",1,1,1,1,1));
        partData.add(new InHouse(1,"three",1,1,1,1,1));
        partData.add(new InHouse(1,"four",1,1,1,1,1));
        partData.add(new InHouse(1,"five",1,1,1,1,1));
        partData.add(new InHouse(1,"six",1,1,1,1,1));
        partData.add(new InHouse(1,"seven",1,1,1,1,1));

        partData.add(new InHouse(1,"eight",1,1,1,1,1));
        partData.add(new InHouse(1,"nine",1,1,1,1,1));
        partData.add(new InHouse(1,"eight",1,1,1,1,1));
        partData.add(new InHouse(1,"nine",1,1,1,1,1));
        partData.add(new InHouse(1,"eight",1,1,1,1,1));
        partData.add(new InHouse(1,"nine",1,1,1,1,1));

        inventory = new Inventory();
        generateFakeData();
    }

    //Methods

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
            dialogStage.setTitle("Modify Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
           // scene.getStylesheets().add(MainApp.class.getResource("util/IMS.css").toExternalForm());

            // Set the part into the controller.
            PartEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.getAddPartLabel().setText("Modify Part");
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
            dialogStage.setTitle("New Part");
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

    public boolean showProductEditDialog(Product product) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Part");
            if(product.getName() == null){
                dialogStage.setTitle("Add Product");
            }
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // scene.getStylesheets().add(MainApp.class.getResource("util/IMS.css").toExternalForm());

            // Set the part into the controller.
            ProductEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            if(product.getName() == null){
                controller.getaddProductLabel().setText("Add Product");
            }
            controller.setProduct(product);

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

    private void generateFakeData(){


        for (int x=0; x < 10; x++){
            Random rn = new Random();
            int number = rn.nextInt(100 - 1 + 1)+1;
            Random rn2 = new Random();
            double double1 = rn2.nextDouble();

            inventory.addProduct(new Product(new InHouse(number,Integer.toString(number),number,number,number,number,number),Integer.toString(number),number,double1,number,number,number));

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
