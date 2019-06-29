package com.project.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Driver {

	private static WebDriver driver;

	public static WebDriver getInstance() {

//		EdgeDriverManager.getInstance().setup();
//		InternetExplorerDriverManager.getInstance().setup();
//		ChromeDriverManager.getInstance().setup();
//		FirefoxDriverManager.getInstance().setup();

		if (driver == null || ((RemoteWebDriver)driver).getSessionId() == null) {
			switch (PropertiesReader.getProperty("browser")) {
			case "linuxChrome":
				System.setProperty("webdriver.chrome.driver", PropertiesReader.getProperty("linuxChromePath"));
				driver = new ChromeDriver();
				break;
			case "winChrome":
				System.setProperty("webdriver.chrome.driver", PropertiesReader.getProperty("winChromePath"));
				driver = new ChromeDriver();
				break;
			case "linuxFirefox":
				System.setProperty("webdriver.gecko.driver", PropertiesReader.getProperty("linuxFirefoxPath"));
				driver = new FirefoxDriver();
				break;
			case "winFirefox":
				System.setProperty("webdriver.gecko.driver", PropertiesReader.getProperty("winFirefoxPath"));
				driver = new FirefoxDriver();
				break;
			case "ie":
				driver = new InternetExplorerDriver();
				break;

			}
		}

		return driver;

	}

	
	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
		}

	}

}
