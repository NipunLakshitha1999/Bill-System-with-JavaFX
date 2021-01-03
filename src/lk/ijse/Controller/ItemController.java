package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lk.ijse.DB.DBConnetion;
import lk.ijse.Model.Customer;
import lk.ijse.Model.Item;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private JFXTextField txtItemIDIForm;

    @FXML
    private JFXTextField txtItemNameIForm;

    @FXML
    private JFXTextField txtItemQTYIForm;

    @FXML
    private JFXTextField txtItemPriceIForm;

    @FXML
    private JFXTextField txtItemDiscountIForm;

    @FXML
    private TableView<Item> tblItemIForm;

    @FXML
    private JFXButton btnItemSearch;

    @FXML
    private TextField txtItemSearchIForm;

    @FXML
    private JFXButton btnItemAdd;

    @FXML
    private JFXButton btnItemUpdate;

    @FXML
    private JFXButton btnItemDelete;

    @FXML
    void btnItemAddClick(ActionEvent event) {
        String itemID=txtItemIDIForm.getText();
        String itemName=txtItemNameIForm.getText();
        int QTY=Integer.parseInt(txtItemQTYIForm.getText());
        double itemPrice=Double.parseDouble(txtItemPriceIForm.getText());
        double itemDiscount=Double.parseDouble(txtItemDiscountIForm.getText());

        boolean b=addItem(new Item(itemID,itemName,QTY,itemPrice,itemDiscount));
        if (b) {
            loadItemTable( );
            JOptionPane.showMessageDialog(null, "Item Added Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Item Added fail");
        }

    }

    private boolean addItem(Item item) {
        boolean isItemAdded = false;

        try {
            Connection connection =DBConnetion.getInstance().getConnection();
            String sql = "INSERT INTO Item VALUES(?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, item.getItemID());
            pstm.setObject(2, item.getItemName());
            pstm.setObject(3, item.getItemQTY());
            pstm.setObject(4, item.getItemPrice());
            pstm.setObject(5, item.getItemDiscount());

            isItemAdded = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());

        }

        return isItemAdded;

    }

    @FXML
    void btnItemAddMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnItemDeleteClick(ActionEvent event) {
        String itemID=txtItemIDIForm.getText();

        boolean b=deleteItem(itemID);
        if (b) {
            loadItemTable( );
            JOptionPane.showMessageDialog(null, "Item Delete Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Item Delete fail");
        }

    }

    private boolean deleteItem(String itemID) {
        boolean isDeleteItem = false;
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "DELETE FROM Item WHERE ItemID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, itemID);
            isDeleteItem = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return isDeleteItem;
    }

    @FXML
    void btnItemDeleteMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnItemSearchClick(ActionEvent event) {

    }

    @FXML
    void btnItemSearchMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnItemUpdateClick(ActionEvent event) {

        String itemID=txtItemIDIForm.getText();
        String itemName=txtItemNameIForm.getText();
        int QTY=Integer.parseInt(txtItemQTYIForm.getText());
        double itemPrice=Double.parseDouble(txtItemPriceIForm.getText());
        double itemDiscount=Double.parseDouble(txtItemDiscountIForm.getText());
        
        boolean b=updateItem(new Item(itemID,itemName,QTY,itemPrice,itemDiscount));
        if (b) {
            loadItemTable( );
            JOptionPane.showMessageDialog(null, "Item Update Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Item Update fail");
        }
    }

    private boolean updateItem(Item item) {
        boolean isUpdateItem = false;
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "UPDATE Item SET ItemName=?,QTYOnHand=?,UnitPrice=?,Discount=? WHERE ItemID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, item.getItemName());
            pstm.setObject(2, item.getItemQTY());
            pstm.setObject(3, item.getItemPrice());
            pstm.setObject(4, item.getItemDiscount());
            pstm.setObject(5, item.getItemID());
            isUpdateItem = pstm.executeUpdate() > 0;

            return isUpdateItem;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return isUpdateItem;
    }

    @FXML
    void btnItemUpdateMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblItemIFormMouseClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            int index = tblItemIForm.getSelectionModel( ).getSelectedIndex( );
            Item item = tblItemIForm.getItems( ).get(index);
            txtItemIDIForm.setText(item.getItemID());
            txtItemNameIForm.setText(item.getItemName());
            txtItemQTYIForm.setText(Integer.toString(item.getItemQTY()));
            txtItemPriceIForm.setText(Double.toString(item.getItemPrice()));
            txtItemDiscountIForm.setText(Double.toString(item.getItemDiscount()));
        }
    }

    @FXML
    void txtItemDiscountIFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemIDIFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemNameIFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemPriceIFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemQTYIFormEntered(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblItemIForm.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tblItemIForm.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItemIForm.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemQTY"));
        tblItemIForm.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tblItemIForm.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("itemDiscount"));


        if(getItemDetail()!=null){
            loadItemTable();
        }else {

        }
    }

    private void loadItemTable() {
        ArrayList<Item> itemDetail = getItemDetail( );
        tblItemIForm.setItems(FXCollections.observableArrayList(itemDetail));
    }

    private ArrayList<Item> getItemDetail() {
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "SELECT * FROM Item";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet set = pstm.executeQuery();
            ArrayList<Item> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Item(set.getString(1),set.getString(2),set.getInt(3),set.getDouble(4),set.getDouble(5)));
            }
            return list;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;

    }
}
