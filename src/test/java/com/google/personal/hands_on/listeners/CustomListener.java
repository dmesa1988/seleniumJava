package com.google.personal.hands_on.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.personal.hands_on.config.DriverManager;


public class CustomListener implements ITestListener{
	
	private static final Logger log = LogManager.getLogger(CustomListener.class);
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			log.debug("Listener: Test Failure ");
			String name = captureScreenshoot(result.getName());
			
			System.setProperty("org.uncommons.reportng.escape-output","false");
			Reporter.log("<a href=\""+name+"\" target=\"_blank\">Screenshot link</a>");
			Reporter.log("<br>");
			Reporter.log("<a href=\""+name+"\" target=\"_blank\"><img height=200 width=300 src=\""+name+"\"></a>");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Listener: Error Opening File ");
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	private static String captureScreenshoot(String methodName) throws IOException {
		log.debug("Capturing screenshot");
		Date d = new Date();		
		String fileName= System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+d.toString().replace(":", "_").replace(" ", "_")+".jpg";	
		File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(fileName));
		log.debug("Filename: "+fileName);
		return fileName;
	}	

}
