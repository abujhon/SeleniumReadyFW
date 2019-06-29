package com.entellitrak_epa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

/**
 * @author Edgar Servellon
 */
public class PersonPage {

	public PersonPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	/*
	 * All elements on Person
	 */
	
	/*
	 * Personal Information
	 */
	
	public WebElement Person_firstName;

	public WebElement Person_middleName;
	
	public WebElement Person_lastName;
	
	public WebElement Person_suffix;
	
	public WebElement Person_personalEmailAddress;
	
	public WebElement Person_epaEmailAddress;
	
	public WebElement Person_phoneNumber;
	
	public WebElement Person_dateOfBirth;
	
	public WebElement Person_employeeCommonIdentifier;
	
	@FindBy(id = "Person_photo-path")
	public WebElement Person_photoPath;
	
	/*
	 * Citizenship Information
	 */
	
	public WebElement Person_usCitizen_yes;
	
	public WebElement Person_usCitizen_no;
	
	public WebElement Person_socialSecurityNumber;

	public WebElement Person_naturalizationCertificateNumber;
	
	public WebElement Person_multipleCitizenship_yes;
	
	public WebElement Person_multipleCitizenship_no;
	
	@FindBy(css = "#Person_countriesOfCitizenship_multiValue label")
	public List<WebElement> Person_countriesMultiSelectLabel;
	
	@FindBy(css = "#Person_countriesOfCitizenship_multiValue input")
	public List<WebElement> Person_countriesMultiSelect;

	public WebElement Person_birthCountry;
	
	public WebElement Person_birthState;
	
	public WebElement Person_birthCity;
	
	/*
	 * Address
	 */
	
	public WebElement Person_street1;
	
	public WebElement Person_street2;
	
	public WebElement Person_city;
	
	public WebElement Person_country;
	
	public WebElement Person_zipCode;

	/*
	 * Record Retention
	 */
	
	public WebElement Person_restrictedList_yes;
	
	public WebElement Person_restrictedList_no;
	
	public WebElement Person_notHiredDate;
	
	public WebElement Person_separationDate;
	
	public WebElement Person_litigationHold;
	
	/*
	 * Case Summary
	 */
	
	@FindBy(id = "caseSummary")
	public WebElement caseSummaryFieldSet;
	
	@FindBy(css = "#caseSummary a")
	public WebElement caseSummaryCaseLink;
	
	@FindBy(css = "#caseSummary tbody tr:nth-child(1) td:last-child")
	public WebElement workflowStatus;

	@FindBy(css = "#caseSummary tbody tr:nth-child(2) td:last-child")
	public WebElement applicantType;
	
	@FindBy(css = "#caseSummary tbody tr:nth-child(3) td:last-child")
	public WebElement createdOn;
	
}
