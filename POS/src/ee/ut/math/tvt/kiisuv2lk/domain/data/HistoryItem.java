package ee.ut.math.tvt.kiisuv2lk.domain.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="HISTORYITEM")
public class HistoryItem implements Cloneable, DisplayableItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@OneToMany(mappedBy="historyItem")
    private List<SoldItem> soldItems;
	
	@Transient
    private StockItem STI;
	
	@Transient
    private double price;
	
	@Transient
	private String date;
	
	@Transient
	private String time;
	
	@Transient
	private double sum;
	
	@Transient
	private int AmountOfELements;
	
	public HistoryItem(List<SoldItem> listsold,
			String date, String time, Long id) {
		super();
		this.STI=listsold.get(0).getStockItem();
		this.AmountOfELements=listsold.size();
		this.soldItems = listsold;
		this.id = id;
		this.date=date;
		this.time=time;
		this.price=STI.getPrice();
		this.sum=totalSum();
		//this.price=soldItem;
	}

	public HistoryItem(){
		
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
		return id;
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




	public void setId(Long id) {
		this.id = id;
	}




	public int getAmountOfELements() {
		return AmountOfELements;
	}




	public void setAmountOfELements(int amountOfELements) {
		AmountOfELements = amountOfELements;
	}







}
	