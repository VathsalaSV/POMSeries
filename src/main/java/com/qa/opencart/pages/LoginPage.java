package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	// each page class we need to declare private webdriver
	private WebDriver driver;
	private ElementUtil eleUtil;

	// and public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// private by locators of the page
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By loginErrorMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	private By registerLink = By.linkText("Register");

	public String getLoginPageUrl() {

		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}

	// public page actions/methods
	public AccountsPage Login(String email, String pwd) {
		eleUtil.waitForElementPresence(emailId, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(email);
		eleUtil.getElement(password).sendKeys(pwd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	
	public String LoginWithWrongCredentials(String email, String pwd) {
		System.out.println("Wrong credentials are:"+email+" , "+pwd);
		eleUtil.waitForElementPresence(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId,email);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		String errorMsg=eleUtil.doGetElementText(loginErrorMsg);
		System.out.println(errorMsg);
		return errorMsg;
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
