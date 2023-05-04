package com.qa.opencart.base;


import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.opencart.factory.DriverFactory;
import com.opencart.pages.LoginPage;

public class BaseTest {

	DriverFactory driverFactory;
	Properties properties;
	WebDriver driver;
	protected LoginPage loginPage;

	@BeforeTest
	public void setup() throws IOException {
		DriverFactory driverFactory = new DriverFactory();
		properties = driverFactory.initProp();
		driver = driverFactory.initDriver(properties);
		loginPage = new LoginPage(driver);

	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
