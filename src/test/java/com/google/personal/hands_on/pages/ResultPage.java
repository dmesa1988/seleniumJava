package com.google.personal.hands_on.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.personal.hands_on.helper.GenericHelper;

public class ResultPage extends BasePage{

	@FindBys({@FindBy(id = "rso"),@FindBy(tagName = "h3")})
	private List<WebElement> resultList;	
	
	public ResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public String getResultLink() {
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(resultList)));
		return resultList.stream().findFirst().get().getText();
	}
	
	public void clickOnResultLink() {
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(resultList)));
		GenericHelper.click(resultList.stream().findFirst().get(), "resultListFirstRecord");	
	}	
}
