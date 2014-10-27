package ee.ut.math.tvt.kiisuv2lk.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.kiisuv2lk.domain.data.HistoryItem;
import ee.ut.math.tvt.kiisuv2lk.ui.model.SalesSystemModel;
import ee.ut.math.tvt.kiisuv2lk.ui.windows.AddToStockWindow;
import ee.ut.math.tvt.kiisuv2lk.ui.windows.HistoryItemDetailsWindow;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {

	  private SalesSystemModel model;

	  public HistoryTab(SalesSystemModel model) {
	    this.model = model;
	  }

    public Component draw() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 0d;


        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryMainPane(), gc);
        return panel;
      }



      // table of the purchase history
      private Component drawHistoryMainPane() {
        JPanel panel = new JPanel();

        final JTable table = new JTable(model.getHistorytableModel());
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                long row = table.rowAtPoint(evt.getPoint());
                if (row >= 0 ) {
                	HistoryItem HI=model.getHistorytableModel().getItemById(row);
                	HistoryItemDetailsWindow newWin= new HistoryItemDetailsWindow(model,HI);
        	        newWin.setVisible(true);

                }
            }
        });
        
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        panel.setLayout(gb);
        panel.add(scrollPane, gc);

        panel.setBorder(BorderFactory.createTitledBorder("Purchase history"));
        return panel;
      }

    }
