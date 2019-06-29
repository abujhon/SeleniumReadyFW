/**
 * 
 */
package com.project.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

/**
 * @author Musa
 *
 */
public class IntakeInitiationPage {
	
	public IntakeInitiationPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	
	/*
	 * Intake From Investigation Information # Start
	 */

	@FindBy(id = "investigation-information-group")
	public WebElement investigationInformatinFieldSet;
	
	@FindBy(xpath = "//span[contains(text(), 'Investigation Information')]")
	public WebElement investigationFieldSet;
	
	public WebElement IntakeInitiation_existingPerson_searchButton;
	
	public WebElement IntakeInitiation_existingPerson_deleteButton;
	
	public WebElement IntakeInitiation_existingPerson_liveSearchInput;
	
	public WebElement IntakeInitiation_existingPerson_undoButton;
	
	public WebElement IntakeInitiation_skipApplicantIntake_yes;
	
	public WebElement IntakeInitiation_skipApplicantIntake_no;
	
	@FindBy(id = "IntakeInitiation_caseType")
	public WebElement IntakeInitiationCasetypeDropdown;
	
	public WebElement IntakeInitiation_reason;

	/*
	 * Intake From Investigation Information # End
	 */
	
	
	/*
	 * Intake From Applicant Information # Start
	 */
	
	@FindBy(id = "applicant-information-group")
	public WebElement applicantInformationFieldset;
	
	@FindBy(id = "IntakeInitiation_applicantType")
	public WebElement IntakeIni_ApplicantType_Dropdown;
	
	public WebElement IntakeInitiation_vip_yes;
	
	public WebElement IntakeInitiation_vip_no;
	
	public WebElement IntakeInitiation_employeeCommonIdentifier;
	
	public WebElement IntakeInitiation_firstName;
	
	public WebElement IntakeInitiation_middleName;
	
	public WebElement IntakeInitiation_lastName;
	
	public WebElement IntakeInitiation_suffix;
	
	public WebElement IntakeInitiation_socialSecurityNumber;
	
	public WebElement IntakeInitiation_socialSecurityNumberRequiredIcon;
	
	public WebElement IntakeInitiation_applicantEmail;
	
	public WebElement IntakeInitiation_personalPhoneNumber;
	
	public WebElement IntakeInitiation_applicantTenureLessThan6Months_yes;
	
	public WebElement IntakeInitiation_applicantTenureLessThan6Months_no;
	
	public WebElement IntakeInitiation_startDate;
	
	public WebElement IntakeInitiation_endDate;
	
	@FindBy(id = "IntakeInitiation_programOfficeAndRegion")
	public WebElement IntakeInitiation_programOfficeAndRegionDropdown;
	
	@FindBy(id = "IntakeInitiation_san")
	public WebElement IntakeInitiationSanDropdown;
	
	@FindBy(id = "IntakeInitiation_dutyLocation")
	public WebElement IntakeInitiationDutyLocationDropdown;
	
	@FindBy(id = "IntakeInitiation_dutyRegion")
	public WebElement IntakeInitiationDutyRegionDropdown;
	
	@FindBy(id = "IntakeInitiation_buildingName")
	public WebElement IntakeInitiationBuildingNameDropdown;
	
	public WebElement IntakeInitiation_federalEmergencyResponseOfficer_yes;
	
	public WebElement IntakeInitiation_federalEmergencyResponseOfficer_no;
	
	public WebElement IntakeInitiation_lawEnforcementOfficial_yes;
	
	public WebElement IntakeInitiation_lawEnforcementOfficial_no;
	
	public WebElement IntakeInitiation_logicalAccessOrEmailAddressRequired_yes;
	
	public WebElement IntakeInitiation_logicalAccessOrEmailAddressRequired_no;
	
	public WebElement IntakeInitiation_requestUsaccessBadge_yes;
	
	public WebElement IntakeInitiation_requestUsaccessBadge_no;
	
	public WebElement IntakeInitiation_of306;
	
	public WebElement IntakeInitiation_resume;
	
	public WebElement IntakeInitiation_other;
	
	// Contractor specific 
	
	public WebElement IntakeInitiation_contractNumber_searchButton;
	
	public WebElement IntakeInitiation_contractNumber_deleteButton;
	
	public WebElement IntakeInitiation_contractNumber_liveSearchInput;
	
	public WebElement IntakeInitiation_contractNumber_undoButton;
	
	@FindBy(css = "#IntakeInitiation_contractNumber_liveSearchResultMainTable a")
	public WebElement singleContractNumberResult;
	
	public WebElement IntakeInitiation_contractName;
	
	public WebElement IntakeInitiation_vendorName;
	
	public WebElement IntakeInitiation_contractExpirationDate;
	
	// foreign national specific
	
	public WebElement IntakeInitiation_pssn;
	
	// Grantee specific 
	
	public WebElement IntakeInitiation_grantNumber;
	
	// var specific 
	
	public WebElement IntakeInitiation_visitorComments;
	
	public WebElement IntakeInitiation_dateVarReceived;
	
	public WebElement IntakeInitiation_birthCountry;
	
	public WebElement IntakeInitiation_birthState;
	
	public WebElement IntakeInitiation_birthCity;
	
	public WebElement IntakeInitiation_visitorsAgency;
	
	public WebElement IntakeInitiation_otherAgency;
	
	public WebElement IntakeInitiation_otherAgencyRequiredIcon;

	public WebElement IntakeInitiation_investigationCompletionDate;
	
	/*
	 * Intake From Applicant Information # End
	 */
	
	
	/*
	 * Intake From HR specialist Information # Start
	 */
	
	@FindBy(xpath = "//span[contains(text(), 'HR Specialist Information')]")
	public WebElement HR_specialistFieldset;
	
	public WebElement IntakeInitiation_hrSpecialistFirstName;
	
	public WebElement IntakeInitiation_hrSpecialistLastName;
	
	public WebElement IntakeInitiation_hrSpecialistEmailAddress;
	
	public WebElement IntakeInitiation_hrSpecialistPhoneNumber;
	
	public WebElement IntakeInitiation_hiringManagerFirstName;
	
	public WebElement IntakeInitiation_hiringManagerLastName;
	
	public WebElement IntakeInitiation_hiringManagerEmailAddress;
	
	public WebElement IntakeInitiation_hiringManagerPhoneNumber;
	
	public WebElement IntakeInitiation_actionType;
	
	public WebElement IntakeInitiation_actionNumber;
	
	/*
	 * Intake From HR specialist Information # End
	 */
	
	
	/*
	 * Intake From POC Information # Start
	 */
	
	@FindBy(xpath = "//span[contains(text(), 'POC Information')]")
	public WebElement POC_InformationFieldset;
	
	public WebElement IntakeInitiation_pocFirstName;
	
	public WebElement IntakeInitiation_pocLastName;
	
	public WebElement IntakeInitiation_pocEmailAddress;
	
	public WebElement IntakeInitiation_pocPhoneNumber;
	
	public WebElement IntakeInitiation_alternatePocFirstName;
	
	public WebElement IntakeInitiation_alternatePocLastName;
	
	public WebElement IntakeInitiation_alternatePocEmailAddress;
	
	public WebElement IntakeInitiation_alternatePocPhoneNumber;
	
	public WebElement IntakeInitiation_coOrGoFirstName;
	
	public WebElement IntakeInitiation_coOrGoLastName;
	
	public WebElement IntakeInitiation_coOrGoEmailAddress;
	
	public WebElement IntakeInitiation_coOrGoPhoneNumber;
	
	public WebElement IntakeInitiation_requestingPocFirstName;

	public WebElement IntakeInitiation_requestingPocLastName;
	
	public WebElement IntakeInitiation_requestingPocEmailAddress;

	public WebElement IntakeInitiation_requestingPocPhoneNumber;
	
	/*
	 * Intake From POC Information # End
	 */
	
	
	
	/*
	 * Intake From position designation # Start
	 */
	
	
	@FindBy(id = "position-designation-group")
	public WebElement positionDesignationFieldset;
	
	public WebElement IntakeInitiation_positionTitle;
	
	public WebElement IntakeInitiation_designationDate;
	
	@FindBy(id = "IntakeInitiation_sensitivity")
	public WebElement IntakeInitiation_sensitivityDropdown;
	
	@FindBy(id = "IntakeInitiation_riskLevel")
	public WebElement IntakeInitiation_riskLevelDropdown;
	
	@FindBy(id = "IntakeInitiation_investigationType")
	public WebElement IntakeInitiation_investigationTypeDropdown;

	public WebElement IntakeInitiation_opmDesignationSummary;
	
	@FindBy(id = "IntakeInitiation_opmDesignationSummary-path")
	public WebElement opmDesignationSummaryFilePath;
	
	/*
	 * Intake From position designation # End
	 */
	
	@FindBy(css = "#loading img")
	public WebElement loading;
	
	public WebElement saveButton;
	
	
	// after intake submission
	
	public WebElement baseTab;
	
	@FindBy(xpath = "//span[contains(text(), 'New Intake form has been submitted.')]")
	public WebElement newIntakeSubmittedMessage;
	
	@FindBy(css = "#messagesList span")
	public List<WebElement> successfulMessages;
	
	@FindBy(linkText = "Case")
	public WebElement intakeCaseLink;
	
	@FindBy(linkText = "Person")
	public WebElement intakePersonLink;
	
	
	

}
