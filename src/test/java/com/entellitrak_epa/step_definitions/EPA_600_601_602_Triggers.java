/**
 * 
 */
package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.*;
import java.util.List;

import javax.swing.text.html.FormSubmitEvent;
import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.google.common.base.Preconditions;
import com.project.pages.BasePage;
import com.project.pages.CasePage;
import com.project.pages.IntakeInitiationPage;
import com.project.pages.MyCasesPage;
import com.project.pages.PersonPage;
import com.project.pages.PreScreeningPage;
import com.project.pages.SignOnPage;
import com.project.utilities.Applicant_Intake_Submit;
import com.project.utilities.BrowserUtils;
import com.project.utilities.Driver;
import com.project.utilities.EPA_constants;
import com.project.utilities.FormFunctionsUtils;
import com.project.utilities.IntakeCreator;
import com.project.utilities.PropertiesReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Musa
 *
 */
public class EPA_600_601_602_Triggers {
	
	
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
	MyCasesPage mycasepg = new MyCasesPage();
	PreScreeningPage prescreeningPg = new PreScreeningPage();
	
	
	@Given("^user logs in as FSEM adjudicator$")
	public void user_logs_in_as_FSEM_adjudicator() throws Throwable {
	    constant.signIn(PropertiesReader.getProperty("automation_fsem"), PropertiesReader.getProperty("password"));
	    browserUtils.waitForElementToBeVisible(basePg.myCases);
	    assertTrue(basePg.myCases.isDisplayed());
	}

	@When("^user navigates to FSEM unassigned queue and assignes the case to FSEM adj$")
	public void user_navigates_to_FSEM_unassigned_queue_and_assignes_the_case_to_FSEM_adj() throws Throwable {
	    basePg.myCases.click();
	    browserUtils.sleep(2000);
	    WebElement myCaseFrame = Driver.getInstance().findElement(By.id("myCasesIframe"));
	    browserUtils.waitForElementToBeVisible(myCaseFrame);
	    assertTrue(myCaseFrame.isDisplayed());
	    
	    Driver.getInstance().switchTo().frame(mycasepg.eiFrame);
	    browserUtils.sleep(1000);
	    assertTrue(mycasepg.enhancedInbox.isDisplayed());
	    
	    formUtils.partialDropDown(mycasepg.inboxes, "Unassigned Cases");
	    browserUtils.sleep(1000);
	    
	    List<WebElement> tableContent = Driver.getInstance().findElements(By.xpath("(//table[@id='inbox_content_table']/tbody/tr/td[2])"));
	    browserUtils.sleep(1000);
	    int count = 0;
	    
	    for (WebElement webElement : tableContent) {
	    	++count;
			String names = webElement.getText();
			
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				Driver.getInstance().findElement(By.xpath("(//input[@name='inbox_content_actionableRow'])["+ count +"]")).click();
				break;
			}
		    
		}
	    
	    browserUtils.sleep(500);
		formUtils.dropDown(mycasepg.inbox_content_actions, "fsem, automation");
		mycasepg.inbox_content_actionSubmit.click();
		
		Driver.getInstance().switchTo().defaultContent();
		browserUtils.sleep(900);
		Driver.getInstance().switchTo().alert().accept();
		browserUtils.sleep(900);
		
	    System.out.println("After alert.....");
	    
	    
	    
	}

	@Then("^the case displays in assigned queue and user navigates to case form$")
	public void the_case_displays_in_assigned_queue_and_user_navigates_to_case_form() throws Throwable {
	    
		Driver.getInstance().switchTo().frame(mycasepg.eiFrame);
	    browserUtils.sleep(500);
	    assertTrue(mycasepg.enhancedInbox.isDisplayed());
	    
	    formUtils.partialDropDown(mycasepg.inboxes, "Assigned Cases");
	    browserUtils.sleep(1000);
	    
	    List<WebElement> tableContent = Driver.getInstance().findElements(By.xpath("(//table[@id='inbox_content_table']/tbody/tr/td[1])"));
	    browserUtils.sleep(1000);
	    for (WebElement webElement : tableContent) {
			String names = webElement.getText();
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				webElement.click();
				break;
			}
		}
	    
	    browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.applicantInformationFieldset.isDisplayed());

		
	}

	@When("^user navigates to prescreening form$")
	public void user_navigates_to_prescreening_form() throws Throwable {
		casePg.pre_screeningCTO.click();
	    browserUtils.sleep(900);
	    Driver.getInstance().findElement(By.xpath("//td[@scope='row']")).click();
	    browserUtils.waitForElementToBeVisible(prescreeningPg.fingerprintSacInformationFieldset);
	    assertTrue(prescreeningPg.fingerprintSacInformationFieldset.isDisplayed());
	    
	}

	@Then("^Release to NBIB trigger button is disabled$")
	public void release_to_NBIB_trigger_button_is_disabled() throws Throwable {
	    browserUtils.scrollDown();
	    assertTrue(!prescreeningPg.releaseToNbibButton.isEnabled());
	}

	@Then("^Unfavorable trigger button is disabled$")
	public void unfavorable_trigger_button_is_disabled() throws Throwable {
		assertTrue(!prescreeningPg.unfavorableButton.isEnabled());
	}

	@Then("^Skip Investigation trigger buttons is disabled$")
	public void skip_Investigation_trigger_buttons_is_disabled() throws Throwable {
		assertTrue(!prescreeningPg.skipInvestigationButton.isEnabled());
	}

	@When("^user selects Favorable from prescreening decision dropdown$")
	public void user_selects_Favorable_from_prescreening_decision_dropdown() throws Throwable {
	    formUtils.dropDown(prescreeningPg.PreScreening_preScreeningDecision, "Favorable");
	}

	@Then("^Release to NBIB button remains inactive$")
	public void release_to_NBIB_button_remains_inactive() throws Throwable {
		browserUtils.sleep(700);
		assertTrue(!prescreeningPg.releaseToNbibButton.isEnabled());
	}

	@When("^user completes the eQIP section on case form$")
	public void user_completes_the_eQIP_section_on_case_form() throws Throwable {
	    Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
	    browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.eqipInformationFieldset.isDisplayed());
	    
	    casePg.Case_eqipRequired_yes.click();
	    casePg.Case_eqipInitiationDate.sendKeys(formUtils.pastDate(15));
	    formUtils.dropDown(casePg.Case_sfForm, "SF 85");
	    casePg.Case_eqipSubmissionDueDate.sendKeys(formUtils.pastDate(2));
	    casePg.Case_eqipCompleteDate.sendKeys(formUtils.pastDate(5));
	    
	    casePg.saveButton.click();
	    
	    browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.applicantInformationFieldset.isDisplayed());
	    
	}

	@Then("^Release to NBIB button becomes active$")
	public void release_to_NBIB_button_becomes_active() throws Throwable {
		casePg.pre_screeningCTO.click();
		browserUtils.sleep(900);
	    Driver.getInstance().findElement(By.xpath("//td[@scope='row']")).click();
	    browserUtils.waitForElementToBeVisible(prescreeningPg.fingerprintSacInformationFieldset);
	    assertTrue(prescreeningPg.fingerprintSacInformationFieldset.isDisplayed());
	    
	    
	    formUtils.dropDown(prescreeningPg.PreScreening_preScreeningDecision, "Favorable");
	    browserUtils.sleep(900);
	    assertTrue(prescreeningPg.releaseToNbibButton.isEnabled());
	    browserUtils.sleep(900);
	}

	@When("^user clicks the Release to NBIB button$")
	public void user_clicks_the_Release_to_NBIB_button() throws Throwable {
		// fill out the pre-screening form
		
		prescreeningPg.PreScreening_sacReceivedDate.sendKeys(formUtils.pastDate(12));
		formUtils.dropDown(prescreeningPg.PreScreening_sacResults, "Favorable");
		
		formUtils.dropDown(prescreeningPg.PreScreening_sacAdjudication, "Unclassifiable");
		prescreeningPg.PreScreening_sacAdjudicationDate.sendKeys(formUtils.pastDate(30));
		
		prescreeningPg.PreScreening_preScreeningDecisionDate.sendKeys(formUtils.currentDate());
		prescreeningPg.PreScreening_interimAdjudicativeComments.sendKeys("This is FSEM comments....");
		prescreeningPg.PreScreening_convictedFelonOrStandAlone_no.click();
		
	    prescreeningPg.releaseToNbibButton.click();
	    browserUtils.waitForElementToBeVisible(casePg.pre_screeningCTO);
	    assertTrue(casePg.pre_screeningCTO.isDisplayed());
	}

	@Then("^case workflow becomes NBIB Investigation$")
	public void case_workflow_becomes_NBIB_Investigation() throws Throwable {
		Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
		browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	    
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("NBIB Investigation Processing"));
	}
	
	
	//  EPA-601 
	
	@When("^user selects Yes to Skip investigation$")
	public void user_selects_Yes_to_Skip_investigation() throws Throwable {
		
	    prescreeningPg.PreScreening_skipInvestigation_yes.click();
	    browserUtils.sleep(500);
	}

	@Then("^Skip Investigation trigger buttons becomes active$")
	public void skip_Investigation_trigger_buttons_becomes_active() throws Throwable {
	    assertTrue(prescreeningPg.skipInvestigationButton.isEnabled());
	    browserUtils.sleep(900);
	}

	@When("^user clicks the Skip Investigation button$")
	public void user_clicks_the_Skip_Investigation_button() throws Throwable {
		
		prescreeningPg.PreScreening_sacReceivedDate.sendKeys(formUtils.pastDate(12));
		formUtils.dropDown(prescreeningPg.PreScreening_sacResults, "Favorable");
		
		formUtils.dropDown(prescreeningPg.PreScreening_sacAdjudication, "Unclassifiable");
		prescreeningPg.PreScreening_sacAdjudicationDate.sendKeys(formUtils.pastDate(30));
		
		prescreeningPg.PreScreening_preScreeningDecisionDate.sendKeys(formUtils.currentDate());
		prescreeningPg.PreScreening_interimAdjudicativeComments.sendKeys("This is FSEM comments....");
		prescreeningPg.PreScreening_convictedFelonOrStandAlone_no.click();
		
	    prescreeningPg.skipInvestigationButton.click();
	    browserUtils.waitForElementToBeVisible(casePg.pre_screeningCTO);
	    assertTrue(casePg.pre_screeningCTO.isDisplayed());
	    
	}

	@Then("^case workflow becomes Adjudication Queue$")
	public void case_workflow_becomes_Adjudication_Queue() throws Throwable {
		Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
		browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	    
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("Adjudication Queue"));
	}
	
	
	// EPA-602
	
	@When("^user selects Unfavorable from prescreening decision dropdown$")
	public void user_selects_Unfavorable_from_prescreening_decision_dropdown() throws Throwable {
	    formUtils.dropDown(prescreeningPg.PreScreening_preScreeningDecision, "Unfavorable");
	}
	
	@Then("^Unfavorable trigger button is enabled$")
	public void unfavorable_trigger_button_is_enabled() throws Throwable {
	    browserUtils.sleep(900);
	    assertTrue(prescreeningPg.unfavorableButton.isEnabled());
		
	}

	@When("^user clicks the Unfavorable trigger button$")
	public void user_clicks_the_Unfavorable_trigger_button() throws Throwable {
		
		prescreeningPg.PreScreening_sacReceivedDate.sendKeys(formUtils.pastDate(12));
		formUtils.dropDown(prescreeningPg.PreScreening_sacResults, "Unfavorable");
		formUtils.dropDown(prescreeningPg.PreScreening_sacAdjudication, "Unclassifiable");
		prescreeningPg.PreScreening_sacAdjudicationDate.sendKeys(formUtils.pastDate(30));
		
		prescreeningPg.PreScreening_preScreeningDecisionDate.sendKeys(formUtils.currentDate());
		prescreeningPg.PreScreening_interimAdjudicativeComments.sendKeys("This is FSEM comments....");
		prescreeningPg.PreScreening_convictedFelonOrStandAlone_no.click();
		
	    prescreeningPg.unfavorableButton.click();
	    browserUtils.waitForElementToBeVisible(casePg.pre_screeningCTO);
	    assertTrue(casePg.pre_screeningCTO.isDisplayed());
	    
	}

	@Then("^case workflow becomes PreScreening Decision Review Queue state$")
	public void case_workflow_becomes_PreScreening_Decision_Review_Queue_state() throws Throwable {
	    
		Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
		browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	    
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("Pre-Screening Decision Review Queue"));
	}

}
