@Regression
Feature: Create Render Unfavorable and Overturn Decision Triggers

	
	@TEST_EPA-778
	Scenario: Create Render Unfavorable and Overturn Decision Triggers
		Given hr intake initiator logs in 
			And user creates an intake for "Federal Detailee" applicant with "mabuduhelili@chainbridgesolutions.com" 
			Then user logs out from EPA 
			
			Given as applicant log in and submit intake form 
			Then user logs out from EPA 
			
			Given user logs in as supervisor 
			When user navigates to case form through rapid search
			Then user assigns the case to security speciliast
			Then user logs out from EPA
			
			Given user logs in to system as security specialist 
			When user navigates to case form through rapid search 
			Then the workflow status is Intake Forms Review 
			Then action to take dropdown displays 
			When user selects Complete Intake from action to take dropdown 
			And user clicks save and continue button 
			Then workflow status becomes Record Check 
			When user selects Send to Pre-Screening Review Queue from action to take 
			And user clicks save and continue button 
			Then workflow status becomes Pre-Screening Review Queue
			Then user logs out from EPA
			
			Given user logs in as FSEM adjudicator
			When user navigates to FSEM unassigned queue and assignes the case to FSEM adj
			Then the case displays in assigned queue and user navigates to case form
			
			When user navigates to prescreening form 
			Then Release to NBIB trigger button is disabled 
			And Unfavorable trigger button is disabled
			And Skip Investigation trigger buttons is disabled
			When user selects Unfavorable from prescreening decision dropdown
			Then Unfavorable trigger button is enabled
			When user clicks the Unfavorable trigger button 
			Then case workflow becomes PreScreening Decision Review Queue state
			Then user logs out from EPA 
			
			Given user logs in as supervisor 
			When user navigates to case form through rapid search
			And user assigns the case to epa_adjudicator
			And user selects Send to PreScreening Decision Review from action to take
			And user clicks save and continue button
			Then case workflow becomes PreScreening Decision Review
			Then user logs out from EPA
			
			Given user logs in as adjudicator
			When user navigates to case form through rapid search
			Then the workflow status is PreScreening Decision Review
			When user navigates to prescreening form
			Then Save button is enabled
			And Overturn Decision trigger button is disabled
			And Render Unfavorable trigger button is disabled
			When user selects concur from EPA review dropdown
			Then Render Unfavorable trigger button is enabled
			When user click on Render Unfavorable trigger button
			Then case workflow becomes Unfovorable Close
			Then user logs out from EPA
			
			
			
    @test111			
    Scenario: Overturn Decision Triggers
		    
		    Given hr intake initiator logs in 
			And user creates an intake for "Federal Detailee" applicant with "mabuduhelili@chainbridgesolutions.com" 
			Then user logs out from EPA 
			
			Given as applicant log in and submit intake form 
			Then user logs out from EPA 
			
			Given user logs in as supervisor 
			When user navigates to case form through rapid search
			Then user assigns the case to security speciliast
			Then user logs out from EPA
			
			Given user logs in to system as security specialist 
			When user navigates to case form through rapid search 
			Then the workflow status is Intake Forms Review 
			Then action to take dropdown displays 
			When user selects Complete Intake from action to take dropdown 
			And user clicks save and continue button 
			Then workflow status becomes Record Check 
			When user selects Send to Pre-Screening Review Queue from action to take 
			And user clicks save and continue button 
			Then workflow status becomes Pre-Screening Review Queue
			Then user logs out from EPA
			
			Given user logs in as FSEM adjudicator
			When user navigates to FSEM unassigned queue and assignes the case to FSEM adj
			Then the case displays in assigned queue and user navigates to case form
			
			When user navigates to prescreening form 
			Then Release to NBIB trigger button is disabled 
			And Unfavorable trigger button is disabled
			And Skip Investigation trigger buttons is disabled
			When user selects Unfavorable from prescreening decision dropdown
			Then Unfavorable trigger button is enabled
			When user clicks the Unfavorable trigger button 
			Then case workflow becomes PreScreening Decision Review Queue state
			Then user logs out from EPA 
			
			Given user logs in as supervisor 
			When user navigates to case form through rapid search
			And user assigns the case to epa_adjudicator
			And user selects Send to PreScreening Decision Review from action to take
			And user clicks save and continue button
			Then case workflow becomes PreScreening Decision Review
			Then user logs out from EPA
			
			Given user logs in as adjudicator
			When user navigates to case form through rapid search 
			Then the workflow status is PreScreening Decision Review
			When user navigates to prescreening form
			Then Save button is enabled
			And Overturn Decision trigger button is disabled
			And Render Unfavorable trigger button is disabled
			When user selects Do Not Concur from EPA review dropdown
			Then Overturn Decision trigger button is enabled
			When user clicks on Overturn Decision trigger button 
			Then case workflow becomes pre screening
		    Then user logs out from EPA
