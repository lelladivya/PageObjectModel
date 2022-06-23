package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class AccountPageTests extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountsPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actualTitle = accountsPage.getAccPageTitle();
		System.out.println("actual title:" + actualTitle);
		Assert.assertEquals(actualTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageHeaderTest() {
		Assert.assertEquals(accountsPage.getAccPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void accPageSectionHeadersTest() {
		List<String> actualAccPageSecList = accountsPage.getAccSectionsList();
		Collections.sort(actualAccPageSecList);
		Collections.sort(Constants.ACCOUNTS_PAGE_SECTION_HEADER_LIST);
		Assert.assertEquals(actualAccPageSecList, Constants.ACCOUNTS_PAGE_SECTION_HEADER_LIST);

	}

	@Test(priority = 4)
	public void isUserLoggedOutTest() {
		loginpage = accountsPage.clickOnLogoutLink();
		Assert.assertTrue(loginpage.getLogoutMessage().contains(Constants.USER_LOGOUT_MESSAGE));
	}

	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "MacBook" }, { "iMac" }, { "Samsung" }, { "Apple" } };

	}

	@Test(dataProvider = "getProductName", priority = 5)
	public void searchTest(String productName) {
		commonsPage = new CommonsPage(driver);
		searchResultsPage = commonsPage.doSearch(productName);
		String resultsPageHeader = searchResultsPage.getResultsPageHeader();
		Assert.assertTrue(resultsPageHeader.contains(productName));

	}

	@DataProvider
	public Object[][] getProductData() {
//		return new Object[][] {
//			{"MacBook","MacBook Pro"},
//			{"MacBook","MacBook Air"},
//			{"Samsung","Samsung SyncMaster 941BW"}

//	};
		Object searchData[][] = ExcelUtils.getTestData(Constants.ACCOUNT_SHEET_NAME);
		return searchData;
//		

	}

	@Test(dataProvider = "getProductData", priority = 6)
	public void productInfoTest(String productName, String mainProductName) {
		commonsPage = new CommonsPage(driver);
		searchResultsPage = commonsPage.doSearch(productName);
		String resultsPageHeader = searchResultsPage.getResultsPageHeader();
		productInfoPage = searchResultsPage.selectProductName(mainProductName);
		String mainProductNameValue = productInfoPage.getMainProductName();
		System.out.println(mainProductNameValue);
		Assert.assertEquals(mainProductNameValue, mainProductName);
	}

}
