import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;

public class HistoryItemTest {
    HistoryItem historyI;
    List<SoldItem> soldIs;
    SoldItem soldI1;
    SoldItem soldI2;
    Date date;
    Double expectedsum;
    Double sum;

    @Before
    public void setUp() throws Exception {
	date = new Date();
	historyI = new HistoryItem(soldIs, date, Long.valueOf(45));

    }

    @Test
    public void testAddSoldItem() {

    }

    @Test
    public void testGetSumWithNoItems() {
	sum = historyI.getTotalPrice();
	expectedsum = (Double) 0.0;

	assertTrue(sum.equals(expectedsum));
    }

    @Test
    public void testGetSumWithOneItem() {

    }

    @Test
    public void testGetSumWithMultipleItems() {

    }
}
