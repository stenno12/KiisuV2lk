package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Purchase history model.
 */
public class PurchaseHistoryTableModel extends SalesSystemTableModel<Sale> {
	private static final long serialVersionUID = 1L;

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	private List<Sale> rows = new ArrayList<Sale>();

	public PurchaseHistoryTableModel() {
		super(new String[] { "Id", "Time", "Sum", "Client" });
	}

	@Override
	protected Object getColumnValue(Sale sale, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return sale.getId();
		case 1:
			return DATE_FORMAT.format(sale.getSellingTime());
		case 2:
			return sale.getSum();
	    case 3:
	        return sale.getClient();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final Sale sale : rows) {
			buffer.append(sale.getId() + "\t");
			//buffer.append(sale.getClient() != null ? sale.getClient().getFirstName() : "" + "\t");
			buffer.append(sale.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
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
	public Sale getItemById(long id) {
		for (final Sale item : rows) {
			if (item.getId() == id)
				return item;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void populateWithData(List<Sale> data) {
		rows.clear();
		rows.addAll(data);
		
	}

	public void addRow(Sale sale) {
		rows.add(sale);
		fireTableDataChanged();
		
	}

	public Sale getRow(int selectedRow) {
		return rows.get(selectedRow);
	}
}
