package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//div/label[@class='radio-inline'])[position()=1]");
	private By subscribeNo = By.xpath("(//div/label[@class='radio-inline'])[position()=2]");

	private By checkBox = By.xpath("//input[@type='checkbox']");
	private By submitBtn = By.xpath("//input[@type='submit']");
	private By registerSuccessMesg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public Boolean doRegister(String firstName, String lastName, String email, String phone, String password,
			String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.phone, phone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);

		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(checkBox);
		eleUtil.doClick(submitBtn);
		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, Constants.DEFAULT_ELEMENT_TIME_OUT)
				.getText();
		System.out.println(successMesg);
		if (successMesg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;

		}
	}
}
