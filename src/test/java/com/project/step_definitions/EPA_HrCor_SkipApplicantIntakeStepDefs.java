package com.project.step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pages.BasePage;
import com.project.pages.IntakeInitiationPage;
import com.project.pages.SignOnPage;
import com.project.utilities.BrowserUtils;
import com.project.utilities.Driver;
import com.project.utilities.FormFunctionsUtils;
import com.project.utilities.IntakeCreator;
import com.project.utilities.PropertiesReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EPA_HrCor_SkipApplicantIntakeStepDefs {

	SignOnPage signOnPage = new SignOnPage();
	BrowserUtils browserUtils = new BrowserUtils();
	IntakeInitiationPage intakeInitiationPage = new IntakeInitiationPage();
	BasePage basePage =  new BasePage();
	FormFunctionsUtils formFunctionsUtils = new FormFunctionsUtils();
	IntakeCreator intakeCreator = new IntakeCreator();
	
	@Given("^user logs in as a \"([^\"]*)\"$")
	public void user_logs_in_as_a(String user) {
		switch (user) {
		case "HR":
			signOnPage.username.sendKeys(PropertiesReader.getProperty("automation_hr"));
			signOnPage.password.sendKeys(PropertiesReader.getProperty("password"));
			signOnPage.submit.click();
			break;
		case "COR":
			signOnPage.username.sendKeys(PropertiesReader.getProperty("automation_cor"));
			signOnPage.password.sendKeys(PropertiesReader.getProperty("password"));
			signOnPage.submit.click();
			break;
		}
	}

	@Then("^successful intake creation message displays$")
	public void successful_intake_creation_message_displays() {
		browserUtils.sleep(1000);
	    String expectedMessage = "New Intake form has been submitted.";
	    assertTrue(intakeInitiationPage.newIntakeSubmittedMessage.isDisplayed());
	    assertTrue(intakeInitiationPage.newIntakeSubmittedMessage.getText().equals(expectedMessage));
	}

	@When("^user selects the Record Checks Queue inbox$")
	public void user_selects_the_Record_Checks_Queue_inbox() {
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		browserUtils.sleep(1000);
		formFunctionsUtils.partialDropDown(basePage.inboxSelection, "Record Checks Queue");
		Driver.getInstance().switchTo().defaultContent();
	}

	@When("^user searches for case initiated in the Record Checks Queue inbox$")
	public void user_searches_for_case_initiated_in_the_Record_Checks_Queue_inbox() {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		System.out.println(personNameInbox);
		browserUtils.sleep(1000);
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		browserUtils.sleep(1000);
		basePage.enhancedInboxSearch.sendKeys(personNameInbox);
		browserUtils.sleep(1000);
		Driver.getInstance().switchTo().defaultContent();
	}

	@Then("^record will display in the Record Checks Queue inbox$")
	public void record_will_display_in_the_Record_Checks_Queue_inbox() {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		int x = 0;
		for (WebElement personNames: basePage.enhancedInboxPersonNames_assignmentQueues) {
			if(personNames.getText().trim().equals(personNameInbox.trim())) {
				assertTrue(basePage.enhancedInboxPersonNames_assignmentQueues.get(x).getText().trim().equals(personNameInbox));
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}
	
	@Then("^case status in the Record Checks Queue inbox displays as Record Checks$")
	public void case_status_in_the_Record_Checks_Queue_inbox_displays_as_Record_Checks() {
		String expectedStatus = "Record Checks";
		String actualTargetStatus="";
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		int x = 0;
		for (WebElement caseStatuses : basePage.enhancedInboxCaseStatus_assignmentQueues) {
			System.out.println(expectedStatus+" ----- "+caseStatuses.getText());
			if(caseStatuses.getText().trim().equals(expectedStatus.trim())) {
				actualTargetStatus = basePage.enhancedInboxCaseStatus_assignmentQueues.get(x).getText();
				assertEquals(expectedStatus, actualTargetStatus);
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}

	@When("^user checks the assignment checkbox next to the applicants name$")
	public void user_checks_the_assignment_checkbox_next_to_the_applicants_name() {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		browserUtils.sleep(1000);
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		int x = 0;
		for (WebElement personNames: basePage.enhancedInboxPersonNames_assignmentQueues) {
			if(personNames.getText().trim().equals(personNameInbox.trim())) {
				assertTrue(basePage.enhancedInboxPersonNames_assignmentQueues.get(x).getText().trim().equals(personNameInbox));
				basePage.enhancedInboxAssignmentCheckboxes.get(x).click();
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}

	@When("^user sets the assignment inbox to <\"([^\"]*)\">$")
	public void user_sets_the_assignment_inbox_to(String specialist) {
		browserUtils.sleep(1000);
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		formFunctionsUtils.dropDown(basePage.enhancedInboxAssignmentDropdown, specialist);
		Driver.getInstance().switchTo().defaultContent();
	}

	@When("^user clicks on Assign$")
	public void user_clicks_on_Assign() {
		browserUtils.sleep(1000);
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		basePage.enhancedInboxAssign.click();
		Driver.getInstance().switchTo().defaultContent();
	}

	@Then("^the assignment has been made$")
	public void the_assignment_has_been_made() {
		String expectedPartialText = "assignments have been successfully created";
		WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = Driver.getInstance().switchTo().alert(); 
		String actualMsg = alert.getText().trim();
		if(!(actualMsg.equals("") && actualMsg.equals(null))) {
			assertTrue(actualMsg.contains(expectedPartialText));
		} else {
			System.out.println("Not able to run assertion:"+actualMsg);
		}
		alert.accept();
	}

	@When("^user searches for case assigned in the Assigned Cases inbox$")
	public void user_searches_for_case_assigned_in_the_Assigned_Cases_inbox() {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		basePage.enhancedInboxSearch.sendKeys(personNameInbox);
		browserUtils.sleep(1000);
		Driver.getInstance().switchTo().defaultContent();
	}

	@When("^user navigates to case record searched on from the Assigned Cases inbox$")
	public void user_navigates_to_case_record_searched_on_from_the_Assigned_Cases_inbox() {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		browserUtils.sleep(1000);
		int x = 0;
		for (WebElement personNames: basePage.enhancedInboxPersonNames) {
			if(personNames.getText().trim().equals(personNameInbox.trim())) {
				basePage.enhancedInboxPersonNames.get(x).click();
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}
}
