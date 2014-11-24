package ee.ut.math.tvt.kiisuv2lk.domain.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import ee.ut.math.tvt.kiisuv2lk.domain.dao.SalesSystemDao;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.salessystem.util.HibernateUtil;

public class SalesSystemDaoImpl implements SalesSystemDao {

    private Map<Long, StockItem> stockItems;

    private Map<Long, SoldItem> soldItems;

    private static long stockItemId = 1;

    private static long soldItemId = 1;
    
    private static long saleId = 1;
    
    public Session session = HibernateUtil.currentSession();

    public SalesSystemDaoImpl() {
	init();
    }

    private void init() {
	stockItems = new HashMap<Long, StockItem>();
	soldItems = new HashMap<Long, SoldItem>();
	addSampleItems();
    }

    private void addSampleItems() {
	StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
	StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

	addStockItem(chips);
	addStockItem(chupaChups);
	addStockItem(frankfurters);
	addStockItem(beer);
	
	Long nextSaleId = getNextSaleId();
	SoldItem soldItem = new SoldItem(chips, 1);
	soldItem.setSaleId(nextSaleId);
	addSoldItem(soldItem);
    }

    /*
     * StockItem
     */
    @Override
    public void addStockItem(StockItem stockItem) {
	stockItems.put(stockItemId, stockItem);
	stockItemId++;
    }

    @Override
    public StockItem getStockItem(Long id) {
	return stockItems.get(id);
    }

    @Override
    public List<StockItem> getAllStockItems() {
	return new ArrayList<StockItem>(stockItems.values());
    }

    @Override
    public void updateStockItem(StockItem stockItem) {
	stockItems.put(stockItem.getId(), stockItem);
    }

    @Override
    public void deleteStockItem(Long id) {
	stockItems.remove(id);
    }

    /*
     * SoldItem
     */
    @Override
    public void addSoldItem(SoldItem soldItem) {
	soldItems.put(soldItemId, soldItem);
	soldItemId++;
    }
    
    @Override
    public void addSoldItemBatch(List<SoldItem> stockItemList) {
	for (SoldItem soldItem : stockItemList) {
	    addSoldItem(soldItem);
	}
    }

    @Override
    public SoldItem getSoldItem(Long id) {
	return soldItems.get(id);
    }

    @Override
    public List<SoldItem> getAllSoldItems() {
	return new ArrayList<SoldItem>(soldItems.values());
    }

    @Override
    public void updateSoldItem(SoldItem SoldItem) {
	soldItems.put(SoldItem.getId(), SoldItem);
    }

    @Override
    public void deleteSoldItem(Long id) {
	soldItems.remove(id);
    }

    @Override
    public Long getNextSaleId() {
	return saleId++;
    }
    
}
