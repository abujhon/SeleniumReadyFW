package com.project.utilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

public class PropertiesReader {

	private static Properties userDataFile;

	static {

		try {

			String propertyPath = "src/test/resources/epa_test_data/epa_test_data.properties";
			FileInputStream input = new FileInputStream(propertyPath);

			userDataFile = new Properties();
			userDataFile.load(input);

			input.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getProperty(String keyName) {
		return userDataFile.getProperty(keyName);
	}

}
