@Regression @TEST_EPA-588
Feature: Discontinue Case from any point in the workflow 


 
Scenario: discontinue case from intake forms review 
	Given hr intake initiator logs in 
	And user creates an intake for "Federal" applicant with "mabuduhelili@chainbridgesolutions.com"
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
	Then discontinue button displays 
	When user clicks on Discontnue button 
	Then a pop up displays saying The case will be permanently closed. Do you wish to continue? 
	When user click No 
	Then case status and workflow status does not change 
	When user clicks on Discontnue button 
	Then a pop up displays saying The case will be permanently closed. Do you wish to continue? 
	When user click YES 
	Then case status becomes inactive and workflow status becomes closed 
	And user logs out from EPA 
	
	
Scenario: discontinue case from record check state 
	Given hr intake initiator logs in 
	And user creates an intake for "Political Appointee" applicant with "mabuduhelili@chainbridgesolutions.com" 
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
	And discontinue button displays 
	When user clicks on Discontnue button 
	Then a pop up displays saying The case will be permanently closed. Do you wish to continue? 
	When user click No 
	Then case status and workflow status does not change 
	When user clicks on Discontnue button 
	Then a pop up displays saying The case will be permanently closed. Do you wish to continue? 
	When user click YES 
	Then case status becomes inactive and workflow status becomes closed 
	And user logs out from EPA 
	
	
Scenario: discontinue case from adjudication state 

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
	
	Given user logs in as supervisor 
	When user navigates to case form through rapid search  
	Then discontinue button displays 
	
	When user slects Send for Pre-Screening Review 
	And user clicks save and continue button 
	Then workflow status becomes Pre-Screening Review 
	Then discontinue button displays 
	
	When user selects Unfavorable from Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes Pre-Screening Decision Review Queue 
	Then discontinue button displays 
	
	When user selects Send to Pre-Screening Decision Review from Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes Pre-Screening Decision Review 
	And discontinue button displays 
	
	When user selects Overturn Decision from Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes Pre-Screening Review 
	Then discontinue button displays 
	
	When user selects Favorable - Release EQIP to NBIB from Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes NBIB Investigation Processing 
	Then discontinue button displays 
	
	When user selects Receive ROI form Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes Adjudication Queue 
	Then discontinue button displays 
	
	When user selects Assign Adjudicator from Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes Adjudication  
	Then discontinue button displays  
	
	When user selects Enter Adjudicator from Action to Take dropdown 
	And user clicks save and continue button 
	Then workflow status becomes Final Adjudication  
	Then discontinue button displays    
	
	When user clicks on Discontnue button 
	Then a pop up displays saying The case will be permanently closed. Do you wish to continue? 
	When user click No 
	Then case status and workflow status does not change 
	When user clicks on Discontnue button 
	Then a pop up displays saying The case will be permanently closed. Do you wish to continue? 
	When user click YES 
	Then case status becomes inactive and workflow status becomes closed
	Then user logs out from EPA 
