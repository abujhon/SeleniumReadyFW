package com.project.runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:Reports/cucumber-pretty",
				"json:Reports/cucumber.json"},
		features = "src/test/resources/epa_features", 
		glue = "com/entellitrak_epa/step_definitions", 
		tags = "@epa_test",
		dryRun = false)

public class CukesRunner {


}
