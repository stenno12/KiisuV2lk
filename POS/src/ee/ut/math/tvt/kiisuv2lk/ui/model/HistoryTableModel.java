package ee.ut.math.tvt.kiisuv2lk.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;

/**
 * History table model.
 */

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public HistoryTableModel() {
		super(new String[] {"Date", "Time", "Bill"});
	}

	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}


}


