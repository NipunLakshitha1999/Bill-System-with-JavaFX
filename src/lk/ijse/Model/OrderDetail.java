package lk.ijse.Model;

public class OrderDetail {
    private String orderID;
    private String itemID;
    private String itemName;
    private int orderQTY;
    private double orderPrice;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String itemID, String itemName, int orderQTY, double orderPrice) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.orderQTY = orderQTY;
        this.orderPrice = orderPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderID='" + orderID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", orderQTY=" + orderQTY +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
