package com.qa.opencart.base;

/**
 * 
 * @author Divya
 * base test is to create before and after test methods
 *
 */

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	protected WebDriver driver;
	protected DriverFactory df;
	protected LoginPage loginpage;
	protected Properties prop;
	protected AccountsPage accountsPage;
	protected ForgotPasswordPage forgotpwdpage;
	protected CommonsPage commonsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;

	protected RegisterPage regpage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);

		loginpage = new LoginPage(driver);
		forgotpwdpage = new ForgotPasswordPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}

}
