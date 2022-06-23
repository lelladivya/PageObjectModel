package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div #logo h1 a");
	private By accPageSectionHeaders = By.cssSelector("div #content  h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getAccPageTitle() {
		String accPageTitle = eleUtil.waitForTitleIS(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Account page title is" + accPageTitle);
		return accPageTitle;
	}

	public String getAccPageUrl() {
		String accPageUrl = eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION,
				Constants.DEFAULT_TIME_OUT);
		System.out.println("Account page URL is" + accPageUrl);
		return accPageUrl;
	}

	public String getAccPageHeader() {
		String accPageHeader = eleUtil.waitForElementVisible(header, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		return accPageHeader;
	}

	public List<String> getAccSectionsList() {
		List<WebElement> accPageSecHeaders = eleUtil.waitForElementsVisible(accPageSectionHeaders,
				Constants.DEFAULT_ELEMENT_TIME_OUT);
		List<String> accPageSectionHeadersList = new ArrayList<String>();
		for (WebElement e : accPageSecHeaders) {
			String text = e.getText();
			accPageSectionHeadersList.add(text);
		}
		return accPageSectionHeadersList;

	}

	public boolean isLogoutLinkExists() {
		return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public LoginPage clickOnLogoutLink() {
		if (isLogoutLinkExists()) {
			eleUtil.doClick(logoutLink);
			return new LoginPage(driver);
		}
		return null;
	}

}
