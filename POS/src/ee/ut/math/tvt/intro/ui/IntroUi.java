package ee.ut.math.tvt.intro.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



import ee.ut.math.tvt.intro.Intro;
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
//		try {
//			UIManager.setLookAndFeel(new WindowsLookAndFeel());
//			UIManager.setLookAndFeel(new GTKLookAndFeel());

//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace(); // TODO should be logged
//		}
		JPanel panel = new JPanel();
		
		JLabel introText = new JLabel(model.getIntroText());
		panel.add(introText);
		JLabel version = new JLabel(model.getSoftwareVersion());
		panel.add(version);
		
		BufferedImage myPicture;
		URL url;
		try {
			url = this.getClass().getClassLoader().getResource(model.getLogoImage());
			myPicture = ImageIO.read(url);
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setSize(50, 50);
			panel.add(picLabel);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace(); //TODO should be logged
		} catch (IOException e2) {
			e2.printStackTrace(); //TODO should be logged
		}
		getContentPane().add(panel);

		// size & location
		int width = 650;
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

}
