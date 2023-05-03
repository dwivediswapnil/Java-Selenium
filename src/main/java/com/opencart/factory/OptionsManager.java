package com.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	Properties prop;
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	private EdgeOptions edgeOptions;

	public OptionsManager(Properties properties) {
		this.prop = properties;

	}

	// Handle different chrome options like remote==true, headless = true
	public ChromeOptions getChromeOptions() {
		chromeOptions = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			chromeOptions.setBrowserVersion(prop.getProperty("browser.version"));
			chromeOptions.setCapability("browsername", prop.getProperty("browser"));
			chromeOptions.setCapability("enableVNC", true);
			chromeOptions.setCapability("name", prop.getProperty("testcase.name"));
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("===================Headless mode====================");
			chromeOptions.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("===================Running in incognito====================");
			chromeOptions.addArguments("--incognito");
		}
		return chromeOptions;

	}

	public FirefoxOptions getFirefoxOptions() {
		System.out.println("firefox options");
		firefoxOptions = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			firefoxOptions.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			firefoxOptions.addArguments("--incognito");
		return firefoxOptions;
	}

	public EdgeOptions getEdgeOptions() {
		edgeOptions = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			edgeOptions.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			edgeOptions.addArguments("--incognito");
		return edgeOptions;
	}

}
