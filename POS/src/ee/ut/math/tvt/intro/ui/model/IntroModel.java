package ee.ut.math.tvt.intro.ui.model;

import ee.ut.math.tvt.intro.domain.controller.IntroDomainController;
import ee.ut.math.tvt.intro.domain.data.IntroData;
import ee.ut.math.tvt.intro.domain.data.Team;

public class IntroModel {

	private String logoImage;
	private String introText;

	private String softwareVersion;

	public IntroModel(IntroDomainController introController) {
		IntroData introData = introController.getIntroData();
		softwareVersion = introData.getSoftwareVersion();
		//logoImage = introData.getLogoImage();
		Team team = introData.getTeam();
		introText = team.getLeader() + ", " + team.getLeaderEmail() + ", "
				+ team.getMembers();
	}
	/*
	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}
	*/
	public String getIntroText() {
		return introText;
	}

	public void setIntroText(String introText) {
		this.introText = introText;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

}
