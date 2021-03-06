package ee.ut.math.tvt.kiisuv2lk.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving
 * history.
 */
@Entity
@Table(name = "SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SALE_ID")
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "STOCKITEM_ID", nullable = false)
    private StockItem stockItem;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "itemprice")
    private double price;

    @Transient
    private HistoryItem historyItem;

    public SoldItem() {

    }

    public SoldItem(StockItem stockItem, int quantity) {
	this.stockItem = stockItem;
	this.id = stockItem.getId();
	this.name = stockItem.getName();
	this.price = stockItem.getPrice();
	this.quantity = quantity;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public Integer getQuantity() {
	return quantity;
    }

    public void setQuantity(Integer quantity) {
	this.quantity = quantity;
    }

    public double getSum() {
	return price * ((double) quantity);
    }

    public StockItem getStockItem() {
	return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
	this.stockItem = stockItem;
    }

    public Long getSaleId() {
	return saleId;
    }

    public void setSaleId(Long saleId) {
	this.saleId = saleId;
    }

}
