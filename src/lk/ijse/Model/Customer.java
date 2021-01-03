package lk.ijse.Model;

public class Customer {
    private String CustomerID;
    private String CustomerFName;
    private String CustomerLName;
    private String CustomerAddress;
    private int CustomerContact;

    public Customer() {
    }

    public Customer(String customerID, String customerFName, String customerLName, String customerAddress, int customerContact) {
        CustomerID = customerID;
        CustomerFName = customerFName;
        CustomerLName = customerLName;
        CustomerAddress = customerAddress;
        CustomerContact = customerContact;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerFName() {
        return CustomerFName;
    }

    public void setCustomerFName(String customerFName) {
        CustomerFName = customerFName;
    }

    public String getCustomerLName() {
        return CustomerLName;
    }

    public void setCustomerLName(String customerLName) {
        CustomerLName = customerLName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public int getCustomerContact() {
        return CustomerContact;
    }

    public void setCustomerContact(int customerContact) {
        CustomerContact = customerContact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerID='" + CustomerID + '\'' +
                ", CustomerFName='" + CustomerFName + '\'' +
                ", CustomerLName='" + CustomerLName + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", CustomerContact=" + CustomerContact +
                '}';
    }
}
