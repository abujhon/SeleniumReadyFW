@epa_test
Feature: create intake 




Scenario: user creates intake and applicant completes applicant intake

	Given user is on the EPA login screen 
	And user signs in as intake creator
	And navigates to intake initiation form
	And completes intake for "Federal" and "mabuduhelili@chainbridgesolutions.com"
	When initiator click save
	Then intake succes message displays
	Then user logs out from EPA
	
	Given applicant gets the temporary user credentials
	And applicant resets the password
	When applicant completes the applicant intake form
	Then applicant be able to submit
	Then user logs out from EPA
			