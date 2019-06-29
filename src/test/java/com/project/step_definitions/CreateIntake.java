/**
 * 
 */
package com.project.step_definitions;

import static org.junit.Assert.*;

import com.project.pages.BasePage;
import com.project.pages.IntakeInitiationPage;
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
public class CreateIntake {
	
	SignOnPage signOnPg = new SignOnPage();
	BrowserUtils browserUtils = new BrowserUtils();
	BasePage basePg = new BasePage();
	IntakeInitiationPage intakePg = new IntakeInitiationPage();
	IntakeCreator intakeCreator = new IntakeCreator();
	EPA_constants constant = new EPA_constants();
	Applicant_Intake_Submit appSubmit = new Applicant_Intake_Submit();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	
	
	static String appType = "";
	static String appEmail = "";
	
	@Given("^user is on the EPA login screen$")
	public void user_is_on_the_EPA_login_screen() throws Throwable {
	    Driver.getInstance().get(EPA_constants.URL);
	    browserUtils.waitForElementToBeVisible(signOnPg.username);
	    assertTrue(signOnPg.username.isDisplayed());
	}

	@Given("^user signs in as intake creator$")
	public void user_signs_in_as_intake_creator() throws Throwable {
		constant.signIn(PropertiesReader.getProperty("automation_hr"), PropertiesReader.getProperty("password"));
	    browserUtils.waitForElementToBeVisible(basePg.startIntake);
	    assertTrue(basePg.startIntake.isDisplayed());
	}

	@Given("^navigates to intake initiation form$")
	public void navigates_to_intake_initiation_form() throws Throwable {
	    basePg.startIntake.click();
	    browserUtils.waitForElementToBeVisible(intakePg.applicantInformationFieldset);
	    assertTrue(intakePg.applicantInformationFieldset.isDisplayed());
	}

	@Given("^completes intake for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void completes_intake_for_and(String type, String email) throws Throwable {
		appType = type;
		appEmail = email;
	    intakeCreator.submitIntakeFor(appType, appEmail);
	}

	@When("^initiator click save$")
	public void initiator_click_save() throws Throwable {
	    intakePg.saveButton.click();
	    browserUtils.waitForElementToBeVisible(intakePg.newIntakeSubmittedMessage);
	}

	@Then("^intake succes message displays$")
	public void intake_succes_message_displays() throws Throwable {
	    assertTrue(intakePg.newIntakeSubmittedMessage.isDisplayed());
	}
	
	@Then("^user logs out from EPA$")
	public void user_logs_out_from_EPA() throws Throwable {
	    basePg.signOut.click();
	    browserUtils.waitForElementToBeVisible(basePg.loginLink);
	    basePg.loginLink.click();
	    browserUtils.waitForElementToBeVisible(signOnPg.username);
	    assertTrue(signOnPg.username.isDisplayed());
	}
	
	
	
	/*
	 * applicant intake completion
	 */
	
	@Given("^applicant gets the temporary user credentials$")
	public void applicant_gets_the_temporary_user_credentials() throws Throwable {
	    appSubmit.getApplicantCredentials(appEmail, "Password1!");
	}

	@Given("^applicant resets the password$")
	public void applicant_resets_the_password() throws Throwable {
		formUtils.signOut();
	    appSubmit.changePasswordAndLogin();
	}

	@When("^applicant completes the applicant intake form$")
	public void applicant_completes_the_applicant_intake_form() throws Throwable {
	    appSubmit.completeApplicantIntakeForm(appType);
	}

	@Then("^applicant be able to submit$")
	public void applicant_be_able_to_submit() throws Throwable {
	    appSubmit.submitApplicantIntake();
	}

}
