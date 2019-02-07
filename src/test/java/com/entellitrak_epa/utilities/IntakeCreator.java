package com.entellitrak_epa.utilities;

import java.io.File;

import org.openqa.selenium.support.ui.Select;

import com.entellitrak_epa.pages.IntakeInitiationPage;

public class IntakeCreator {

	FormFunctionsUtils formFunctionsUtils = new FormFunctionsUtils();
	IntakeInitiationPage intakeInitiationPage = new IntakeInitiationPage();

	public void submitIntakeFor(String applicantType, String applicantEmail) {
		switch (applicantType) {
		case "Federal":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completeHrSpecialistInfoSection();
			completePositionDesignationSection();
			break;
		case "Political Appointee":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completeHrSpecialistInfoSection();
			completePositionDesignationSection();
			break;
		case "Federal Detailee":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completeHrSpecialistInfoSection();
			completePositionDesignationSection();
			break;
		case "Non-Federal Detailee":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "Contractor":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "SEE":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "Grantee":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "Foreign National":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "Volunteer":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "Student":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			completePositionDesignationSection();
			break;
		case "VAR":
			formFunctionsUtils.dropDown(intakeInitiationPage.IntakeInitiationCasetypeDropdown, "New");
			completeApplicantInfoSection(applicantType, applicantEmail);
			completePOCInfoSection();
			break;
		}
	}

	private void completeApplicantInfoSection(String applicantType, String applicantEmail) {
		formFunctionsUtils.dropDown(intakeInitiationPage.IntakeIni_ApplicantType_Dropdown, applicantType);
		if (applicantType.equals("Federal") || applicantType.equals("Political Appointee")
				|| applicantType.equals("Federal Detailee")) {
			intakeInitiationPage.IntakeInitiation_employeeCommonIdentifier
					.sendKeys(formFunctionsUtils.generateRandomNumber(10));
			File testPdfFile = new File("src/test/resources/epa_test_files/My Test PDF.pdf");
			String testPdfFilePath = testPdfFile.getAbsolutePath();
			intakeInitiationPage.IntakeInitiation_of306.sendKeys(testPdfFilePath);
		}
		intakeInitiationPage.IntakeInitiation_vip_yes.click();
		String appFirstName = formFunctionsUtils.randomFirstName();
		setFirstName(appFirstName);
		intakeInitiationPage.IntakeInitiation_firstName.sendKeys(appFirstName);
		String appMiddleName = formFunctionsUtils.randomMiddleName();
		setMiddleName(appMiddleName);
		intakeInitiationPage.IntakeInitiation_middleName.sendKeys(appMiddleName);
		String appLastName = formFunctionsUtils.randomLastName();
		setLastName(appLastName);
		intakeInitiationPage.IntakeInitiation_lastName.sendKeys(appLastName);
		if (!"Foreign National".equals(applicantType)) {
			String appSSN = formFunctionsUtils.generateSSN();
			setSSN(appSSN);
			intakeInitiationPage.IntakeInitiation_socialSecurityNumber.sendKeys(appSSN);
		} else if (applicantType.equals("Foreign National")) {
			String appPSSN = formFunctionsUtils.generateRandomNumber(8);
			setpSSN(appPSSN);
			intakeInitiationPage.IntakeInitiation_pssn.sendKeys("p" + appPSSN);
		}
		intakeInitiationPage.IntakeInitiation_applicantEmail.sendKeys(applicantEmail);
		if (applicantType.equals("Grantee")) {
			intakeInitiationPage.IntakeInitiation_grantNumber.sendKeys(formFunctionsUtils.generateRandomNumber(10));
		}
		if (applicantType.equals("Contractor")) {
			intakeInitiationPage.IntakeInitiation_contractNumber_searchButton.click();
			intakeInitiationPage.IntakeInitiation_contractNumber_liveSearchInput.sendKeys("123456789AAA");
			intakeInitiationPage.singleContractNumberResult.click();
			intakeInitiationPage.IntakeInitiation_applicantTenureLessThan6Months_no.click();
		}
		if (!"VAR".equals(applicantType)) {
			formFunctionsUtils
					.randomDropDownValue(intakeInitiationPage.IntakeInitiation_programOfficeAndRegionDropdown);
			formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiationSanDropdown);
			formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiationDutyLocationDropdown);
			formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiationBuildingNameDropdown);
			intakeInitiationPage.IntakeInitiation_requestUsaccessBadge_yes.click();
		}
		if (applicantType.equals("VAR")) {
			intakeInitiationPage.IntakeInitiation_personalPhoneNumber
					.sendKeys(formFunctionsUtils.generatePhoneNumber());
			formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiation_birthCountry);
			Select birthCountryDropdown = new Select(intakeInitiationPage.IntakeInitiation_birthCountry);
			String selectedBirthCountry = birthCountryDropdown.getFirstSelectedOption().getText();
			if (selectedBirthCountry.equals("U.S.A")) {
				formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiation_birthState);
			}
			intakeInitiationPage.IntakeInitiation_birthCity.sendKeys("City of Light");
			intakeInitiationPage.IntakeInitiation_dateVarReceived.sendKeys(formFunctionsUtils.currentDate());
			formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiation_visitorsAgency);
			Select visitorAgencyDropdown = new Select(intakeInitiationPage.IntakeInitiation_visitorsAgency);
			String selectedVisitorAgency = visitorAgencyDropdown.getFirstSelectedOption().getText();
			if (selectedVisitorAgency.equals("Other")) {
				intakeInitiationPage.IntakeInitiation_otherAgency.sendKeys("Dep. of International Services");
			}
			intakeInitiationPage.IntakeInitiation_investigationCompletionDate
					.sendKeys(formFunctionsUtils.currentDate());
			intakeInitiationPage.IntakeInitiation_visitorComments
					.sendKeys("Test Visitor Comments!~!@#$%^&*();_+{}|[]:;'<>?,.//\\");
		}
	}

	private void completeHrSpecialistInfoSection() {
		String fakeEmail = "fakeEmail123@chainbridgesolutionsFakeEmail.com";
		intakeInitiationPage.IntakeInitiation_hrSpecialistFirstName.sendKeys(formFunctionsUtils.randomFirstName());
		intakeInitiationPage.IntakeInitiation_hrSpecialistLastName.sendKeys(formFunctionsUtils.randomLastName());
		//intakeInitiationPage.IntakeInitiation_hrSpecialistEmailAddress.sendKeys(fakeEmail);
		intakeInitiationPage.IntakeInitiation_hrSpecialistPhoneNumber
				.sendKeys(formFunctionsUtils.generatePhoneNumber());
		intakeInitiationPage.IntakeInitiation_hiringManagerFirstName.sendKeys(formFunctionsUtils.randomFirstName());
		intakeInitiationPage.IntakeInitiation_hiringManagerLastName.sendKeys(formFunctionsUtils.randomLastName());
		intakeInitiationPage.IntakeInitiation_hiringManagerEmailAddress.sendKeys(fakeEmail);
		intakeInitiationPage.IntakeInitiation_hiringManagerPhoneNumber
				.sendKeys(formFunctionsUtils.generatePhoneNumber());
	}

	private void completePOCInfoSection() {
		String fakeEmail = "fakeEmail123@chainbridgesolutionsFakeEmail.com";

		if (new Select(intakeInitiationPage.IntakeIni_ApplicantType_Dropdown).getFirstSelectedOption().getText()
				.equals("VAR")) {
			if (intakeInitiationPage.IntakeInitiation_requestingPocFirstName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_requestingPocFirstName
						.sendKeys(formFunctionsUtils.randomFirstName());
			}
			if (intakeInitiationPage.IntakeInitiation_requestingPocLastName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_requestingPocLastName
						.sendKeys(formFunctionsUtils.randomLastName());
			}
			if (intakeInitiationPage.IntakeInitiation_requestingPocEmailAddress.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_requestingPocEmailAddress.sendKeys(fakeEmail);
			}
			if (intakeInitiationPage.IntakeInitiation_requestingPocPhoneNumber.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_requestingPocPhoneNumber
						.sendKeys(formFunctionsUtils.generatePhoneNumber());
			}
			if (intakeInitiationPage.IntakeInitiation_pocFirstName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocFirstName.sendKeys(formFunctionsUtils.randomFirstName());
			}
			if (intakeInitiationPage.IntakeInitiation_pocLastName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocLastName.sendKeys(formFunctionsUtils.randomLastName());
			}
			if (intakeInitiationPage.IntakeInitiation_pocEmailAddress.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocEmailAddress.sendKeys(fakeEmail);
			}
			if (intakeInitiationPage.IntakeInitiation_pocPhoneNumber.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocPhoneNumber.sendKeys(formFunctionsUtils.generatePhoneNumber());
			}
		} else {
			if (intakeInitiationPage.IntakeInitiation_pocFirstName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocFirstName.sendKeys(formFunctionsUtils.randomFirstName());
			}
			if (intakeInitiationPage.IntakeInitiation_pocLastName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocLastName.sendKeys(formFunctionsUtils.randomLastName());
			}
			if (intakeInitiationPage.IntakeInitiation_pocEmailAddress.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocEmailAddress.sendKeys(fakeEmail);
			}
			if (intakeInitiationPage.IntakeInitiation_pocPhoneNumber.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_pocPhoneNumber.sendKeys(formFunctionsUtils.generatePhoneNumber());
			}
			if (intakeInitiationPage.IntakeInitiation_alternatePocFirstName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_alternatePocFirstName
						.sendKeys(formFunctionsUtils.randomFirstName());
			}
			if (intakeInitiationPage.IntakeInitiation_alternatePocLastName.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_alternatePocLastName
						.sendKeys(formFunctionsUtils.randomLastName());
			}
			if (intakeInitiationPage.IntakeInitiation_alternatePocEmailAddress.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_alternatePocEmailAddress.sendKeys(fakeEmail);
			}
			if (intakeInitiationPage.IntakeInitiation_alternatePocPhoneNumber.getAttribute("value").equals("")) {
				intakeInitiationPage.IntakeInitiation_alternatePocPhoneNumber
						.sendKeys(formFunctionsUtils.generatePhoneNumber());
			}
		}
	}

	private void completePositionDesignationSection() {
		String positionTitle = "CDC Specialist";
		intakeInitiationPage.IntakeInitiation_positionTitle.sendKeys(positionTitle);
		formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiation_sensitivityDropdown);
		formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiation_riskLevelDropdown);
		formFunctionsUtils.randomDropDownValue(intakeInitiationPage.IntakeInitiation_investigationTypeDropdown);
		File testPdfFile = new File("src/test/resources/epa_test_files/My Test PDF.pdf");
		String testPdfFilePath = testPdfFile.getAbsolutePath();
		intakeInitiationPage.IntakeInitiation_opmDesignationSummary.sendKeys(testPdfFilePath);
	}

	private String firstName, middleName, lastName, SSN, pSSN;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String randomFirst) {
		this.firstName = randomFirst;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String randomLast) {
		this.lastName = randomLast;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String randomMiddle) {
		this.middleName = randomMiddle;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String randomSSN) {
		this.SSN = randomSSN;
	}

	public String getpSSN() {
		return pSSN;
	}

	public void setpSSN(String randonPSSN) {
		this.pSSN = randonPSSN;
	}

}