package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.entellitrak_epa.pages.AdjudicationPage;
import com.entellitrak_epa.pages.BasePage;
import com.entellitrak_epa.pages.CasePage;
import com.entellitrak_epa.pages.IntakeInitiationPage;
import com.entellitrak_epa.pages.PersonPage;
import com.entellitrak_epa.pages.SignOnPage;
import com.entellitrak_epa.utilities.BrowserUtils;
import com.entellitrak_epa.utilities.Driver;
import com.entellitrak_epa.utilities.EPA_constants;
import com.entellitrak_epa.utilities.FormFunctionsUtils;
import com.entellitrak_epa.utilities.IntakeCreator;
import com.entellitrak_epa.utilities.PropertiesReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EPA_adjudicationFormFunctionalityStepDefs {

	BrowserUtils browserUtils = new BrowserUtils();
	SignOnPage signOnPage = new SignOnPage();
	BasePage basePage = new BasePage();
	IntakeCreator intakeCreator = new IntakeCreator();
	IntakeInitiationPage intakeInitiationPage = new IntakeInitiationPage();
	PersonPage personPage = new PersonPage();
	CasePage casePage = new CasePage();
	FormFunctionsUtils formFunctionsUtils = new FormFunctionsUtils();
	AdjudicationPage adjudicationPage = new AdjudicationPage();

	@Given("^user navigates to PSS$")
	public void user_navigates_to_PSS() throws Throwable {
		Driver.getInstance().get(EPA_constants.URL);
	}

	@When("^user logs in as a Supervisor$")
	public void user_logs_in_as_a_Supervisor() throws Throwable {
		browserUtils.waitForElementToBeVisible(signOnPage.username);
		signOnPage.username.sendKeys(PropertiesReader.getProperty("automation_supervisor"));
		signOnPage.password.sendKeys(PropertiesReader.getProperty("password"));
		signOnPage.submit.click();
	}

	@When("^user creates an intake$")
	public void user_creates_an_intake() throws Throwable {
		browserUtils.waitForElementToBeVisible(basePage.startIntake);
		basePage.startIntake.click();
		browserUtils.waitForElementToBeVisible(intakeInitiationPage.investigationInformatinFieldSet);
		intakeCreator.submitIntakeFor("Student", "eservellon@chainbridgesolutions.com");
		intakeInitiationPage.saveButton.click();
	}

	@When("^user navigates to case via rapid search from home page$")
	public void user_navigates_to_case_via_rapid_search_from_home_page() throws Throwable {
		basePage.home.click();
		browserUtils.waitForElementToBeClickable(basePage.rapidSearchInput);
		String formattedSSN = intakeCreator.getSSN();
		formattedSSN = new StringBuilder(formattedSSN).insert(formattedSSN.length() - 4, "-")
				.insert(formattedSSN.length() - 6, "-").toString();
		basePage.rapidSearchInput.sendKeys(formattedSSN);
		basePage.rapidSearchIcon.click();
		browserUtils.waitForElementToBeVisible(basePage.rapidSearchResultsTable);
		browserUtils.sleep(1000);
		for (WebElement SSNs : basePage.rapidSearchSSN) {
			if (SSNs.getText().equals(formattedSSN)) {
				((JavascriptExecutor) Driver.getInstance()).executeScript("arguments[0].click();",
						basePage.rapidSearchLastName.get(0));
			}
		}
		browserUtils.sleep(1000);
		browserUtils.switchTabs("Person");
		browserUtils.waitForElementToBeClickable(personPage.caseSummaryCaseLink);
		personPage.caseSummaryCaseLink.click();
	}

	@When("^user pushes created case to the Adjudication state$")
	public void user_pushes_created_case_to_the_Adjudication_state() throws Throwable {
		browserUtils.waitForElementToBeClickable(casePage.addCommentButton);
		if(!(casePage.Case_applicantTenureLessThan6Months_no.isSelected() && casePage.Case_applicantTenureLessThan6Months_yes.isSelected())) {
			casePage.Case_applicantTenureLessThan6Months_no.click();
		}
		if (!(casePage.Case_eqipRequired_no.isSelected() && casePage.Case_eqipRequired_yes.isSelected())) {
			casePage.Case_eqipRequired_yes.click();
		}
		if(casePage.caseSubwayWorkflowStatus.getText().substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim().equals("Applicant Intake")) {
			formFunctionsUtils.dropDown(casePage.actionToTake, "Submit Forms");
		}
		casePage.saveAndContinueButton.click();
		browserUtils.waitForElementToBeClickable(casePage.saveAndContinueButton);
		if(casePage.caseSubwayWorkflowStatus.getText().substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim().equals("Intake Forms Review Queue")) {
			formFunctionsUtils.randomDropDownValue(casePage.Case_assignedSpecialist);
			formFunctionsUtils.dropDown(casePage.actionToTake, "Assign Specialist");
		}
		casePage.saveAndContinueButton.click();
		browserUtils.waitForElementToBeClickable(casePage.saveAndContinueButton);
		if(casePage.caseSubwayWorkflowStatus.getText().substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim().equals("Intake Forms Review")) {
			formFunctionsUtils.dropDown(casePage.actionToTake, "Complete Intake");
		}
		casePage.saveAndContinueButton.click();
		browserUtils.waitForElementToBeClickable(casePage.saveAndContinueButton);
		if(casePage.caseSubwayWorkflowStatus.getText().substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim().equals("Record Checks")) {
			formFunctionsUtils.dropDown(casePage.actionToTake, "Skip to Adjudication");
		}
		casePage.saveAndContinueButton.click();
		browserUtils.waitForElementToBeClickable(casePage.saveAndContinueButton);
		if(casePage.caseSubwayWorkflowStatus.getText().substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim().equals("Adjudication Queue")) {
			formFunctionsUtils.dropDown(casePage.Case_assignedAdjudicator, "Adjudicator I, Automation");
			formFunctionsUtils.dropDown(casePage.actionToTake, "Assign Adjudicator");
		}
		casePage.saveAndContinueButton.click();
		browserUtils.waitForElementToBeClickable(casePage.saveAndContinueButton);
		if(!(casePage.caseSubwayWorkflowStatus.getText().substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim().equals("Adjudication"))) {
			System.out.println("not at the Adjudication state - test failed");
			fail();
		}
	}

	@When("^user logs out from PSS$")
	public void user_logs_out_from_PSS() throws Throwable {
		basePage.signOut.click();
	}

	@Given("^user logs in as an Adjudicator$")
	public void user_logs_in_as_an_Adjudicator() throws Throwable {
		browserUtils.waitForElementToBeVisible(signOnPage.username);
		signOnPage.username.sendKeys(PropertiesReader.getProperty("automation_adj"));
		signOnPage.password.sendKeys(PropertiesReader.getProperty("password"));
		signOnPage.submit.click();
	}

	@When("^user navigates to the Assigned Cases queue$")
	public void user_navigates_to_the_Assigned_Cases_queue() throws Throwable {
		basePage.myCases.click();
	}

	@When("^user selects case assigned in the Adjudication state$")
	public void user_selects_case_assigned_in_the_Adjudication_state() throws Throwable {
		String personNameInbox = intakeCreator.getLastName()+", "+intakeCreator.getFirstName();
		browserUtils.waitframeToBeAvailableAndSwitchToIt(basePage.enhancedInboxIframe);
		basePage.enhancedInboxSearch.sendKeys(personNameInbox);
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

	@Then("^the case record of selected applicant displays$")
	public void the_case_record_of_selected_applicant_displays() throws Throwable {
		assertTrue(casePage.trackingForm.isDisplayed());
	}

	@When("^user navigates to the Adjudication CTO$")
	public void user_navigates_to_the_Adjudication_CTO() throws Throwable {
		casePage.caseAdjudicationChildLink.click();
	}

	@When("^user clicks on the already created Adjudication record$")
	public void user_clicks_on_the_already_created_Adjudication_record() throws Throwable {
		browserUtils.waitForElementToBeVisible(adjudicationPage.adjListingTable);
		adjudicationPage.adjListingTable_rowLink.click();
	}

	@Then("^the Adjudication record displays \\(display mapping Adjudication state\\)$")
	public void the_Adjudication_record_displays_display_mapping_Adjudication_state() throws Throwable {
		for (WebElement formFields: adjudicationPage.adjFields_adjState()) {
			assertTrue(formFields.isDisplayed());
		}
	}

	@When("^user clicks Save on Adjudication$")
	public void user_clicks_Save_on_Adjudication() throws Throwable {
		adjudicationPage.saveButton.click();
	}

	@Then("^form does not save due to empty required fields on Adjudication$")
	public void form_does_not_save_due_to_empty_required_fields_on_Adjudication() throws Throwable {
		for (WebElement msgs: adjudicationPage.fieldsRequiredMessages) {
			assertTrue(msgs.isDisplayed());
		}
		assertTrue(adjudicationPage.fieldsRequiredMessages.get(0).getText().equals("Adjudication Recommendation is required."));
		assertTrue(adjudicationPage.fieldsRequiredMessages.get(1).getText().equals("Adjudication Recommendation Date is required."));
		assertTrue(adjudicationPage.fieldsRequiredMessages.get(2).getText().equals("Adjudication Summary is required."));
	}

	@When("^user populates Seriousness Issue Code$")
	public void user_populates_Seriousness_Issue_Code() throws Throwable {
		formFunctionsUtils.randomDropDownValue(adjudicationPage.Adjudication_seriousnessIssueCode);
	}

	@When("^user selects all Adjudicative Guidelines$")
	public void user_selects_all_Adjudicative_Guidelines() throws Throwable {
		adjudicationPage.adjGuidelines_add.click();
		for (WebElement guidelines: adjudicationPage.adjGuidelines()) {
			guidelines.click();
		}
		adjudicationPage.adjGuidelines_done.click();
		for (WebElement adjGuidelines: adjudicationPage.adjGuidelinesSelectedDisplay) {
			assertTrue(adjGuidelines.isDisplayed());
		}
	}

	@When("^user selects all Suitability Guidelines$")
	public void user_selects_all_Suitability_Guidelines() throws Throwable {
		adjudicationPage.suitabilityGuidelines_add.click();
		for (WebElement selectedGuidelines: adjudicationPage.suitabilityGuidelines()) {
			selectedGuidelines.click();
		}
		adjudicationPage.suitabilityGuidelines_done.click();
		for (WebElement selectedGuidelines: adjudicationPage.suitabilityGuidelinesSelectedDisplay) {
			assertTrue(selectedGuidelines.isDisplayed());
		}
	}

	@When("^user populates Adjudication Recommendation$")
	public void user_populates_Adjudication_Recommendation() throws Throwable {
		formFunctionsUtils.randomDropDownValue(adjudicationPage.Adjudication_adjudicationRecommendation);
	}

	@When("^user populates Adjudication Recommendation Date$")
	public void user_populates_Adjudication_Recommendation_Date() throws Throwable {
		adjudicationPage.Adjudication_adjudicationRecommendationDate.sendKeys(formFunctionsUtils.currentDate());
	}

	@When("^user populates Adjudication Summary$")
	public void user_populates_Adjudication_Summary() throws Throwable {
		adjudicationPage.Adjudication_adjudicationSummary.sendKeys("Testing Adjudication Summary ~!@#$%^&*(){}|[]\"'<>?',./]``");
	}

	@When("^user sets Ready for Final Adjudication to No$")
	public void user_sets_Ready_for_Final_Adjudication_to_No() throws Throwable {
		adjudicationPage.Adjudication_readyForFinalAdjudication_no.click();
	}

	@Then("^the Adjudication Recommendation fields remain editable$")
	public void the_Adjudication_Recommendation_fields_remain_editable() throws Throwable {
		if (Driver.getInstance().getCurrentUrl().contains("listChild")) {
			browserUtils.waitForElementToBeVisible(adjudicationPage.adjListingTable);
			adjudicationPage.adjListingTable_rowLink.click();
		}
		
		// FIND A WAY TO VERIFY FIELDS ARE STILL EDITABLE
		
		// pick new values, if new value is the same as old value, pick new value again, check that new value is different
		String selectedSeriousnessIssueCode = formFunctionsUtils.dropDownElement(adjudicationPage.Adjudication_seriousnessIssueCode).getFirstSelectedOption().getText().trim();
		formFunctionsUtils.randomDropDownValue(adjudicationPage.Adjudication_seriousnessIssueCode);
		String updatedSeriousnessIssueCode = formFunctionsUtils.dropDownElement(adjudicationPage.Adjudication_seriousnessIssueCode).getFirstSelectedOption().getText().trim();
		while (selectedSeriousnessIssueCode.equals(updatedSeriousnessIssueCode)) {
			formFunctionsUtils.randomDropDownValue(adjudicationPage.Adjudication_seriousnessIssueCode);
			updatedSeriousnessIssueCode = formFunctionsUtils.dropDownElement(adjudicationPage.Adjudication_seriousnessIssueCode).getFirstSelectedOption().getText().trim();
		}
		assertTrue(!(selectedSeriousnessIssueCode.equals(updatedSeriousnessIssueCode)));
		
		// assert edit is available and displayed
		assertTrue(adjudicationPage.adjGuidelines_edit.isDisplayed());
		assertTrue(adjudicationPage.suitabilityGuidelines_edit.isDisplayed());
		
		// adj recommendation mutability check && asserting that selected and updated values do not equal one another
		String selectedAdjRecommendation = formFunctionsUtils.dropDownElement(adjudicationPage.Adjudication_adjudicationRecommendation).getFirstSelectedOption().getText().trim();
		formFunctionsUtils.randomDropDownValue(adjudicationPage.Adjudication_adjudicationRecommendation);
		String updatedAdjRecommendation = formFunctionsUtils.dropDownElement(adjudicationPage.Adjudication_adjudicationRecommendation).getFirstSelectedOption().getText().trim();
		while (selectedAdjRecommendation.equals(updatedAdjRecommendation)) {
			formFunctionsUtils.randomDropDownValue(adjudicationPage.Adjudication_adjudicationRecommendation);
			updatedAdjRecommendation = formFunctionsUtils.dropDownElement(adjudicationPage.Adjudication_adjudicationRecommendation).getFirstSelectedOption().getText().trim();
		}
		assertTrue(!(selectedAdjRecommendation.equals(updatedAdjRecommendation)));
		
		// adj recommendation date && asserting that selected and updated values do not equal one another
		String selectedAdjDate = adjudicationPage.Adjudication_adjudicationRecommendationDate.getAttribute("value").trim();
		String updatedAdjDate;
		adjudicationPage.Adjudication_adjudicationRecommendationDate.clear();
		adjudicationPage.Adjudication_adjudicationRecommendationDate.sendKeys(formFunctionsUtils.pastDate(10));
		JavascriptExecutor js = ((JavascriptExecutor) Driver.getInstance());
		updatedAdjDate = js.executeScript("return arguments[0].value;",adjudicationPage.Adjudication_adjudicationRecommendationDate).toString();
		assertTrue(!(selectedAdjDate.equals(updatedAdjDate)));
		
		// adj summary mutability check
		String existingAdjSummary = adjudicationPage.Adjudication_adjudicationSummary.getText().trim();
		String updatedTextAdjSummary;
		adjudicationPage.Adjudication_adjudicationSummary.clear();
		adjudicationPage.Adjudication_adjudicationSummary.sendKeys("Testing editability of text field");
		updatedTextAdjSummary = adjudicationPage.Adjudication_adjudicationSummary.getAttribute("value");
		
	}

	@When("^user sets Ready for Final Adjudication to Yes$")
	public void user_sets_Ready_for_Final_Adjudication_to_Yes() throws Throwable {
		adjudicationPage.Adjudication_readyForFinalAdjudication_yes.click();
	}

	@Then("^Select Final Adjudicator displays$")
	public void select_Final_Adjudicator_displays() throws Throwable {
		assertTrue(adjudicationPage.Adjudication_finalAdjudicator.isDisplayed());
	}

	@Then("^form does not save due to empty required field$")
	public void form_does_not_save_due_to_empty_required_field() throws Throwable {
		for (WebElement msgs: adjudicationPage.fieldsRequiredMessages) {
			assertTrue(msgs.isDisplayed());
		}
		assertTrue(adjudicationPage.fieldsRequiredMessages.get(0).getText().equals("Select Final Adjudicator is required."));
	}

	@When("^user sets Select Final Adjudicator to <\"([^\"]*)\">$")
	public void user_sets_Select_Final_Adjudicator_to(String finalAdjudicator) throws Throwable {
		formFunctionsUtils.dropDown(adjudicationPage.Adjudication_finalAdjudicator, finalAdjudicator);
	}

	@Then("^message displays on listing indicating that case has been sent to final adjudication$")
	public void message_displays_on_listing_indicating_that_case_has_been_sent_to_final_adjudication() throws Throwable {
		browserUtils.sleep(1000);
		if (Driver.getInstance().getCurrentUrl().contains("listChild")) {
			for (WebElement msgs: adjudicationPage.adjListingSuccessfulMessages) {
				assertTrue(msgs.isDisplayed());
			}
			assertTrue(adjudicationPage.adjListingSuccessfulMessages.get(0).getText().equals("The Case has been sent for Final Adjudication."));
		}
	}

	@Then("^the Adjudication Recommendation fields become read only$")
	public void the_Adjudication_Recommendation_fields_become_read_only() throws Throwable {
		if (Driver.getInstance().getCurrentUrl().contains("listChild")) {
			browserUtils.waitForElementToBeVisible(adjudicationPage.adjListingTable);
			adjudicationPage.adjListingTable_rowLink.click();
		}
		// FIND WAY TO VERIFY FIELDS ARE READ ONLY
		String readOnlyType = "hidden";
		int numOfEditGuidelineButtons = adjudicationPage.editGuidelinesButtons.size();
		boolean doesReadyForFinalAdjudicationDisplay = adjudicationPage.Adjudication_readyForFinalAdjudication_container.isDisplayed();
		boolean doesSelectFinalAdjudicatorDisplay = adjudicationPage.Adjudication_finalAdjudicator.isDisplayed();
		
		for (WebElement readOnlyFields : adjudicationPage.adjRecommendationReadOnlyFields) {
			assertTrue(readOnlyFields.getAttribute("type").toString().equals(readOnlyType));
		}
		assertTrue(numOfEditGuidelineButtons==0);
		assertTrue(!doesReadyForFinalAdjudicationDisplay);
		assertTrue(!doesSelectFinalAdjudicatorDisplay);
		assertTrue(adjudicationPage.adjGuidelinesSelectedDisplay.size() == 13);
		assertTrue(adjudicationPage.suitabilityGuidelinesSelectedDisplay.size() == 9);
	}

	@When("^user navigates to Case$")
	public void user_navigates_to_Case() throws Throwable {
		basePage.breadcrumb_caseLink.click();
	}

	@Then("^the workflow status of Case is now Final Adjudication$")
	public void the_workflow_status_of_Case_is_now_Final_Adjudication() throws Throwable {
		String actualCaseWorkflowStatus = casePage.caseSubwayWorkflowStatus.getText()
				.substring(casePage.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim();
		String expectedCaseWorkflowStatus = "Final Adjudication";
		assertEquals(expectedCaseWorkflowStatus,actualCaseWorkflowStatus); 
	}
}
