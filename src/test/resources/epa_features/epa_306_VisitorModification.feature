@Regression
Feature: Intake Initiation: Visitor Modifications

	#As a user, I want the ability to create an intake initiation where applicant type = Visitor 
	#and have these additional fields display so that I can enter all VAR information during intake.



	@TEST_EPA-559
	Scenario: Intake Initiation: Visitor Modifications
		Given user is on the EPA login screen
		And user signs in as intake creator
		And navigates to intake initiation form
		When user sets the Applicant type to VAR
		Then SSN field is not required
		When user selects Other from Visitor agency dropdown
		Then Other Agency text field displays and is required
		And user logs out from EPA
		
