package ee.ut.math.tvt.kiisuv2lk.ui.windows;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;
import ee.ut.math.tvt.kiisuv2lk.ui.tabs.PurchaseTab;

public class SaleWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField payField;

    private PurchaseTab parentTab;

    // public void actionPerformed(ActionEvent e) {
    // this.dispose();
    // }

    private double sum = 0;

    public SaleWindow(final SalesSystemModel model, final SalesDomainController domainController,
	    PurchaseTab purchaseTab) {
	parentTab = purchaseTab;
	setSize(300, 300);
	setLocation(100, 100);
	
	Container sisu = getContentPane();
	sisu.setLayout(new FlowLayout());
	for (SoldItem soldItem : model.getCurrentPurchaseTableModel().getTableRows()) {
	    sum += soldItem.getSum();
	}
	JLabel sumLabel = new JLabel(String.valueOf(sum));
	payField = new JTextField(5);
	final JLabel label = new JLabel("0");

	payField.setText("0");

	JButton accept = new JButton("accept");
	accept.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    if (Double.parseDouble(payField.getText()) >= sum) {
			System.out.println("Sale confirmed");
			List<SoldItem> currentPurchaseItems = model.getCurrentPurchaseTableModel().getTableRows();
			try {
			    domainController.submitCurrentPurchase(currentPurchaseItems, model);
			    parentTab.endSale();
			} catch (VerificationFailedException e1) {
			    return;
			}
			domainController.saveHistoryState(currentPurchaseItems);
		    } else {
			System.out.println("Not enough money! Go be poor somewhere else");
		    }
		    dispose();
		    model.getCurrentPurchaseTableModel().clear();
		} catch (NumberFormatException f) {
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
	sisu.add(sumLabel);
	sisu.add(payField);
	sisu.add(label);
	sisu.add(accept);
	sisu.add(dec);

	payField.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		label.setText(String.valueOf(Double.parseDouble(payField.getText()) - sum));
	    }
	});

    }

}