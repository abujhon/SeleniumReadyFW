Feature: Intake Creation by Specialist: Skip Applicant Intake

	@TEST_EPA-647
	Scenario Outline: Intake Creation by Specialist: Skip Applicant Intake
		    Given user navigates to PSS
		    And user logs in as a Specialist
		    When user clicks on Start Intake
		    And user completes a "<Applicant type>" intake with "<email>" while skipping applicant intake
		    And user clicks Submit on the Intake form
		    Then successful intake creation messages display
		    And a person and case link display
		    
		    When user clicks on the Case link
		    Then case form displays
		    And case workflow status is now Record Checks
		    And specialist assigned is the specialist who submitted the intake
		    
		    When user navigates to home page
		    And user clicks on My Cases
		    And user searches for case initiated in the Assigned Cases inbox
		    Then record will display in the Assigned Cases inbox
		    And case status in the inbox displays as Record Checks
		    And user logs out from PSS
		    
			Examples:	
			| Applicant type   		| email                               |
			| Federal    	   		| eservellon@chainbridgesolutions.com |
			| Foreign National 		| eservellon@chainbridgesolutions.com |
			| Political Appointee   | eservellon@chainbridgesolutions.com |
			| Federal Detailee 		| eservellon@chainbridgesolutions.com |
			| Non-Federal Detailee  | eservellon@chainbridgesolutions.com |
			| Contractor 			| eservellon@chainbridgesolutions.com |
			| SEE 					| eservellon@chainbridgesolutions.com |
			| Grantee  				| eservellon@chainbridgesolutions.com |
			| Volunteer 			| eservellon@chainbridgesolutions.com |
			| Student  				| eservellon@chainbridgesolutions.com |
			| VAR 					| eservellon@chainbridgesolutions.com |
