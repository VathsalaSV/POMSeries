package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataproviders;

public class SearchTest extends BaseTest {
	@BeforeClass
	public void searchsetup() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(dataProvider="ProductDataWithSearchKey", dataProviderClass=ProductDataproviders.class)
	public void SearchProductTest(String searchKey) {
		resultsPage = accPage.doSearch(searchKey);
		String actTitle=resultsPage.getResultsPageTitle(searchKey);
		System.out.println("Results page title : "+actTitle);
		Assert.assertEquals(actTitle, "Search - "+searchKey);
	}

	@Test(dataProvider="ProductDataWithSearchKey", dataProviderClass=ProductDataproviders.class)
	public void productCountTest(String searchKey) {
		resultsPage = accPage.doSearch(searchKey);
		int actProductCount = resultsPage.getSearchItemCount();
		System.out.println("The search of Product returned "+actProductCount +" items");
		Assert.assertTrue(actProductCount > 0);
	}
	
	
	@Test(dataProvider="ProductDataWithName",dataProviderClass = ProductDataproviders.class)
	public void selectProductTest(String searchKey, String ProductName) {
		resultsPage = accPage.doSearch(searchKey);
		detailsPage = resultsPage.selectProduct(ProductName);
		String actProductHeader = detailsPage.getProductHeaderName();
		System.out.println("The header of selected product is :" + actProductHeader);
		Assert.assertEquals(actProductHeader, ProductName);
	}
	
	
	@Test(dataProvider="ProductDataWithImages",dataProviderClass = ProductDataproviders.class)
	public void ProductImagesCount(String searchKey, String ProductName, int count) {
		resultsPage = accPage.doSearch(searchKey);
		detailsPage = resultsPage.selectProduct(ProductName);
		int totalImages=detailsPage.getProductImagesCount();
		System.out.println("The total number of images of selcted product : "+totalImages);
		Assert.assertEquals(totalImages, count);
		
	}
}
