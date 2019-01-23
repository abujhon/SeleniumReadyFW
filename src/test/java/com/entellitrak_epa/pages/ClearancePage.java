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
 *
 */
public class ClearancePage {
	
	public ClearancePage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	@FindBy(xpath = "//span[contains(text(), 'Clearance Information')]")
	public WebElement clearanceInformation_section;
	
	public WebElement Clearance_clearanceLevel;
	
	public WebElement Clearance_clearanceGrantedOrDeniedDate;
	
	public WebElement Clearance_clearanceType;
	
	public WebElement Clearance_statusUpdate;
	
	public WebElement Clearance_statusDate;
	
	public WebElement saveButton;

}
