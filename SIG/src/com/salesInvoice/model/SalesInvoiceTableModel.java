
package com.salesInvoice.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;



public class SalesInvoiceTableModel extends AbstractTableModel{
   
    private ArrayList<SalesInvoiceModel> invoices;
    private String[] col = {"ID", "Date", "Customer Name", "Total"};
    
    //Constructor
    public SalesInvoiceTableModel(ArrayList<SalesInvoiceModel> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }
    
    @Override
    public String getColumnName(int c)
    {
        return col[c];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        SalesInvoiceModel inv = invoices.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return inv.getInvoiceId();
            case 1: return inv.getDate();
            case 2: return inv.getCustomerName();
            case 3: return inv.getTotalOfInvoice();
            default : return "";
        }
        
    }

   
    
}
