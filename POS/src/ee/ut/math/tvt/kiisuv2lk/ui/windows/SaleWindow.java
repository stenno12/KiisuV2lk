package ee.ut.math.tvt.kiisuv2lk.ui.windows;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;

public class SaleWindow extends JFrame {
	
	private JTextField payField;
	
//	public void actionPerformed(ActionEvent e) {
//		this.dispose();
//	}
	
	private double sum = 0;
	
	public SaleWindow(SalesSystemModel model) {
	
	setSize(300, 300); // 
    setLocation(100, 100); // 

    Container sisu = getContentPane(); // konteineri loomine
   
    sisu.setLayout(new FlowLayout());
    
    for(SoldItem temp : model.getCurrentPurchaseTableModel().getTableRows() ){
    	sum += temp.getSum();
    	
    }
    
    JLabel silt1 = new JLabel(String.valueOf(sum));
    payField = new JTextField(5);
    final JLabel silt2 = new JLabel("0");
    
    
    
    payField.setText("0");
    
    JButton accept = new JButton("accept");
    accept.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try{
    		if (Double.parseDouble(payField.getText())>= sum){
    			System.out.println("yay");
    		}else{
    			System.out.println("Not enough money!");
    		}
    		dispose();
    		model.getCurrentPurchaseTableModel().clear();
    		} catch(NumberFormatException f){
    			System.out.println("Incorrect input!");
    		}
    		
    	}
    });
    
    JButton dec = new JButton("decline");
    dec.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		dispose();
    	}
    });
    sisu.add(silt1);
    sisu.add(payField);
    sisu.add(silt2);
    sisu.add(accept);
    sisu.add(dec);
    
  // Fill the dialog fields if the bar code text field loses focus
//  payField.addFocusListener(new FocusListener() {
//      public void focusGained(FocusEvent e) {
//      }
//
//      public void focusLost(FocusEvent e) {
//          silt2.setText(String.valueOf(Double.parseDouble(silt2.getText()) - PurchaseItemPanel.kSum));
//      }
//  });
//  payField.addVetoableChangeListener(new VetoableChangeListener() {
//      public void vetoableChange(PropertyChangeEvent e) {
//    	  silt2.setText(String.valueOf(Double.parseDouble(silt2.getText()) - PurchaseItemPanel.kSum));
//      }
//  });
    
    payField.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		silt2.setText(String.valueOf(Double.parseDouble(payField.getText()) - sum));
    	}
    });
    
    
    
    
	}

}