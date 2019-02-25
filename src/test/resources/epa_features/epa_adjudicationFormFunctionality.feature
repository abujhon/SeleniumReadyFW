Feature: Automation: Adjudication Form - Display and Assignment Functionality 

Background: 
	Given user navigates to PSS 
	When user logs in as a Supervisor 
	And user creates an intake 
	And user navigates to case via rapid search from home page 
	And user pushes created case to the Adjudication state 
	And user logs out from PSS 
	
@TEST_EPA-585	
Scenario: Automation: Adjudication Form - Display and Assignment Functionality 
	Given user navigates to PSS 
	And user logs in as an Adjudicator 
	When user navigates to the Assigned Cases queue 
	And user selects case assigned in the Adjudication state 
	Then the case record of selected applicant displays 
	
	When user navigates to the Adjudication CTO 
	And user clicks on the already created Adjudication record 
	Then the Adjudication record displays (display mapping Adjudication state) 
	
	When user clicks Save on Adjudication 
	Then form does not save due to empty required fields on Adjudication 
	
	When user populates Seriousness Issue Code 
	And user selects all Adjudicative Guidelines 
	And user selects all Suitability Guidelines 
	And user populates Adjudication Recommendation 
	And user populates Adjudication Recommendation Date 
	And user populates Adjudication Summary 
	And user sets Ready for Final Adjudication to No 
	And user clicks Save on Adjudication 
	Then the Adjudication Recommendation fields remain editable 
	
	When user sets Ready for Final Adjudication to Yes 
	Then Select Final Adjudicator displays 
	
	When user clicks Save on Adjudication 
	Then form does not save due to empty required field 
	
	When user sets Select Final Adjudicator to <"Adjudicator II, Automation"> 
	And user clicks Save on Adjudication 
	Then message displays on listing indicating that case has been sent to final adjudication 
	And the Adjudication Recommendation fields become read only 
	
	When user navigates to Case 
	Then the workflow status of Case is now Final Adjudication
	And user logs out from PSS