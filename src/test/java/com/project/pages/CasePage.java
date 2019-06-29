package com.project.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.utilities.Driver;

/**
 * @author Edgar Servellon
 */
public class CasePage {

	public CasePage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	/*
	 * All elements on Case
	 */
	
	public WebElement trackingForm;
	
	@FindBy(css = "#sideList li:nth-child(5) a")
	public WebElement caseAdjudicationChildLink;
	
	/*
	 * Assignment Information
	 */
	
	@FindBy(id = "assignment-information-group")
	public WebElement assignmentInformationFieldset;
	
	@FindBy(css = "#assignment-information-group legend span")
	public WebElement assignmentInformationFieldsetTitle;
	
	public WebElement Case_assignedIntakeInitiator_display;
	
	@FindBy(id = "Case_dateIntakeInitiatorAssigned_display")
	public WebElement dateIntakeInitiatorAssigned;

	public WebElement Case_assignedSpecialist;
	
	public WebElement Case_assignedSpecialist_display;
	
	@FindBy(id = "Case_dateSpecialistAssigned_display")
	public WebElement dateSpecialistAssigned;
	
	public WebElement Case_assignedFsemAdjudicator;
	
	@FindBy(id = "Case_fsemAdjudicatorAssignedDate_display")
	public WebElement dateFsemAdjudicatorAssigned;
	
	public WebElement Case_assignedAdjudicator;
	
	@FindBy(id = "Case_dateAdjudicatorAssigned_display")
	public WebElement dateAdjudicatorAssigned;
	
	/*
	 * Applicant Information
	 */
	
	@FindBy(id = "applicant-information-group")
	public WebElement applicantInformationFieldset;
	
	@FindBy(css = "#applicant-information-group legend span")
	public WebElement applicantInformationFieldsetTitle;
	
	public WebElement Case_applicantType_display;
	
	public WebElement Case_vip_yes;
	
	public WebElement Case_vip_no;
	
	public WebElement Case_applicantTenureLessThan6Months_yes;
	
	public WebElement Case_applicantTenureLessThan6Months_no;
	
	public WebElement Case_entranceOnDutyDate;
	
	public WebElement Case_endDate;
	
	public WebElement Case_programOfficeAndRegion;
	
	public WebElement Case_san;
	
	public WebElement Case_dutyLocation;
	
	public WebElement Case_dutyRegion;
	
	public WebElement Case_buildingName;
	
	public WebElement Case_logicalAccessOrEmailAddressRequired_yes;
	
	public WebElement Case_logicalAccessOrEmailAddressRequired_no;
	
	public WebElement Case_requestUsaccessBadge_yes;
	
	public WebElement Case_requestUsaccessBadge_no;
	
	public WebElement Case_workNumber;
	
	public WebElement Case_terminationDate;
	
	/*
	 * Position Designation
	 */
	
	@FindBy(id = "position-designation-group")
	public WebElement positionDesignationFieldset;
	
	@FindBy(css = "#position-designation-group legend span")
	public WebElement positionDesignationFieldsetTitle;
	
	public WebElement Case_caseType;
	
	public WebElement Case_positionTitle;
	
	public WebElement Case_sensitivityLevel;
	
	public WebElement Case_riskLevel;
	
	public WebElement Case_investigationType;
	
	public WebElement Case_designationDate;
	
	public WebElement Case_pivCardExpirationDate;
	
	/*
	 * EQIP Information
	 */
	
	@FindBy(id = "eqip-information-group")
	public WebElement eqipInformationFieldset;
	
	@FindBy(css = "#eqip-information-group legend span")
	public WebElement eqipInformationFieldsetTitle;
	
	public WebElement Case_eqipRequired_yes;
	
	public WebElement Case_eqipRequired_no;
	
	public WebElement Case_eqipInitiationDate;
	
	public WebElement Case_sfForm;
	
	public WebElement Case_eqipSubmissionDueDate;
	
	public WebElement Case_eqipCompleteDate;
	
	/*
	 * POC Information
	 */
	
	@FindBy(id = "poc-information-group")
	public WebElement pocInformationFieldset;
	
	@FindBy(css = "#poc-information-group legend span")
	public WebElement pocInformationFieldsetTitle;
	
	public WebElement Case_pocFirstName;
	
	public WebElement Case_pocLastName;
	
	public WebElement Case_pocEmailAddress;
	
	public WebElement Case_pocPhoneNumber;
	
	public WebElement Case_alternatePocFirstName;
	
	public WebElement Case_alternatePocLastName;
	
	public WebElement Case_alternatePocEmailAddress;
	
	public WebElement Case_alternatePocPhoneNumber;
	
	public WebElement Case_coOrGoFirstName;
	
	public WebElement Case_coOrGoLastName;
	
	public WebElement Case_coOrGoEmailAddress;
	
	public WebElement Case_coOrGoPhoneNumber;
	
	/*
	 * Action
	 */
	
	@FindBy(id = "action-group")
	public WebElement actionToTakeFieldset;
	
	@FindBy(css = "#action-group legend span")
	public WebElement actionToTakeFieldsetTitle;
	
	@FindBy(id = "Step")
	public WebElement actionToTake;
	
	public WebElement discontinueDialogButton;
	
	public WebElement saveButton;
	
	public WebElement saveAndContinueButton;
	
	/*
	 * Workflow Details
	 */
	
	@FindBy(id = "workflowSummary")
	public WebElement workflowDetailsFieldset;
	
	@FindBy(css = "#workflowSummary legend span")
	public WebElement workflowDetailsFieldsetTitle;
	
	@FindBy(css = "#workflowSummary tbody tr td:first-child")
	public List<WebElement> workflowActions;
	
	@FindBy(css = "#workflowSummary tbody tr td:nth-child(2)")
	public List<WebElement> workflowCreatedBy;
	
	@FindBy(css = "#workflowSummary tbody tr td:nth-child(3)")
	public List<WebElement> workflowCreatedOn;
	
	@FindBy(css = "#workflowSummary tbody tr td:nth-child(4)")
	public List<WebElement> workflowFrom;
	
	@FindBy(css = "#workflowSummary tbody tr td:nth-child(5)")
	public List<WebElement> workflowTo;
	
	/*
	 * Comment Section
	 */
	
	public WebElement addCommentButton;
	
	public WebElement Comment_subject;
	
	public WebElement Comment_body;
	
	@FindBy(xpath = "(//span[.='Add Comment']/..)[2]")
	public WebElement Comment_addComment;
	
	@FindBy(xpath = "//span[.='Cancel']/..")
	public WebElement Comment_cancel;
	
	@FindBy(xpath = "(//span[.='close']/..)[3]")
	public WebElement Comment_close;
	
	@FindBy(id = "commentSummary")
	public WebElement commentsFieldset;
	
	@FindBy(css = "#commentSummary legend span")
	public WebElement commentsFieldsetTitle;
	
	@FindBy(css = "#commentSummary tbody tr td:first-child")
	public List<WebElement> commentsCreatedOn;
	
	@FindBy(css = "#commentSummary tbody tr td:nth-child(2)")
	public List<WebElement> commentsCreatedBy;
	
	@FindBy(css = "#commentSummary tbody tr td:nth-child(3)")
	public List<WebElement> commentsSubject;
	
	@FindBy(css = "#commentSummary tbody tr td:nth-child(4)")
	public List<WebElement> comments_Comment;
	
	/*
	 * Case Documents Section
	 */
	
	@FindBy(id = "documentSummary")
	public WebElement documentSummaryFieldset;
	
	@FindBy(css = "#documentSummary legend span")
	public WebElement documentSummaryFieldsetTitle;
	
	@FindBy(css = "#documentSummary tbody tr a:first-child")
	public List<WebElement> caseDocumentsFileName;
	
	@FindBy(css = "#documentSummary tbody tr td:nth-child(2)")
	public List<WebElement> caseDocumentsDocumentType;
	
	@FindBy(css = "#documentSummary div.formButton")
	public List<WebElement> caseDocumentsPreviewButton;
	
	/*
	 * Case Subway Model
	 */
	
	@FindBy(css = "#displayPanel div:nth-child(5)")
	public WebElement caseSubwayWorkflowStatus;
	
	@FindBy(css = "#displayPanel div:nth-child(4)")
	public WebElement caseSubwayCaseStatus;
	
	/*
	 * Case miscellaneous
	 */
	
	
	/*
	 * Discontinue case popup
	 */
	
	@FindBy(xpath = "//span[contains(text(), 'Discontinue Case')]")
	public WebElement popUpDiscontinueHeader;
	
	@FindBy(xpath = "//span[@class = 'ui-icon ui-icon-alert']/ancestor::p")
	public WebElement popUpContent;
	
	@FindBy(xpath = "//button[contains(text(), 'No')]")
	public WebElement popup_no;
	
	@FindBy(xpath = "//button[contains(text(), 'Yes')]")
	public WebElement popup_yes;
	
	
	/*
	 * CTOs 
	 */
	
	@FindBy(xpath = "//a[contains(text(), 'Record Check')]")
	public WebElement recordCheckCTO;
	
	@FindBy(xpath = "//a[contains(text(), 'Pre-Screening')]")
	public WebElement pre_screeningCTO;
	
	@FindBy(xpath = "//a[contains(text(), 'Investigation')]")
	public WebElement investigationCTO;
	
	@FindBy(xpath = "//a[contains(text(), 'Adjudication')]")
	public WebElement adjudicationCTO;
	
	@FindBy(xpath = "//a[contains(text(), 'Documents')]")
	public WebElement documentsCTO;
	
	@FindBy(xpath = "//a[contains(text(), 'LOI')]")
	public WebElement LOI_CTO;
	
	public WebElement Case_createdOn_display;
	public WebElement Case_createdBy_display;
	public WebElement Case_updatedOn_display;
	public WebElement Case_updatedBy_display;
	
}
