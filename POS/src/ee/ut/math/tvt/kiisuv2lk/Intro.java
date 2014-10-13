package ee.ut.math.tvt.kiisuv2lk;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.IntroDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.controller.impl.IntroDomainControllerImpl;
import ee.ut.math.tvt.kiisuv2lk.ui.IntroUi;


public class Intro {
	private static final Logger log = Logger.getLogger(Intro.class);
	public static void main(String args[]) {
		
		final IntroDomainController introController = new IntroDomainControllerImpl();
		
		// Swing UI
		final IntroUi ui = new IntroUi(introController);
		
		ui.setVisible(true);
		log.info("Intro window is opened");
	}
}
