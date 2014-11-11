package ee.ut.math.tvt.kiisuv2lk.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;

public class SalesSystemBaseFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(SalesSystemBaseFrame.class);

    private static final int FRAME_HEIGHT = 400;
    
    private static final int FRAME_WIDTH = 600;
    
    private static final String FRAME_TITLE = "Sales system";

    private SalesDomainController domainController;

    public SalesSystemBaseFrame(SalesDomainController domainController) {
	this.domainController = domainController;

	setTitle(FRAME_TITLE);

	setLookAndFeel();

	setSizeAndLocation();

	addOnExitListener();
    }

    private void setLookAndFeel() {
	try {
	    UIManager.setLookAndFeel(new WindowsLookAndFeel());

	} catch (UnsupportedLookAndFeelException e) {
	    log.warn(e.getMessage());
	}
    }

    private void setSizeAndLocation() {
	setSize(FRAME_WIDTH, FRAME_HEIGHT);
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation((screen.width - FRAME_WIDTH) / 2, (screen.height - FRAME_HEIGHT) / 2);
    }

    private void addOnExitListener() {
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		try {
		    domainController.endSession();
		} catch (Error er) {
		    System.exit(0);
		}
	    }
	});
    }

}
