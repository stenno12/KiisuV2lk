package ee.ut.math.tvt.kiisuv2lk.domain.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryItem implements Cloneable, DisplayableItem {

    private Long saleId;

    private List<SoldItem> soldItems;

    private Date date;

    public HistoryItem(List<SoldItem> soldItems, Date date, Long saleId) {
	this.soldItems = soldItems;
	this.saleId = saleId;
	this.date = date;
    }

    public List<SoldItem> getSoldItems() {
	return soldItems;
    }

    @Override
    public Long getId() {
	return saleId;
    }

    public String getTime() {
	return new SimpleDateFormat("HH:mm").format(date);
    }

    public String getDate() {
	return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public double getTotalPrice() {
	double sum = 0.0;
	if (getSoldItems().isEmpty() == false) {
	    for (SoldItem i : soldItems) {
		sum += i.getSum();
	    }
	}

	return sum;
    }
}
