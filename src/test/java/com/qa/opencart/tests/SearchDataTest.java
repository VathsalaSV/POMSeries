package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest{
	@BeforeClass
	public void searchsetup() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider(name="ProductTestData")
	public Object[][] getProductSearchKey() {
		return new Object[][] {
			{new Product ("Macbook", "MacBook Pro",4)},
			{new Product ("iMac", "iMac",3)},
			{new Product ("Samsung", "Samsung Galaxy Tab 10.1",7)},
		};
	}
	@Test(dataProvider="ProductTestData")
	public void searchPageTitleTest(Product product) {
		resultsPage = accPage.doSearch(product.getSearchKey());
		String actTitle=resultsPage.getResultsPageTitle(product.getSearchKey());
		System.out.println("Results page title : "+actTitle);
		Assert.assertEquals(actTitle, "Search - "+product.getSearchKey());
	}

	@Test(dataProvider="ProductTestData")
	public void productResultCountTest(Product product) {
		resultsPage = accPage.doSearch(product.getSearchKey());
		int actProductCount = resultsPage.getSearchItemCount();
		System.out.println("The search of Product returned "+actProductCount +" items");
		Assert.assertTrue(actProductCount > 0);
	}
	
	@Test(dataProvider="ProductTestData")
	public void selectProductTest(Product product) {
		resultsPage = accPage.doSearch(product.getSearchKey());
		detailsPage = resultsPage.selectProduct(product.getProductName());
		String actProductHeader = detailsPage.getProductHeaderName();
		System.out.println("The header of selected product is :" + actProductHeader);
		Assert.assertEquals(actProductHeader, product.getProductName());
	}
	
	@Test(dataProvider="ProductTestData")
	public void ProductImagesCount(Product product) {
		resultsPage = accPage.doSearch(product.getSearchKey());
		detailsPage = resultsPage.selectProduct(product.getProductName());
		int totalImages=detailsPage.getProductImagesCount();
		System.out.println("The total number of images of selcted product : "+totalImages);
		Assert.assertEquals(totalImages, product.getProductImages());
	}	
}
