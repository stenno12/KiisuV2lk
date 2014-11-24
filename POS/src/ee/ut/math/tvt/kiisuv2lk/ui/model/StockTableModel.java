package ee.ut.math.tvt.kiisuv2lk.ui.model;

import java.util.List;
import java.util.NoSuchElementException;

import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {
    private static final long serialVersionUID = 1L;

    public StockTableModel() {
	super(new String[] { "Id", "Name", "Price", "Quantity" });
    }
    
    //testimiseks vajalik
    public void addItem(final StockItem stockItem) throws VerificationFailedException {
	try {
	    StockItem item = getItemById(stockItem.getId());
	    if(item.getName().equals(stockItem.getName()) == false)
		throw new VerificationFailedException("ID sama, erinev nimi");
	    item.setQuantity(item.getQuantity() + stockItem.getQuantity());
	    item.setQuantity(item.getQuantity() + stockItem.getQuantity());
	}
	catch (NoSuchElementException e) {
	    rows.add(stockItem);
	}
	fireTableDataChanged();
    }

    @Override
    protected Object getColumnValue(StockItem item, int columnIndex) {
	switch (columnIndex) {
	case 0:
	    return item.getId();
	case 1:
	    return item.getName();
	case 2:
	    return item.getPrice();
	case 3:
	    return item.getQuantity();
	}
	throw new IllegalArgumentException("Column index out of range");
    }

    public void reload(List<StockItem> stockItems){
	rows.clear();
	rows.addAll(stockItems);
	fireTableDataChanged();
    }

    @Override
    public String toString() {
	final StringBuffer buffer = new StringBuffer();

	for (int i = 0; i < headers.length; i++)
	    buffer.append(headers[i] + "\t");
	buffer.append("\n");

	for (final StockItem stockItem : rows) {
	    buffer.append(stockItem.getId() + "\t");
	    buffer.append(stockItem.getName() + "\t");
	    buffer.append(stockItem.getPrice() + "\t");
	    buffer.append(stockItem.getQuantity() + "\t");
	    buffer.append("\n");
	}

	return buffer.toString();
    }

}
