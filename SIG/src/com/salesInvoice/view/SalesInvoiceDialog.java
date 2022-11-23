
package com.salesInvoice.view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SalesInvoiceDialog extends JDialog{
    
    private JTextField invoiceDatefield;
    private JTextField customerNameField;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel invoiceDateLabel;
    private JLabel customerNameLabel;
    
    
    public SalesInvoiceDialog(SalesInvoiceFrame frame)
    {
        customerNameLabel = new JLabel(" Customer Name : ");
        customerNameField = new JTextField(20);
        
        invoiceDateLabel = new JLabel(" Invoice Date : ");
        invoiceDatefield = new JTextField(20);
        
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("Create_Invoice_Ok");
        cancelButton.setActionCommand("Create_Invoice_Cancel");
        
        okButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());
        
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLabel);
        add(invoiceDatefield);
        add(customerNameLabel);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
        
    }

    public JTextField getInvoiceDatefield() {
        return invoiceDatefield;
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }
}
