package ee.ut.math.tvt.kiisuv2lk.domain.dao;

import java.util.List;

import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;

public interface SalesSystemDao {
    
    /*
     * StockItem
     */
    void addStockItem(StockItem stockItem);

    StockItem getStockItem(Long id);

    List<StockItem> getAllStockItems();

    void updateStockItem(StockItem stockItem);

    void deleteStockItem(Long id);
    
    /*
     * SoldItem
     */
    void addSoldItem(SoldItem stockItem);
    
    void addSoldItemBatch(List<SoldItem> stockItemList);

    SoldItem getSoldItem(Long id);

    List<SoldItem> getAllSoldItems();

    void updateSoldItem(SoldItem stockItem);

    void deleteSoldItem(Long id);
    
    /*
     * Sale
     */

    Long getNextSaleId();
    
}
