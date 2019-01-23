/**
 * 
 */
package com.entellitrak_epa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

/**
 * @author Musa
 * @author Edgar Servellon
 */
public class BasePage {
	
	public BasePage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = "//img[contains(@src, 'Intake.png')]")
	public WebElement startIntake;
	
	@FindBy(id = "//img[contains(@src, 'Contracts.png')]")
	public WebElement newContract;
	
	@FindBy(id = "//img[contains(@src, 'My_Work.png')]")
	public WebElement myCases;
	
	@FindBy(id = "//span/img[contains(@src, 'Search.png')]")
	public WebElement savedSearches;
	
	@FindBy(id = "//img[contains(@src, 'Reports.png')]")
	public WebElement reports;
	
	@FindBy(id = "//img[contains(@src, 'Profile.png')]")
	public WebElement userProfile;
	
	@FindBy(id = "//img[contains(@src, 'Interface.png')]")
	public WebElement interfaceConsole;
	
	@FindBy(id = "//img[contains(@src, 'System_Monitor.png')]")
	public WebElement systemMonitor;
	
	@FindBy(id = "//img[contains(@src, 'Admin_Tools.png')]")
	public WebElement devTools;
	
}
