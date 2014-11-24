package ee.ut.math.tvt.kiisuv2lk.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;

/**
 * History table model.
 */

public class HistoryItemDetailTableModel extends SalesSystemTableModel<SoldItem> {
    private static final long serialVersionUID = 1L;

    public HistoryItemDetailTableModel() {
	super(new String[] { "Name", "Price", "Quantity", "Sum" });
    }

    @Override
    protected Object getColumnValue(SoldItem item, int columnIndex) {

	    switch (columnIndex) {
	    case 0:
		return item.getName();
	    case 1:
		return item.getPrice();
	    case 2:
		return item.getQuantity();
	    case 3:
		return item.getSum();
	    }
	    throw new IllegalArgumentException("Column index out of range");
    }

}
