/**
 * 
 */
package com.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.utilities.Driver;

/**
 * @author Musa
 *
 */
public class ApplicantIntakeFormPage {
	
	public ApplicantIntakeFormPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	
	@FindBy(xpath = "//span[contains(text(), 'IMPORTANT')]")
	public WebElement AIF_formsFieldset;
	
	@FindBy(xpath = "//a[contains(text(), 'Applicant Intake Form')]")
	public WebElement AIF_button;
	
	@FindBy(id = "parentTab")
	public WebElement AIF_parentTab;
	
	@FindBy(xpath = "//span[contains(text(), 'FAIR CREDIT REPORTING ACT OF 1970')]")
	public WebElement AIF_fairCreditSection;
	
	public WebElement ApplicantIntakeForm_fairCreditFirstName;
	
	public WebElement ApplicantIntakeForm_fairCreditLastName;
	
	public WebElement ApplicantIntakeForm_fairCreditSocialSecurityNumber;
	
	public WebElement signature_chbx;
	
	@FindBy(xpath = "//span[contains(text(), 'Applicant Information')]")
	public WebElement applicantInformationSection;
	
	public WebElement ApplicantIntakeForm_applicantType_display;
	
	public WebElement ApplicantIntakeForm_firstName;
	
	public WebElement ApplicantIntakeForm_middleName;
	
	public WebElement ApplicantIntakeForm_lastName;
	
	public WebElement ApplicantIntakeForm_suffix;
	
	public WebElement ApplicantIntakeForm_personalEmailAddress;
	
	public WebElement ApplicantIntakeForm_street1;
	
	public WebElement ApplicantIntakeForm_street2;
	
	public WebElement ApplicantIntakeForm_city;
	
	public WebElement ApplicantIntakeForm_country;
	
	public WebElement ApplicantIntakeForm_state;
	
	public WebElement ApplicantIntakeForm_zipCode;
	
	public WebElement ApplicantIntakeForm_phoneNumber;
	
	public WebElement ApplicantIntakeForm_dateOfBirth;
	
	public WebElement ApplicantIntakeForm_usCitizen_yes;
	
	public WebElement ApplicantIntakeForm_usCitizen_no;
	
	public WebElement ApplicantIntakeForm_socialSecurityNumber;
	
	@FindBy(id = "ApplicantIntakeForm_placeOfBirthCountry")
	public WebElement ApplicantIntakeForm_placeOfBirthCountryDropdown;
	
	public WebElement ApplicantIntakeForm_placeOfBirthState;
	
	public WebElement ApplicantIntakeForm_placeOfBirthCity;
	
	@FindBy(xpath = "//span[contains(text(), 'Download/Upload Signature')]")
	public WebElement downloadSignatureSection;
	
	@FindBy(id = "upload_instructions_label-container")
	public WebElement AIF_downloadSection_instruction;
	
	@FindBy(xpath = "//a[contains(text(), 'Noncriminal Justice Applicant')]")
	public WebElement noncriminalJusticeApplicant_download;
	
	public WebElement ApplicantIntakeForm_njaprUpload;
	
	@FindBy(xpath = "//a[contains(text(), 'EPA Credit Release')]")
	public WebElement EPACreditRelease_download;
	
	public WebElement ApplicantIntakeForm_epaCreditReleaseUpload;
	
	@FindBy(xpath = "//a[contains(text(), 'OF-306')]")
	public WebElement OF306_download;
	
	public WebElement ApplicantIntakeForm_of306Upload;
	
	public WebElement saveButton;
	
	public WebElement submitButton;
	
	@FindBy(xpath = "//div[contains(text(), 'Success')]")
	public WebElement applicantSubmitSuccess;
	
	
	

}
