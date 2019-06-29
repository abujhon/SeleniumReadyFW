/**
 * 
 */
package com.project.utilities;

import static org.junit.Assert.*;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;
import com.project.pages.ApplicantIntakeFormPage;
import com.project.pages.EntelliSQLpage;
import com.project.pages.SignOnPage;



/**
 * @author Musa
 *
 */
public class Applicant_Intake_Submit {
	
	
	
	SignOnPage signIn = new SignOnPage();
	EntelliSQLpage sqlPage = new EntelliSQLpage();
	BrowserUtils browserUtils = new BrowserUtils();
	ApplicantIntakeFormPage applicantIFP = new ApplicantIntakeFormPage();
	Faker faker = new Faker();
	FormFunctionsUtils formUtils = new FormFunctionsUtils();
	
	String[] splitEmailBodyForUsername;
	String[] splitEmailBodyForPassword;
	String applicantUsername = "";
	String applicantPassword = "";
	String fullEmailBody = "";
	String applicantNewPassword = "";
	int rowNumOfTargetEmailBody = 0;
	WebElement applicantTargetBody = null;
	
	
	String applicantFName = "";
	String applicantLName = "";
	String applicantSSN = "";
	String appEmail = "";
	
	/*
	 *  use getApplicantCredentials method to go email queue and get temporary username and password
	 */
	
	
	public void getApplicantCredentials(String applicantEmail, String applicantResetPassword) {
		
		applicantNewPassword = applicantResetPassword;
	    appEmail = applicantEmail;
		
		signIn.username.sendKeys(PropertiesReader.getProperty("automation_admin"));
		signIn.password.sendKeys(PropertiesReader.getProperty("password"));
		signIn.submit.click();
		
		sqlPage.configurationTab.click();
		sqlPage.configurationTab.click();
		browserUtils.waitForElementToBeClickable(sqlPage.exitEntelliSQL);
		sqlPage.exitEntelliSQL.click();
		browserUtils.waitForElementToBeClickable(sqlPage.newQueryButton);
		sqlPage.newQueryButton.click();
		
		browserUtils.waitForElementToBeVisible(sqlPage.editorFrame);
		Driver.getInstance().switchTo().frame(sqlPage.editorFrame);
		
		sqlPage.sqlTextBox.sendKeys("Select * from T_EU_EMAIL_QUEUE where C_Subject LIKE '%Applicant Invitation%' order by C_CREATED_TIME desc");
		
		Driver.getInstance().switchTo().defaultContent();
		browserUtils.sleep(900);
		Driver.getInstance().switchTo().frame(sqlPage.toolBarFrame);
		browserUtils.sleep(900);
		sqlPage.executeQuery.click();
		Driver.getInstance().findElement(By.cssSelector("#toolbar>li>a>img")).click();
		browserUtils.sleep(1500);
		
		Driver.getInstance().switchTo().defaultContent();
		Driver.getInstance().switchTo().frame(sqlPage.resultsFrame);
		
		
		
		for (WebElement email : sqlPage.allApplicantEmails) {
			rowNumOfTargetEmailBody++;
			if (email.getText().trim().equals(applicantEmail)) {
				applicantTargetBody = Driver.getInstance().findElement(
						By.xpath("(//table[@class='grid']/tbody/tr[" + rowNumOfTargetEmailBody + "]/td[3])"));
				break;
			} else {
				System.out.println("Inside else");
				continue;
			}
		}

		fullEmailBody = applicantTargetBody.getText();

		splitEmailBodyForUsername = fullEmailBody.split("Username: <strong>");

		for (String s : splitEmailBodyForUsername) {
			System.out.println(s);
		}


		applicantUsername = splitEmailBodyForUsername[1].substring(0, splitEmailBodyForUsername[1].indexOf("&")).trim();
		System.out.println("applicant username :"+applicantUsername);

		splitEmailBodyForPassword = fullEmailBody.split("Password: <strong>");

		for (String s : splitEmailBodyForPassword) {
			System.out.println(s);
		}

		applicantPassword = splitEmailBodyForPassword[1].substring(0, splitEmailBodyForPassword[1].indexOf("<")).trim();
		System.out.println("applicant password :"+applicantPassword);

		Driver.getInstance().switchTo().defaultContent();
		Driver.getInstance().switchTo().frame(sqlPage.toolBarFrame);
		sqlPage.exitFromSQL.click();
		browserUtils.waitForElementToBeVisible(sqlPage.newQueryButton);

		
	}
	
	
	/*
	 *  use complateApplicantIntake method to log in with the temporary password and change it, 
	 */
	
	public void changePasswordAndLogin() {
		signIn.username.sendKeys(applicantUsername);
		signIn.password.sendKeys(applicantPassword);
		signIn.submit.click();
		
		browserUtils.waitForElementToBeVisible(signIn.passwordCurrent);
		
		signIn.passwordCurrent.sendKeys(applicantPassword);
		signIn.newPassword.sendKeys(applicantNewPassword);
		signIn.passwordConfirm.sendKeys(applicantNewPassword);
		signIn.changePassword.click();
		
		browserUtils.waitForElementToBeVisible(applicantIFP.AIF_formsFieldset);
		assertTrue(applicantIFP.AIF_formsFieldset.isDisplayed());
		
		
	}
	
	
	/*
	 *  use this method to complete applicant intake form
	 */
	
	public void completeApplicantIntakeForm(String applicantType) {
		
		applicantIFP.AIF_button.click();
		browserUtils.waitForElementToBeVisible(applicantIFP.AIF_fairCreditSection);
		assertTrue(applicantIFP.AIF_fairCreditSection.isDisplayed());
		
		
		applicantIFP.signature_chbx.click();
		
		applicantIFP.ApplicantIntakeForm_street1.sendKeys(faker.address().streetAddress());
		applicantIFP.ApplicantIntakeForm_city.sendKeys(faker.address().city());
		
		formUtils.dropDown(applicantIFP.ApplicantIntakeForm_country, "U.S.A");
		browserUtils.waitForElementToBeVisible(applicantIFP.ApplicantIntakeForm_state);
		formUtils.randomDropDownValue(applicantIFP.ApplicantIntakeForm_state);
		
		applicantIFP.ApplicantIntakeForm_zipCode.sendKeys(faker.address().zipCode());
		applicantIFP.ApplicantIntakeForm_phoneNumber.sendKeys(formUtils.generatePhoneNumber());
		applicantIFP.ApplicantIntakeForm_dateOfBirth.sendKeys("11/12/1980");
		applicantIFP.ApplicantIntakeForm_usCitizen_yes.click();
		formUtils.dropDown(applicantIFP.ApplicantIntakeForm_placeOfBirthCountryDropdown, "U.S.A");
		browserUtils.waitForElementToBeVisible(applicantIFP.ApplicantIntakeForm_placeOfBirthState);
		formUtils.randomDropDownValue(applicantIFP.ApplicantIntakeForm_placeOfBirthState);
		applicantIFP.ApplicantIntakeForm_placeOfBirthCity.sendKeys(faker.address().city());
		
		File ncjFile = new File("src/test/resources/epa_test_files/NCJ.pdf");
		String ncjFilePath = ncjFile.getAbsolutePath();
		File epaCreditRealease = new File("src/test/resources/epa_test_files/epaCredit.pdf");
		String epaCreditPath = epaCreditRealease.getAbsolutePath();
		File of306File = new File("src/test/resources/epa_test_files/OF-306.pdf");
		String of306Path = of306File.getAbsolutePath();
		
		
		
		if (applicantType.equals("Federal") || applicantType.equals("Political Appointee") || applicantType.equals("Federal Detailee")) {
			applicantIFP.ApplicantIntakeForm_njaprUpload.sendKeys(ncjFilePath);
			browserUtils.sleep(900);
			applicantIFP.ApplicantIntakeForm_epaCreditReleaseUpload.sendKeys(epaCreditPath);
		}else {
			applicantIFP.ApplicantIntakeForm_njaprUpload.sendKeys(ncjFilePath);
			browserUtils.sleep(900);
			applicantIFP.ApplicantIntakeForm_epaCreditReleaseUpload.sendKeys(epaCreditPath);
			browserUtils.sleep(900);
			applicantIFP.ApplicantIntakeForm_of306Upload.sendKeys(of306Path);
		}
		
		
		
	}
	
	
	public void submitApplicantIntake() {
		
		applicantIFP.submitButton.click();
		browserUtils.waitForElementToBeVisible(applicantIFP.applicantSubmitSuccess);
		assertTrue(applicantIFP.applicantSubmitSuccess.isDisplayed());
	}

}
