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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;

public class HistoryItemDetailsWindow extends JFrame{
	private SalesSystemModel model; 
	private HistoryItem HItem;
	private JButton OKButton;
	public HistoryItemDetailsWindow(SalesSystemModel model,HistoryItem HItem) {
		this.model=model;
		this.HItem=HItem;
		setSize(400, 600);
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
	    
	    OKButton= createOKButton();

	    

	    panel.add(OKButton, gc);		  
	    
	    gc.gridx=2;
	    
	    panel.add(OKButton, gc);

	    return panel;
	  }
	protected void cancelButtonClicked() {
		this.dispose();
		
	}	
	private JButton createOKButton() {
	    JButton b = new JButton("OK");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  cancelButtonClicked();
	      }
	    });
	    return b;
	}
	   
}
