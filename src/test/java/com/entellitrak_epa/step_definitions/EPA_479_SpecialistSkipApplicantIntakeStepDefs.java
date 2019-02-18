package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.*;

import org.openqa.selenium.WebElement;

import com.entellitrak_epa.pages.BasePage;
import com.entellitrak_epa.pages.CasePage;
import com.entellitrak_epa.pages.IntakeInitiationPage;
import com.entellitrak_epa.pages.SignOnPage;
import com.entellitrak_epa.utilities.BrowserUtils;
import com.entellitrak_epa.utilities.Driver;
import com.entellitrak_epa.utilities.IntakeCreator;
import com.entellitrak_epa.utilities.PropertiesReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class EPA_479_SpecialistSkipApplicantIntakeStepDefs {
	
	BrowserUtils browserUtils = new BrowserUtils();
	SignOnPage signOnPage = new SignOnPage();
	BasePage basePage = new BasePage();
	IntakeCreator intakeCreator = new IntakeCreator();
	IntakeInitiationPage intakeInitiationPage = new IntakeInitiationPage();
	CasePage casePage = new CasePage();

	@Given("^user logs in as a Specialist$")
	public void user_logs_in_as_a_Specialist()  {
		browserUtils.waitForElementToBeVisible(signOnPage.username);
		signOnPage.username.sendKeys(PropertiesReader.getProperty("automation_specialist"));
		signOnPage.password.sendKeys(PropertiesReader.getProperty("password"));
		signOnPage.submit.click();
	}

	@When("^user clicks on Start Intake$")
	public void user_clicks_on_Start_Intake()  {
		browserUtils.waitForElementToBeVisible(basePage.startIntake);
		basePage.startIntake.click();
	}

	@When("^user completes a \"([^\"]*)\" intake with \"([^\"]*)\" while skipping applicant intake$")
	public void user_completes_a_intake_with_while_skipping_applicant_intake(String appType, String email)  {
	    intakeCreator.submitIntake_SkipApplicantIntakeFor(appType, email, true);
	}

	@When("^user clicks Submit on the Intake form$")
	public void user_clicks_Submit_on_the_Intake_form()  {
	    intakeInitiationPage.saveButton.click();
	}

	@Then("^successful intake creation messages display$")
	public void successful_intake_creation_messages_display()  {
		browserUtils.sleep(1000);
	    String expectedMessage = "New Intake form has been submitted.";
		for (WebElement msgs : intakeInitiationPage.successfulMessages) {
	    	assertTrue(msgs.isDisplayed());
	    }
	    assertTrue(intakeInitiationPage.successfulMessages.get(0).getText().equals(expectedMessage));
	}

	@Then("^a person and case link display$")
	public void a_person_and_case_link_display()  {
		assertTrue(intakeInitiationPage.intakePersonLink.isDisplayed());
		assertTrue(intakeInitiationPage.intakeCaseLink.isDisplayed());
	}

	@When("^user clicks on the Case link$")
	public void user_clicks_on_the_Case_link()  {
		intakeInitiationPage.intakeCaseLink.click();
	}

	@Then("^case form displays$")
	public void case_form_displays()  {
	    assertTrue(casePage.trackingForm.isDisplayed());
	}

	@Then("^case workflow status is now Record Checks$")
	public void case_workflow_status_is_now_Record_Checks()  {
		String actualStatus = casePage.caseSubwayWorkflowStatus.getText()
				.substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim();
		String expectedStatus = "Record Checks";
		assertTrue(expectedStatus.equals(actualStatus));
	}

	@Then("^specialist assigned is the specialist who submitted the intake$")
	public void specialist_assigned_is_the_specialist_who_submitted_the_intake()  {
	    String expectedAssignedSpecialist = "Specialist, Automation";
	    String actualAssignedSpecialist = casePage.Case_assignedSpecialist_display.getText().trim();
	    assertTrue(expectedAssignedSpecialist.equals(actualAssignedSpecialist));
	}

	@When("^user navigates to home page$")
	public void user_navigates_to_home_page()  {
		basePage.home.click();
	}

	@When("^user clicks on My Cases$")
	public void user_clicks_on_My_Cases()  {
	    browserUtils.sleep(1000);
	    basePage.myCases.click();
	}

	@When("^user searches for case initiated in the Assigned Cases inbox$")
	public void user_searches_for_case_initiated_in_the_Assigned_Cases_inbox()  {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		basePage.enhancedInboxSearch.sendKeys(personNameInbox);
		browserUtils.sleep(1000);
		int x = 0;
		for (WebElement personNames: basePage.enhancedInboxPersonNames) {
			System.out.println(personNameInbox+" ----- "+personNames.getText());
			if(personNames.getText().trim().equals(personNameInbox.trim())) {
//				basePage.enhancedInboxPersonNames.get(x).click();
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}

	@Then("^record will display in the Assigned Cases inbox$")
	public void record_will_display_in_the_Assigned_Cases_inbox()  {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		String targetName;
	    browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		int x = 0;
		for (WebElement personNames : basePage.enhancedInboxPersonNames) {
//			System.out.println(personNameInbox+" ----- "+personNames.getText());
			if(personNames.getText().trim().equals(personNameInbox.trim())) {
				targetName = basePage.enhancedInboxPersonNames.get(x).getText();
				assertEquals(personNameInbox, targetName);
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}

	@Then("^case status in the inbox displays as Record Checks$")
	public void case_status_in_the_inbox_displays_as_Record_Checks()  {
		String expectedStatus = "Record Checks";
		String actualTargetStatus="";
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		int x = 0;
		for (WebElement caseStatuses : basePage.enhancedInboxCaseStatus) {
			System.out.println(expectedStatus+" ----- "+caseStatuses.getText());
			if(caseStatuses.getText().trim().equals(expectedStatus.trim())) {
				actualTargetStatus = basePage.enhancedInboxCaseStatus.get(x).getText();
				assertEquals(expectedStatus, actualTargetStatus);
			}
			x++;
		}
		Driver.getInstance().switchTo().defaultContent();
	}
}
