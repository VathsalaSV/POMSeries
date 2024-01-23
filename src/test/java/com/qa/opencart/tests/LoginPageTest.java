package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority=0)
	public void loginPageUrl() {
		String PageUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(PageUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_VALUE));
	}

	@Test(priority=1)
	public void login() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	 	Assert.assertTrue(accPage.isLogoutLinkPresent());
	}

}
