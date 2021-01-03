package lk.ijse.Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String orderID;
    private String orderDate;
    private String customerID;
    private ArrayList<OrderDetail> list;

    public Order() {
    }

    public Order(String orderID, String orderDate, String customerID, ArrayList<OrderDetail> list) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.list = list;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public ArrayList<OrderDetail> getList() {
        return list;
    }

    public void setList(ArrayList<OrderDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerID='" + customerID + '\'' +
                ", list=" + list +
                '}';
    }
}
