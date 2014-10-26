package ee.ut.math.tvt.kiisuv2lk.ui.windows;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;
import ee.ut.math.tvt.kiisuv2lk.ui.model.StockTableModel;

public class AddToStockWindow extends JFrame{
	
	private Long id;
	private String name;
	private double price;
	private String description;
	private int quantity;

	private JButton addButton;
	private JButton cancelButton;

	private JTextField idField;
	private JTextField quantityField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField descriptionField;

	private JLabel idLabel;
	private JLabel quantityLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel descriptionLabel;
	
	private SalesSystemModel model;  	    

	public AddToStockWindow(SalesSystemModel model) {
		this.model=model;
		setSize(200, 300);
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((screen.width - 200) / 2, (screen.height - 300) / 2); 
	    
	    Container cont = getContentPane();
	    cont.setLayout(new FlowLayout());
	    
	    cont.add(getPane());
	    
	    pack();
	    
	}
	
	  private Component getPane() {
		    JPanel panel = new JPanel();

		    // Initialize layout
		    panel.setLayout(new GridBagLayout());
		    GridBagConstraints gc = new GridBagConstraints();
		    
		    gc.gridx=1;
		    gc.gridy=GridBagConstraints.RELATIVE;
		    
		    idLabel= new JLabel("Id");
		    quantityLabel= new JLabel("Quantity");
		    nameLabel= new JLabel("Name");
		    priceLabel= new JLabel("Price");
		    descriptionLabel = new JLabel("Description");
		    
		    idField= new JTextField(5);
		    quantityField= new JTextField(5);
		    nameField= new JTextField(5);
		    priceField= new JTextField(5);
		    descriptionField = new JTextField(5);
		    
		    addButton= createAddButton();
		    cancelButton= createCancelButton();
		    
		    panel.add(idLabel, gc);
		    panel.add(quantityLabel, gc);
		    panel.add(nameLabel, gc);
		    panel.add(priceLabel, gc);
		    panel.add(descriptionLabel, gc);
		    panel.add(addButton, gc);		  
		    
		    gc.gridx=2;
		    
		    panel.add(idField, gc);
		    panel.add(quantityField, gc);
		    panel.add(nameField, gc);
		    panel.add(priceField, gc);
		    panel.add(descriptionField, gc);

		    panel.add(cancelButton, gc);

		    return panel;
		  }
	  
	  
	
	

	private JButton createCancelButton() {
	    JButton b = new JButton("Cancel");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  cancelButtonClicked();
	      }
	    });
	    return b;
	}



	private JButton createAddButton() {
	    JButton b = new JButton("Add");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  addButtonClicked();
	      }
	    });
	    return b;
	}
	
	protected void cancelButtonClicked() {
		this.dispose();
		
	}	

	protected void addButtonClicked() {
		try{
			//if id exists 
			id=Long.parseLong(idField.getText());
			model.getWarehouseTableModel().getItemById(id);
			
			name = nameField.getText();
			description = descriptionField.getText();
			
			if(name.equals("")){
				name=model.getWarehouseTableModel().getItemById(id).getName();
			}
			
			if(description.equals("")){
				description=model.getWarehouseTableModel().getItemById(id).getDescription();
			}
			
			if(quantityField.getText().equals("")){
				quantity=model.getWarehouseTableModel().getItemById(id).getQuantity();
			}
			else{
				quantity=Integer.parseInt(quantityField.getText());	
			}
			
			if(priceField.getText().equals("")){
				price=model.getWarehouseTableModel().getItemById(id).getPrice();
			}
			else{
				price=Double.parseDouble(priceField.getText().replace(",", "."));
				price = Math.round(price * 100.0)/ 100.0;
			}
			StockItem newItem= new StockItem(id, name, description, price, quantity);
			model.getWarehouseTableModel().addItem(newItem);
			model.getWarehouseTableModel().fireTableDataChanged();
			this.dispose();
			
		}catch(NoSuchElementException e){
			try{
				id=Long.parseLong(idField.getText());
				name=nameField.getText();
				price=Double.parseDouble(priceField.getText().replace(",", "."));
				description=descriptionField.getText();
				quantity=Integer.parseInt(quantityField.getText());	

				price = Math.round(price * 100.0)/ 100.0;

				if((name.equals(""))||(description.equals(""))){
					JOptionPane.showMessageDialog(null, "Undefined input!");
				}
				else{
					StockItem newItem= new StockItem(id, name, description, price, quantity);
					model.getWarehouseTableModel().addItem(newItem);
					model.getWarehouseTableModel().fireTableDataChanged();
				}
				this.dispose();
			}catch(NumberFormatException f){
				JOptionPane.showMessageDialog(null, "Undefiend input!");
			}
		}catch(NumberFormatException d){
			JOptionPane.showMessageDialog(null, "Undefiend input!");
		}
		
	
		
	}
	
	  
	  

}