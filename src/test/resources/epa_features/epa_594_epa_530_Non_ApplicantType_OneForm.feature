@Regression
Feature: Intake Initiation: Non-Federal Applicant type one form

	

	@TEST_EPA-657
		  Scenario Outline: All non-federal applicants shares one same intake form 
		    Given user logs in the system as COR-Intake initiator
		    And user navigates to intake initiation form
		    When user selects "<applicantType>" from applicant type dropdown
		    Then all the fields for non-federal displays
		    When user searchs and selects "<contractNumber>"
		    Then contract number vendor name contract expiration date populates with all POC information fields
		    And user logs out from EPA
		    
		    Examples:
		      | applicantType       | contractNumber |
		      | Non-Federal Detailee| 77777 |
		      | Contractor          | 77777 |
		      | SEE                 | 77777 |
		      | Grantee             | 77777 |
		      | Foreign National    | 77777 |
		      | Volunteer           | 77777 |
		      | Student             | 77777 |
