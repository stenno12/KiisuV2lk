package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.SalesSystemException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);

	private SalesSystemModel model;
	
	private List<SoldItem> rows = new ArrayList<SoldItem>();
	

    public PurchaseInfoTableModel() {
        super(new String[] { "Id", "Name", "Price", "Quantity", "Sum"});
        
    }

	public PurchaseInfoTableModel(SalesSystemModel model) {
	    this();
	    this.model = model;
	    rows = model.getCurrentSale().getSoldItems();
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}



    /**
     * Returns the total sum that needs to be paid for all the items in the basket.
     */
    public double getTotalPrice() {
        double price = 0.0;
        for (SoldItem item : rows) {
            price += item.getSum();
        }
        return price;
    }




    public static PurchaseInfoTableModel getEmptyTable() {
        return new PurchaseInfoTableModel();
    }

    /**
     * Replace the current contents of the table with the SoldItems of the given Sale.
     * (Used by the history details table in the HistoryTab).
     */
    public void showSale(Sale sale) {

        rows = sale.getSoldItems();
        fireTableDataChanged();
    }

	@Override
	public int getRowCount() {
		return rows.size();
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getColumnValue(rows.get(rowIndex), columnIndex);
	}

	@Override
	public void populateWithData(List<SoldItem> data) {
		rows.clear();
        rows.addAll(model.getCurrentSale().getSoldItems());
		
	}
	public void updatePITM() {
		rows.clear();
		rows.addAll(model.getCurrentSale().getSoldItems());
		fireTableDataChanged();
	}

	@Override
	public SoldItem getItemById(long id) {
		for (final SoldItem item : rows) {
			if (item.getId() == id)
				return item;
		}
		throw new NoSuchElementException();
	}
	
	public void clear() {
        rows = new ArrayList<SoldItem>();
        fireTableDataChanged();
    }

	@Override
	public void addRow(SoldItem row) {
		rows.add(row);
		fireTableDataChanged();
		
	}


}
