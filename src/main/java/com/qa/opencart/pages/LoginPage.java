package com.qa.opencart.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	//need to mention all by locators of the page..use access specifier as private
		//pom is classic example for encapsulation
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		private By email=By.id("input-email");
		private By password=By.id("input-password");
		private By loginbtn=By.xpath("//input[@value='Login']");
		private By forgotpwd=By.linkText("Forgotten Password");
		private By registerlink=By.linkText("Register");
		private By forgottenpwdlink=By.linkText("Forgotten Password");
		private By myAccountlink=By.linkText("My Account");
//		private By addressBooklink=By.linkText("Address Book");
//		private By wishListlink=By.linkText("Wish List");
//		private By orderHistorylink=By.linkText("Order History");
//		private By downloadslink=By.linkText("Downloads");
//		private By recurringPaymentslink=By.linkText("Recurring payments");
//		private By rewardPointslink=By.linkText("Reward Points");
//		private By returnslink=By.linkText("Returns");
//		private By transactionslink=By.linkText("Transactions");
//		private By newsLetterlink=By.linkText("Newsletter");
//		
//		private By newCustomer=By.xpath("//h2[text()='New Customer']");
//		private By continuebtn=By.linkText("Continue");

		
		//logout page by locators
		private By accLogoutMessage=By.cssSelector("div #content");
		
		
		//public page class constructor for driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(this.driver);
	}
	@Step("getting login page title..")
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleIS(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println(title);
		return title;
	}
	@Step("getting login page url")
	public String getLoginPageUrl() {
		String url=eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		return url;
		
	}
	@Step("checking forgot password link exist or not...")
	public boolean isForgottenPasswordLinkExists() {
		return eleUtil.waitForElementVisible(forgottenpwdlink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	@Step("checking register link exist or not...")
	public boolean isRegisterLinkExists() {
		return eleUtil.waitForElementVisible(registerlink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
		
		
		
	}
	@Step("navigating to register page")
	public RegisterPage clickOnRegisterLink() {
		if(isRegisterLinkExists()) {
			eleUtil.doClick(registerlink);
			
		}
		return new RegisterPage(driver);
	}
	
	
	public boolean isMyAccountLinkExists() {
		return eleUtil.waitForElementVisible(myAccountlink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	@Step("login with username {0} and password {1}")
	public AccountsPage doLogin(String username,String pwd) {
		eleUtil.waitForElementVisible(email, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	//method for checking logout:
	@Step("getting success message after logout")
	public String getLogoutMessage() {
		String logoutMesg=eleUtil.waitForElementVisible(accLogoutMessage, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		System.out.println(logoutMesg);
		return logoutMesg;
	}
	@Step("navigating to forgot password page")
	public ForgotPasswordPage clickOnForgotPwd() {
		eleUtil.doClick(forgotpwd);
		return new ForgotPasswordPage(driver);
	}
	
		

}

