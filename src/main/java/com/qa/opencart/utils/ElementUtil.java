package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElementUtil {
	
	
		private WebDriver driver;
		
		public ElementUtil(WebDriver driver) {
			
			this.driver=driver;
		}
		public By getBy(String locatorType, String locatorValue) {
			By locator=null;
			
			switch (locatorType.toLowerCase()) {
			case "id":
				 locator=By.id(locatorValue);
				
				break;
				
			case "name":
				locator=By.name(locatorValue);
				break;
			case "className":
				 locator=By.className(locatorValue);
				
				break;
			case "xpath":
				 locator=By.xpath(locatorValue);
				
				break;
			case "cssSelector":
				 locator=By.cssSelector(locatorValue);
				
				break;
			case "linkText":
				 locator=By.linkText(locatorValue);
				
				break;
			case "partialLinkText":
				 locator=By.partialLinkText(locatorValue);
				
				break;
			case "tagName":
				 locator=By.tagName(locatorValue);
				
				break;

			default:
				System.out.println("please pass the right locator..");
				break;
			}
			return locator;
			
			
		}

		
		public  WebElement getElement(By locator) {
			return driver.findElement(locator);
			
		}
		public List <WebElement> getElements(By locator){
			return driver.findElements(locator);
		}
		public  void doSendKeys(By locator,String value) {
			
			getElement(locator).sendKeys(value);
		}	
		
		public void doClick(By locator) {
			getElement(locator).click();
		
		}
		
		public String doGetElementText(By locator) {
			return getElement(locator).getText();
			
		}
		public String doGetAttribute(By locator,String attributeName) {
			return getElement(locator).getAttribute(attributeName);
		
		}
		public boolean doIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		
		}
		public int getElementCount(By locator) {
			return getElements(locator).size();
		}
		public void clickOnElementFromSection(By locator,String value) {
			List<WebElement>eleList=driver.findElements(locator);
			for(WebElement e:eleList) {
				String text=e.getText();
				System.out.println(text);
				if(text.equals(value)) {
					
					e.click();
				break;
				}
				 
				}
		}
	public void printAllElementsList(By locator) {
			
			List<WebElement>eleList=getElements(locator);
			
			for(WebElement e:eleList) {
				String text=e.getText();
				System.out.println(text);
			}
				
			}
				
		public int getElementsListCount(By locator) {
			List<WebElement>eleList=getElements(locator);
			return eleList.size();
			
		}
		public List<String> getElementsTextList(By locator) {
			List<String>eleTextList=new ArrayList<String>();
			
			
			List<WebElement>eleList=getElements(locator);
			
			for(WebElement e:eleList) {
				String text=e.getText();
				eleTextList.add(text);
				
			}
				return eleTextList;
			
		}
		//************************DropDownUtils****************************
		
		public  void doSelectDropDownByIndex(By locator,int index) {
			Select select=new Select(getElement( locator));
			select.selectByIndex(index);
			
			
		}
		public void doSelectDropDownByValue(By locator,String value) {
			Select select=new Select(getElement( locator));
			select.selectByValue(value);
		
		}
		public void doSelectDropDownByVisibleText(By locator,String text) {
			Select select=new Select(getElement( locator));
			select.selectByVisibleText(text);
		}
		public int getSelectDropDownOptionsCount(By locator) {////ask sir
			//return getElements(locator).size();
			Select select=new Select(getElement(locator));
			List <WebElement> optionsList=select.getOptions();
			return optionsList.size();
		}
		public void printSelectDropDownValues(By locator) {
			Select select=new Select(getElement(locator));
			List <WebElement> optionsList=select.getOptions();
			System.out.println(optionsList.size());
			for(WebElement e:optionsList) {
				String text=e.getText();
				System.out.println(text);
			}
		}
		
		public void selectValueFromSelectDropDown(By locator,String value) {
			Select select=new Select(getElement(locator));
			List <WebElement> optionsList=select.getOptions();
		
			for(WebElement e:optionsList) {
				String Text=e.getText();
				System.out.println(Text);
				if(Text.contains("India")) {
					e.click();
					break;
			
				}
			}
		}
		public List<String> getSelectDropDownValuesList(By locator) {
			List<String>valuesList=new ArrayList<String>();
			Select select=new Select(getElement(locator));
			List <WebElement> optionsList=select.getOptions();
		
			for(WebElement e:optionsList) {
				String Text=e.getText();
				valuesList.add(Text);
			
			}
			return valuesList;	
			
		}
		//By using xpath for dropdown..(without using any select class):
		public  void selectValueFromDropDown(By locator,String value) {
			 List<WebElement> optionsList=getElements(locator);
			 System.out.println(optionsList.size());
			 for(WebElement e:optionsList) {
					String text=e.getText();
					System.out.println(text);
					if(text.contains(value)) {
						e.click();
						break;
					}
				}
			 
		}
		//************waits*********************
		public WebElement waitForElementPresent(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			
		}
		public WebElement waitForElementVisible(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			
		}
		
		public List<WebElement> waitForElementsVisible(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
		}

		public String waitForTitleContains(String titleFraction,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
			return null;
			
		}
		public String waitForTitleIS(String title,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			if (wait.until(ExpectedConditions.titleContains(title))) {
				return driver.getTitle();
			}
			return null;
			
		}
		public String waitForUrlContains(String urlFraction, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
			return null;
			
		}
		public String waitForUrlIs(String url,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			if (wait.until(ExpectedConditions.urlToBe(url))) {
				return driver.getCurrentUrl();
			}
			return null;
			
		}
		


			
			


	}

	



