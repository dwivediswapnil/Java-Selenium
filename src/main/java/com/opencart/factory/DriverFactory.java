package com.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

	static Properties properties;
	FileInputStream fileInputStream;
	WebDriver driver;
	static OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();


	/*
	 * get the local thread copy of the driver
	 */
	public static synchronized WebDriver getDriver() {
		return threadLocal.get();
	}

	public Properties initProp() throws IOException {

		properties = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);
		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fileInputStream = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					fileInputStream = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					fileInputStream = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					throw new RuntimeException("WRONG ENV IS PASSED...");
				// break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;

	}

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		String browserName = properties.getProperty("browser");
		System.out.println(browserName);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			threadLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			threadLocal.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else {
			System.out.println("Invalid browsername");
			throw new RuntimeException("Please pass valid browser name");
		}
		// Maximizing the window
		getDriver().manage().window().maximize();

		// Implicitly waiting for page load.
		getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(properties.getProperty("implicit.wait"))));
		getDriver().get(properties.getProperty("url"));
		return getDriver();
	}

}
