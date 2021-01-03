package lk.ijse.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashBordForm extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass( ).getResource("DashBordForm.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Dashbord");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
