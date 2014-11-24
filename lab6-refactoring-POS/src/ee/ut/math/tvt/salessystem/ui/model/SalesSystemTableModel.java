package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.table.AbstractTableModel;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;

/**
 * Generic table model implementation suitable for extending.
 */
public abstract class SalesSystemTableModel<T extends DisplayableItem> extends
        AbstractTableModel {

    private static final long serialVersionUID = 1L;

    protected final String[] headers;

    public SalesSystemTableModel(final String[] headers) {
        this.headers = headers;
    }

    /**
     * @param item
     *            item describing selected row
     * @param columnIndex
     *            selected column index
     * @return value displayed in column with specified index
     */
    protected abstract Object getColumnValue(T item, int columnIndex);

    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(final int columnIndex) {
        return headers[columnIndex];
    }

    public abstract int getRowCount(); 

    public abstract Object getValueAt(final int rowIndex, final int columnIndex); 

    public abstract T getItemById(final long id); 

    public abstract void populateWithData(final List<T> data); 
    
    public abstract void addRow(T row); 
//    {
//        rows.add(row);
//        fireTableDataChanged();
//    }
    
//    public T getRow(int index) {
//        return rows.get(index);
//    }
    
//    public List<T> getRows() {
//        return rows;
//    }
    
}
