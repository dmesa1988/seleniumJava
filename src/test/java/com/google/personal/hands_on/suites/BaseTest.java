package com.google.personal.hands_on.suites;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.google.personal.hands_on.pages.BasePage;


public class BaseTest {
	SoftAssert softAssert ;
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws IOException {
		softAssert = new SoftAssert();
		BasePage.initBrowser(browser);
		
	}

	@AfterMethod
	public void tearDown() {		
		BasePage.closeBrowser();
	}

}
