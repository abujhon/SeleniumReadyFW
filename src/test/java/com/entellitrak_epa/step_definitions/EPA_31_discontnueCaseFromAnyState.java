/**
 * 
 */
package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.*;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.entellitrak_epa.pages.BasePage;
import com.entellitrak_epa.pages.CasePage;
import com.entellitrak_epa.pages.IntakeInitiationPage;
import com.entellitrak_epa.pages.PersonPage;
import com.entellitrak_epa.pages.SignOnPage;
import com.entellitrak_epa.utilities.Applicant_Intake_Submit;
import com.entellitrak_epa.utilities.BrowserUtils;
import com.entellitrak_epa.utilities.Driver;
import com.entellitrak_epa.utilities.EPA_constants;
import com.entellitrak_epa.utilities.FormFunctionsUtils;
import com.entellitrak_epa.utilities.IntakeCreator;
import com.entellitrak_epa.utilities.PropertiesReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Musa
 *
 */
public class EPA_31_discontnueCaseFromAnyState {

	SignOnPage signOnPg = new SignOnPage();
	BrowserUtils browserUtils = new BrowserUtils();
	BasePage basePg = new BasePage();
	IntakeInitiationPage intakePg = new IntakeInitiationPage();
	IntakeCreator intakeCreator = new IntakeCreator();
	EPA_constants constant = new EPA_constants();
	Applicant_Intake_Submit appSubmit = new Applicant_Intake_Submit();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	CasePage casePg = new CasePage();
	PersonPage personPg = new PersonPage();
	

	static String appType = "";
	static String appEmail = "";

	@Given("^hr intake initiator logs in$")
	public void hr_intake_initiator_logs_in() throws Throwable {
		Driver.getInstance().get(EPA_constants.URL);
		browserUtils.waitForElementToBeVisible(signOnPg.username);
		assertTrue(signOnPg.username.isDisplayed());

		constant.signIn(PropertiesReader.getProperty("automation_hr"), PropertiesReader.getProperty("password"));
		browserUtils.waitForElementToBeVisible(basePg.startIntake);
		assertTrue(basePg.startIntake.isDisplayed());

	}

	@Given("^user creates an intake for \"([^\"]*)\" applicant with \"([^\"]*)\"$")
	public void user_creates_an_intake_for_applicant_with(String applicantType, String applicantEmail)
			throws Throwable {
		appType = applicantType;
		appEmail = applicantEmail;

		basePg.startIntake.click();
		browserUtils.waitForElementToBeVisible(intakePg.applicantInformationFieldset);
		assertTrue(intakePg.applicantInformationFieldset.isDisplayed());

		intakeCreator.submitIntakeFor(appType, appEmail);
		intakePg.saveButton.click();
		browserUtils.waitForElementToBeVisible(intakePg.newIntakeSubmittedMessage);
		assertTrue(intakePg.newIntakeSubmittedMessage.isDisplayed());
	}

	@Given("^as applicant log in and submit intake form$")
	public void as_applicant_log_in_and_submit_intake_form() throws Throwable {
		appSubmit.getApplicantCredentials(appEmail, "Password1!");
		formUtils.signOut();
		appSubmit.changePasswordAndLogin();
		appSubmit.completeApplicantIntakeForm(appType);
		appSubmit.submitApplicantIntake();
	}

	@Given("^user logs in as supervisor$")
	public void user_logs_in_as_supervisor() throws Throwable {
		constant.signIn(PropertiesReader.getProperty("automation_supervisor"),
				PropertiesReader.getProperty("password"));
		browserUtils.waitForElementToBeVisible(basePg.myCases);
	}
	
	@When("^user navigates to case form through rapid search$")
	public void user_navigates_to_case_form_through_rapid_search() throws Throwable {
		browserUtils.waitForElementToBeClickable(basePg.rapidSearchInput);
	    String formattedSSN = intakeCreator.getSSN();
	    formattedSSN = new StringBuilder(formattedSSN).insert(formattedSSN.length() - 4, "-")
				.insert(formattedSSN.length() - 6, "-").toString();
	    basePg.rapidSearchInput.sendKeys(formattedSSN);
	    basePg.rapidSearchIcon.click();
	    browserUtils.waitForElementToBeVisible(basePg.rapidSearchResultsTable);
	    browserUtils.sleep(2000);
	    for (WebElement SSNs : basePg.rapidSearchSSN) {
			if (SSNs.getText().equals(formattedSSN.substring(7))) {
				basePg.rapidSearchName.get(1).click();
			}
		}
	    browserUtils.sleep(900);
	    browserUtils.switchTabs("Person");
		browserUtils.waitForElementToBeClickable(personPg.caseSummaryCaseLink);
		assertTrue(personPg.caseSummaryCaseLink.isDisplayed());
		personPg.caseSummaryCaseLink.click();
		
	}


	
	@When("^user assigns the case to security speciliast$")
	public void user_assigns_the_case_to_security_speciliast() throws Throwable {
		
		browserUtils.waitForElementToBeVisible(casePg.Case_assignedSpecialist);
		formUtils.partialDropDown(casePg.Case_assignedSpecialist, "Specialist, Automation");
		
		if (!(casePg.Case_eqipRequired_no.isSelected() && casePg.Case_eqipRequired_yes.isSelected())) {
			casePg.Case_eqipRequired_yes.click();
		}
		
		browserUtils.scrollDown();
		assertTrue(casePg.discontinueDialogButton.isDisplayed());
		formUtils.dropDown(casePg.actionToTake, "Assign Specialist");
		casePg.saveAndContinueButton.click();
		

	}

	@Given("^user logs in to system as security specialist$")
	public void user_logs_in_to_system_as_security_specialist() throws Throwable {
		constant.signIn(PropertiesReader.getProperty("automation_specialist"),
				PropertiesReader.getProperty("password"));
		browserUtils.waitForElementToBeVisible(basePg.myCases);
		assertTrue(basePg.myCases.isDisplayed());
	}
	
	

	@Then("^the workflow status is Intake Forms Review$")
	public void the_workflow_status_is_Intake_Forms_Review() throws Throwable {
		browserUtils.sleep(900);
	    browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(casePg.caseSubwayWorkflowStatus.getText().indexOf(":")+2).trim();
	    assertTrue(workflow.equals("Intake Forms Review"));
	}
	
	

	@Then("^discontinue button displays$")
	public void discontinue_button_displays() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.discontinueDialogButton);
		assertTrue(casePg.discontinueDialogButton.isDisplayed());
	}

	@When("^user clicks on Discontnue button$")
	public void user_clicks_on_Discontnue_button() throws Throwable {
		casePg.discontinueDialogButton.click();

	}

	@Then("^a pop up displays saying The case will be permanently closed\\. Do you wish to continue\\?$")
	public void a_pop_up_displays_saying_The_case_will_be_permanently_closed_Do_you_wish_to_continue()
			throws Throwable {
		
		
		browserUtils.waitForElementToBeVisible(casePg.popUpDiscontinueHeader);
		String popupMessage = casePg.popUpContent.getText();
		assertTrue(popupMessage.contains("The case will be permanently closed"));
	}

	@When("^user click No$")
	public void user_click_No() throws Throwable {
		
		casePg.popup_no.click();
	}

	@Then("^case status and workflow status does not change$")
	public void case_status_and_workflow_status_does_not_change() throws Throwable {
		browserUtils.sleep(900);
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayCaseStatus);
		String status = casePg.caseSubwayCaseStatus.getText().substring(13).trim();;
		assertTrue(status.equals("Active - In Progress"));
		
	}

	@When("^user click YES$")
	public void user_click_YES() throws Throwable {
		browserUtils.waitForElementToBeClickable(casePg.popup_yes);
		System.out.println("coming to Yes button");
		casePg.popup_yes.click();
	}

	@Then("^case status becomes inactive and workflow status becomes closed$")
	public void case_status_becomes_inactive_and_workflow_status_becomes_closed() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayCaseStatus);
		String caseStatus = casePg.caseSubwayCaseStatus.getText().substring(13).trim();
	    assertTrue(caseStatus.equals("Inactive"));
	}
	
	
	
	// discontinue from record check
	@Then("^action to take dropdown displays$")
	public void action_to_take_dropdown_displays() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		assertTrue(casePg.actionToTake.isDisplayed());
	}

	@When("^user selects Complete Intake from action to take dropdown$")
	public void user_selects_Complete_Intake_from_action_to_take_dropdown() throws Throwable {
	    formUtils.dropDown(casePg.actionToTake, "Complete Intake");
	}

	@When("^user clicks save and continue button$")
	public void user_clicks_save_and_continue_button() throws Throwable {
		casePg.saveAndContinueButton.click();
	}

	@Then("^workflow status becomes Record Check$")
	public void workflow_status_becomes_Record_Check() throws Throwable {
	    browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
	    assertTrue(workflow.equals("Record Checks"));
	}
	
	
	// discontinue from final adjudication 
	
	@When("^user selects Send to Pre-Screening Review Queue from action to take$")
	public void user_selects_Send_to_Pre_Screening_Review_Queue_from_action_to_take() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Send to Pre-Screening Queue");
	}

	@Then("^workflow status becomes Pre-Screening Review Queue$")
	public void workflow_status_becomes_Pre_Screening_Review_Queue() throws Throwable {
	   browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
	   assertTrue(workflow.equals("Pre-Screening Queue"));
	}

	@When("^user slects Send for Pre-Screening Review$")
	public void user_slects_Send_for_Pre_Screening_Review() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Send for Pre-Screening Review");
	}

	@Then("^workflow status becomes Pre-Screening Review$")
	public void workflow_status_becomes_Pre_Screening_Review() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("Pre-Screening Review"));
	}

	@When("^user selects Unfavorable from Action to Take dropdown$")
	public void user_selects_Unfavorable_from_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Unfavorable");
	}

	@Then("^workflow status becomes Pre-Screening Decision Review Queue$")
	public void workflow_status_becomes_Pre_Screening_Decision_Review_Queue() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("Pre-Screening Decision Review Queue"));
	}

	@When("^user selects Send to Pre-Screening Decision Review from Action to Take dropdown$")
	public void user_selects_Send_to_Pre_Screening_Decision_Review_from_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Send to Pre-Screening Decision Review");
	}

	@Then("^workflow status becomes Pre-Screening Decision Review$")
	public void workflow_status_becomes_Pre_Screening_Decision_Review() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("Pre-Screening Decision Review"));
	}

	@When("^user selects Overturn Decision from Action to Take dropdown$")
	public void user_selects_Overturn_Decision_from_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Overturn Decision");
	}

	@When("^user selects Favorable - Release EQIP to NBIB from Action to Take dropdown$")
	public void user_selects_Favorable_Release_EQIP_to_NBIB_from_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Favorable - Release EQIP to NBIB");
	}

	@Then("^workflow status becomes NBIB Investigation Processing$")
	public void workflow_status_becomes_NBIB_Investigation_Processing() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("NBIB Investigation Processing"));
	}

	@When("^user selects Receive ROI form Action to Take dropdown$")
	public void user_selects_Receive_ROI_form_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Receive ROI");
	}

	@Then("^workflow status becomes Adjudication Queue$")
	public void workflow_status_becomes_Adjudication_Queue() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("Adjudication Queue"));
	}

	@When("^user selects Assign Adjudicator from Action to Take dropdown$")
	public void user_selects_Assign_Adjudicator_from_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Assign Adjudicator");
	}

	@Then("^workflow status becomes Adjudication$")
	public void workflow_status_becomes_Adjudication() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("Adjudication"));
	}

	@When("^user selects Enter Adjudicator from Action to Take dropdown$")
	public void user_selects_Enter_Adjudicator_from_Action_to_Take_dropdown() throws Throwable {
		browserUtils.scrollDown();
		browserUtils.waitForElementToBeVisible(casePg.actionToTake);
		formUtils.dropDown(casePg.actionToTake, "Enter Adjudication");
	}

	@Then("^workflow status becomes Final Adjudication$")
	public void workflow_status_becomes_Final_Adjudication() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
		   String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		    assertTrue(workflow.equals("Final Adjudication"));
	}
	
	


	public void getNamesOfRecords() {

		List<WebElement> applicantNames = Driver.getInstance().findElements(By.xpath("//table[@id = 'inbox_content_table']/tbody//tr/td[2]"));
		
		int rowCount = 0;
		for (WebElement webElem : applicantNames) {
			System.out.println("inside the loop");
			String tempName = webElem.getText();
			System.out.println(tempName + " this is the tempName !");
			System.out.println(intakeCreator.getFirstName() + " " + intakeCreator.getLastName());
			rowCount++;
			System.out.println(rowCount);
			if (tempName.contains(intakeCreator.getLastName() + ", " + intakeCreator.getFirstName())) {
				System.out.println("inside the if");
				WebElement row = Driver.getInstance().findElement(By.xpath("//table[@id = 'inbox_content_table']/tbody//tr["+ rowCount +"]/td[1]"));
				row.click();
				break;
	}else {
		continue;
	}
	}
		
		// this piece of code was used in a step to get the assignment done
		
//		browserUtils.sleep(1000);
//
//		try {
//			getNamesOfRecords();
//		} catch (Exception e) {
//			browserUtils.scrollToElement(Driver.getInstance().findElement(By.id("inbox_content_table_next")));
//			Driver.getInstance().findElement(By.id("inbox_content_table_next")).click();
//			browserUtils.sleep(1000);
//			getNamesOfRecords();
//		}
		
	}
	
	

}
