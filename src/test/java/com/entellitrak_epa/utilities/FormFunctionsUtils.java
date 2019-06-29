/**
 * 
 */
package com.entellitrak_epa.utilities;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;
import com.project.pages.BasePage;
import com.project.pages.SignOnPage;

/**
 * @author Musa
 *
 */
public class FormFunctionsUtils {
	
	
	/*
	 * Purpose of this utility class is to store methods that allows us to fill out
	 * forms throughout the EPA application
	 */

	Faker faker = new Faker();
	Random random = new Random();
	BasePage basePg = new BasePage();
	BrowserUtils browserUtils = new BrowserUtils();
	SignOnPage signOnPg = new SignOnPage();
	
	public void signOut() {
//		basePg.signOut.click();
		Driver.getInstance().get("http://52.3.187.122:8080/epa-bi-test/system.logout.do");
	    browserUtils.waitForElementToBeVisible(basePg.loginLink);
	    basePg.loginLink.click();
	    browserUtils.waitForElementToBeVisible(signOnPg.username);
	    assertTrue(signOnPg.username.isDisplayed());
	}
	
	public String randomFirstName() {
		String randomFN = faker.name().firstName();
		return randomFN;
	}
	
	public String randomMiddleName() {
		//String randomMN = faker.name().nameWithMiddle();
		String randomMN = faker.name().firstName();
				return randomMN;
	}
	
	public String randomLastName() {
		String randomLN = faker.name().lastName();
		return randomLN;
	}
	
	public String randomFullName() {
		String randomFN = faker.name().fullName();
		return randomFN;
	}
	
	public String generateSSN() {

		String randomSSN = "";
		Long[] ssnArray;

		ssnArray = random.longs(9, 0, 10).boxed().toArray(Long[]::new);

		return randomSSN = Arrays.toString(ssnArray).replaceAll("\\W", "").trim();

	}
	
	// updated this method as a random number generator for overall use in any length
		public String generateRandomNumber(int lengthOfNumber) {

			String randomAccessID = "";
			Long[] accessIDArray;
			
			accessIDArray = random.longs(lengthOfNumber, 0, 10).boxed().toArray(Long[]::new);

			return randomAccessID = Arrays.toString(accessIDArray).replaceAll("\\W", "").trim();

		}
		
		public String generatePhoneNumber() {

			/*
			 * This method generates and returns a valid number according to the National
			 * Phone Number conventions
			 */

			String randomPhoneNumber = "";
			Long[] phoneNumArrayPt2;
			Long[] phoneNumArrayPt3;
			int[] areaCodeNumArray = { 703, 202, 571, 301 };

			int rnd = new Random().nextInt(areaCodeNumArray.length);

			phoneNumArrayPt2 = random.longs(3, 2, 10).boxed().toArray(Long[]::new);
			phoneNumArrayPt3 = random.longs(4, 0, 10).boxed().toArray(Long[]::new);

			return randomPhoneNumber = areaCodeNumArray[rnd] + Arrays.toString(phoneNumArrayPt2).replaceAll("\\W", "").trim()
					+ Arrays.toString(phoneNumArrayPt3).replaceAll("\\W", "").trim();

		}
		
		public String currentDate() {

			LocalDate today = LocalDate.now();
			return today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).trim();
		}
		
		public String currentDateoracle() {

			LocalDate today = LocalDate.now();
			DateTimeFormatter orac = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yy")
					.toFormatter();
			return today.format(orac);
		}
	
		public String generateZIP() {

			String randomZipNumber = "";
			Long[] randomZipNumberArray;
			
			randomZipNumberArray = random.longs(5, 0, 10).boxed().toArray(Long[]::new);
			return randomZipNumber = Arrays.toString(randomZipNumberArray).replaceAll("\\W", "").trim();

		}
		
		public String generateContractNumber() {

			String randomContractNumber = "";
			Long[] randomContractNumberArray;

			randomContractNumberArray = random.longs(9, 0, 10).boxed().toArray(Long[]::new);
			return randomContractNumber = Arrays.toString(randomContractNumberArray).replaceAll("\\W", "").trim();

		}
		
		public void randomDropDownValue(WebElement dropdown) {

			Select elementDropDown = new Select(dropdown);

			List<WebElement> optionsList = elementDropDown.getOptions();
			List<String> dropDownValues = new ArrayList<>();
			String textValues = "";

			for (WebElement options : optionsList) {
				textValues = options.getText();
				dropDownValues.add(textValues);
			}

			int randomNumber = random.nextInt(dropDownValues.size());

			while (randomNumber == 0) {
				randomNumber = random.nextInt(dropDownValues.size());
			}

			String randomValue = dropDownValues.get(randomNumber).trim();

			// if (randomValue.equals("")) {
			// randomDropDownValue(dropdown);
			// } else {
			// elementDropDown.selectByVisibleText(randomValue);
			// }

			if (!randomValue.equals("")) {
				elementDropDown.selectByVisibleText(randomValue);
			}

		}
		
		public void dropDown(WebElement element, String optionToBeSelected) {

			Select elementDropDown = new Select(element);
			elementDropDown.selectByVisibleText(optionToBeSelected);

		}

		public void partialDropDown(WebElement element, String partialText) {
			int dropDownIndex = -1;
			Select dropDown = new Select(element);
			List<WebElement> dropDownOptions = dropDown.getOptions();
			try {
				dropDown.selectByVisibleText(partialText);
			} catch (Exception e) {
				for (WebElement options : dropDownOptions) {
					dropDownIndex++;
					if (options.getText().trim().contains(partialText)) {
						dropDown.selectByIndex(dropDownIndex);
						break;
					}
				}
			}
		}
		
		public Select dropDownElement(WebElement dropDownElement) {
			Select dropDown = new Select(dropDownElement);
			return dropDown;
		}
		
		/**
		 * @param element
		 *            This method accepts an integer. The integer parameter represents
		 *            the number of days the user would like to add to the current date.
		 */
		public String futureDate(int numOfDays) {
			LocalDate futureDay = LocalDate.now().plusDays(numOfDays);
			return futureDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).trim();
		}

		public String pastDate(int numOfDays) {
			LocalDate pastDay = LocalDate.now().minusDays(numOfDays);
			return pastDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).trim();
		}
		
		
		
}
