package com.entellitrak_epa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entellitrak_epa.utilities.Driver;

public class DocumentsPage {

	public DocumentsPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	/*
	 * All elements on Documents
	 */
	
	/*
	 * Document section
	 */
	
	@FindBy(id = "document-group")
	public WebElement documentsFieldset;
	
	@FindBy(css = "#document-group legend span")
	public WebElement documentsFieldsetTitle;
	
	public WebElement Documents_documentType;
	
	@FindBy(id = "Documents_file-path")
	public WebElement Documents_file;

	@FindBy(id = "Documents_file-browse")
	public WebElement Documents_fileBrowse;

	@FindBy(xpath = "//a[.='Replace']")
	public WebElement Documents_fileReplace;

	@FindBy(xpath = "//a[.='Remove']")
	public WebElement Documents_fileRemove;
	
	public WebElement previewButton;
	
	@FindBy(xpath = "//span[.='close']/..")
	public WebElement docPreviewClose;
	
	public WebElement Documents_comments;
	
	public WebElement saveButton;

	public WebElement deleteButton;

}
