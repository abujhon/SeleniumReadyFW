/**
 * 
 */
package com.entellitrak_epa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

/**
 * @author Musa
 *
 */
public class EntelliSQLpage {
	
	
	public EntelliSQLpage() {
	PageFactory.initElements(Driver.getInstance(), this);
	
	}
	
	
	// configuration 
	
	@FindBy(css = ".configuration")
	public WebElement configurationTab;
	
	// entelliSQL
	
	@FindBy(xpath = "//span[contains(text(), 'entelli')]")
	public WebElement exitEntelliSQL;
	
	
	  //New Query button
	 

	@FindBy(css = ".button")
	public WebElement newQueryButton;
	
	
	

	/*
	 * Frames
	 */

	@FindBy(xpath = "//frame[@name='toolbarFrame']")
	public WebElement toolBarFrame;

	@FindBy(xpath = "//frame[@name='editorFrame']")
	public WebElement editorFrame;

	@FindBy(xpath = "//frame[@name='outputFrame']")
	public WebElement resultsFrame;

	/*
	 * Tool bar frame elements
	 */

	@FindBy(css = "#toolbar>li>a")
	public WebElement executeQuery;

	

	/*
	 * SQL Editor frame elements
	 */

	@FindBy(id = "sql")
	public WebElement sqlTextBox;

	/*
	 * Email Queue results
	 */

	@FindBy(xpath = "(//table[@class='grid']/tbody/tr[1]/td[3])")
	public WebElement applicantInviteBody;

	@FindBy(xpath = "(//table[@class='grid']/tbody/tr[1]/td[8])")
	public WebElement applicantEmail;

	@FindBy(xpath = "(//table[@class='grid']/tbody/tr[1]/td[10])")
	public WebElement sentStatus;

	@FindBy(xpath = "(//table[@class='grid']/tbody/tr/td[8])")
	public List<WebElement> allApplicantEmails;
	
	@FindBy(xpath = "//img[contains(@alt, 'Exit')]")
	public WebElement exitFromSQL;
	
}
