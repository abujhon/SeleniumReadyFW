/**
 * 
 */
package com.entellitrak_epa.utilities;

import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;





/**
 * @author Musa
 *
 */
public class BrowserUtils {
	
	WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 15);
	JavascriptExecutor js = (JavascriptExecutor) Driver.getInstance();
	Actions action = new Actions(Driver.getInstance());
	

	
	
	public void scrollDown() {
		js.executeScript("javascript:window.scrollBy(0,400)");
	}
	
	public void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	// action class util move to the element
		public void moveTo(WebElement element) {
			action.moveToElement(element).perform();
		}
		
		// hover over an element
		public void hover(WebElement element) {
			action.moveToElement(element).perform();
		}

		// javaScript to click type = hidden element
		public void clickToHiddenElement(WebElement element) {
			js.executeScript("arguments[0].click();", element);
		}
		
		
		// waitForElement

		public void waitForElementToBeClickable(WebElement element) {

			wait.until(ExpectedConditions.elementToBeClickable(element));

		}

		public void waitForFieldsToBePopulated(WebElement element) {
			wait.until(ExpectedConditions.invisibilityOf(element));
		}

		public void waitframeToBeAvailableAndSwitchToIt(WebElement element) {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		}

		public void waitForElementToBeVisible(WebElement element) {
			wait.until(ExpectedConditions.visibilityOf(element));
		}

		public void waitForFormToLoad(WebElement element) {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		
		
		public void switchTabs(String title) {

			Set<String> tabs = Driver.getInstance().getWindowHandles();
			for (String windowHandle : tabs) {
				Driver.getInstance().switchTo().window(windowHandle);
				if (Driver.getInstance().getTitle().contains(title)) {

					break;

				}

			}

			// Driver.getInstance().switchTo().defaultContent();

			// when we want to switch back to default content, just use this method in the
			// step definition

		}
		
		/*
		 * .isDisplayed() exists to tell you whether the element, which has already been
		 * located, is visible on the page; i.e. whether its width and height are
		 * greater than zero, it isn't hidden by CSS, etc. If the element is present on
		 * the page, but has style="display: none;" then isDisplayed() will return
		 * false.
		 */
		
		public boolean isPresent(WebElement element) {
			try {
				return element.isDisplayed();
			} catch (Exception e) {
				return false;
			}
		}
	
}
