package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By resultProduct=By.cssSelector("div.product-layout.product-grid");
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(this.driver);
	}
	public String getResultsPageTitle(String searchkey) {
		return eleUtil.waitForTitleIsAndCapture(searchkey, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public int getSearchItemCount() {
		int resultCount = eleUtil.waitForElementsVisible(resultProduct, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product search count = "+resultCount);
		return resultCount;
	}
	
	public ProductDetailsPage selectProduct(String productName) {
		 By productNameLocator = By.linkText(productName);
		 eleUtil.waitForElementPresence(productNameLocator, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		 eleUtil.doClick(productNameLocator);
		 return new ProductDetailsPage(driver);
	}
}
