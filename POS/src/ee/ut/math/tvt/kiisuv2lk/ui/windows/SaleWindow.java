package ee.ut.math.tvt.kiisuv2lk.ui.windows;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;
import ee.ut.math.tvt.kiisuv2lk.ui.tabs.PurchaseTab;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.Date;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;

public class SaleWindow extends JFrame {
	
	private JTextField payField;
	
	private PurchaseTab parentTab;
	
//	public void actionPerformed(ActionEvent e) {
//		this.dispose();
//	}
	
	private double sum = 0;
	private static final Logger log = Logger.getLogger(PurchaseTab.class);
	
	public SaleWindow(final SalesSystemModel model, final SalesDomainController domainController, PurchaseTab purchaseTab) {
	parentTab = purchaseTab;
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
    			System.out.println("Sale confirmed");
    			try {
    				
    				
					domainController.submitCurrentPurchase(model.getCurrentPurchaseTableModel().getTableRows(),model);
					parentTab.endSale();
				} catch (VerificationFailedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			domainController.saveHistoryState(model.getCurrentPurchaseTableModel().getTableRows());
    			
    			
    		}else{
    			System.out.println("Not enough money! Go be poor somewhere else");
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