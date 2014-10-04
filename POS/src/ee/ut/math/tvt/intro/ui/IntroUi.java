package ee.ut.math.tvt.intro.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;

import ee.ut.math.tvt.intro.domain.controller.IntroDomainController;
import ee.ut.math.tvt.intro.ui.model.IntroModel;

/**
 * Graphical user interface of the sales system.
 */
public class IntroUi extends JFrame {

	private static final long serialVersionUID = 1L;

	private final IntroDomainController introController;

	private IntroModel model;

	/**
	 * Constructs sales system GUI.
	 * 
	 * @param domainController
	 *            Sales domain controller.
	 */
	public IntroUi(IntroDomainController introController) {
		this.introController = introController;
		this.model = new IntroModel(introController);

		setTitle("Intro");

		// set L&F to the nice Windows style
		try {
//			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			UIManager.setLookAndFeel(new GTKLookAndFeel());

		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace(); // TODO should be logged
		}

		drawWidgets();

		// size & location
		int width = 600;
		int height = 400;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void drawWidgets() {
		JLabel leadNameAndSoftwareVersion = new JLabel(model.getLeadName() + " " + model.getSoftwareVersion());
		getContentPane().add(leadNameAndSoftwareVersion);
	}

}
