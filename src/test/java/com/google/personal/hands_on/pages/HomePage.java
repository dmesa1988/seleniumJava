package com.google.personal.hands_on.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.personal.hands_on.config.CustomProperties;
import com.google.personal.hands_on.helper.GenericHelper;

public class HomePage extends BasePage {

	@FindBy(css = "input[name='q']")
	private WebElement searchTextbox;

	@FindBy(css = "input.gNO89b")
	private WebElement googleSearchButton;

	@FindBys({@FindBy(css = "ul.erkvQe"),@FindBy(css = "li[role='presentation']")})
	private List<WebElement> suggestionList;

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	public ResultPage searchFullText() {
		GenericHelper.setText(searchTextbox, CustomProperties.getTestProperties().getProperty("SEARCH_TEXT"), "searchTextbox");	
		GenericHelper.click(googleSearchButton, "googleSearchButton");		
		return new ResultPage();
	}

	public ResultPage searchTextUsingSuggestionList() {
		GenericHelper.setText(searchTextbox, CustomProperties.getTestProperties().getProperty("SEARCH_PARTIAL_TEXT"), "searchTextbox");	
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(suggestionList)));
		GenericHelper.click(suggestionList.stream().findFirst().get(), "suggestionListFirstRecord");
		return new ResultPage();
	}

}
