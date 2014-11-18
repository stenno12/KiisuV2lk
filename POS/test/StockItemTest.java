import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;

public class StockItemTest {
    StockItem stockItem;

    @Before
    public void setUp() throws Exception {
	stockItem = new StockItem((long) 56, "vodka", "See on alkohol", 5.0, 2);
    }

    @Test
    public void testClone() {
	StockItem itemClone = stockItem.clone();
	assertTrue(stockItem.equals(itemClone));
    }

    @Test
    public void testGetColumn() {
	long id = stockItem.getId();
	assertTrue(stockItem.getColumn(0).equals(id));
    }
}
