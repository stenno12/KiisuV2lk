import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;


public class SoldItemTest {
    
    private StockItem item;
    @Before
    public void setUp() throws Exception {
	item= new StockItem((long)99, "Mahl", "õunamahl", 2.0);
    }

    @Test
    public void testGetSum() {
	SoldItem sold=new SoldItem(item, 100);
	assertEquals(sold.getSum(), 200.0, 0.00001);
    }
    
    @Test
    public void testGetSumWithZeroQuantity() {
	SoldItem sold= new SoldItem(item, 0);
	assertEquals(sold.getSum(), 0, 0.00001);
    }

}
