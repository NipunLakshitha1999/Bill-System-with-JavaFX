package lk.ijse.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashbordController {
    @FXML
    private Pane dashBordHomePane;

    @FXML
    private Pane dashBordCustomerPane;

    @FXML
    private Pane dashBordItemPane;

    @FXML
    private Pane dashBordOrderPane;

    @FXML
    private Pane dashBordUserPane;

    @FXML
    private Label homeCustomerCountTag;

    @FXML
    private Label homeItemCountTag;

    @FXML
    private Label homeOrderCountTag;

    @FXML
    private AnchorPane spaPane;
    private AnchorPane hold;

    @FXML
    void dashBordHomePaneMouseClick(MouseEvent event) {
        try {
            FXMLLoader load = new FXMLLoader(getClass( ).getResource("../view/OrderForm.fxml"));
            hold = load.load( );
            spaPane.getChildren( ).setAll(hold);
        } catch (IOException e) {
            e.printStackTrace( );
        }

    }

}
