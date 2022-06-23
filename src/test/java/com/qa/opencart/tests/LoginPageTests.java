package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC -100: Design login page for open cart application")
@Story("US-101: design login page features ")

public class LoginPageTests extends BaseTest {

	@Description("verify login page Title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginpage.getLoginPageTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("verify login page Url test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualUrl = loginpage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}

	@Description("verify forgot password link exist on the login page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgottenPasswordLinkExists() {
		Assert.assertTrue(loginpage.isForgottenPasswordLinkExists());
	}

	@Description("verify register link on the login page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void isRegisterLinkExists() {
		Assert.assertTrue(loginpage.isRegisterLinkExists());

	}

	@Description("verify user is able to login to the application with correct credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accountsPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		softAssert.assertEquals(accountsPage.getAccPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
		softAssert.assertTrue(accountsPage.isLogoutLinkExists());
		softAssert.assertAll();
	}

}
