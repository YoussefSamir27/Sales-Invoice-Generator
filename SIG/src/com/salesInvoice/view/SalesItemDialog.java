
package com.salesInvoice.view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SalesItemDialog extends JDialog{
    
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel itemNameLabel;
    private JLabel itemQuantityLabel;
    private JLabel itemPriceLabel;

    public SalesItemDialog() {
    }
    
    
    
    public SalesItemDialog(SalesInvoiceFrame frame)
    {
        itemNameLabel = new JLabel(" Item Name");
        itemNameField = new JTextField(20);
        
        itemPriceLabel = new JLabel(" Item Price");
        itemPriceField = new JTextField(20);
        
        itemQuantityLabel = new JLabel(" Item Count");
        itemQuantityField = new JTextField(20);
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("Create_Item_Ok");
        cancelButton.setActionCommand("Create_Item_Cancel");
        
        okButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel);
        add(itemNameField);
        add(itemPriceLabel);
        add(itemPriceField);
        add(itemQuantityLabel);
        add(itemQuantityField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
        
        
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemQuantityField() {
        return itemQuantityField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    
}
