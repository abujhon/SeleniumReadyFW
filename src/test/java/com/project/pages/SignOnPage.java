/**
 * 
 */
package com.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

/**
 * @author Musa
 *
 */
public class SignOnPage {

	public SignOnPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	

	public WebElement username;

	public WebElement password;

	public WebElement submit;

	/*
	 * Applicant Sign on elements (Start)
	 */

	public WebElement passwordCurrent;
	
	@FindBy(id = "password")
	public WebElement newPassword;

	public WebElement passwordConfirm;

	@FindBy(css = ".button")
	public WebElement changePassword;
	
}
