import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;

public class HistoryItemTest {
    Date date;
    Double expectedsum;
    Double sum;
    StockItem item;
    StockItem itemm;

    @Before
    public void setUp() throws Exception {
	date = new Date();

    }

    @Test
    public void testAddSoldItem() {
	List<SoldItem> soldIs = new ArrayList<SoldItem>();
	HistoryItem historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

	item = new StockItem((long) 99, "Vesi", "vesii", 1.0);
	SoldItem sold = new SoldItem(item, 2);

	historyI.addSoldItem(sold);
	sum = historyI.getTotalPrice();
	assertEquals(sum, 2.0, 0.00001);

    }

    @Test
    public void testGetSumWithNoItems() {
	List<SoldItem> soldIs = new ArrayList<SoldItem>();
	HistoryItem historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

	sum = historyI.getTotalPrice();

	assertEquals(sum, 0.0, 0.00001);
    }

    @Test
    public void testGetSumWithOneItem() {
	List<SoldItem> soldIs = new ArrayList<SoldItem>();
	item = new StockItem((long) 99, "Vesi", "vesii", 1.0);
	SoldItem sold = new SoldItem(item, 2);
	soldIs.add(sold);
	HistoryItem historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

	sum = historyI.getTotalPrice();
	expectedsum = (Double) 1.0;

	assertEquals(sum, 2.0, 0.00001);
    }

    @Test
    public void testGetSumWithMultipleItems() {
	List<SoldItem> soldIs = new ArrayList<SoldItem>();
	item = new StockItem((long) 99, "Vesi", "vesii", 1.0);
	itemm = new StockItem((long) 98, "MegaVesi", "megavedel vesi", 2.0);
	SoldItem sold = new SoldItem(item, 1);
	SoldItem soldd = new SoldItem(itemm, 2);
	soldIs.add(sold);
	soldIs.add(soldd);

	HistoryItem historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

	sum = historyI.getTotalPrice();
	expectedsum = (Double) 1.0;

	assertEquals(sum, 5.0, 0.00001);
    }
}
