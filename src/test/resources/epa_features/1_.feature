Feature: XRAY AUTOMATION TEST - Multiple execution

	#EPA Supervisor Dashboard Icons
	@TEST_EPA-455 @both
	Scenario: XRAY AUTOMATION TEST - Multiple execution
		Given User navigates to PSS
		When User logs in as a Supervisor
		Then Home Dashboard displays
