package ee.ut.math.tvt.intro.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import ee.ut.math.tvt.intro.service.PropertiesLoader;
/*
 * this load again if file changed is just for fun
 * don't know if holding 2 Properties object and cheking file last modified
 * is cheapier than parsing files...
 * 
 */
public class PropertiesLoaderImpl implements PropertiesLoader{

	private static final String APPLICATION_PROPERTIES_PATH = "application.properties";
	
	private static final String VERSION_PROPERTIES_PATH = "version.properties";
	
	public static final PropertiesLoaderImpl INSTANCE = new PropertiesLoaderImpl();
	
	private PropertiesLoaderImpl(){	};
	
	private Properties applicationProperties;
	private long applicationPropertiesModified;
	
	private Properties versionProperties;
	private long versionPropertiesModified;

	@Override
	public Properties getApplicationProperties() {
		if(applicationProperties == null || wasModified(APPLICATION_PROPERTIES_PATH, applicationPropertiesModified)){
			applicationProperties = loadPropertiesFromFile(APPLICATION_PROPERTIES_PATH);
			applicationPropertiesModified = new Date().getTime();
		}
		return applicationProperties;
	}

	@Override
	public Properties getVersionProperties() {
		if(versionProperties == null || wasModified(VERSION_PROPERTIES_PATH, versionPropertiesModified)){
			versionProperties = loadPropertiesFromFile(VERSION_PROPERTIES_PATH);
			versionPropertiesModified = new Date().getTime();
		}
		return versionProperties;
	}
	
	private boolean wasModified(String path, long lastModified) {
		File file = new File(getClassLoader().getResource(path).getFile());
		return lastModified < file.lastModified();
	}

	private Properties loadPropertiesFromFile(String path) {
		System.out.println(String.format("loading properties from %s", path));
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(path));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace(); //TODO should be logged
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace(); //TODO should be logged
			System.exit(1);
		}
		return properties;
	}

	public String getApplicationProperty(String key) {
		return getProperty(getApplicationProperties(), key);
	}

	public String getVersionProperty(String key) {
		return getProperty(getVersionProperties(), key);
	}
	
	private String getProperty(Properties properties, String key) {
		String value = properties.getProperty(key);
		if(value == null){
			String message = String.format("%s not defined", key);
			System.out.println(message);
			//throw new PropertyNotDefined(message); throw if properties would be obligatory
			//TODO should be logged anyway
			return "";
		}
		if(value.isEmpty()){
			String message = String.format("%s is empty", key);
			//throw new PropertyIsEmpty(message); throw if properties would be obligatory
			System.out.println(message);
			//TODO should be logged anyway
		}
		return value;
	}
	
	private ClassLoader getClassLoader() {
		return this.getClass().getClassLoader();
	}
}
