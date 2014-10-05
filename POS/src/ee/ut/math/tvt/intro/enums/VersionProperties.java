package ee.ut.math.tvt.intro.enums;

import ee.ut.math.tvt.intro.service.PropertiesLoader;
import ee.ut.math.tvt.intro.service.impl.PropertiesLoaderImpl;

public enum VersionProperties {

	BUILD_REVISION_NUMBER("build.revision.number"),

	BUILD_MINOR_NUMBER("build.minor.number"),

	BUILD_MAJOR_NUMBER("build.major.number");
	
	private String key;
	private String value;

	VersionProperties(String key){
		this.key = key;
		value = PropertiesLoaderImpl.INSTANCE.getVersionProperty(key);
	}
	
	public String getValue(){
		return value;
	}

	public String getKey(){
		return key;
	}
}
