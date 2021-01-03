package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.DB.DBConnetion;
import lk.ijse.Model.Item;
import lk.ijse.Model.Order;
import lk.ijse.Model.OrderDetail;

import javax.print.attribute.standard.MediaSizeName;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderController implements Initializable  {
    double price=0;
    double netAmount=0;




    @FXML
    private JFXTextField txtOrderIDODForm;

    @FXML
    private JFXTextField txtOrderDateODForm;

    @FXML
    private JFXTextField txtItemIDODForm;

    @FXML
    private JFXTextField txtItemNameODForm;

    @FXML
    private JFXTextField txtItemQTYOnHandODForm;

    @FXML
    private JFXTextField txtItemDiscountODForm;

    @FXML
    private JFXTextField txtQTYODForm;

    @FXML
    private JFXTextField txtPriceODForm;

    @FXML
    private JFXTextField txtItemPriceODForm;

    @FXML
    private JFXComboBox<String> cmbCustomerIDODForm;

    @FXML
    private TableView<OrderDetail> tblOrder;

    @FXML
    private TextField txtNetAmountODForm;

    @FXML
    private TextField txtDiscountODForm;

    @FXML
    private TextField txtSubAmountODForm;

    @FXML
    private TextField txtCashODForm;

    @FXML
    private TextField txtBalanceODForm;

    @FXML
    private JFXButton btnPurchesOrderODForm;

    @FXML
    private JFXButton btnDeleteODForm;

    @FXML
    private JFXButton btnCancelODForm;

    @FXML
    private JFXListView<String> ItemlistViewOrderForm;

    @FXML
    private JFXButton btnAddToCartODForm;

    @FXML
    private JFXButton btnCustomerFormLoad;

    @FXML
    private JFXButton btnItemFormLoad;

    @FXML
    void btnCustomerFormLoadClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CustomerFrom.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace( );
        }



    }

    @FXML
    void btnItemFormLoadClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ItemForm.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }

    @FXML
    void txtDiscountODFormKeyReleased(KeyEvent event) {
        if(txtDiscountODForm.getText() != null){
            double discount=Double.parseDouble(txtDiscountODForm.getText());
            double afterDiscount=discount*(price/100);
            double subAmoount=netAmount-afterDiscount;

            txtSubAmountODForm.setText(Double.toString(subAmoount));
        }else{
            JOptionPane.showMessageDialog(null,"Input Discount");
        }


    }

    @FXML
    void txtCashODFormKeyReleased(KeyEvent event) {
        double cach=Double.parseDouble(txtCashODForm.getText());
        double balance=cach-Double.parseDouble(txtSubAmountODForm.getText());
       txtBalanceODForm.setText(Double.toString(balance));
    }

    @FXML
    void txtQTYODFormKeyReleased(KeyEvent event) {

        if(!txtQTYODForm.getText().isEmpty()){
            double qty=Double.parseDouble(txtQTYODForm.getText());
            double UnitPrice=Double.parseDouble(txtItemPriceODForm.getText());
            price=qty*UnitPrice;
            txtPriceODForm.setText(Double.toString(price));
        }else{
            JOptionPane.showMessageDialog(null,"Input QTY");
        }

    }
    @FXML
    void btnAddToCartODFormClick(ActionEvent event) {
        try {
            netAmount=netAmount+Double.parseDouble(txtPriceODForm.getText());
            checkRow();
            txtNetAmountODForm.setText(Double.toString(netAmount));
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,ex);
        }


    }

    private void checkRow(){
        boolean found=false;
        for (int i=0;i<tblOrder.getItems().size();i++){
            if (txtItemIDODForm.getText().equals(tblOrder.getItems().get(i).getItemID())){
                tblOrder.getItems().get(i).setOrderQTY(tblOrder.getItems().get(i).getOrderQTY()+Integer.parseInt(txtQTYODForm.getText()));
                tblOrder.getItems().get(i).setOrderPrice(tblOrder.getItems().get(i).getOrderPrice()+Double.parseDouble(txtPriceODForm.getText()));
                found=true;
            }
        }
        if(!found){
            tblOrder.getItems().add(new OrderDetail(txtOrderIDODForm.getText(),txtItemIDODForm.getText(),txtItemIDODForm.getText(),Integer.parseInt(txtQTYODForm.getText()),Double.parseDouble(txtPriceODForm.getText())));
        }
        tblOrder.refresh();
    }

    @FXML
    void ItemlistViewOrderFormMouseClicked(MouseEvent event) {
        String selectedItem = ItemlistViewOrderForm.getSelectionModel( ).getSelectedItem( );
        txtItemNameODForm.setText(selectedItem);

        ArrayList<Item> itemDetail = getItemDetail(selectedItem);
        for (Item item : itemDetail) {
            txtItemIDODForm.setText(item.getItemID());
            txtItemNameODForm.setText(item.getItemName());
            txtItemQTYOnHandODForm.setText(Integer.toString(item.getItemQTY()));
            txtItemPriceODForm.setText(Double.toString(item.getItemPrice()));
            txtItemDiscountODForm.setText(Double.toString(item.getItemDiscount()));
        }
        ItemlistViewOrderForm.setVisible(false);
    }

    private ArrayList<Item> getItemDetail(String selectedItem) {
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "SELECT * FROM Item WHERE ItemName=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,selectedItem);
            ArrayList<Item> list = new ArrayList<>();
            ResultSet set = pstm.executeQuery();
            while (set.next()) {
                list.add(new Item(set.getString(1),set.getString(2),set.getInt(3),set.getDouble(4),set.getDouble(5)));
            }
           return list;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    @FXML
    void txtItemNameODFormKeyReleased(KeyEvent event) {
        ArrayList<String> itemNameSuggetion = getItemNameSuggetion(txtItemNameODForm.getText( ));
        ObservableList<String> obList = FXCollections.observableArrayList(itemNameSuggetion);
        ItemlistViewOrderForm.setItems(obList);
        ItemlistViewOrderForm.setVisible(true);

    }

    private ArrayList<String> getItemNameSuggetion(String text) {
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "SELECT ItemName FROM Item WHERE ItemName LIKE '%" + text + "%'";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ArrayList<String> list = new ArrayList<>();
            ResultSet set = pstm.executeQuery();
            while (set.next()) {
                list.add(set.getString(1));
            }
            return list;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }


    @FXML
    void btnCancelODFormClick(ActionEvent event) {

    }

    @FXML
    void btnCancelODFormMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnDeleteODFormClick(ActionEvent event) {

    }

    @FXML
    void btnDeleteODFormMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnPurchesOrderODFormClick(ActionEvent event) {
        String orderID=txtOrderIDODForm.getText();
        String orderDate=txtOrderDateODForm.getText();
        String orderCusID=cmbCustomerIDODForm.getValue();

        ArrayList<OrderDetail> list=new ArrayList<>();
        for (OrderDetail item : tblOrder.getItems( )) {
            list.add(new OrderDetail(item.getOrderID(),item.getItemID(),item.getItemName(),item.getOrderQTY(),item.getOrderPrice()));
            System.out.println(list );
        }




        try {
          boolean  b = addOrder(new Order(orderID, orderDate, orderCusID, list));
            System.out.println(orderDate);
            if(b){
                JOptionPane.showMessageDialog(null,"Place order Successfully");
                loadOrderDate();
                loadOrderID();
                tblOrder.getItems().clear();
                txtItemIDODForm.setText("");
                txtItemNameODForm.setText("");
                txtItemQTYOnHandODForm.setText("");
                txtPriceODForm.setText("");
                txtQTYODForm.setText("");

            }else{
                JOptionPane.showMessageDialog(null,"Place order not Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        }

    }

    private boolean addOrder(Order order) throws SQLException {
        Connection connection=null;

        try {
            connection = DBConnetion.getInstance().getConnection();
            String sql = "INSERT INTO CustomerOrder values(?,?,?)";
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, order.getOrderID());
            pstm.setString(2, order.getOrderDate());
            pstm.setString(3, order.getCustomerID());

            boolean result=pstm.executeUpdate()>0;

            if(result){
                boolean set=addOrderDetail(order.getList());
                if(set){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();

        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private boolean addOrderDetail(ArrayList<OrderDetail> list) {
        boolean r=false;
        for (OrderDetail orderDetail : list) {
            r=insertOrderDetail(orderDetail);
        }
        return r;
    }

    private boolean insertOrderDetail(OrderDetail orderDetail) {
        boolean result = false;
        try {
            Connection connection = DBConnetion.getInstance( ).getConnection( );
            String sql = "INSERT INTO Orderdetail VALUES(?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, orderDetail.getOrderID( ));
            pstm.setString(2, orderDetail.getItemID( ));
            pstm.setString(3, orderDetail.getItemName( ));
            pstm.setDouble(4, orderDetail.getOrderQTY( ));
            pstm.setDouble(5, orderDetail.getOrderPrice( ));
            result = pstm.executeUpdate( ) > 0;


        } catch (SQLException e) {
            e.printStackTrace( );
        }
        return result;
    }

    @FXML
    void btnPurchesOrderODFormMouseEntered(MouseEvent event) {

    }

    @FXML
    void cmbCustomerIDODFormEnterd(ActionEvent event) {

    }

    @FXML
    void tblOrderMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtCashODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtDiscountODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemDiscountODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemIDODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemNameODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemPriceODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtItemQTYOnHandODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtNetAmountODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtOrderDateODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtOrderIDODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtPriceODFormEntered(ActionEvent event) {

    }

    @FXML
    void txtQTYODFormEntered(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderPrice"));

        ItemlistViewOrderForm.setVisible(false);
        loadOrderID();
        loadOrderDate();
        loadCustomerID();
    }

    private void loadCustomerID() {
        ArrayList<String> list=getCustomerID();
        ObservableList<String> obList=FXCollections.observableArrayList(list);
       cmbCustomerIDODForm.setItems(obList);

    }

    private ArrayList<String> getCustomerID() {
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "SELECT CusID FROM Customer";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet set = pstm.executeQuery();
            ArrayList<String> list = new ArrayList<>();

            while (set.next()) {
                list.add(set.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private void loadOrderDate() {
        Date d1 = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("YYYY-MM-dd");
        txtOrderDateODForm.setText(format1.format(d1));

    }

    private void loadOrderID() {
        String OrderId = getOrderID();
        //System.out.println(OrderId);
        if (OrderId != null) {
            String[] temparr = OrderId.split("O");
            int tempNumber = Integer.parseInt(temparr[1]);
            tempNumber += 1;

            if (tempNumber < 10) {
                txtOrderIDODForm.setText("O00" + tempNumber);
            } else if (tempNumber < 100) {
                txtOrderIDODForm.setText("O0" + tempNumber);
            } else {
                txtOrderIDODForm.setText("O" + tempNumber);
            }

        } else {
            txtOrderIDODForm.setText("O001");
        }
    }

    private String getOrderID() {
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "SELECT OrderID FROM  customerorder ORDER BY OrderID DESC LIMIT 1";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet set = pstm.executeQuery();
            System.out.println(set);
            if (set.next()) {
                return set.getString("OrderID");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

}
