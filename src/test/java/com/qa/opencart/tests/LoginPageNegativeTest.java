package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class LoginPageNegativeTest extends BaseTest{
	@DataProvider
	public Object[][] incorrectLoginTestData(){
		return new Object[][]{
			{"Test@yopmail.com","asd@123"},
			{"automation@yopmail.com", "4345@Q#$"},
			{"automation1@yopmail.com","Test@123"},
		};
				
	}
	@Test(dataProvider = "incorrectLoginTestData")
	public void loginWithWrongcredentials(String username, String pwd) {
		System.out.println("Wrong credentials are:"+username +" , "+pwd);
		String errorMsg= loginPage.LoginWithWrongCredentials(username, pwd);
		Assert.assertEquals(errorMsg, AppConstants.LOGIN_ERROR_MESSAGE);	
	}
}
