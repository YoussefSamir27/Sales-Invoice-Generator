
package com.salesInvoice.model;


public class SalesItemModel {
    
    //Data
    private int quantity;
    private double itemPrice;
    private String itemName;
    private SalesInvoiceModel invoice;

    
    //Constructor
    public SalesItemModel() {
    }

    //Constructor overloading
    public SalesItemModel(int quantity, double itemPrice, String itemName) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }

    public SalesItemModel(String itemName, double itemPrice, int quantity, SalesInvoiceModel invoice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
        this.invoice = invoice;
    }

    
    //Methods
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    public double getItemTotalPrice()
    {
        return (itemPrice * quantity);
    }

    public SalesInvoiceModel getInvoice() {
        return invoice;
    }

    
    @Override
    public String toString() {
        return "SalesItemModel{" + "ID=" + invoice.getInvoiceId() + ", quantity=" + quantity + ", itemPrice=" + itemPrice + ", itemName=" + itemName + '}';
    }
    
    
    public String getCSVFile()
    {
        return invoice.getInvoiceId() + "," + itemName + "," + itemPrice + "," + quantity;
    }
    
}
