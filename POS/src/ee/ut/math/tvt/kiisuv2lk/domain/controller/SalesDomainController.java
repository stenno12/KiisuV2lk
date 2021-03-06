package ee.ut.math.tvt.kiisuv2lk.domain.controller;

import java.util.List;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> loadWarehouseState();

    public List<HistoryItem> loadDetailedDeatailHistoryState();

    public List<HistoryItem> loadHistoryState();

    public List<SoldItem> loadDetailedHistoryState();

    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

    /**
     * Commit business transaction - purchsae of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @param model
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(List<SoldItem> goods, SalesSystemModel model) throws VerificationFailedException;

    public void saveHistoryState(List<SoldItem> tableRows);

    public void endSession();

    public void addStockItem(StockItem newItem);

}
