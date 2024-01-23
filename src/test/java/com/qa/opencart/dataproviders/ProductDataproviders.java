package com.qa.opencart.dataproviders;

import org.testng.annotations.DataProvider;

public class ProductDataproviders {
	@DataProvider(name="ProductDataWithSearchKey")
	public Object[][] getProductSearchKey() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
		};
	}
	
	@DataProvider(name="ProductDataWithName")
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
		};
	}
	
	@DataProvider(name="ProductDataWithImages")
	public Object[][] getProductTestImagesData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro",4},
			{"iMac", "iMac",3},
			{"Samsung", "Samsung Galaxy Tab 10.1",7},
		};
	}
}
