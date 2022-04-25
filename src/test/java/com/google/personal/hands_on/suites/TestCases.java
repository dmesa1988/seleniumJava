package com.google.personal.hands_on.suites;

import org.testng.annotations.Test;

import com.google.personal.hands_on.config.CustomProperties;
import com.google.personal.hands_on.pages.HomePage;
import com.google.personal.hands_on.pages.ResultPage;


public class TestCases extends BaseTest {
	private HomePage homepage;
	private ResultPage resultpage;
	

	@Test
	public void checkResultsUsingGoogleSearchButton() {
		homepage = new HomePage();
		resultpage = homepage.searchFullText();		
		softAssert.assertEquals(resultpage.getResultLink(), CustomProperties.getTestProperties().getProperty("LINK_RESULT_TEXT"),"Validation of link text"); 
		resultpage.clickOnResultLink();
		softAssert.assertEquals(resultpage.getCurrentURL(), CustomProperties.getTestProperties().getProperty("LINK_RESULT_PAGE_URL"),"Validation of opened link URL");
		softAssert.assertEquals(resultpage.getPageTitle(), CustomProperties.getTestProperties().getProperty("LINK_RESULT_PAGE_TITLE"),"Validation of opened link Title");
		softAssert.assertAll();
	}

	@Test
	public void checkResultsUsingSuggestionList() {
		homepage = new HomePage();
		resultpage = homepage.searchTextUsingSuggestionList();		
		softAssert.assertEquals(resultpage.getResultLink(), CustomProperties.getTestProperties().getProperty("LINK_RESULT_TEXT"),"Validation of link text");
		resultpage.clickOnResultLink();
		softAssert.assertEquals(resultpage.getCurrentURL(), CustomProperties.getTestProperties().getProperty("LINK_RESULT_PAGE_URL"),"Validation of opened link URL");
		softAssert.assertEquals(resultpage.getPageTitle(), CustomProperties.getTestProperties().getProperty("LINK_RESULT_PAGE_TITLE"),"Validation of opened link Title");
		softAssert.assertAll();  
	}	
	
}
