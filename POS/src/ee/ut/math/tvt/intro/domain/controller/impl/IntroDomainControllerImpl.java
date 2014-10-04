package ee.ut.math.tvt.intro.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.intro.domain.controller.IntroDomainController;
import ee.ut.math.tvt.intro.domain.data.IntroData;
import ee.ut.math.tvt.intro.domain.data.Team;

public class IntroDomainControllerImpl implements IntroDomainController{

	@Override
	public IntroData getIntroData() { //TODO get values from property files
		IntroData introData = new IntroData();
		Team team = new Team();
		team.setLeader("Stenno");
		team.setLeaderEmail("stenno@abc.ee");
		team.setMembers(getMembers());
		introData.setTeam(team);
		introData.setSoftwareVersion("v_0.0.1");
		return introData ;
	}

	private List<String> getMembers() {
		List<String> members = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			members.add("average member nr " + i);
		}
		return members;
	}

}
