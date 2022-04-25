package com.google.personal.hands_on.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CustomProperties {
	
	private static CustomProperties seleniumProperties;
	private static CustomProperties testProperties;
	private Properties prop;
	
	public CustomProperties(String filePath) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis); 		
	}
	
	public String getProperty (String propertyKey) {
		
		return prop.getProperty(propertyKey);
	}

	public static CustomProperties getSeleniumProperties() {
		return seleniumProperties;
	}

	public static CustomProperties getTestProperties() {
		return testProperties;
	}	
	
	public static void initializePropertiesFiles() throws IOException {
		seleniumProperties = new CustomProperties(System.getProperty("user.dir") + "\\src\\test\\resources\\Selenium.properties");
		testProperties = new CustomProperties(System.getProperty("user.dir") + "\\src\\test\\resources\\Test.properties");		
	}
	
	
	
}
