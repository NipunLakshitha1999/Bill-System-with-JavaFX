package lk.ijse.Model;

public class Item {
    private String itemID;
    private String itemName;
    private int itemQTY;
    private double itemPrice;
    private double itemDiscount;

    public Item() {
    }

    public Item(String itemID, String itemName, int itemQTY, double itemPrice, double itemDiscount) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemQTY = itemQTY;
        this.itemPrice = itemPrice;
        this.itemDiscount = itemDiscount;
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

    public int getItemQTY() {
        return itemQTY;
    }

    public void setItemQTY(int itemQTY) {
        this.itemQTY = itemQTY;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQTY=" + itemQTY +
                ", itemPrice=" + itemPrice +
                ", itemDiscount=" + itemDiscount +
                '}';
    }

}
