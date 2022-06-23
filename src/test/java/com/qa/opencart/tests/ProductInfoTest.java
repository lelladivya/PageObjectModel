package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.DescriptionConstants;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		commonsPage = new CommonsPage(driver);
		productInfoPage = new ProductInfoPage(driver);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "MacBook", "MacBook Air", 4 },
				{ "Samsung", "Samsung SyncMaster 941BW", 1 } };

	}

	@Test(dataProvider = "getProductData")
	public void productImagesCount(String searchKey, String productName, int imagesCount) {
		searchResultsPage = commonsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProductName(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);

	}

	@Test
	public void productDescriptionTest() {
		searchResultsPage = commonsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProductName("MacBook Air");
		String productDesc = productInfoPage.getProductDescription();
		System.out.println("product description:" + productDesc);
		softAssert.assertTrue(productDesc != null);
		softAssert.assertFalse(productDesc.isEmpty());
		softAssert.assertTrue(productDesc.contains("MacBook Air"));
		softAssert.assertTrue(productDesc.contains(DescriptionConstants.MACBOOK_AIR_DESCRIPTION));
		softAssert.assertAll();

//		Assert.assertTrue(productDesc != null);
//		Assert.assertFalse(productDesc.isEmpty());
//		Assert.assertTrue(productDesc.contains("MacBook Air"));
//		Assert.assertTrue(productDesc.contains(DescriptionConstants.MACBOOK_AIR_DESCRIPTION));

	}

	@Test
	public void productDataTest() {
		searchResultsPage = commonsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProductName("MacBook Air");
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfo.get("name"), "MacBook Air");
		softAssert.assertEquals(actProductInfo.get("Reward Points"), "700");
		softAssert.assertAll();
	}

}
