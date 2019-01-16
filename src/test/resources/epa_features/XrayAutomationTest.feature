Feature: XRAY AUTOMATION TEST

	#EPA Supervisor Dashboard Icons
	@TEST_EPA-375
	Scenario: XRAY AUTOMATION TEST
		Given User navigates to PSS
		When User logs in as a Supervisor
		Then Home Dashboard displays
		And The My Cases icon displays
		And The Saved Searches icon displays
		And The Reports icon displays