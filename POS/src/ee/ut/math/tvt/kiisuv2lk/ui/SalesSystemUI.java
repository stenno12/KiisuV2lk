package ee.ut.math.tvt.kiisuv2lk.ui;

import javax.swing.JTabbedPane;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;
import ee.ut.math.tvt.kiisuv2lk.ui.tabs.HistoryTab;
import ee.ut.math.tvt.kiisuv2lk.ui.tabs.PurchaseTab;
import ee.ut.math.tvt.kiisuv2lk.ui.tabs.StockTab;

/**
 * Graphical user interface of the sales system.
 */
public class SalesSystemUI extends SalesSystemBaseFrame {

    private static final long serialVersionUID = 1L;

    private final SalesDomainController domainController;

    private SalesSystemModel model;

    private JTabbedPane tabbedPane;

    public SalesSystemUI(SalesDomainController domainController) {
	super(domainController);
	this.domainController = domainController;
	this.model = new SalesSystemModel(domainController);
	
	createTabbedPane();
	addPurchaseTab();
	addHistoryTab();
	addStockTab();
	addTabbedPane();
	
    }

    private void addTabbedPane() {
	getContentPane().add(tabbedPane);
    }

    private void createTabbedPane() {
	tabbedPane = new JTabbedPane();
    }

    private void addStockTab() {
	StockTab stockTab= new StockTab(model);
	tabbedPane.add("Warehouse", stockTab.draw());
    }

    private void addHistoryTab() {
	HistoryTab historyTab = new HistoryTab(model);
	tabbedPane.add("History", historyTab.draw());
    }

    private void addPurchaseTab() {
	PurchaseTab purchaseTab = new PurchaseTab(domainController, model);
	tabbedPane.add("Point-of-sale", purchaseTab.draw());
    }

}
