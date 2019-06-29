/**
 * 
 */
package com.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.utilities.Driver;

/**
 * @author Musa
 *
 */
public class InvestigationPage {
	
	public InvestigationPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	@FindBy(xpath = "//span[contains(text(), 'Investigation Information')]")
	public WebElement investigationinformation_section;
	
	public WebElement Investigation_investigationType_display;
	
	public WebElement Investigation_eqipReleaseToOpmDate;
	
	public WebElement Investigation_opmCaseNumber;
	
	public WebElement Investigation_investigationScheduledDate;
	
	public WebElement Investigation_opmCaseStatus;
	
	public WebElement Investigation_investigationClosedDate;
	
	public WebElement Investigation_edeliveryMatch_yes;
	
	public WebElement Investigation_edeliveryMatch_no;
	
	public WebElement Investigation_roiReceivedDate;
	
	public WebElement Investigation_reinvestigationDueDate;
	
	public WebElement Investigation_investigationComments;
	
	public WebElement saveButton;
	
	
	
	
	

}
