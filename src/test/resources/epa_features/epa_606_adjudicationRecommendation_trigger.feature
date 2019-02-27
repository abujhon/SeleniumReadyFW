Feature: Create Adjudication Recommendation trigger

	
	@TEST_EPA-793
	Scenario: Create Adjudication Recommendation trigger
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
			When user selects Favorable from prescreening decision dropdown
			Then Release to NBIB button remains inactive
			When user completes the eQIP section on case form
			Then Release to NBIB button becomes active
			When user selects Yes to Skip investigation 
			Then Release to NBIB trigger button is disabled
			And Skip Investigation trigger buttons becomes active
			When user clicks the Skip Investigation button 
			Then case workflow becomes Adjudication Queue
			Then user logs out from EPA 
			
			
			Given user logs in as supervisor 
			When user navigates to case form through rapid search
			Then user assigns the case to epa_adjudicator
			And user selects Send to Assign Adjudicator from action to take
			And user clicks save and continue button
			Then case workflow becomes Adjudication
			Then user logs out from EPA
			
			Given user logs in as adjudicator
			When user navigates to case form through rapid search
			Then case workflow becomes Adjudication
			When user navigates to Adjudication form
			Then Adjudication recommendation dropdown displays
			When user selects a option from Adjudication recommendation dropdown
			And user clicks Yes to Ready for Final Adjudication YES/NO
			Then assign adjudication dropdown displays
			When user selects a final adjudicator
			Then Send to Final Adjudication button becomes enabled
			When user clicks on Send to Final Adjudication button
			Then case workflow becomes Final Adjudication
			Then user logs out from EPA
			And case assigns to Final adjudicator
			Then user logs out from EPA 
