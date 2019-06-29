package com.entellitrak_epa.step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.project.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestFeatureStepDefs {
	@Given("^user is on google home page$")
	public void user_is_on_the_google_home_page(String arg1) throws Throwable {
			Driver.getInstance().get("https://www.google.com/");
	}

	@When("^user clicks on gmail link provided$")
	public void the_user_clicks_on_gmail_link_provided(String arg1) throws Throwable {
			Driver.getInstance().findElement(By.linkText("Gmail")).click();
	}

	@Then("^two create an account links shall display$")
	public void the_two_create_an_account_links_shall_display(String arg1) throws Throwable {
	   WebElement firstCreateAccountLink = Driver.getInstance().findElement(By.xpath("//a[.='Create an account']"));
	   WebElement secondCreateAccountLink = Driver.getInstance().findElement(By.xpath("//a[.='CREATE AN ACCOUNT']"));
	   
//	   Assert.assertFalse(firstCreateAccountLink.isDisplayed());
//	   Assert.assertTrue(secondCreateAccountLink.isDisplayed());
	   
	}
}
