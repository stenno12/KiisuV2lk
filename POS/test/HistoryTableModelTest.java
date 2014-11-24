import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.ui.model.HistoryTableModel;

public class HistoryTableModelTest {
    StockItem item;
    Date date;

    @Before
    public void setUp() throws Exception {
	date = new Date();
	item = new StockItem((long) 99, "Vesi", "vesii", 1.0);
    }

    @Test(expected = NoSuchElementException.class)
    public void EmptyModelgetItemTest() {
	HistoryTableModel model = new HistoryTableModel();
	model.getItemById(0);
    }

    @Test
    public void getColumnValueTest() {
	HistoryTableModel model = new HistoryTableModel();

	List<SoldItem> soldIs = new ArrayList<SoldItem>();
	SoldItem sold = new SoldItem(item, 2);
	soldIs.add(sold);
	HistoryItem historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

	Object id = model.getColumnValue(historyI, 0);
	assertEquals((Long) id, Long.valueOf(45));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getOutofRangeColumnValueTest() {
	HistoryTableModel model = new HistoryTableModel();

	List<SoldItem> soldIs = new ArrayList<SoldItem>();
	SoldItem sold = new SoldItem(item, 2);
	soldIs.add(sold);
	HistoryItem historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

	Object id = model.getColumnValue(historyI, 44664);
	assertEquals((Long) id, Long.valueOf(45));
    }
}
