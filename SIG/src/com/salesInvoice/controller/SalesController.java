
package com.salesInvoice.controller;

import com.salesInvoice.model.SalesInvoiceModel;
import com.salesInvoice.model.SalesInvoiceTableModel;
import com.salesInvoice.model.SalesItemModel;
import com.salesInvoice.model.SalesItemTableModel;
import com.salesInvoice.view.SalesInvoiceDialog;
import com.salesInvoice.view.SalesInvoiceFrame;
import com.salesInvoice.view.SalesItemDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SalesController implements ActionListener, ListSelectionListener{

    private SalesInvoiceFrame frame;
    private SalesInvoiceDialog salesInvDialog;
    private SalesItemDialog itemDialog;
    
    public SalesController(SalesInvoiceFrame frame)
    {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        
        switch(command)
        {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "Create_Invoice_Ok":
                Create_Invoice_Ok();
                break;
            case "Create_Invoice_Cancel":
                Create_Invoice_Cancel();
                break;
            case "Create_Item_Ok":
                Create_Item_Ok();
            case "Create_Item_Cancel":
                Create_Item_Cancel();
                break;
            default:
                break;
        }
    }

    private void deleteItem() {

          int rowSelected = frame.getItemsTable().getSelectedRow();
          if(rowSelected != -1)
          {
              SalesItemTableModel itemTable = (SalesItemTableModel) frame.getItemsTable().getModel();
              itemTable.getItems().remove(rowSelected);
              itemTable.fireTableDataChanged();
              frame.getInvTableModel().fireTableDataChanged();
          }
       
           
    }

    private void createNewItem() {
        itemDialog = new SalesItemDialog(frame);
        itemDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int rowSelection = frame.getSalesInvoiceTable().getSelectedRow();
        if(rowSelection > -1)
        {
            frame.getInvoices().remove(rowSelection);
            frame.getInvTableModel().fireTableDataChanged();
        }
    }

    private void createNewInvoice() {
        
        salesInvDialog = new SalesInvoiceDialog(frame);
        salesInvDialog.setVisible(true);
    }

    private void saveFile() {
        
        ArrayList<SalesInvoiceModel> salesInvoices = frame.getInvoices();
        String header = "";
        String items = "";
        for(SalesInvoiceModel inv : salesInvoices)
        {
            String invoiceCSV = inv.getCSVFile();
            header += invoiceCSV;
            header += "\n";
            
            for(SalesItemModel itm : inv.getItems())
            {
                String itemCSV = itm.getCSVFile();
                items += itemCSV;
                items += "\n";
            }
        }
        
        try{
            JFileChooser fileChooser = new JFileChooser();
            int clickSave = fileChooser.showSaveDialog(frame);
            if(clickSave == JFileChooser.APPROVE_OPTION)
            {
                File headerFile = fileChooser.getSelectedFile();
                FileWriter headerFileWriter = new FileWriter(headerFile);
                headerFileWriter.write(header);
                headerFileWriter.flush();
                headerFileWriter.close();
                
                clickSave = fileChooser.showSaveDialog(frame);
                if(clickSave == JFileChooser.APPROVE_OPTION)
                {
                    File lineFile = fileChooser.getSelectedFile();
                    FileWriter lineFileWriter = new FileWriter(lineFile);
                    lineFileWriter.write(items);
                    lineFileWriter.flush();
                    lineFileWriter.close();
                }
            }
        }catch(Exception e){
            
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int rowIndex = frame.getSalesInvoiceTable().getSelectedRow();
        if(rowIndex > -1)
        {
            System.out.println("Row "+ rowIndex + " has been selected");
            SalesInvoiceModel actualInv = frame.getInvoices().get(rowIndex);
            frame.getInvoiceID().setText(""+ actualInv.getInvoiceId());
            frame.getInvoiceDate().setText(actualInv.getDate());
            frame.getCustomerName().setText(actualInv.getCustomerName());
            frame.getTotal().setText(""+ actualInv.getTotalOfInvoice());

            SalesItemTableModel itemTableModel = new SalesItemTableModel(actualInv.getItems()); 
            frame.getItemsTable().setModel(itemTableModel);
            itemTableModel.fireTableDataChanged();
        }
    }
    
    private void loadFile() {
        int click;
        JFileChooser fileChooser = new JFileChooser();
        try{
            click = fileChooser.showOpenDialog(frame);
            if(click == JFileChooser.APPROVE_OPTION)
            {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headers = Files.readAllLines(headerPath);
                System.out.println("Read Invoices Done");
                ArrayList<SalesInvoiceModel> arrayOfInvoices = new ArrayList<>();
                for(String header : headers)
                {
                    String[] splittedParts = header.split(",");
                    int invoiceID = Integer.parseInt(splittedParts[0]);
                    String date = splittedParts[1];
                    String name = splittedParts[2];
                    
                    SalesInvoiceModel sInvM = new SalesInvoiceModel(invoiceID, date, name);
                    arrayOfInvoices.add(sInvM);
                }
                
                
                click = fileChooser.showOpenDialog(frame);
                if(click == JFileChooser.APPROVE_OPTION);
                {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Read Lines Done");
                    for(String lineLine : lineLines)
                    {
                        String[] splittedLines = lineLine.split(",");
                        int invoiceID = Integer.parseInt(splittedLines[0]);
                        String nameOfItem = splittedLines[1];
                        double priceOfItem = Double.parseDouble(splittedLines[2]);
                        int quantity = Integer.parseInt(splittedLines[3]);
                        
                        SalesInvoiceModel invoice = null;
                        for(SalesInvoiceModel inv : arrayOfInvoices)
                        {
                            if(inv.getInvoiceId() == invoiceID)
                            {
                                invoice = inv;
                                break;
                            }
                        }
                        
                        SalesItemModel item = new SalesItemModel(nameOfItem, priceOfItem, quantity, invoice);
                        invoice.getItems().add(item);
                    }
                    
                }
                
                frame.setInvoices(arrayOfInvoices);
                SalesInvoiceTableModel invTableModel = new SalesInvoiceTableModel(arrayOfInvoices);
                frame.setInvTableModel(invTableModel);
                frame.getSalesInvoiceTable().setModel(invTableModel);
                frame.getInvTableModel().fireTableDataChanged();
                
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }

    private void Create_Invoice_Ok() {
        String customerName = salesInvDialog.getCustomerNameField().getText();
        String date = salesInvDialog.getInvoiceDatefield().getText();
        
        int nextID = frame.getIdOfNextInvoice();
        SalesInvoiceModel salesInvoice = new SalesInvoiceModel(nextID, date, customerName);
        frame.getInvoices().add(salesInvoice);
        frame.getInvTableModel().fireTableDataChanged();
        salesInvDialog.setVisible(false);
        salesInvDialog.dispose();
        salesInvDialog = null;
    }

    private void Create_Invoice_Cancel() {
        salesInvDialog.setVisible(false);
        salesInvDialog.dispose();
        salesInvDialog = null;    
    }

    private void Create_Item_Ok() {
        
        String itemName = itemDialog.getItemNameField().getText();
        String itemQuantity = itemDialog.getItemQuantityField().getText();
        String itemPrice = itemDialog.getItemPriceField().getText();
        
        int quantity = Integer.parseInt(itemQuantity);
        double p = Double.parseDouble(itemPrice);
        
        int invoiceSelection = frame.getSalesInvoiceTable().getSelectedRow();
        if(invoiceSelection != -1 )
        {
            SalesInvoiceModel inv = frame.getInvoices().get(invoiceSelection);
            SalesItemModel item = new SalesItemModel(itemName, p, quantity, inv);
            inv.getItems().add(item);
            SalesItemTableModel itemTableModel = (SalesItemTableModel) frame.getItemsTable().getModel();
            itemTableModel.fireTableDataChanged();
            frame.getInvTableModel().fireTableDataChanged();
        }
        
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog = null;
        if(itemDialog == null)
            itemDialog = new SalesItemDialog();
    }

    private void Create_Item_Cancel() {
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog =  null;
    }

    
    
}
