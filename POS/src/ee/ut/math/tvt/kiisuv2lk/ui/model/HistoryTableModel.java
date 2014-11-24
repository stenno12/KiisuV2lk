package ee.ut.math.tvt.kiisuv2lk.ui.model;

import java.util.List;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;

/**
 * History table model.
 */

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
    private static final long serialVersionUID = 1L;

    public HistoryTableModel() {
	super(new String[] { "Id", "Date", "Time", "Bill" });
    }

    @Override
    public Object getColumnValue(HistoryItem item, int columnIndex) {
	switch (columnIndex) {
	case 0:
	    return item.getId();
	case 1:
	    return item.getDate();
	case 2:
	    return item.getTime();
	case 3:
	    return item.getTotalPrice();
	}
	throw new IllegalArgumentException("Column index out of range");
    }

    public void reload(List<HistoryItem> historyItemList) {
	rows.clear();
	rows.addAll(historyItemList);
	fireTableDataChanged();
    }

}
