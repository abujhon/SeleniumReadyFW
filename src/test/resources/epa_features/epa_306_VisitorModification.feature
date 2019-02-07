@Regression
Feature: Intake Initiation: Visitor Modifications

	#As a user, I want the ability to create an intake initiation where applicant type = Visitor and have these additional fields display so that I can enter all VAR information during intake.

#h2. Requirement
#Update the following fields on Intake form for Visitor:

# Add:
#* Visitor's Agency is a required lookup using agency values
#** Include Other in drop down
#*** If Other is selected then display Other Agency text field
#* Investigation Completion Date is a required date field
# SSN should not be required
# Change Visitor applicant type = VAR

#*Note: The visitor agency drop down values have been updated with values from the customer*

#h1. Acceptance Criteria
# Given as a user with access to create Intake Initiation
# When I create an Intake Initiation
## And Set the Applicant type = VAR
# Then Investigation Completion Date displays as a required date field
# And SSN is not required
# And Visitor's Agency displays as required drop down field
## When I select Visitors Agency = Other
### Then Other Agency text field displays and is required
# When I click Save
# Then the record saves
# Update

	@TEST_EPA-559
	Scenario: Intake Initiation: Visitor Modifications
		Given user is on the EPA login screen
		And user signs in as intake creator
		And navigates to intake initiation form
		When user sets the Applicant type to VAR
		Then SSN field is not required
		When user selects Other from Visitor agency dropdown
		Then Other Agency text field displays and is required
