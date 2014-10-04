package ee.ut.math.tvt.intro;

import ee.ut.math.tvt.intro.domain.controller.IntroDomainController;
import ee.ut.math.tvt.intro.domain.controller.impl.IntroDomainControllerImpl;
import ee.ut.math.tvt.intro.ui.IntroUi;


public class Intro {
	
	public static void main(String args[]) {
		
		final IntroDomainController introController = new IntroDomainControllerImpl();
		
		// Swing UI
		final IntroUi ui = new IntroUi(introController);
		
		ui.setVisible(true);
	}
}
