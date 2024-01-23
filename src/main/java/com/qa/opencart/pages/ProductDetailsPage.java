package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductDetailsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.xpath("//a[@class=\"thumbnail\"]/img");
	private By productMetaDetails = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceDetails = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quanity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successAddtoCart =By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]");

	private Map<String, String> productDetailsMap;

	public String getProductHeaderName() {
		return eleUtil.doGetElementText(productHeader);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
	}
	public Map<String,String> getProductDetails() {
		productDetailsMap = new LinkedHashMap<String, String>();
		productDetailsMap.put("productname", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		return productDetailsMap;
	}
	
	public void selectProductQuantity(int units) {
		eleUtil.doSendKeys(quanity,toString().valueOf(units));
	}
	
	public String ProductAddedToCart() {
		eleUtil.doClick(addToCart);
		
		String msg = eleUtil.waitForElementPresence(successAddtoCart, AppConstants.SHORT_DEFAULT_WAIT).getText();
		System.out.println(msg);
		return msg;
		
	}
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaDetails);
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productDetailsMap.put(key, value);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceDetails);
		String actPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText();
		String exTaxPriceValue = exTaxPrice.split(":")[1].trim();
		productDetailsMap.put("productprice", actPrice);
		productDetailsMap.put("extaxprice", exTaxPriceValue);
	}
}
