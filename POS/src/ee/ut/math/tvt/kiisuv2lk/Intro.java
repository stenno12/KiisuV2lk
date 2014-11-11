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
    private static SalesDomainController domainController;

    public static void main(String args[]) {
	
	domainController = new SalesDomainControllerImpl();
	
	if (isConsoleMode(args)) {
	    startConsoleUi();
	} else {
	    startGraphicUi();
	}
    }

    private static boolean isConsoleMode(String[] args) {
	return args.length == 1 && args[0].equals(MODE);
    }

    private static void startConsoleUi() {
	log.debug("Mode: " + MODE);
	new ConsoleUI(domainController).run();
    }

    private static void startGraphicUi() {
	boolean introRequired = false;
	if(introRequired ){
	    startIntro();
	}
	startSalesSystemUi();
    }

    private static void startSalesSystemUi() {
	SalesSystemUI salesSystemUi = new SalesSystemUI(domainController);
	salesSystemUi.setVisible(true);
    }

    private static void startIntro() {
	Thread introThread = new Thread(){
	    private IntroUi introUI;
	    
	    public void run() {
		setupIntro();
		introUI.setVisible(true);
		introUI.setAlwaysOnTop(false);
		waitPause();
		introUI.setVisible(false);
	    }

	    private void setupIntro() {
		IntroDomainController introController = new IntroDomainControllerImpl();
		introUI = new IntroUi(introController);
		introUI.setVisible(true);
		introUI.setAlwaysOnTop(true);
	    }

	    private void waitPause() {
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	};
	introThread.start();
    }

}
