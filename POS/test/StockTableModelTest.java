import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.kiisuv2lk.ui.model.StockTableModel;

public class StockTableModelTest {
    private StockItem item;
    //private StockItem item_id;
    private StockItem item2;
    private StockItem item2_name;

    @Before
    public void setUp() throws Exception {
	item = new StockItem((long) 99, "Mahl", "õunamahl", 2.0);
	//item_id = new StockItem((long) 101, "Mahl", "õunamahl", 2.0);
	item2 = new StockItem((long) 100, "Morss", "õunamorss", 2.0, 10);
	item2_name = new StockItem((long) 100, "Morss2", "õunamorss", 2.0);

    }

    // meil ei ole konkreetset nime unikaalsuse kontrolli koodis...
    // kui oleks näeks testklass midagi sellist välja...
    // @Test (expected=VerificationFailedException.class)
    // public void testValidateNameUniqueness() throws VerificationFailedException{
    //		StockTableModel model = new StockTableModel();
    // 		model.addItem(item);
    // 		model.addItem(item_id);
    // }

    // teen selle asemel id unikaalsuse kontrolli
    @Test(expected = VerificationFailedException.class)
    public void testValidateIdUniqueness() throws VerificationFailedException{
	StockTableModel model = new StockTableModel();
	model.addItem(item2);
	model.addItem(item2_name);
    }

    @Test
    public void testHasEnoughInStock() throws VerificationFailedException{
	StockTableModel model = new StockTableModel();
	model.addItem(item);
	model.addItem(item2);
	assertEquals(model.getRowCount(), 2);
    }

    @Test
    public void testGetItemByIdWhenItemExists() throws VerificationFailedException {
	StockTableModel model = new StockTableModel();
	model.addItem(item);
	assertSame(model.getItemById(item.getId()), item);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetItemByIdWhenThrowsException() throws VerificationFailedException{
	StockTableModel model = new StockTableModel();
	model.addItem(item);
	model.getItemById(999);
    }
    
    //when stock is empty
    @Test(expected = NoSuchElementException.class)
    public void testGetItemByIdWhenEmptyStock(){
	StockTableModel model = new StockTableModel();
	model.getItemById(999);
    }
    
}

