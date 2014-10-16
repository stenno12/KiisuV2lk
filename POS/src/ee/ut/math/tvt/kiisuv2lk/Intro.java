package ee.ut.math.tvt.kiisuv2lk;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.IntroDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.controller.SalesDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.controller.impl.IntroDomainControllerImpl;
import ee.ut.math.tvt.kiisuv2lk.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.kiisuv2lk.ui.ConsoleUI;
import ee.ut.math.tvt.kiisuv2lk.ui.IntroUi;
import ee.ut.math.tvt.kiisuv2lk.ui.SalesSystemUI;


public class Intro {
	
	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";
	public static void main(String args[]) {
		
		final SalesDomainController domainController = new SalesDomainControllerImpl();

		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {
			
			final IntroDomainController introController = new IntroDomainControllerImpl();
			IntroUi introUI = new IntroUi(introController);
			introUI.setVisible(true);
			introUI.setAlwaysOnTop(true);

			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);

			introUI.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			introUI.setVisible(false);
		}
		
//		final IntroDomainController introController = new IntroDomainControllerImpl();
//		
//		// Swing UI
//		final IntroUi ui = new IntroUi(introController);
//		
//		ui.setVisible(true);
//		log.info("Intro window is opened");
	}
}
