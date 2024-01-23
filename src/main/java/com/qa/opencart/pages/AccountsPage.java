package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	// each page class we need to declare private webdriver
	private WebDriver driver;
	private ElementUtil eleUtil;

	// and public constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// create private locators and give indirect access to these from methods, this
	// is encapsulation. Locators should be kept private as if anyone changes them
	// it might result in failure
	private By logout = By.linkText("Logout");
	private By myAccount = By.linkText("My Account");
	private By pageHeaders = By.xpath("//div/h2");
	private By search = By.name("search");
	private By searchBtn = By.xpath("//div[@id='search']//button");

	// 3. Public methods
	public String getAccPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}

	public boolean isLogoutLinkPresent() {
		return eleUtil.checkElementIsDisplayed(logout);
	}

	public boolean isMyAccountLinkPresent() {
		return eleUtil.checkElementIsDisplayed(myAccount);
	}

	public List<String> AccPageHeaderList() {
		List<WebElement> HeadersList = eleUtil.waitForElementsVisible(pageHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> Headers = new ArrayList<String>();
		for (WebElement e : HeadersList) {
			String text = e.getText();
			Headers.add(text);
		}
		return Headers;
	}

	public SearchResultsPage doSearch(String searchTerm) {
		eleUtil.waitForElementVisible(search, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(search, searchTerm);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}
}
