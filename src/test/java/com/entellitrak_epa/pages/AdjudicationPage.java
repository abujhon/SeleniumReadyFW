package com.entellitrak_epa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

public class AdjudicationPage {

	public AdjudicationPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	/*
	 * Adj Listing Table
	 */
	
	@FindBy(css = ".grid")
	public WebElement adjListingTable;
	
	@FindBy(css = ".grid tbody a")
	public WebElement adjListingTable_rowLink;
	
	/*
	 * Adjudication Form
	 */
	
	public WebElement Adjudication_seriousnessIssueCode;
	
	@FindBy(xpath = "(//a[.='Add'])[1]")
	public WebElement adjGuidelines_add;
	
	@FindBy(xpath = "(//a[.='Add'])[2]")
	public WebElement suitabilityGuidelines_add;
	
	public WebElement Adjudication_adjudicationRecommendation;
	
	public WebElement Adjudication_adjudicationRecommendationDate;
	
	public WebElement Adjudication_adjudicationSummary;
	
	public WebElement Adjudication_readyForFinalAdjudication_yes;
	
	public WebElement Adjudication_readyForFinalAdjudication_no;
	
	public WebElement saveButton;
	
	public List<WebElement> adjFields_adjState() {
		List<WebElement> adjFields_adjState = new ArrayList<>();
		adjFields_adjState.add(Adjudication_seriousnessIssueCode);
		adjFields_adjState.add(adjGuidelines_add);
		adjFields_adjState.add(suitabilityGuidelines_add);
		adjFields_adjState.add(Adjudication_adjudicationRecommendation);
		adjFields_adjState.add(Adjudication_adjudicationRecommendationDate);
		adjFields_adjState.add(Adjudication_adjudicationSummary);
		adjFields_adjState.add(Adjudication_readyForFinalAdjudication_yes);
		adjFields_adjState.add(Adjudication_readyForFinalAdjudication_no);
		return adjFields_adjState;
	}
}
