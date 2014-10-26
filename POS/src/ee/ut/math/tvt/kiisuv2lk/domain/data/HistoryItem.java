package ee.ut.math.tvt.kiisuv2lk.domain.data;

import java.util.List;

public class HistoryItem implements Cloneable, DisplayableItem {
    private List<SoldItem> soldItems;
    private StockItem STI;
    private double price;
	private String date;
	private String time;
	private double sum;
	
	public HistoryItem(List<SoldItem> listsold,
			String date, String time) {
		super();
		this.STI=listsold.get(0).getStockItem();
		this.soldItems = listsold;
		this.date=date;
		this.time=time;
		this.price=STI.getPrice();
		this.sum=totalSum();
		//this.price=soldItem;
	}




	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}




	public List<SoldItem> getSoldItems() {
		return soldItems;
	}




	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItems = soldItems;
	}




	public StockItem getSTI() {
		return STI;
	}




	public void setSTI(StockItem sTI) {
		STI = sTI;
	}

	public double totalSum(){
		sum=0;
		StockItem SI;
		
		for(int i=0;i<soldItems.size();i++){
			SI= soldItems.get(i).getStockItem();
			sum+=SI.getPrice();
		}
		return sum;
	}




	public double getSum() {
		return sum;
	}




	public void setSum(double sum) {
		this.sum = sum;
	}







}
	