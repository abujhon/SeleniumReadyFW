Feature: HR/COR: Skip Applicant Intake and Assignment to Specialist 

@TEST_EPA-712 
Scenario Outline: HR/COR: Skip Applicant Intake and Assignment to Specialist 
	Given user navigates to PSS 
	And user logs in as a "<user>" 
	When user clicks on Start Intake 
	And user completes a "<Applicant type>" intake with "<email>" while skipping applicant intake 
	And user clicks Submit on the Intake form 
	Then successful intake creation message displays 
	And user logs out from PSS 
	
	When user logs in as a Supervisor 
	And user clicks on My Cases 
	And user selects the Record Checks Queue inbox 
	And user searches for case initiated in the Record Checks Queue inbox 
	Then record will display in the Record Checks Queue inbox 
	And case status in the Record Checks Queue inbox displays as Record Checks 
	
	When user checks the assignment checkbox next to the applicants name 
	And user sets the assignment inbox to <"Specialist, Automation"> 
	And user clicks on Assign 
	Then the assignment has been made 
	And user logs out from PSS 
	
	When user logs in as a Specialist
	And user clicks on My Cases
	And user searches for case assigned in the Assigned Cases inbox 
	Then record will display in the Assigned Cases inbox 
	And case status in the inbox displays as Record Checks 
	
	When user navigates to case record searched on from the Assigned Cases inbox 
	Then case form displays 
	And case workflow status is now Record Checks 
	And user logs out from PSS 
	
	Examples: 
		| Applicant type   		| email                               | user   |
		| Federal    	   		| eservellon@chainbridgesolutions.com | HR     |
		| Federal Detailee 	 	| eservellon@chainbridgesolutions.com | HR     |
		| Political Appointee   | eservellon@chainbridgesolutions.com | HR     |
		| Foreign National	    | eservellon@chainbridgesolutions.com | COR    |
		| Non-Federal Detailee  | eservellon@chainbridgesolutions.com | COR    |
		| Contractor 			| eservellon@chainbridgesolutions.com | COR    |
		| SEE 					| eservellon@chainbridgesolutions.com | COR    |
		| Grantee  				| eservellon@chainbridgesolutions.com | COR    |
		| Volunteer 			| eservellon@chainbridgesolutions.com | COR    |
		| Student  				| eservellon@chainbridgesolutions.com | COR    |
		| VAR 					| eservellon@chainbridgesolutions.com | HR     |
