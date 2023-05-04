package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTests extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String  actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, "Account Login");
	}
	@Test
	public void loginPageUrlTest() {
		
	}
	@Test
	public void forgotPwdLinkExistTest() {
		
	}
	@Test
	public void loginTest() {
		
	}

}
