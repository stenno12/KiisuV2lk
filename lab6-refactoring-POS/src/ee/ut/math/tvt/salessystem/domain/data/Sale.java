package ee.ut.math.tvt.salessystem.domain.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.dialect.FirebirdDialect;

import ee.ut.math.tvt.salessystem.domain.exception.SalesSystemException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Sale object. Contains client and sold items.
 */
@Entity
@Table(name = "SALE")
public class Sale implements DisplayableItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = SoldItem.class, mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SoldItem> soldItems = new ArrayList<SoldItem>();
    private Date sellingTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    
    /** Empty constructors are used by hibernate */
    public Sale() {
    }
    
    public Sale(Client client) {
    	this.client = client;
    }
    
    
    

    public Sale(Client client, List<SoldItem> goods) {
    	this.client = client;
        this.soldItems = goods;
        this.sellingTime = new Date();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getSellingTime() {
        return sellingTime;
    }

    public void setSellingTime(Date sellingTime) {
        this.sellingTime = sellingTime;
    }

    public List<SoldItem> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
	public SoldItem getForStockItem(long stockItemId) {
		for (SoldItem item : soldItems) {
			if (item.getStockItem().getId().equals(stockItemId)) {
				return item;
			}
		}
	    return null;
	}
    
    public void addItem(final SoldItem soldItem) //throws SalesSystemException 
    {
    	
    	soldItem.setSale(this);

        StockItem stockItem = soldItem.getStockItem();
        long stockItemId = stockItem.getId();
        SoldItem existingItem = getForStockItem(stockItemId);

        if (existingItem != null) {
            int totalQuantity = existingItem.getQuantity() + soldItem.getQuantity();
            existingItem.setQuantity(totalQuantity);
        } else {
            soldItems.add(soldItem);
        }
        System.out.println(soldItems.size());

    }

    public double getSum() {
        double sum = 0.0;
        for (SoldItem item : soldItems) {
            sum = sum + item.getPrice() * ((double) item.getQuantity());
        }
        return sum;
    }

}
