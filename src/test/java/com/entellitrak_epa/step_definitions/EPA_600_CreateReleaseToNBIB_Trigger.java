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
import com.entellitrak_epa.pages.BasePage;
import com.entellitrak_epa.pages.CasePage;
import com.entellitrak_epa.pages.IntakeInitiationPage;
import com.entellitrak_epa.pages.MyCasesPage;
import com.entellitrak_epa.pages.PersonPage;
import com.entellitrak_epa.pages.PreScreeningPage;
import com.entellitrak_epa.pages.SignOnPage;
import com.entellitrak_epa.utilities.Applicant_Intake_Submit;
import com.entellitrak_epa.utilities.BrowserUtils;
import com.entellitrak_epa.utilities.Driver;
import com.entellitrak_epa.utilities.EPA_constants;
import com.entellitrak_epa.utilities.FormFunctionsUtils;
import com.entellitrak_epa.utilities.IntakeCreator;
import com.entellitrak_epa.utilities.PropertiesReader;
import com.google.common.base.Preconditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Musa
 *
 */
public class EPA_600_CreateReleaseToNBIB_Trigger {
	
	
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
	    WebElement myCaseFrame = Driver.getInstance().findElement(By.id("myCasesIframe"));
	    browserUtils.waitForElementToBeVisible(myCaseFrame);
	    assertTrue(myCaseFrame.isDisplayed());
	    
	    Driver.getInstance().switchTo().frame(mycasepg.eiFrame);
	    browserUtils.sleep(500);
	    assertTrue(mycasepg.enhancedInbox.isDisplayed());
	    
	    formUtils.partialDropDown(mycasepg.inboxes, "Unassigned Cases");
	    browserUtils.sleep(1000);
	    
	    List<WebElement> tableContent = Driver.getInstance().findElements(By.xpath("(//table[@id='inbox_content_table']/tbody/tr/td[2])"));
	    browserUtils.sleep(900);
	    int count = 0;
	    System.out.println(" my name --> "+intakeCreator.getLastName()+", "+intakeCreator.getFirstName());
	    System.out.println("SSN is --> "+ intakeCreator.getSSN());
	    for (WebElement webElement : tableContent) {
	    	++count;
	    	
	    	System.out.println(" in the loop "+ count);
			String names = webElement.getText();
			System.out.println("Names in unassigned queue --> " +names);
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				
				System.out.println("in the unassigned if");
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
	    browserUtils.sleep(900);
	    for (WebElement webElement : tableContent) {
	    	System.out.println(" in the loop ");
			String names = webElement.getText();
			System.out.println("Names in assigned queue --> " + names);
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				System.out.println("in the assigned if");
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

}
