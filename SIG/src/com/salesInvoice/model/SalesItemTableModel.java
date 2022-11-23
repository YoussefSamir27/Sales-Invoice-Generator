
package com.salesInvoice.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class SalesItemTableModel extends AbstractTableModel{
    
    private ArrayList<SalesItemModel> items;
    private String[] col = {"ID", "Item Name", "Item Price", "Count", "Total"};

    public SalesItemTableModel(ArrayList<SalesItemModel> items) {
        this.items = items;
    }

    
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int c) {
        return col[c];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        SalesItemModel item = items.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return item.getInvoice().getInvoiceId();
            case 1: return item.getItemName();
            case 2: return item.getItemPrice();
            case 3: return item.getQuantity();
            case 4: return item.getItemTotalPrice();
            default : return "";
        }
    }

    public ArrayList<SalesItemModel> getItems() {
        return items;
    }
    
    
}
