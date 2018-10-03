package com.entellitrak_epa.runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:Reports/cucumber-pretty",
				"json:Reports/cucumber.json"},
		features = "src/test/resources/epa_features", 
		glue = "com/entellitrak_epa/step_definitions", 
		tags = "@test",
		dryRun = false)

public class CukesRunner {


}
