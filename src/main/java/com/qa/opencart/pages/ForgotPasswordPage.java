package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
	private WebDriver driver;
	private By forgorPwdPageHeader = By.cssSelector("div #content");
	private By emailSearch = By.id("input-email");
	private By continuebtn = By.xpath("//input[@value='Continue']");

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getForgotPageTitle() {
		return driver.getTitle();
	}

	public String getForgotPageUrl() {
		return driver.getCurrentUrl();
	}

	public String GetForgotPwdPageHeader() {
		String header = driver.findElement(forgorPwdPageHeader).getText();
		return header;
	}

	public boolean isEmailAddressExists() {
		return driver.findElement(emailSearch).isDisplayed();
	}

	public boolean isContinueButtonExists() {
		return driver.findElement(continuebtn).isDisplayed();

	}

	public void enterEmail(String emailid) {
		if (isEmailAddressExists()) {
			driver.findElement(emailSearch).sendKeys(emailid);
			driver.findElement(continuebtn).click();
		}
	}

}

