package com.project.step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.project.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class XrayAutomationTestClass {

	@Given("^User navigates to PSS$")
	public void user_navigates_to_PSS() throws Throwable {
		String epaTestURL = "http://52.3.187.122:8080/epa-bi-test/login.request.do";
	    Driver.getInstance().get(epaTestURL);
	}

	@When("^User logs in as a Supervisor$")
	public void user_logs_in_as_a_Supervisor() throws Throwable {
	    Driver.getInstance().findElement(By.id("username")).sendKeys("test_supervisor");
	    Driver.getInstance().findElement(By.id("password")).sendKeys("Password1!");
	    Driver.getInstance().findElement(By.id("submit")).submit();
	}

	@Then("^Home Dashboard displays$")
	public void home_Dashboard_displays() throws Throwable {
	    System.out.println("pass");
	    String logOutUrl = "http://52.3.187.122:8080/epa-bi-test/system.logout.do";
	    Driver.getInstance().get(logOutUrl);
	}

	@Then("^The My Cases icon displays$")
	public void the_My_Cases_icon_displays() throws Throwable {
	    Assert.fail();
	}

	@Then("^The Saved Searches icon displays$")
	public void the_Saved_Searches_icon_displays() throws Throwable {
	    
	}

	@Then("^The Reports icon displays$")
	public void the_Reports_icon_displays() throws Throwable {
	    
	}
}
