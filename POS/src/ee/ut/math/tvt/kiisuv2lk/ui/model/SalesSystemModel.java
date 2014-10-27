package ee.ut.math.tvt.kiisuv2lk.ui.model;

import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
    
    private static final Logger log = Logger.getLogger(SalesSystemModel.class);
    //historydetailtablemodel
    private HistoryItemDetailTableModel historydetailtablemodel;
    //History model 
    private HistoryTableModel histoyTableModel;
    
    // Warehouse model
    private StockTableModel warehouseTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        historydetailtablemodel = new HistoryItemDetailTableModel();
        histoyTableModel= new HistoryTableModel();
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        // populate history model with data from history
        histoyTableModel.populateWithData(domainController.loadHistoryState());
        //historydetailtablemodel.populateWithData(domainController.loadDetailedDeatailHistoryState());
    }
    
    public HistoryTableModel getHistorytableModel() {
        return histoyTableModel;
    }
    public HistoryItemDetailTableModel getHistoryItemDetailTableModel() {
		return historydetailtablemodel;
	}
    
    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }

	
    
}
