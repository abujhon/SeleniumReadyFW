/**
 * 
 */
package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.*;

import org.openqa.selenium.By;

import com.project.pages.BasePage;
import com.project.pages.IntakeInitiationPage;
import com.project.pages.SignOnPage;
import com.project.utilities.BrowserUtils;
import com.project.utilities.Driver;
import com.project.utilities.FormFunctionsUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Musa
 *
 */
public class EPA_306_VisitorModification {
	
	IntakeInitiationPage intakePg = new IntakeInitiationPage();
	SignOnPage signOnPg = new SignOnPage();
	BrowserUtils browserUtils = new BrowserUtils();
	BasePage basePg = new BasePage();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	
	
	
	@When("^user sets the Applicant type to VAR$")
	public void user_sets_the_Applicant_type_to_VAR() throws Throwable {
	   formUtils.dropDown(intakePg.IntakeIni_ApplicantType_Dropdown, "VAR");
	}

	@Then("^SSN field is not required$")
	public void ssn_field_is_not_required() throws Throwable {
		browserUtils.scrollToElement(intakePg.IntakeInitiation_socialSecurityNumber);
		boolean ssnIcon ;
		if (intakePg.IntakeInitiation_socialSecurityNumber.getText().contains("true")) {
			ssnIcon = true;
		}else {
			ssnIcon = false;
		}
	    assertFalse(ssnIcon);
	}

	@When("^user selects Other from Visitor agency dropdown$")
	public void user_selects_Other_from_Visitor_agency_dropdown() throws Throwable {
	    formUtils.dropDown(intakePg.IntakeInitiation_visitorsAgency, "Other");
	    
	}

	@Then("^Other Agency text field displays and is required$")
	public void other_Agency_text_field_displays_and_is_required() throws Throwable {
		browserUtils.waitForElementToBeVisible(intakePg.IntakeInitiation_otherAgency);
	    assertTrue(intakePg.IntakeInitiation_otherAgency.isDisplayed());
	    assertTrue(intakePg.IntakeInitiation_otherAgencyRequiredIcon.isDisplayed());
	}

}
