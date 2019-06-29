package com.project.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.utilities.Driver;

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
	
	@FindBy(xpath = "(//a[.='Done'])[1]")
	public WebElement adjGuidelines_done;
	
	@FindBy(xpath = "(//a[.='Add'])[1]")
	public WebElement suitabilityGuidelines_add;
	
	@FindBy(xpath = "(//a[.='Done'])[2]")
	public WebElement suitabilityGuidelines_done;
	
	@FindBy(xpath = "(//a[.='Edit'])[1]")
	public WebElement adjGuidelines_edit;
	
	@FindBy(xpath = "(//a[.='Edit'])[2]")
	public WebElement suitabilityGuidelines_edit;
	
	@FindAll({
		@FindBy(xpath = "//a[.='Edit']")
	})
	public List<WebElement> editGuidelinesButtons;
	
	public WebElement Adjudication_adjudicationRecommendation;
	
	public WebElement Adjudication_adjudicationRecommendationDate;
	
	public WebElement Adjudication_adjudicationSummary;
	
	public WebElement Adjudication_readyForFinalAdjudication_yes;
	
	public WebElement Adjudication_readyForFinalAdjudication_no;
	
	public WebElement Adjudication_finalAdjudicator;
	
	@FindAll({
		@FindBy(xpath = "//span[@id='Adjudication_seriousnessIssueCode_display']/preceding-sibling::input"),
		@FindBy(xpath = "//span[@id='Adjudication_adjudicationRecommendationDate_display']/preceding-sibling::input"),
		@FindBy(xpath = "//span[@id='Adjudication_adjudicationRecommendation_display']/preceding-sibling::input"),
		@FindBy(xpath = "//span[@id='Adjudication_adjudicationSummary_display']/preceding-sibling::input")
	})
	public List<WebElement> adjRecommendationReadOnlyFields;
	
	@FindBy(id = "Adjudication_readyForFinalAdjudication-container")
	public WebElement Adjudication_readyForFinalAdjudication_container;
	
	@FindBy(id = "Adjudication_finalAdjudicator-container")
	public WebElement Adjudication_finalAdjudicator_container;
	
	/*
	 * Adjudicative Guidelines
	 */
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_1")
	public WebElement adjGuidelines_alcoholConsumption;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_2")
	public WebElement adjGuidelines_allegianceToUSA;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_3")
	public WebElement adjGuidelines_criminalConduct;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_4")
	public WebElement adjGuidelines_drugInvolvement;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_5")
	public WebElement adjGuidelines_financialConsiderations;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_6")
	public WebElement adjGuidelines_foreignInfluence;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_7")
	public WebElement adjGuidelines_foreignPreference;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_8")
	public WebElement adjGuidelines_handlingProtectedInfo;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_9")
	public WebElement adjGuidelines_outsideActivities;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_10")
	public WebElement adjGuidelines_personalConduct;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_11")
	public WebElement adjGuidelines_psychologicalConditions;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_12")
	public WebElement adjGuidelines_sexualBehavior;
	
	@FindBy(id = "Adjudication_adjudicativeGuideliness_13")
	public WebElement adjGuidelines_userOfInfoTechSystems;
	
	/*
	 * Adjudicative Guidelines display
	 */
	
	@FindBy(css = "#Adjudication_adjudicativeGuideliness_display li")
	public List<WebElement> adjGuidelinesSelectedDisplay;
	
	/*
	 * Suitability Guidelines
	 */
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_1")
	public WebElement suitabilityGuidelines_alcoholAbuse;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_2")
	public WebElement suitabilityGuidelines_allegianceToUSA;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_3")
	public WebElement suitabilityGuidelines_criminal;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_4")
	public WebElement suitabilityGuidelines_drugUse;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_5")
	public WebElement suitabilityGuidelines_employment;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_6")
	public WebElement suitabilityGuidelines_falseStatement;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_7")
	public WebElement suitabilityGuidelines_financialConsideration;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_8")
	public WebElement suitabilityGuidelines_personalConduct;
	
	@FindBy(id = "Adjudication_suitabilityGuideliness_9")
	public WebElement suitabilityGuidelines_refusalToProvideTestimony;
	
	/*
	 * Suitability Guidelines display
	 */
	
	@FindBy(css = "#Adjudication_suitabilityGuideliness_display li")
	public List<WebElement> suitabilityGuidelinesSelectedDisplay;
	
	public WebElement saveButton;
	
	@FindBy(css = "#messagesList label")
	public List<WebElement> fieldsRequiredMessages;
	
	@FindBy(css = "#messagesList span")
	public List<WebElement> adjListingSuccessfulMessages;	
	
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
	
	public List<WebElement> adjGuidelines() {
		List<WebElement> adjGuidelines = new ArrayList<>();
		adjGuidelines.add(adjGuidelines_alcoholConsumption);
		adjGuidelines.add(adjGuidelines_allegianceToUSA);
		adjGuidelines.add(adjGuidelines_criminalConduct);
		adjGuidelines.add(adjGuidelines_drugInvolvement);
		adjGuidelines.add(adjGuidelines_financialConsiderations);
		adjGuidelines.add(adjGuidelines_foreignInfluence);
		adjGuidelines.add(adjGuidelines_foreignPreference);
		adjGuidelines.add(adjGuidelines_handlingProtectedInfo);
		adjGuidelines.add(adjGuidelines_outsideActivities);
		adjGuidelines.add(adjGuidelines_personalConduct);
		adjGuidelines.add(adjGuidelines_psychologicalConditions);
		adjGuidelines.add(adjGuidelines_sexualBehavior);
		adjGuidelines.add(adjGuidelines_userOfInfoTechSystems);
		return adjGuidelines;
	}
	
	public List<WebElement> suitabilityGuidelines() {
		List<WebElement> suitabilityGuidelines = new ArrayList<>();
		suitabilityGuidelines.add(suitabilityGuidelines_alcoholAbuse);
		suitabilityGuidelines.add(suitabilityGuidelines_allegianceToUSA);
		suitabilityGuidelines.add(suitabilityGuidelines_criminal);
		suitabilityGuidelines.add(suitabilityGuidelines_drugUse);
		suitabilityGuidelines.add(suitabilityGuidelines_employment);
		suitabilityGuidelines.add(suitabilityGuidelines_falseStatement);
		suitabilityGuidelines.add(suitabilityGuidelines_financialConsideration);
		suitabilityGuidelines.add(suitabilityGuidelines_personalConduct);
		suitabilityGuidelines.add(suitabilityGuidelines_refusalToProvideTestimony);
		return suitabilityGuidelines;
	}
	
	
	public WebElement sendToFinalAdjudicationButton;
	
	public WebElement requestCorrectionButton;
	
	public WebElement Adjudication_returnToAdjudicator_yes;
	
	public WebElement Adjudication_returnToAdjudicator_no;
	
	public WebElement Adjudication_adjudicationDecision;
	
	public WebElement Adjudication_adjudicationDecisionDate;
	
	public WebElement Adjudication_comments;
	
	public WebElement Adjudication_reasonForReturn;
	
	
	
	
	
}
