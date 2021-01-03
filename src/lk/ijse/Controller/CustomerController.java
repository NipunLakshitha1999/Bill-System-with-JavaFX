package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lk.ijse.DB.DBConnetion;
import lk.ijse.Model.Customer;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private JFXTextField txtCustomerIDCForm;

    @FXML
    private JFXTextField txtCustomerIFNameCForm;

    @FXML
    private JFXTextField txtCustomerILNameCForm;

    @FXML
    private JFXTextField txtCustomerAddressCForm;

    @FXML
    private JFXTextField txtCustomerContactCForm;

    @FXML
    private TableView<Customer> tblCustomerTable;

    @FXML
    private JFXButton btnCustomerAdd;

    @FXML
    private JFXButton btnCustomerDelete;

    @FXML
    private JFXButton btnCustomerUpdate;

    @FXML
    private JFXButton btnCustomerSearch;

    @FXML
    private TextField txtCustomerSearch;

    @FXML
    void btnCustomerAddClick(ActionEvent event) {
        String cusID = txtCustomerIDCForm.getText( );
        String cusFName = txtCustomerIFNameCForm.getText( );
        String cusLName = txtCustomerILNameCForm.getText( );
        String cusAddress = txtCustomerAddressCForm.getText( );
        int cusContact = Integer.parseInt(txtCustomerContactCForm.getText( ));

        boolean b = addCustomer(new Customer(cusID, cusFName, cusLName, cusAddress, cusContact));
        if (b) {
            loadCustomerTable( );
            JOptionPane.showMessageDialog(null, "Customer Added Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Customer Added fail");
        }

    }

    private boolean addCustomer(Customer customer) {
        boolean isCustomerAdded = false;
        try {
            Connection connection = DBConnetion.getInstance( ).getConnection( );
            String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, customer.getCustomerID( ));
            pstm.setString(2, customer.getCustomerFName( ));
            pstm.setString(3, customer.getCustomerLName( ));
            pstm.setString(4, customer.getCustomerAddress( ));
            pstm.setInt(5, customer.getCustomerContact( ));

            isCustomerAdded = pstm.executeUpdate( ) > 0;
        } catch (SQLException ex) {

        }

        return isCustomerAdded;
    }

    @FXML
    void btnCustomerAddMouseEnterd(MouseEvent event) {

    }

    @FXML
    void btnCustomerDeleteClick(ActionEvent event) {
        String cusID=txtCustomerIDCForm.getText();

        boolean b = deleteCustomer(cusID);
        if (b) {
            loadCustomerTable( );
            JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Customer deleted fail");
        }

    }

    private boolean deleteCustomer(String cusID) {
        boolean isCustomerDeleted = false;
        try {
            Connection connection = DBConnetion.getInstance().getConnection();
            String sql = "DELETE FROM Customer WHERE CusID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, cusID);

            isCustomerDeleted = pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return isCustomerDeleted;
    }

    @FXML
    void btnCustomerDeleteMouseEnterd(MouseEvent event) {

    }

    @FXML
    void btnCustomerSearchClick(ActionEvent event) {

    }

    @FXML
    void btnCustomerUpdateClick(ActionEvent event) {
        String cusID = txtCustomerIDCForm.getText( );
        String cusFName = txtCustomerIFNameCForm.getText( );
        String cusLName = txtCustomerILNameCForm.getText( );

        String cusAddress = txtCustomerAddressCForm.getText( );
        int cusContact = Integer.parseInt(txtCustomerContactCForm.getText( ));

        boolean b = updateCustomer(new Customer(cusID, cusFName, cusLName, cusAddress, cusContact));

        if (b) {
            loadCustomerTable( );
            JOptionPane.showMessageDialog(null, "Customer Update Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Customer Update fail");
        }

    }

    private boolean updateCustomer(Customer customer) {
        boolean isUpdateCustomer = false;
        try {
            Connection connection = DBConnetion.getInstance( ).getConnection( );
            String sql = "UPDATE Customer SET CusFName=?,CusLName=?,Address=?,Contact=? WHERE CusID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, customer.getCustomerFName( ));
            pstm.setString(2, customer.getCustomerLName( ));
            pstm.setString(3, customer.getCustomerAddress( ));
            pstm.setInt(4, customer.getCustomerContact( ));
            pstm.setString(5, customer.getCustomerID( ));

            isUpdateCustomer = pstm.executeUpdate( ) > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return isUpdateCustomer;
    }

    @FXML
    void btnCustomerUpdateMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblCustomerTableMouseClick(MouseEvent event) {
       if(event.getButton().equals(MouseButton.PRIMARY)){
           int index = tblCustomerTable.getSelectionModel( ).getSelectedIndex( );
           Customer customer = tblCustomerTable.getItems( ).get(index);
           txtCustomerIDCForm.setText(customer.getCustomerID( ));
           txtCustomerIFNameCForm.setText(customer.getCustomerFName( ));
           txtCustomerILNameCForm.setText(customer.getCustomerLName( ));
           txtCustomerAddressCForm.setText(customer.getCustomerAddress( ));
           txtCustomerContactCForm.setText(Integer.toString(customer.getCustomerContact( )));
       }
    }
    @FXML
    void tblCustomerTableClick(ActionEvent event) {
        //wrong
    }

    @FXML
    void txtCustomerAddressCFormEntered(ActionEvent event) {

    }

    @FXML
    void txtCustomerContactCFormEntered(ActionEvent event) {

    }

    @FXML
    void txtCustomerIDCFormEntered(ActionEvent event) {

    }

    @FXML
    void txtCustomerIFNameCFormEnterd(ActionEvent event) {

    }

    @FXML
    void txtCustomerILNameCFormEntered(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCustomerTable.getColumns( ).get(0).setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        tblCustomerTable.getColumns( ).get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerFName"));
        tblCustomerTable.getColumns( ).get(2).setCellValueFactory(new PropertyValueFactory<>("CustomerLName"));
        tblCustomerTable.getColumns( ).get(3).setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        tblCustomerTable.getColumns( ).get(4).setCellValueFactory(new PropertyValueFactory<>("CustomerContact"));

        if(getCustomerDetail()!=null){
            loadCustomerTable();
        }else {

        }

    }

    private void loadCustomerTable() {
        ArrayList<Customer> customerDetail = getCustomerDetail( );
        tblCustomerTable.setItems(FXCollections.observableArrayList(customerDetail));
    }

    public ArrayList<Customer> getCustomerDetail() {
        try {
            Connection connection = DBConnetion.getInstance( ).getConnection( );
            String sql = "SELECT * FROM Customer";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet set = pstm.executeQuery( );
            ArrayList<Customer> list = new ArrayList<>( );
            while (set.next( )) {
                list.add(new Customer(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getInt(5)));
            }
            return list;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


        return null;
    }

}
