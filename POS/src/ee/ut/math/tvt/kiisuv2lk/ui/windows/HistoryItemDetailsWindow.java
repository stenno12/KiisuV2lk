package ee.ut.math.tvt.kiisuv2lk.ui.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

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
	    draw();
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
	protected void OKClicked() {
		this.dispose();
		
	}	
	private JButton createOKButton() {
	    JButton b = new JButton("OK");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  OKClicked();
	      }
	    });
	    return b;
	}
	public Component draw() {
		System.out.println("MJAUU");
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 0d;


        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryDetailMainPane(), gc);
        
        return panel;
      }
	// table of the purchase history
    private Component drawHistoryDetailMainPane() {
      JPanel panel = new JPanel();

      //final JTable table = new JTable(model.getHistoryItemDetailTableModel());
      final JTable table = new JTable(model.getWarehouseTableModel());
      

      
      JTableHeader header = table.getTableHeader();
      header.setReorderingAllowed(false);

      JScrollPane scrollPane = new JScrollPane(table);

      GridBagConstraints gc = new GridBagConstraints();
      GridBagLayout gb = new GridBagLayout();
      gc.fill = GridBagConstraints.BOTH;
      gc.weightx = 1.0;
      gc.weighty = 1.0;

      panel.setLayout(gb);
      panel.add(scrollPane, gc);

      panel.setBorder(BorderFactory.createTitledBorder("Purchase history"));
      return panel;
    }
	   
}