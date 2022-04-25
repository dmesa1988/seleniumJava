package com.google.personal.hands_on.config;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static FluentWait<WebDriver> fluentWaitNoSuchElem;
	private static FluentWait<WebDriver> fluentWaitStaleElem;
	private static FluentWait<WebDriver> fluentWaitInvalidElem;	
	private static final Logger log = LogManager.getLogger(DriverManager.class);
	
	public static WebDriver getDriver() {
		return driver;
	}

	protected static FluentWait<WebDriver> getFluentWaitNoSuchElem() {
		return fluentWaitNoSuchElem;
	}

	protected static FluentWait<WebDriver> getFluentWaitStaleElem() {
		return fluentWaitStaleElem;
	}
	
	protected static FluentWait<WebDriver> getFluentWaitInvalidElem() {
		return fluentWaitInvalidElem;
	}

	protected static WebDriverWait getWait() {
		return wait;
	}

	protected static void setUpDriver(String browser) {

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.debug("Setting up Chrome");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.debug("Setting up Firefox");
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.debug("Setting up IE");
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.debug("Setting up Chrome");
			break;
		}
		initializeParameters();
	}

	protected static void teardownDriver() {
		if (driver != null) {
			driver.quit();
			log.debug("quitBrowser- Closing Browser");

		} else {
			log.debug("quitBrowser- driver is null");
		}
	}

	private static void initializeParameters() {
		
		wait = new WebDriverWait(driver, Long.decode(CustomProperties.getSeleniumProperties().getProperty("EXPLICIT_WAIT")));
		
		fluentWaitNoSuchElem = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Long.decode(CustomProperties.getSeleniumProperties().getProperty("FLUENT_WAIT"))))
				.pollingEvery(Duration.ofSeconds(Long.decode(CustomProperties.getSeleniumProperties().getProperty("POOLING_TIME"))))
				.withMessage("Controlled Fluent Wait NoSuchElementException").ignoring(NoSuchElementException.class);
		
		fluentWaitStaleElem = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Long.decode(CustomProperties.getSeleniumProperties().getProperty("FLUENT_WAIT"))))
				.pollingEvery(Duration.ofSeconds(Long.decode(CustomProperties.getSeleniumProperties().getProperty("POOLING_TIME"))))
				.withMessage("Controlled Fluent Wait StaleElementReferenceException")
				.ignoring(StaleElementReferenceException.class);
		
		fluentWaitInvalidElem = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Long.decode(CustomProperties.getSeleniumProperties().getProperty("FLUENT_WAIT"))))
				.pollingEvery(Duration.ofSeconds(Long.decode(CustomProperties.getSeleniumProperties().getProperty("POOLING_TIME"))))
				.withMessage("Controlled Fluent Wait InvalidElementStateException")
				.ignoring(InvalidElementStateException.class);		
	}
}
