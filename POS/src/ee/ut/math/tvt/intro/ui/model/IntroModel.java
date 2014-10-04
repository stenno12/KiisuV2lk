package ee.ut.math.tvt.intro.ui.model;

import ee.ut.math.tvt.intro.domain.controller.IntroDomainController;
import ee.ut.math.tvt.intro.domain.data.IntroData;

public class IntroModel {


    private String leadName;
    private String softwareVersion;

    public IntroModel(IntroDomainController introController) {
        IntroData introData = introController.getIntroData();
		leadName = introData.getTeam().getLeader();
        softwareVersion = introData.getSoftwareVersion();
    }

	public String getLeadName() {
		return leadName;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}
	
}
