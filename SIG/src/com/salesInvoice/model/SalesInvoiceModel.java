
package com.salesInvoice.model;

import java.util.ArrayList;


public class SalesInvoiceModel {
    
    //Data
    private String customerName;
    private String date;
    private int invoiceId;
    private ArrayList<SalesItemModel> items;
    
    //Constructor
    public SalesInvoiceModel() {
    }

    //Constructor overloading
    public SalesInvoiceModel(int invoiceId, String date, String customerName) {
        this.customerName = customerName;
        this.date = date;
        this.invoiceId = invoiceId;
    }

    
    //Methods
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalOfInvoice()
    {
        double sumOfInvoice = 0.0;
        for(SalesItemModel i : getItems())
        {
            sumOfInvoice += i.getItemTotalPrice();
        }
        return sumOfInvoice;
    }
    public ArrayList<SalesItemModel> getItems() {
        if(items == null)
        {
            items = new ArrayList<>();
        }
        return items;
    }

    @Override
    public String toString() {
        return "SalesInvoiceModel{" + "customerName=" + customerName + ", date=" + date + ", invoiceId=" + invoiceId + ", items=" + items + '}';
    }

    public String getCSVFile()
    {
        return invoiceId + "," + date + "," + customerName;
    }
    
    
    
}
