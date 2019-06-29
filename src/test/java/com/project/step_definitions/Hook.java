package com.project.step_definitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.project.utilities.Driver;
import com.project.utilities.FormFunctionsUtils;
import com.project.utilities.PropertiesReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	
	FormFunctionsUtils formUtils = new FormFunctionsUtils();

	@Before
	public void setUp() {
		switch (PropertiesReader.getProperty("browser")) {
		case "winChrome":
			Driver.getInstance().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Driver.getInstance().manage().window().maximize();
			break;
		default:
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) Driver.getInstance()).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			scenario.write(scenario.getName());
			
			formUtils.signOut();
		}
		
		
		Driver.closeDriver();
	}
}
