package ee.ut.math.tvt.kiisuv2lk.domain.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.dao.SalesSystemDao;
import ee.ut.math.tvt.kiisuv2lk.domain.dao.impl.SalesSystemHibernateDaoImpl;
import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.kiisuv2lk.salessystem.util.HibernateUtil;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;

/**
 * Implementation of the sales domain controller.
 */
@SuppressWarnings("unchecked")
public class SalesDomainControllerImpl implements SalesDomainController {
    
    public Session session = HibernateUtil.currentSession();
    
    private SalesSystemDao dao = new SalesSystemHibernateDaoImpl();
    
    public void submitCurrentPurchase(List<SoldItem> soldItems, SalesSystemModel systemModel) throws VerificationFailedException {
	//verify!
	verify(soldItems);
	//save sale
	saveSale(soldItems);
	//update warehouse
	updateWareHouseState(soldItems);
	//update history tab
	systemModel.getHistorytableModel().reload(loadHistoryState());
	//update warehouse tab
	systemModel.getWarehouseTableModel().reload(dao.getAllStockItems());
    }

    private void verify(List<SoldItem> soldItems) throws VerificationFailedException {
	for (SoldItem soldItem : soldItems) {
	    StockItem stockItem = dao.getStockItem(soldItem.getStockItem().getId());
	    if(stockItem.getQuantity() < soldItem.getQuantity()){
		throw new VerificationFailedException("Not enough items");
	    }
	}
    }

    private void updateWareHouseState(List<SoldItem> soldItems) {
	for (SoldItem soldItem : soldItems) {
	    StockItem stockItem = soldItem.getStockItem();
	    int newQuantity = stockItem.getQuantity() - soldItem.getQuantity();
	    stockItem.setQuantity(newQuantity);
	    dao.updateStockItem(stockItem);
	}
    }

    private void saveSale(List<SoldItem> soldItems) {
	Long saleId = dao.getNextSaleId();
	for (SoldItem soldItem : soldItems) {
	    soldItem.setSaleId(saleId);
	}
	dao.addSoldItemBatch(soldItems);
    }

    public void cancelCurrentPurchase() throws VerificationFailedException {
	// XXX - Cancel current purchase
    }

    public void startNewPurchase() throws VerificationFailedException {
	// XXX - Start new purchase
    }

    public List<StockItem> loadWarehouseState() {
	return dao.getAllStockItems();
    }

    public List<HistoryItem> loadHistoryState() {
	List<HistoryItem> historyItemList = new ArrayList<HistoryItem>();
	
	List<SoldItem> allSoldItems = dao.getAllSoldItems();
	
	Map<Long, List<SoldItem>> soldItemsMap = new HashMap<>();
	for (SoldItem soldItem : allSoldItems) {
	    Long saleId = soldItem.getSaleId();
	    if(soldItemsMap.containsKey(saleId)){
		soldItemsMap.get(saleId).add(soldItem);
	    }else {
		soldItemsMap.put(saleId, new ArrayList<SoldItem>(Arrays.asList(soldItem)));
	    }
	}
	
	for (Map.Entry<Long, List<SoldItem>> entry : soldItemsMap.entrySet()) {
	    HistoryItem historyItem = new HistoryItem(entry.getValue(), new Date(), entry.getKey());
	    historyItemList.add(historyItem);
	}
	
	return historyItemList;
    }

    @Override
    public void saveHistoryState(List<SoldItem> soldItems) {
	for (SoldItem soldItem : soldItems) {
	    dao.addSoldItem(soldItem);
	}
    }

    @Override
    public List<SoldItem> loadDetailedHistoryState() {
	return null;
    }

    @Override
    public List<HistoryItem> loadDetailedDeatailHistoryState() {
	return null;
    }

    public void endSession() {
	HibernateUtil.closeSession();
    }

    @Override
    public void addStockItem(StockItem newItem) {
	StockItem stockItem = dao.getStockItem(newItem.getId());
	if (stockItem == null) {
	    dao.addStockItem(newItem);
	}else{
	    stockItem.setQuantity(stockItem.getQuantity() + newItem.getQuantity());
	    dao.updateStockItem(stockItem);
	}
    }

}
