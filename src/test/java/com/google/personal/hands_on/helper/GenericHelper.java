package com.google.personal.hands_on.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.google.personal.hands_on.pages.BasePage;


public class GenericHelper extends BasePage {

	private static final Logger log = LogManager.getLogger(GenericHelper.class);

	public static void click(WebElement element, String elementName) {
		log.debug("Before Clicking on element: " + elementName);			
		getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
		log.debug("Performed Click on element: " + elementName);			
	}

	
	public static void setText(WebElement element, String text, String elementName) {
		
		log.debug("Before Setting text on " + elementName);		
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element))).clear();
		element.sendKeys(text);	
		log.debug("Performed Setting text on " + elementName);
	}

	public static void changeWindow(String windowId) {
		getDriver().switchTo().window(windowId);
	}

}
