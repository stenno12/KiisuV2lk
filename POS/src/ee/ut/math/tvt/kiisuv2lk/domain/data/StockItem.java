package ee.ut.math.tvt.kiisuv2lk.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stock item. Corresponds to the Data Transfer Object design pattern.
 */
@Entity
@Table(name = "STOCKITEM")
public class StockItem implements Cloneable, DisplayableItem {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    public StockItem() {
    };

    public StockItem(Long id, String name, String desc, double price) {
	this.id = id;
	this.name = name;
	this.description = desc;
	this.price = price;
    }

    public StockItem(Long id, String name, String desc, double price, int quantity) {
	this.id = id;
	this.name = name;
	this.description = desc;
	this.price = price;
	this.quantity = quantity;
    }

    /**
     * Method for querying the value of a certain column when StockItems are
     * shown as table rows in the SalesSstemTableModel. The order of the columns
     * is: id, name, price, quantity.
     */
    public Object getColumn(int columnIndex) {
	switch (columnIndex) {
	case 0:
	    return id;
	case 1:
	    return name;
	case 2:
	    return new Double(price);
	case 3:
	    return new Integer(quantity);
	default:
	    throw new RuntimeException("invalid column!");
	}
    }

    public StockItem clone() {
	StockItem item = new StockItem(getId(), getName(), getDescription(), getPrice(), getQuantity());
	return item;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	StockItem other = (StockItem) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return id + " " + name + " " + description + " " + price;
    }

    /*
     * GETTERS SETTERS
     */

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }
}
