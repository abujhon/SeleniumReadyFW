/**
 * 
 */
package com.project.step_definitions;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.project.pages.AdjudicationPage;
import com.project.pages.BasePage;
import com.project.pages.CasePage;
import com.project.pages.MyCasesPage;
import com.project.pages.PersonPage;
import com.project.pages.PreScreeningPage;
import com.project.utilities.BrowserUtils;
import com.project.utilities.Driver;
import com.project.utilities.Project_constants;
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
public class EPA_606_608_triggers {
	
	
	
	Project_constants constant = new Project_constants();
	BrowserUtils browserUtils = new BrowserUtils();
	BasePage basePg = new BasePage();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	CasePage casePg = new CasePage();
	PersonPage personPg = new PersonPage();
	PreScreeningPage prescreeningPg = new PreScreeningPage();
	AdjudicationPage adjudicationPg = new AdjudicationPage();
	MyCasesPage mycasepg = new MyCasesPage();
	IntakeCreator intakeCreator = new IntakeCreator();
	
	
	
	@Then("^user selects Send to Assign Adjudicator from action to take$")
	public void user_selects_Send_to_Assign_Adjudicator_from_action_to_take() throws Throwable {
		formUtils.dropDown(casePg.actionToTake, "Assign Adjudicator");
	}

	@Then("^case workflow becomes Adjudication$")
	public void case_workflow_becomes_Adjudication() throws Throwable {
		browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
		assertTrue(workflow.equals("Adjudication"));
	}

	@When("^user navigates to Adjudication form$")
	public void user_navigates_to_Adjudication_form() throws Throwable {
		casePg.adjudicationCTO.click();
		browserUtils.sleep(900);
	    Driver.getInstance().findElement(By.xpath("//td[@scope='row']")).click();
	    
		
	}

	@Then("^Adjudication recommendation dropdown displays$")
	public void adjudication_recommendation_dropdown_displays() throws Throwable {
		browserUtils.waitForElementToBeVisible(adjudicationPg.Adjudication_adjudicationRecommendation);
		assertTrue(adjudicationPg.Adjudication_adjudicationRecommendation.isDisplayed());
	}

	@When("^user selects a option from Adjudication recommendation dropdown$")
	public void user_selects_a_option_from_Adjudication_recommendation_dropdown() throws Throwable {
	    formUtils.randomDropDownValue(adjudicationPg.Adjudication_adjudicationRecommendation);
	    
	    adjudicationPg.Adjudication_adjudicationRecommendationDate.sendKeys(formUtils.currentDate());
	    adjudicationPg.Adjudication_adjudicationSummary.sendKeys("This is adjudication summary...");
	}

	@When("^user clicks Yes to Ready for Final Adjudication YES/NO$")
	public void user_clicks_Yes_to_Ready_for_Final_Adjudication_YES_NO() throws Throwable {
	   adjudicationPg.Adjudication_readyForFinalAdjudication_yes.click();
	}

	@Then("^assign adjudication dropdown displays$")
	public void assign_adjudication_dropdown_displays() throws Throwable {
	    browserUtils.waitForElementToBeVisible(adjudicationPg.Adjudication_finalAdjudicator);
	    assertTrue(adjudicationPg.Adjudication_finalAdjudicator.isDisplayed());
	    
	}

	@When("^user selects a final adjudicator$")
	public void user_selects_a_final_adjudicator() throws Throwable {
		formUtils.dropDown(adjudicationPg.Adjudication_finalAdjudicator, "Adjudicator II, Automation");
	}

	@Then("^Send to Final Adjudication button becomes enabled$")
	public void send_to_Final_Adjudication_button_becomes_enabled() throws Throwable {
	    browserUtils.sleep(900);
	    assertTrue(adjudicationPg.sendToFinalAdjudicationButton.isEnabled());
	}

	@When("^user clicks on Send to Final Adjudication button$")
	public void user_clicks_on_Send_to_Final_Adjudication_button() throws Throwable {
	    adjudicationPg.sendToFinalAdjudicationButton.click();
	    browserUtils.sleep(900);
	}

	@Then("^case workflow becomes Final Adjudication$")
	public void case_workflow_becomes_Final_Adjudication() throws Throwable {
		Driver.getInstance().findElement(By.xpath("//span[contains(text(), 'CASE')]")).click();
		browserUtils.waitForElementToBeVisible(casePg.applicantInformationFieldset);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	    
	    String workflow = casePg.caseSubwayWorkflowStatus.getText().substring(16).trim();
	    System.out.println("this is my workflow message -->" + workflow);
		assertTrue(workflow.equals("Final Adjudication"));
	}

	@Then("^case assigns to Final adjudicator$")
	public void case_assigns_to_Final_adjudicator() throws Throwable {
	    constant.signIn(PropertiesReader.getProperty("automation_adj2"), PropertiesReader.getProperty("password"));
	    browserUtils.waitForElementToBeVisible(basePg.myCases);
	    assertTrue(basePg.myCases.isDisplayed());
	    
	    basePg.myCases.click();
	    browserUtils.sleep(2000);
	    WebElement myCaseFrame = Driver.getInstance().findElement(By.id("myCasesIframe"));
	    browserUtils.waitForElementToBeVisible(myCaseFrame);
	    assertTrue(myCaseFrame.isDisplayed());
	    
	    Driver.getInstance().switchTo().frame(mycasepg.eiFrame);
	    browserUtils.sleep(1000);
	    assertTrue(mycasepg.enhancedInbox.isDisplayed());
	    
	    mycasepg.lastSaveDateSorting.click();
	    browserUtils.sleep(900);
	    mycasepg.lastSaveDateSorting.click();
	    browserUtils.sleep(900);
	    
	    List<WebElement> tableContent = Driver.getInstance().findElements(By.xpath("(//table[@id='inbox_content_table']/tbody/tr/td[1])"));
	    browserUtils.sleep(1000);
	    boolean flag = false;
	    for (WebElement webElement : tableContent) {
			String names = webElement.getText();
			System.out.println(" My name is --> " + names);
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				flag = true;
				break;
			}
		}
	    
	    assertTrue(flag);
	    
	    
	    Driver.getInstance().switchTo().defaultContent();
	    browserUtils.sleep(1000);
	}
	
	
	
	// epa-608
	
	
	@Given("^user logs in as EPA Final Adjudicator$")
	public void user_logs_in_as_EPA_Final_Adjudicator() throws Throwable {
		constant.signIn(PropertiesReader.getProperty("automation_adj2"), PropertiesReader.getProperty("password"));
	    browserUtils.waitForElementToBeVisible(basePg.myCases);
	    assertTrue(basePg.myCases.isDisplayed());
	}

	@When("^user navigates to my cases assigned queue$")
	public void user_navigates_to_my_cases_assigned_queue() throws Throwable {
		basePg.myCases.click();
	    browserUtils.sleep(2000);
	    WebElement myCaseFrame = Driver.getInstance().findElement(By.id("myCasesIframe"));
	    browserUtils.waitForElementToBeVisible(myCaseFrame);
	    assertTrue(myCaseFrame.isDisplayed());
	}

	@Then("^the record displays in his queue and user navigates to case form$")
	public void the_record_displays_in_his_queue_and_user_navigates_to_case_form() throws Throwable {
		Driver.getInstance().switchTo().frame(mycasepg.eiFrame);
	    browserUtils.sleep(1000);
	    assertTrue(mycasepg.enhancedInbox.isDisplayed());
	    
	    List<WebElement> tableContent = Driver.getInstance().findElements(By.xpath("(//table[@id='inbox_content_table']/tbody/tr/td[1])"));
	    browserUtils.sleep(1000);
	    
	    mycasepg.lastSaveDateSorting.click();
	    browserUtils.sleep(900);
	    mycasepg.lastSaveDateSorting.click();
	    browserUtils.sleep(900);
	    
	    for (WebElement webElement : tableContent) {
			String names = webElement.getText();
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				webElement.click();
				break;
			}
		}
	    
	    browserUtils.waitForElementToBeVisible(casePg.caseSubwayWorkflowStatus);
	    assertTrue(casePg.caseSubwayWorkflowStatus.isDisplayed());
	}

	@Then("^final adjudication section displays$")
	public void final_adjudication_section_displays() throws Throwable {
	   browserUtils.waitForElementToBeVisible(adjudicationPg.Adjudication_returnToAdjudicator_yes);
	   assertTrue(adjudicationPg.Adjudication_returnToAdjudicator_yes.isDisplayed());
	}

	@Then("^Request Correction trigger button is disabled$")
	public void request_Correction_trigger_button_is_disabled() throws Throwable {
	    assertTrue(!adjudicationPg.requestCorrectionButton.isEnabled());
	    browserUtils.sleep(900);
	}

	@When("^user sets Return to Adjudicator to YES$")
	public void user_sets_Return_to_Adjudicator_to_YES() throws Throwable {
	    adjudicationPg.Adjudication_returnToAdjudicator_yes.click();
	    browserUtils.waitForElementToBeVisible(adjudicationPg.Adjudication_reasonForReturn);
	    assertTrue(adjudicationPg.Adjudication_reasonForReturn.isDisplayed());
	}

	@When("^enters comment to Reason$")
	public void enters_comment_to_Reason() throws Throwable {
	    adjudicationPg.Adjudication_reasonForReturn.sendKeys("this is my comment...");
	}

	@Then("^Request Correction trigger button is enabled$")
	public void request_Correction_trigger_button_is_enabled() throws Throwable {
		assertTrue(adjudicationPg.requestCorrectionButton.isEnabled());
		browserUtils.sleep(900);
	}

	@When("^user clicks on Request correction trigger button$")
	public void user_clicks_on_Request_correction_trigger_button() throws Throwable {
	   adjudicationPg.requestCorrectionButton.click();
	   browserUtils.waitForElementToBeVisible(casePg.adjudicationCTO);
	   assertTrue(casePg.adjudicationCTO.isDisplayed());
	}

	@Then("^the case assigns back to original adjudicator$")
	public void the_case_assigns_back_to_original_adjudicator() throws Throwable {
		constant.signIn(PropertiesReader.getProperty("automation_adj2"), PropertiesReader.getProperty("password"));
	    browserUtils.waitForElementToBeVisible(basePg.myCases);
	    assertTrue(basePg.myCases.isDisplayed());
	    
	    basePg.myCases.click();
	    browserUtils.sleep(2000);
	    WebElement myCaseFrame = Driver.getInstance().findElement(By.id("myCasesIframe"));
	    browserUtils.waitForElementToBeVisible(myCaseFrame);
	    assertTrue(myCaseFrame.isDisplayed());
	    
	    Driver.getInstance().switchTo().frame(mycasepg.eiFrame);
	    browserUtils.sleep(1000);
	    assertTrue(mycasepg.enhancedInbox.isDisplayed());
	    
	    mycasepg.lastSaveDateSorting.click();
	    browserUtils.sleep(900);
	    mycasepg.lastSaveDateSorting.click();
	    browserUtils.sleep(900);
	    
	    List<WebElement> tableContent = Driver.getInstance().findElements(By.xpath("(//table[@id='inbox_content_table']/tbody/tr/td[1])"));
	    browserUtils.sleep(1000);
	    boolean flag = false;
	    for (WebElement webElement : tableContent) {
			String names = webElement.getText();
			System.out.println(" My name is --> " + names);
			if (names.equals(intakeCreator.getLastName()+", "+intakeCreator.getFirstName())) {
				flag = true;
				break;
			}
		}
	    
	    assertTrue(flag);
	    
	    
	    Driver.getInstance().switchTo().defaultContent();
	    browserUtils.sleep(1000);
		
		
		
		
		
	}
	
	
	

}
