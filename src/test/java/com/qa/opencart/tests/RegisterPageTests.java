package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterPageTests extends BaseTest {
	@BeforeClass
	public void regSetUp() {
		regpage = loginpage.clickOnRegisterLink();

	}

	public String getRandomEmailid() {
		Random random = new Random();
		String email = "febAutomation" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData[][] = ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegisterTestData")
	public void userRegisterTest(String firstName, String lastName, String phone, String password, String subscribe) {
		Assert.assertTrue(regpage.doRegister(firstName, lastName, getRandomEmailid(), phone, password, subscribe));
	}

}
