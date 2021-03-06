package ee.ut.math.tvt.kiisuv2lk.ui.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Text field on the dialogPane
    private JComboBox<StockItem> barCodeComboBox;
    private JTextField quantityField;
    private JTextField nameField;
    private JTextField priceField;

    private JButton addItemButton;

    // Warehouse model
    private SalesSystemModel model;

    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public PurchaseItemPanel(SalesSystemModel model) {
	this.model = model;

	setLayout(new GridBagLayout());

	add(drawDialogPane(), getDialogPaneConstraints());
	add(drawBasketPane(), getBasketPaneConstraints());

	setEnabled(false);
    }

    // shopping cart pane
    private JComponent drawBasketPane() {

	// Create the basketPane
	JPanel basketPane = new JPanel();
	basketPane.setLayout(new GridBagLayout());
	basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

	// Create the table, put it inside a scollPane,
	// and add the scrollPane to the basketPanel.
	JTable table = new JTable(model.getCurrentPurchaseTableModel());
	JScrollPane scrollPane = new JScrollPane(table);

	basketPane.add(scrollPane, getBacketScrollPaneConstraints());

	return basketPane;
    }

    // purchase dialog
    private JComponent drawDialogPane() {

	// Create the panel
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(5, 2));
	panel.setBorder(BorderFactory.createTitledBorder("Product"));

	addBarCodeComboBox();
	// Initialize the textfields
	quantityField = new JTextField("1");
	nameField = new JTextField();
	priceField = new JTextField();

	nameField.setEditable(false);
	priceField.setEditable(false);

	// == Add components to the panel

	// - bar code
	panel.add(new JLabel("Bar code:"));
	panel.add(barCodeComboBox);

	// - amount
	panel.add(new JLabel("Amount:"));
	panel.add(quantityField);

	// - name
	panel.add(new JLabel("Name:"));
	panel.add(nameField);

	// - price
	panel.add(new JLabel("Price:"));
	panel.add(priceField);

	// Create and add the button
	addItemButton = new JButton("Add to cart");
	addItemButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		addItemEventHandler();
	    }
	});

	panel.add(addItemButton);

	return panel;
    }

    private void addBarCodeComboBox() {
	barCodeComboBox = new JComboBox<StockItem>();

	// add stock items from warehouse model
	setComboBoxElements();
	barCodeComboBox.setSelectedItem(null);

	// add action listeners to fill fields with selected item data
	barCodeComboBox.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		fillDialogFields();
	    }
	});

	// add custom renderer to display only item's name in drop menu
	barCodeComboBox.setRenderer(new DefaultListCellRenderer() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value != null) {
		    StockItem stockItem = (StockItem) value;
		    setText(stockItem.getName());
		}
		return this;
	    }

	});

    }

    private void setComboBoxElements() {
	List<StockItem> stockItemList = model.getWarehouseTableModel().getTableRows();
	for (StockItem stockItem : stockItemList) {
	    barCodeComboBox.addItem(stockItem);
	}
    }

    // Fill dialog with data from the "database".
    public void fillDialogFields() {
	StockItem stockItem = (StockItem) barCodeComboBox.getSelectedItem();

	if (stockItem != null) {
	    nameField.setText(stockItem.getName());
	    String priceString = String.valueOf(stockItem.getPrice());
	    priceField.setText(priceString);
	} else {
	    reset();
	}
    }

    // Search the warehouse for a StockItem with the bar code of selected item
    private StockItem getSelectedStockItem() {
	return (StockItem) barCodeComboBox.getSelectedItem();
    }

    /**
     * Add new item to the cart.
     */
    public void addItemEventHandler() {
	// add chosen item to the shopping cart.
	StockItem stockItem = getSelectedStockItem();
	if (stockItem != null) {
	    int quantity;
	    try {
		quantity = Integer.parseInt(quantityField.getText());
	    } catch (NumberFormatException ex) {
		quantity = 1;
	    }
	    // Do only if there is enough stock
	    long id = stockItem.getId();
	    int basketItemQuantity;
	    try {
		basketItemQuantity = model.getCurrentPurchaseTableModel().getItemById(id).getQuantity();
	    } catch (NoSuchElementException e) {
		basketItemQuantity = 0;
	    }
	    if (basketItemQuantity + quantity <= stockItem.getQuantity()) {
		model.getCurrentPurchaseTableModel().addItem(new SoldItem(stockItem, quantity));
	    } else {
		JOptionPane.showMessageDialog(null, "Not enough items in stock!");
	    }
	}
    }

    /**
     * Sets whether or not this component is enabled.
     */
    @Override
    public void setEnabled(boolean enabled) {
	this.addItemButton.setEnabled(enabled);
	this.barCodeComboBox.setEnabled(enabled);
	this.quantityField.setEnabled(enabled);
    }

    private void refreshBarCodeComboBox() {
	this.barCodeComboBox.removeAllItems();
	setComboBoxElements();
	barCodeComboBox.setSelectedItem(null);
    }

    /**
     * Reset dialog fields.
     */
    public void reset() {
	refreshBarCodeComboBox();
	quantityField.setText("1");
	nameField.setText("");
	priceField.setText("");
    }

    /*
     * === Ideally, UI's layout and behavior should be kept as separated as
     * possible. If you work on the behavior of the application, you don't want
     * the layout details to get on your way all the time, and vice versa. This
     * separation leads to cleaner, more readable and better maintainable code.
     * 
     * In a Swing application, the layout is also defined as Java code and this
     * separation is more difficult to make. One thing that can still be done is
     * moving the layout-defining code out into separate methods, leaving the
     * more important methods unburdened of the messy layout code. This is done
     * in the following methods.
     */

    // Formatting constraints for the dialogPane
    private GridBagConstraints getDialogPaneConstraints() {
	GridBagConstraints gc = new GridBagConstraints();

	gc.anchor = GridBagConstraints.WEST;
	gc.weightx = 0.2;
	gc.weighty = 0d;
	gc.gridwidth = GridBagConstraints.REMAINDER;
	gc.fill = GridBagConstraints.NONE;

	return gc;
    }

    // Formatting constraints for the basketPane
    private GridBagConstraints getBasketPaneConstraints() {
	GridBagConstraints gc = new GridBagConstraints();

	gc.anchor = GridBagConstraints.WEST;
	gc.weightx = 0.2;
	gc.weighty = 1.0;
	gc.gridwidth = GridBagConstraints.REMAINDER;
	gc.fill = GridBagConstraints.BOTH;

	return gc;
    }

    private GridBagConstraints getBacketScrollPaneConstraints() {
	GridBagConstraints gc = new GridBagConstraints();

	gc.fill = GridBagConstraints.BOTH;
	gc.weightx = 1.0;
	gc.weighty = 1.0;

	return gc;
    }

}
