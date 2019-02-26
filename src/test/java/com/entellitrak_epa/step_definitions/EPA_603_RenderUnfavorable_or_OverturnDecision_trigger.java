/**
 * 
 */
package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import com.entellitrak_epa.pages.BasePage;
import com.entellitrak_epa.pages.CasePage;
import com.entellitrak_epa.pages.PersonPage;
import com.entellitrak_epa.pages.PreScreeningPage;
import com.entellitrak_epa.utilities.BrowserUtils;
import com.entellitrak_epa.utilities.Driver;
import com.entellitrak_epa.utilities.EPA_constants;
import com.entellitrak_epa.utilities.FormFunctionsUtils;
import com.entellitrak_epa.utilities.PropertiesReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * @author Musa
 *
 */
public class EPA_603_RenderUnfavorable_or_OverturnDecision_trigger {
	
	EPA_constants constant = new EPA_constants();
	BrowserUtils browserUtils = new BrowserUtils();
	BasePage basePg = new BasePage();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	CasePage casePg = new CasePage();
	PersonPage personPg = new PersonPage();
	PreScreeningPage prescreeningPg = new PreScreeningPage();
	
	
	
	
	@When("^user assigns the case to epa_adjudicator$")
	public void user_assigns_the_case_to_epa_adjudicator() throws Throwable {
		formUtils.dropDown(casePg.Case_assignedAdjudicator, "Adjudicator I, Automation");
	}


	@When("^user selects Send to PreScreening Decision Review from action to take$")
	public void user_selects_Send_to_PreScreening_Decision_Review_from_action_to_take() throws Throwable {
		browserUtils.scrollDown();
		formUtils.dropDown(casePg.actionToTake, "Send to Pre-Screening Decision Review");
	}


	@Then("^case workflow becomes PreScreening Decision Review$")
	public void case_workflow_becomes_PreScreening_Decision_Review() throws Throwable {
	    browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		   System.out.println(workflow);
		    assertTrue(workflow.equals("Pre-Screening Decision Review"));
	    
	}

	@Given("^user logs in as adjudicator$")
	public void user_logs_in_as_adjudicator() throws Throwable {
		constant.signIn(PropertiesReader.getProperty("automation_adj"), PropertiesReader.getProperty("password"));
	    browserUtils.waitForElementToBeVisible(basePg.myCases);
	    assertTrue(basePg.myCases.isDisplayed());
	}

	@Then("^the workflow status is PreScreening Decision Review$")
	public void the_workflow_status_is_PreScreening_Decision_Review() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("Pre-Screening Decision Review"));
	}

	@Then("^Save button is enabled$")
	public void save_button_is_enabled() throws Throwable {
	    browserUtils.waitForElementToBeVisible(prescreeningPg.PreScreening_epaReview);
	    assertTrue(prescreeningPg.saveButton.isEnabled());
	}

	@Then("^Overturn Decision trigger button is disabled$")
	public void overturn_Decision_trigger_button_is_disabled() throws Throwable {
		assertTrue(!prescreeningPg.overturnDecisionButton.isEnabled());
	}

	@Then("^Render Unfavorable trigger button is disabled$")
	public void render_Unfavorable_trigger_button_is_disabled() throws Throwable {
		assertTrue(!prescreeningPg.unfavorableButton.isEnabled());
	}

	@When("^user selects concur from EPA review dropdown$")
	public void user_selects_concur_from_EPA_review_dropdown() throws Throwable {
	    formUtils.dropDown(prescreeningPg.PreScreening_epaReview, "Concur");
	    prescreeningPg.PreScreening_epaReviewComments.sendKeys("This is my comment...");
	    browserUtils.sleep(900);
	}

	@Then("^Render Unfavorable trigger button is enabled$")
	public void render_Unfavorable_trigger_button_is_enabled() throws Throwable {
		assertTrue(prescreeningPg.unfavorableButton.isEnabled());
	}

	@When("^user click on Render Unfavorable trigger button$")
	public void user_click_on_Render_Unfavorable_trigger_button() throws Throwable {
	    prescreeningPg.unfavorableButton.click();
	    browserUtils.sleep(900);
	}

	@Then("^case workflow becomes Unfovorable Close$")
	public void case_workflow_becomes_Unfovorable_Close() throws Throwable {
		Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
		browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	    
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("Unfavorable Close"));
	}
	
	
	
	
	
	// scenario #2 overturn decision


	@When("^user selects Do Not Concur from EPA review dropdown$")
	public void user_selects_Do_Not_Concur_from_EPA_review_dropdown() throws Throwable {
		formUtils.dropDown(prescreeningPg.PreScreening_epaReview, "Do Not Concur");
	    prescreeningPg.PreScreening_epaReviewComments.sendKeys("This is my comment...");
	    browserUtils.sleep(900);
	}

	@Then("^Overturn Decision trigger button is enabled$")
	public void overturn_Decision_trigger_button_is_enabled() throws Throwable {
		assertTrue(prescreeningPg.overturnDecisionButton.isEnabled());
	}

	@When("^user clicks on Overturn Decision trigger button$")
	public void user_clicks_on_Overturn_Decision_trigger_button() throws Throwable {
		prescreeningPg.overturnDecisionButton.click();
	    browserUtils.sleep(900);
	}

	@Then("^case workflow becomes pre screening$")
	public void case_workflow_becomes_pre_screening() throws Throwable {
		Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
		browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	    
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("Pre-Screening"));
	}


}
