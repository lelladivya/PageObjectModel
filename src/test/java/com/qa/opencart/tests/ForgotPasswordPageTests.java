package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ForgotPasswordPageTests extends BaseTest {

	@BeforeClass
	public void clickOnForgotPwdPage() {
		forgotpwdpage = loginpage.clickOnForgotPwd();

	}

	@Test
	public void forgotPwdPageTitleTest() {
		String actualForgotPwdPageTitle = forgotpwdpage.getForgotPageTitle();
		Assert.assertEquals(actualForgotPwdPageTitle, Constants.FORGOT_PWD_PAGE_TITLE);

	}

	@Test
	public void forgotpwdPageEmailidExistTest() {
		Assert.assertTrue(forgotpwdpage.isEmailAddressExists());
	}

	@Test
	public void forgotpwdPageContinueBtnExistTest() {
		Assert.assertTrue(forgotpwdpage.isContinueButtonExists());
	}

}
