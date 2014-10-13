package ee.ut.math.tvt.kiisuv2lk.enums;

import ee.ut.math.tvt.kiisuv2lk.service.impl.PropertiesLoaderImpl;

public enum ApplicationProperties {

	TEAM_NAME("team.name"),

	TEAM_LEADER("team.leader"),

	TEAM_LEADER_EMAIL("team.leader.email"),

	TEAM_MEMBERS("team.members"),
	
	LOGO_IMAGE("logo.image");
	
	private String key;
	private String value;

	ApplicationProperties(String key){
		this.key = key;
		value = PropertiesLoaderImpl.INSTANCE.getApplicationProperty(key);
	}
	
	public String getValue(){
		return value;
	}

	public String getKey(){
		return key;
	}
}
