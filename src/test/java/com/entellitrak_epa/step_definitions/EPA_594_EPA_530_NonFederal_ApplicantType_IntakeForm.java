/**
 * 
 */
package com.entellitrak_epa.step_definitions;

import static org.junit.Assert.*;

import org.openqa.selenium.By;

import com.entellitrak_epa.pages.BasePage;
import com.entellitrak_epa.pages.IntakeInitiationPage;
import com.entellitrak_epa.pages.SignOnPage;
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
public class EPA_594_EPA_530_NonFederal_ApplicantType_IntakeForm {
	
	
	IntakeInitiationPage intakePg = new IntakeInitiationPage();
	SignOnPage signOnPg = new SignOnPage();
	BrowserUtils browserUtils = new BrowserUtils();
	BasePage basePg = new BasePage();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	EPA_constants constant = new EPA_constants();
	
	String appType = "";
	
	@Given("^user logs in the system as COR-Intake initiator$")
	public void user_logs_in_the_system_as_COR_Intake_initiator() throws Throwable {
		 Driver.getInstance().get(EPA_constants.URL);
		    browserUtils.waitForElementToBeVisible(signOnPg.username);
		    assertTrue(signOnPg.username.isDisplayed());
		    
		    constant.signIn(PropertiesReader.getProperty("automation_cor"), PropertiesReader.getProperty("password"));
		    browserUtils.waitForElementToBeVisible(basePg.startIntake);
		    assertTrue(basePg.startIntake.isDisplayed());
	}

	@Given("^user navigates to intake initiation form$")
	public void user_navigates_to_intake_initiation_form() throws Throwable {
	    basePg.startIntake.click();
	    browserUtils.waitForElementToBeVisible(intakePg.applicantInformationFieldset);
	    assertTrue(intakePg.applicantInformationFieldset.isDisplayed());
	}

	@When("^user selects \"([^\"]*)\" from applicant type dropdown$")
	public void user_selects_from_applicant_type_dropdown(String applicantType) throws Throwable {
		appType = applicantType;
	    formUtils.dropDown(intakePg.IntakeIni_ApplicantType_Dropdown, appType);
	}

	@Then("^all the fields for non-federal displays$")
	public void all_the_fields_for_non_federal_displays() throws Throwable {
	    browserUtils.waitForElementToBeVisible(intakePg.IntakeInitiation_vip_no);
		assertTrue(intakePg.IntakeInitiation_vip_no.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_firstName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_lastName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_middleName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_suffix.isDisplayed());
		if (appType.equals("Foreign National")) {
			assertTrue(intakePg.IntakeInitiation_pssn.isDisplayed());
		} else {
			assertTrue(intakePg.IntakeInitiation_socialSecurityNumber.isDisplayed());
		}
		
		assertTrue(intakePg.IntakeInitiation_applicantEmail.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_contractNumber_searchButton.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_contractName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_vendorName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_contractExpirationDate.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_applicantTenureLessThan6Months_no.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_endDate.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_programOfficeAndRegionDropdown.isDisplayed());
		assertTrue(intakePg.IntakeInitiationSanDropdown.isDisplayed());
		assertTrue(intakePg.IntakeInitiationDutyLocationDropdown.isDisplayed());
		assertTrue(intakePg.IntakeInitiationBuildingNameDropdown.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_logicalAccessOrEmailAddressRequired_no.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_requestUsaccessBadge_no.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_pocFirstName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_pocEmailAddress.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_pocPhoneNumber.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_alternatePocFirstName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_alternatePocEmailAddress.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_alternatePocPhoneNumber.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_coOrGoFirstName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_coOrGoLastName.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_coOrGoEmailAddress.isDisplayed());
		assertTrue(intakePg.IntakeInitiation_coOrGoPhoneNumber.isDisplayed());
		
	}

	@When("^user searchs and selects \"([^\"]*)\"$")
	public void user_searchs_and_selects(String contractNum) throws Throwable {
		intakePg.IntakeInitiation_contractNumber_searchButton.click();
		browserUtils.waitForElementToBeVisible(intakePg.IntakeInitiation_contractNumber_liveSearchInput);
		intakePg.IntakeInitiation_contractNumber_liveSearchInput.sendKeys(contractNum);
		browserUtils.sleep(1500);
		browserUtils.waitForElementToBeClickable(Driver.getInstance().findElement(By.xpath("//a[contains(text(),'77777')]")));
		Driver.getInstance().findElement(By.xpath("//a[contains(text(),'77777')]")).click();
		browserUtils.sleep(900);
		
	}

	@Then("^contract number vendor name contract expiration date populates with all POC information fields$")
	public void contract_number_vendor_name_contract_expiration_date_populates_with_all_POC_information_fields() throws Throwable {
	    assertTrue(!intakePg.IntakeInitiation_contractName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_vendorName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_contractExpirationDate.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_pocFirstName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_pocLastName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_pocEmailAddress.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_pocPhoneNumber.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_alternatePocFirstName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_alternatePocLastName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_alternatePocEmailAddress.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_alternatePocPhoneNumber.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_coOrGoFirstName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_coOrGoLastName.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_coOrGoEmailAddress.getAttribute("value").isEmpty());
	    assertTrue(!intakePg.IntakeInitiation_coOrGoPhoneNumber.getAttribute("value").isEmpty());
	}


}
