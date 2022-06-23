package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By mainProductName = By.cssSelector("div #content h1");
	private By mainProductDescription = By.cssSelector("div #tab-description");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getMainProductName() {
		return eleUtil.doGetElementText(mainProductName);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}

	public String getProductDescription() {
		return eleUtil.doGetElementText(mainProductDescription);
	}

	public Map<String, String> getProductInfo() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getMainProductName());
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaValue = metaData.split(":")[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		// price data:
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("extaxPrice", exTaxPrice);
		System.out.println(productInfoMap);
		return productInfoMap;

	}

}
