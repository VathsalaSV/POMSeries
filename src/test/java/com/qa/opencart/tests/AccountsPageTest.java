package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void logoutLinkPresentTest() {
		Assert.assertTrue(accPage.isLogoutLinkPresent());
	}

	@Test
	public void accPageHeaderCountTest() {
		List<String> actHeadersList = accPage.AccPageHeaderList();
		Assert.assertEquals(actHeadersList.size(), 4);
	}

	@Test
	public void accPageHeaderListTest() {
		List<String> actHeadersList = accPage.AccPageHeaderList();
		
		Assert.assertEquals(actHeadersList, AppConstants.EXP_ACCOUNTS_HEADERS_LIST);
	}

}
