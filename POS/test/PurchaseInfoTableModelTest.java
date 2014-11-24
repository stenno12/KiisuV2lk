import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {
    private StockItem item;
    private SoldItem sold;
    private StockItem item2;
    private SoldItem sold2;
    private StockItem item1;
    private SoldItem sold1;

    @Before
    public void setUp() throws Exception {
	item = new StockItem((long) 1, "mahl", "õunamahl", 2.0);
	sold = new SoldItem(item, 1);

	item1 = new StockItem((long) 2, "morss", "õunamorss", 2.0);
	sold1 = new SoldItem(item1, 1);

	item2 = new StockItem((long) 3, "siirup", "õunasiirup", 2.0);
	sold2 = new SoldItem(item2, 1);
    }

    // "Id", "Name", "Price", "Quantity", "Sum"
    @Test
    public void testColumns() {
	PurchaseInfoTableModel model = new PurchaseInfoTableModel();
	model.addItem(sold);
	assertEquals(sold.getId(), (long) model.getValueAt(0, 0), 0.0001);
	assertEquals(sold.getName(), model.getValueAt(0, 1));
	assertEquals(sold.getPrice(), (double) model.getValueAt(0, 2), 0.0001);
	assertEquals(sold.getQuantity(), (int) model.getValueAt(0, 3), 0.0001);
	assertEquals(sold.getSum(), (double) model.getValueAt(0, 4), 0.0001);
    }

    @Test
    public void testAddingMultipleItems() {
	PurchaseInfoTableModel model = new PurchaseInfoTableModel();
	model.addItem(sold);
	model.addItem(sold1);
	model.addItem(sold2);
	assertEquals(sold.getId(), (long) model.getValueAt(0, 0), 0.0001);
	assertEquals(sold1.getId(), (long) model.getValueAt(1, 0), 0.0001);
	assertEquals(sold2.getId(), (long) model.getValueAt(2, 0), 0.0001);
	assertEquals(model.getRowCount(), 3);
    }

}
