package com.collpoll.web.test.home;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.collpoll.web.page.home.RequestADemoPage;
import com.collpoll.web.test.CollpollBaseTest;
import static com.classplusapp.util.Constants.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("CollPoll")
@Feature("Home")
public class RequestADemoTest extends CollpollBaseTest{
	
	private WebDriver driver = null;
	private RequestADemoPage requestADemoPage = null;

	private static final Logger logger = Logger.getLogger(RequestADemoTest.class.getName());

	@BeforeClass
	@Parameters({ "browser" })
	public void initCollPollLogin(String browser) {
		logger.info("Starting of initCollPollLogin method");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.collpoll.com/team.html");
		
		this.requestADemoPage = new RequestADemoPage(driver);

		logger.info("Ending of initCollPollLogin method");
	}

	@Test(priority = 1, description = "Verify Home page")
	@Description("Test Description:Verify Home page")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Verify Home page")
	public void testHomePage() {
		logger.info("Starting of testHomePage method");
		
		try {
		    String homeLabel = this.requestADemoPage.getHomeLabel();
		    Assert.assertEquals(homeLabel,expectedAssertionsProp.getProperty(HOME_LABEL));
			 
		    String productLabel = this.requestADemoPage.getProductLabel();
		    Assert.assertEquals(productLabel,expectedAssertionsProp.getProperty(PRODUCT_LABEL));
		    
		    String peopleLabel = this.requestADemoPage.getPeopleLabel();
		    Assert.assertEquals(peopleLabel,expectedAssertionsProp.getProperty(PEOPLE_LABEL));
		    
		    String clientsLabel = this.requestADemoPage.getClientsLabel();
		    Assert.assertEquals(clientsLabel,expectedAssertionsProp.getProperty(CLIENTS_LABEL));
		    
		    String careerLabel = this.requestADemoPage.getCareerLabel();
		    Assert.assertEquals(careerLabel,expectedAssertionsProp.getProperty(CAREER_LABEL));
		    
		    String pressLabel = this.requestADemoPage.getPressLabel();
		    Assert.assertEquals(pressLabel,expectedAssertionsProp.getProperty(PRESS_LABEL));
		    
		    String blogLabel = this.requestADemoPage.getBlogLabel();
		    Assert.assertEquals(blogLabel,expectedAssertionsProp.getProperty(BLOG_LABEL));
		    
		    String contactUsLabel = this.requestADemoPage.getContactUsLabel();
		    Assert.assertEquals(contactUsLabel,expectedAssertionsProp.getProperty(CONTACT_US_LABEL));

		    this.requestADemoPage.clickOnHomeTab();
		} catch (Exception e) {
			Assert.fail("Exception occured while testing Home page:" + e.getMessage());
			logger.error("Error occured while testing Home page:", e);
		}

		logger.info("Ending of testHomePage method");
	}
	
	@Test(priority = 2, description = "Verify Request a demo")
	@Description("Test Description:Verify Request a demo")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Verify Request a demo")
	public void testRequestADemo() {
		logger.info("Starting of testRequestADemo method");
		
		try {
            this.requestADemoPage.clickOnRequestADemoButton();
            this.requestADemoPage.setContactName(testDataProp.getProperty(CONTACT_NAME));
            this.requestADemoPage.setContactNumber(testDataProp.getProperty(CONTACT_NUMBER));
            this.requestADemoPage.setContactEmail(testDataProp.getProperty(CONTACT_EMAIL));
            this.requestADemoPage.setContactInstitute(testDataProp.getProperty(CONTACT_INSTITUTE));
            
		} catch (Exception e) {
			Assert.fail("Exception occured while testing Request a demo:" + e.getMessage());
			logger.error("Error occured while testing Request a demo:", e);
		}

		logger.info("Ending of testRequestADemo method");
	}
	
	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (driver != null) {
				driver.quit();
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}
