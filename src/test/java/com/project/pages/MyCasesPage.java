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
public class MyCasesPage {
	
	public MyCasesPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	
	public WebElement myCasesIframe;
	
	public WebElement eiFrame;
	
	public WebElement inboxes;
	
	public WebElement enhancedInbox;
	
	public WebElement inbox_content_actions;
	
	public WebElement inbox_content_actionSubmit;
	
	@FindBy(xpath = "//th[contains(text(), 'Last Save Date')]")
	public WebElement lastSaveDateSorting;
	
	

}
