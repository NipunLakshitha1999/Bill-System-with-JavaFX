package lk.ijse.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class CustomerFrom extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass( ).getResource("CustomerFrom.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Customer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
