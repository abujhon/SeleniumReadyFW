Feature: Google Test 

Scenario: Admin login 

	Given the user is on the sign on page 
	When user signs in as admin 
	Then the home page should display with the correct user name on the right hand corner of the page 
	
@testGmail @test 
Scenario: Google Test 

	Given user is on google home page 
		"""
			 At the Goole home page
			"""
	When user clicks on gmail link provided 
		"""
			Clicking on Gmail link
			"""
	Then two create an account links shall display 
		""" 
			Two create an account links display on gmail page
			"""
			