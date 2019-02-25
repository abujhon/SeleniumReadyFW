/**
 * 
 */
package com.entellitrak_epa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

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
	
	

}
