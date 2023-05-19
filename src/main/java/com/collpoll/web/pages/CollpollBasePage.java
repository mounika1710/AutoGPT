package com.collpoll.web.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CollpollBasePage {

	protected WebDriver driver = null;
	protected Actions action = null;
	private static final Logger logger = Logger.getLogger(CollpollBasePage.class.getName());

	protected static String uniqueBatchName = null;
	public static String TEST_FILE_PATH = null;

	public CollpollBasePage(WebDriver driver) {
		this.driver = driver;

		if (TEST_FILE_PATH == null) {

			TEST_FILE_PATH = getTestFilePath();

			logger.debug("In Constructor " + TEST_FILE_PATH);
		}

		PageFactory.initElements(driver, this);
	}

	public static String randomNumber(int digits) {
		logger.info("Starting of randomNumber method");
		logger.info("Ending of randomNumber method");

		return String.valueOf(RandomStringUtils.randomNumeric(digits));
	}

	public String getUniqueName(String value) {
		logger.info("Starting of getUniqueName method");
		logger.info("Ending of getUniqueName method");

		return value + randomNumber(2);
	}

	protected void selectDropdown(String name, String value) {
		logger.info("Starting of selectDropdown method");

		Select conditions = new Select(driver.findElement(By.name(name)));
		conditions.selectByValue(value);

		logger.info("Ending of selectDropdown method");
	}

	public void selectDropdown(String Array) {

	}

	public String getTestFilePath() {
		logger.info("Starting of getTestFilePath method");

		String path = "src/main/resources";
		File file = new File(path);

		logger.info("Ending of getTestFilePath method");

		return file.getAbsolutePath();
	}

	public void clickOnWebElement(WebElement webelement) {
		logger.info("Starting of clickOnWebElement method");

		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		jsExec.executeScript("arguments[0].click();", webelement);

		logger.info("Ending of clickOnWebElement method");
	}

	public void scrollIntoView(WebElement element) {
		logger.info("Starting of scrollIntoView method");

		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		jsExec.executeScript("arguments[0].scrollIntoView(true);", element);

		logger.info("Ending of scrollIntoView method");
	}

	public void switchToNewWindow() {
		logger.info("Starting of switchToNewWindow method");

		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));

		logger.info("Ending of switchToNewWindow method");
	}

	public void closeWindow() {
		logger.info("Starting of closeWindow method");

		driver.close();
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));

		logger.info("Ending of closeWindow method");
	}

	public void waitForElementVisibilty(WebElement element) {
		logger.info("Starting of waitForElementVisibilty method");

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));

		logger.info("Ending of waitForElementVisibilty method");
	}

	public void scrollDown(int scroll) {
		logger.info("Starting of scrollDown method");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, " + scroll + ")");

		logger.info("Ending of scrollDown method");
	}

	public void scrollDown(int scroll, WebElement element) {
		logger.info("Starting of scrollDown method");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);

		logger.info("Ending of scrollDown method");
	}

	public void refresh() {
		logger.info("Starting of refresh method");

		driver.navigate().refresh();

		logger.info("Ending of refresh method");
	}

	public void implicitWait() {
		logger.info("Starting of implicitWait Method");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		logger.info("Ending of implicitWait Method");
	}

	public void explicitWait(List<WebElement> categoryOptions) {
		logger.info("Starting of explicitWait method");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElements(categoryOptions));

		logger.info("Ending of explicitWait method");
	}

	public void explicitWait(WebElement categoryOptions) {
		logger.info("Staritng of explicitWait method");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(categoryOptions));

		logger.info("Ending of explicitWait method");
	}

	public void pickFromWebElemetList(List<WebElement> webElements, String containsText) {
		logger.info("Staritng of pickFromWebElemetList method");

		for (WebElement webElement : webElements) {
			if (webElement.getText().contains(containsText)) {
				this.clickOnWebElement(webElement);
				break;
			}
		}

		logger.info("Ending of pickFromWebElemetList method");
	}

	public void pickFromWebElemetList(List<WebElement> webElements, List<WebElement> textWebElements,
			String containsText) {
		logger.info("Staritng of pickFromWebElemetList method");

		WebElement webElement = null;
		WebElement textWebElement = null;
		Object[] webElementsArray = webElements.toArray();
		Object[] xPathArray = textWebElements.toArray();

		for (int i = 0; i < webElements.size(); i++) {
			webElement = (WebElement) webElementsArray[i];
			textWebElement = (WebElement) xPathArray[i];
			if (textWebElement.getText().contains(containsText)) {
				this.clickOnWebElement(webElement);
				break;
			}
		}

		logger.info("Ending of pickFromWebElemetList method");
	}

	public void sendKeysUsingJavaScript(String value, WebElement element) {
		logger.info("Staritng of sendKeysUsingJavaScript method");

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value='" + value + "';", element);

		logger.info("Ending of sendKeysUsingJavaScript method");
	}

	public void dragAndSort(List<WebElement> webElementList, Integer targetIndex, Integer destIndex) {
		logger.info("Staritng of dragAndSort method");

		action = new Actions(driver);
		WebElement target = webElementList.get(targetIndex);
		WebElement dest = webElementList.get(destIndex);
		action.click(target).clickAndHold().moveToElement(dest).moveByOffset(0,10).release().build().perform();

		logger.info("Ending of dragAndSort method");
	}

	public void uploadFile(String filepath) {
		logger.info("Staritng of uploadFile method");

		Robot robot = null;
		try {
			robot = new Robot();

			StringSelection stringSelection = new StringSelection(filepath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);

			robot.delay(500);
			robot.keyPress(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);

			robot.delay(500);

			robot.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (AWTException e2) {
			e2.printStackTrace();
		}

		logger.info("Ending of uploadFile method");
	}

	public void mouseHover(WebElement element) {
		logger.info("Staritng of mouseHover method");

		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		// actions.moveToElement(element).perform();

		logger.info("Ending of mouseHover method");
	}

	public void waitForElementVisibility(WebElement element) {
		logger.info("Staritng of waitForElementVisibility method");

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));

		logger.info("Ending of waitForElementVisibility method");
	}

	public void clickOutside() {
		logger.info("Starting of clickOutside method");

		Actions action = new Actions(driver);
		action.moveByOffset(0, 0).click().build().perform();

		logger.info("Ending of clickOutside method");
	}
	
	protected void closeOSWindow() {
		logger.info("Starting of closeOSWindow method");
		
		Robot robot = null;
		try {
			if (System.getProperty("os.name").contains("Windows 10")) {
				robot = new Robot();
				for (int i = 0; i < 3; i++) {
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);

				}
				robot.keyPress(KeyEvent.VK_ENTER);

				robot.delay(500);

				robot.keyRelease(KeyEvent.VK_ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Ending of closeOSWindow method");
	}
}
