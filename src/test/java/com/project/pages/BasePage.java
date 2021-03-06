/**
 * 
 */
package com.project.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.utilities.Driver;

/**
 * @author Musa
 * @author Edgar Servellon
 */
public class BasePage {
	
	public BasePage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	@FindBy(css = "#mainNavigation a.home")
	public WebElement home;
	
	@FindBy(css = ".signOut")
	public WebElement signOut;
	
	@FindBy(xpath = "//a[contains(text(), 'log in')]")
	public WebElement loginLink;
	
	@FindBy(xpath = "//img[contains(@src, 'Intake.png')]")
	public WebElement startIntake;
	
	@FindBy(xpath = "//img[contains(@src, 'Contracts.png')]")
	public WebElement newContract;
	
	@FindBy(xpath = "//img[contains(@src, 'My_Work.png')]")
	public WebElement myCases;
	
	@FindBy(xpath = "//span/img[contains(@src, 'Search.png')]")
	public WebElement savedSearches;
	
	@FindBy(xpath = "//img[contains(@src, 'Reports.png')]")
	public WebElement reports;
	
	@FindBy(xpath = "//img[contains(@src, 'Profile.png')]")
	public WebElement userProfile;
	
	@FindBy(xpath = "//img[contains(@src, 'Interface.png')]")
	public WebElement interfaceConsole;
	
	@FindBy(xpath = "//img[contains(@src, 'System_Monitor.png')]")
	public WebElement systemMonitor;
	
	@FindBy(xpath = "//img[contains(@src, 'Admin_Tools.png')]")
	public WebElement devTools;
	
	/*
	 * Rapid Search
	 */
	
	public WebElement rapidSearchInput;
	
	public WebElement rapidSearchIcon;
	
	@FindBy(xpath = "//button[@title='Close']")
	public WebElement rapidSearchClose;
	
	public WebElement rapidSearchResultsTable;
	
	@FindBy(css = "#rapidSearchResultsTable td:nth-child(1) a")
	public List<WebElement> rapidSearchName;
	
	@FindBy(css = "#rapidSearchResultsTable td:nth-child(2)")
	public List<WebElement> rapidSearchSSN;
	
	@FindBy(css = "#rapidSearchResultsTable td:nth-child(6)")
	public List<WebElement> rapidSearchStatus;
	
	@FindBy(css = "#rapidSearchResultsTable td:nth-child(7)")
	public List<WebElement> rapidSearchCreationDate;
	
	/*
	 * Enhanced Inbox
	 */
	
	@FindBy(id = "eiFrame")
	public WebElement enhancedInboxIframe;
	
	@FindBy(id = "inboxes")
	public WebElement inboxSelection;
	
	@FindBy(css = "#inbox_content_table_filter input")
	public WebElement enhancedInboxSearch;
	
	@FindBy(css = "input[name='inbox_content_actionableRow']")
	public List<WebElement> enhancedInboxAssignmentCheckboxes;
	
	@FindBy(css = "#inbox_content_table td:first-child")
	public List<WebElement> enhancedInboxPersonNames;
	
	@FindBy(css = "#inbox_content_table td:nth-of-type(2)")
	public List<WebElement> enhancedInboxPersonNames_assignmentQueues;
	
	@FindBy(css = "#inbox_content_table td:nth-child(5)")
	public List<WebElement> enhancedInboxCaseStatus;
	
	@FindBy(css = "#inbox_content_table td:nth-of-type(6)")
	public List<WebElement> enhancedInboxCaseStatus_assignmentQueues;
	
	@FindBy(css = "#inbox_content_table td:nth-child(7)")
	public List<WebElement> enhancedInboxLastSaveDate;
	
	@FindBy(id = "inbox_content_actions")
	public WebElement enhancedInboxAssignmentDropdown;
	
	@FindBy(id = "inbox_content_actionSubmit")
	public WebElement enhancedInboxAssign;
	
	/*
	 * Breadcrumbs
	 */
	
	@FindBy(xpath = "//a[contains(text(),'Case (')]")
	public WebElement breadcrumb_caseLink;

}
