package ee.ut.math.tvt.kiisuv2lk.domain.controller.impl;

import ee.ut.math.tvt.kiisuv2lk.domain.controller.IntroDomainController;
import ee.ut.math.tvt.kiisuv2lk.domain.data.IntroData;
import ee.ut.math.tvt.kiisuv2lk.domain.data.Team;
import ee.ut.math.tvt.kiisuv2lk.enums.ApplicationProperties;
import ee.ut.math.tvt.kiisuv2lk.enums.VersionProperties;

public class IntroDomainControllerImpl implements IntroDomainController {

    @Override
    public IntroData getIntroData() {
	IntroData introData = new IntroData();
	introData.setSoftwareVersion(getVerion());
	introData.setLogoImage(getLogoImage());
	introData.setTeam(getTeam());
	return introData;
    }

    private Team getTeam() {
	Team team = new Team();
	team.setLeader(ApplicationProperties.TEAM_LEADER.getValue());
	team.setLeaderEmail(ApplicationProperties.TEAM_LEADER_EMAIL.getValue());
	team.setMembers(ApplicationProperties.TEAM_MEMBERS.getValue());
	return team;
    }

    private String getLogoImage() {
	return ApplicationProperties.LOGO_IMAGE.getValue();
    }

    private String getVerion() {
	String arg1 = VersionProperties.BUILD_MAJOR_NUMBER.getValue();
	String arg2 = VersionProperties.BUILD_MINOR_NUMBER.getValue();
	String arg3 = VersionProperties.BUILD_REVISION_NUMBER.getValue();
	return String.format("%s.%s.%s", arg1, arg2, arg3);
    }

}
