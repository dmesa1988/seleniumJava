package com.google.personal.hands_on.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.personal.hands_on.config.CustomProperties;
import com.google.personal.hands_on.config.DriverManager;


public class BasePage extends DriverManager {

	private static final Logger log = LogManager.getLogger(BasePage.class);


	public static void initBrowser(String browser) throws IOException {
		CustomProperties.initializePropertiesFiles();
		setUpDriver(browser);
		getDriver().manage().deleteAllCookies();
		getDriver().get(CustomProperties.getTestProperties().getProperty("URL"));
		getDriver().manage().window().maximize();
		
	}
	
	public static void closeWindow() {
		if (getDriver() != null) {
			getDriver().close();
			log.debug("quitBrowser- Closing Browser");

		} else {
			log.debug("quitBrowser- driver is null");
		}	
	}
	
	public static void closeBrowser() {
		teardownDriver();
	}	

	public static void switchToDefault() {
		getDriver().switchTo().defaultContent();
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}	
	
	public String getCurrentURL() {
		return getDriver().getCurrentUrl();
	}	

}
