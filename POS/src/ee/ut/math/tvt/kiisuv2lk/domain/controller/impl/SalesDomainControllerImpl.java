package ee.ut.math.tvt.kiisuv2lk.domain.controller.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.SoldItem;
import ee.ut.math.tvt.kiisuv2lk.domain.data.StockItem;
import ee.ut.math.tvt.kiisuv2lk.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;


/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	// simulatin database for history items
	List<HistoryItem> historydataset = new ArrayList<HistoryItem>();
	
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
//		throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		List<StockItem> dataset = new ArrayList<StockItem>();

		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

		dataset.add(chips);
		dataset.add(chupaChups);
		dataset.add(frankfurters);
		dataset.add(beer);
		
		return dataset;
	}

	
	public List<HistoryItem> loadHistoryState() {
		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
		SoldItem sold =new SoldItem(chips, 1);
		SoldItem sold2 =new SoldItem(frankfurters, 2);
		List<SoldItem> listsold=new ArrayList<SoldItem>();
		listsold.add(sold);
		
		HistoryItem newElem= new HistoryItem(listsold, "Oct 26 2014","18:58:41",(long) 0);
		historydataset.add(newElem);
		
		listsold.add(sold2);
		HistoryItem newElem2= new HistoryItem(listsold, "Oct 26 2014","18:58:41",(long) 1);
		
		historydataset.add(newElem2);
		return historydataset;
	}
	//save historyitem to history database (simulated)

	@Override
	public void saveHistoryState(List<SoldItem> goods){
        Date date = new Date();
        String[] parts = date.toString().split(" ");
        Long id= (long) historydataset.size();
		HistoryItem newElem= new HistoryItem(goods, parts[1]+" "+parts[2]+" "+parts[5], parts[4],id);
		historydataset.add(newElem);
		
		System.out.println("Now elements in history: "+ historydataset.size() );
	}

	@Override
	public List<SoldItem> loadDetailedHistoryState() {
		// TODO Auto-generated method stub
		return null;
	}

}
