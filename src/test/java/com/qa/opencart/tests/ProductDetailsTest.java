package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductDetailsTest extends BaseTest{
	
	@BeforeClass
	public void searchsetup() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void productDetailsTest() {
		resultsPage = accPage.doSearch("Macbook");
		detailsPage = resultsPage.selectProduct("MacBook Pro");
		Map<String,String> productDetails=detailsPage.getProductDetails();
		System.out.println(productDetails);
		/* {productname=MacBook Pro, Brand=Apple, Product Code=Product 18, Reward
		 * Points=800, Availability=In Stock, productprice=$2,000.00,
		 * extaxprice=$2,000.00}*/
		softAssert.assertEquals(productDetails.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productDetails.get("Brand"), "Apple");
		//softAssert.assertEquals(productDetails.get("code"), "Product 18");
		softAssert.assertEquals(productDetails.get("Availability"), "In Stock");
		softAssert.assertAll();
	}
	
	@Test(priority=2)
	public void addProductToCart() {
		resultsPage = accPage.doSearch("Macbook");
		detailsPage = resultsPage.selectProduct("MacBook Pro");
		detailsPage.selectProductQuantity(1);
		detailsPage.ProductAddedToCart();
		Assert.assertTrue(detailsPage.ProductAddedToCart().contains("MacBook Pro"));
	}
	
}
