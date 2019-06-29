/**
 * 
 */
package com.project.utilities;

import com.project.pages.SignOnPage;

/**
 * @author Musa
 *
 */
public class Project_constants {
	
	/*
	 * Any constant variables that correspond to the EPA project, such as url, will be
	 * stored here.
	 */
	
	SignOnPage signIn = new SignOnPage();

	public static final String URL = "http://52.3.187.122:8080/epa-bi-test/login.request.do";
	public static final String emailURL = "";
	public static final String UATurl = "";
	public static final String internalProdURL = "";
	public static final String prodURL = "";
	
	
	public void signIn(String epaUsername, String epaPassword) {
		signIn.username.sendKeys(epaUsername);
		signIn.password.sendKeys(epaPassword);
		signIn.submit.click();
	}

}
