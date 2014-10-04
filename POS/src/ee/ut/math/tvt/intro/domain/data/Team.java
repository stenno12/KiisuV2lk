package ee.ut.math.tvt.intro.domain.data;

import java.util.List;

public class Team {
	
	private String leader;
	
	private String leaderEmail;
	
	private List<String> members;
	
	private String logo;
	
	public String getLeader() {
		return leader;
	}
	
	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	public String getLeaderEmail() {
		return leaderEmail;
	}
	
	public void setLeaderEmail(String leaderEmail) {
		this.leaderEmail = leaderEmail;
	}
	
	public List<String> getMembers() {
		return members;
	}
	
	public void setMembers(List<String> members) {
		this.members = members;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}

}
