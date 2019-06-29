package com.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

/**
 * @author Edgar Servellon
 */
public class PreScreeningPage {

	public PreScreeningPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	/*
	 * All elements on Pre-Screening
	 */
	
	/*
	 * Fingerprint SAC Information
	 */
	
	@FindBy(id = "fingerprint-sac-information-group")
	public WebElement fingerprintSacInformationFieldset;
	
	@FindBy(css = "#fingerprint-sac-information-group legend span")
	public WebElement fingerprintSacInformationFieldsetTitle;
	
	public WebElement PreScreening_sacSubmittedDate;

	public WebElement PreScreening_sacScheduledDate;
	
	public WebElement PreScreening_sacReceivedDate;

	public WebElement PreScreening_sacResults;
	
	/*
	 * FSEM Fingerprint Decision (inside of fingerprint sac information)
	 */
	
	@FindBy(id = "fsem-fingerprint-group")
	public WebElement fsemFingerprinDecisiontFieldset;
	
	@FindBy(css = "#fsem-fingerprint-group legend span")
	public WebElement fsemFingerprinDecisionFieldsetTitle;
	
	public WebElement PreScreening_lessThan3YearsInUs_yes;
	
	public WebElement PreScreening_lessThan3YearsInUs_no;
	
	public WebElement PreScreening_dateOfEntry;
	
	public WebElement PreScreening_sacAdjudication;
	
	public WebElement PreScreening_sacAdjudicationDate;
	
	/*
	 * Pre-Screening Review Decision
	 */
	
	@FindBy(id = "prescreening-review-decision-group")
	public WebElement prescreeningReviewDecisionFieldset;
	
	@FindBy(css = "#prescreening-review-decision-group legend span")
	public WebElement prescreeningReviewDecisionFieldsetTitle;

	public WebElement PreScreening_preScreeningDecision;
	
	public WebElement PreScreening_preScreeningDecisionDate;
	
	public WebElement PreScreening_adjudicator;
	
	public WebElement PreScreening_interimAdjudicativeComments;
	
	public WebElement PreScreening_convictedFelonOrStandAlone_yes;
	
	public WebElement PreScreening_convictedFelonOrStandAlone_no;
	
	public WebElement PreScreening_epaReview;
	
	public WebElement PreScreening_epaReviewComments;
	
	public WebElement PreScreening_finalPreScreeningAdjudicationDecision;
	
	public WebElement PreScreening_finalPreScreeningAdjudicator;
	
	public WebElement PreScreening_skipInvestigation_yes;
	
	public WebElement PreScreening_skipInvestigation_no;
	
	/*
	 * Pre-Screening buttons
	 */
	
	public WebElement saveButton;
	
	public WebElement releaseToNbibButton;
	
	public WebElement unfavorableButton;
	
	public WebElement skipInvestigationButton;
	
	public WebElement overturnDecisionButton;
	
	
}
