package ee.ut.math.tvt.kiisuv2lk.domain.dao.impl;

import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.kiisuv2lk.domain.dao.SalesSystemDao;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.salessystem.util.HibernateUtil;

public class SalesSystemHibernateDaoImpl implements SalesSystemDao {

    public Session session = HibernateUtil.currentSession();

    /*
     * StockItem
     */
    @Override
    public void addStockItem(StockItem stockItem) {
	session.save(stockItem);
	session.flush();
    }
    
    @Override
    public StockItem getStockItem(Long id) {
	return (StockItem) session.get(StockItem.class, id);
    }

    @Override
    public List<StockItem> getAllStockItems() {
	return session.createQuery("from StockItem").list();
    }

    @Override
    public void updateStockItem(StockItem stockItem) {
	session.update(stockItem);	session.flush();

    }

    @Override
    public void deleteStockItem(Long id) {
	StockItem item = (StockItem) session.get(StockItem.class, id);
	session.delete(item);
	session.flush();

    }

    /*
     * SoldItem
     */
    @Override
    public void addSoldItem(SoldItem soldItem) {
	session.save(soldItem);
	session.flush();
    }
    
    @Override
    public void addSoldItemBatch(List<SoldItem> stockItemList) {
	for (SoldItem soldItem : stockItemList) {
	    addSoldItem(soldItem);
	}
    }

    @Override
    public SoldItem getSoldItem(Long id) {
	return (SoldItem) session.get(SoldItem.class, id);
    }

    @Override
    public List<SoldItem> getAllSoldItems() {
	return session.createQuery("from SoldItem").list();
    }

    @Override
    public void updateSoldItem(SoldItem soldItem) {
	session.update(soldItem);
	session.flush();

    }

    @Override
    public void deleteSoldItem(Long id) {
	Object item = session.get(SoldItem.class, id);
	session.delete(item);
	session.flush();
    }

    @Override
    public Long getNextSaleId() {
	//SALE_ID_SEQ
	Integer id = (Integer) session.createSQLQuery("select next value for SALE_ID_SEQ from faketable").uniqueResult();
	return id.longValue();
    }

}
