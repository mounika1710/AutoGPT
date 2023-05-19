package com.collpoll.web.page.home;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.collpoll.web.pages.CollpollBasePage;

public class RequestADemoPage extends CollpollBasePage{
	
	@FindBy(xpath = "//a[@href='index.html']/parent::li")
	private WebElement lblHome;
	
	@FindBy(xpath = "//a[@class='cloud-btn']/parent::div")
	private WebElement btnRequestADemo;
	
	@FindBy(xpath = "//a[@class='dropbtn']/parent::div/parent::li")
	private WebElement lblProduct;
	
	@FindBy(xpath = "//a[@href='team.html']")
	private WebElement lblPeople;
	
	@FindBy(xpath = "//a[@href='clients.html']")
	private WebElement lblClients;
	
	@FindBy(xpath = "//a[@href='career.html']")
	private WebElement lblCareer;
	
	@FindBy(xpath = "//a[@href='press.html']")
	private WebElement lblPress;
	
	@FindBy(xpath = "//a[@href='blog']")
	private WebElement lblBlog;
	
	@FindBy(xpath = "//a[@href='contact.html']")
	private WebElement lblContactUs;
	
	@FindBy(id = "contactName")
	private WebElement txtContactName;
	
	@FindBy(id = "contactTel")
	private WebElement txtContactNumber;
	
	@FindBy(id = "contactEmail")
	private WebElement txtContactEmail;
	
	@FindBy(id = "contactInstitution")
	private WebElement txtContactInstitution;

	private static final Logger logger = Logger.getLogger(RequestADemoPage.class.getName());

	public RequestADemoPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of RequestADemoPage method");

		PageFactory.initElements(driver, this);
		
		logger.info("Ending of RequestADemoPage method");
	}

	public void clickOnHomeTab() {
		logger.info("Starting of clickOnHomeTab method");

		try {
			this.lblHome.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblHome);
		}
		
		logger.info("Ending of clickOnHomeTab method");
	}
	
	public void clickOnRequestADemoButton() {
		logger.info("Starting of clickOnRequestADemoButton method");

		this.scrollDown(-200);
		this.btnRequestADemo.click();

		logger.info("Ending of clickOnRequestADemoButton method");
	}

	public String getHomeLabel() {
		logger.info("Starting of getHomeLabel method");
		logger.info("Ending of getHomeLabel method");

		return this.lblHome.getText();
	}

	public String getProductLabel() {
		logger.info("Starting of getProductLabel method");
		logger.info("Ending of getProductLabel method");

		return this.lblProduct.getText();
	}
	
	public String getPeopleLabel() {
		logger.info("Starting of getPeopleLabel method");
		logger.info("Ending of getPeopleLabel method");

		return this.lblPeople.getText();
	}
	
	public String getClientsLabel() {
		logger.info("Starting of getClientsLabel method");
		logger.info("Ending of getClientsLabel method");

		return this.lblClients.getText();
	}
	
	public String getCareerLabel() {
		logger.info("Starting of getClientsLabel method");
		logger.info("Ending of getClientsLabel method");

		return this.lblCareer.getText();
	}
	
	public String getPressLabel() {
		logger.info("Starting of getClientsLabel method");
		logger.info("Ending of getClientsLabel method");

		return this.lblPress.getText();
	}
	
	public String getBlogLabel() {
		logger.info("Starting of getBlogLabel method");
		logger.info("Ending of getBlogLabel method");

		return this.lblBlog.getText();
	}
	
	public String getContactUsLabel() {
		logger.info("Starting of getContactUsLabel method");
		logger.info("Ending of getContactUsLabel method");

		return this.lblContactUs.getText();
	}
	
	public void setContactName(String strName) {
		logger.info("Starting of setContactName method");
		
		this.txtContactName.click();
        this.txtContactName.sendKeys(strName);
		
		logger.info("Ending of setContactName method");
	}
	
	public void setContactNumber(String strNumber) {
		logger.info("Starting of setContactNumber method");
		
		this.txtContactNumber.click();
        this.txtContactNumber.sendKeys(strNumber);
		
		logger.info("Ending of setContactNumber method");
	}
	
	public void setContactEmail(String strEmail) {
		logger.info("Starting of setContactEmail method");
		
		this.txtContactEmail.click();
        this.txtContactEmail.sendKeys(strEmail);
		
		logger.info("Ending of setContactEmail method");
	}
	
	public void setContactInstitute(String strInstitue) {
		logger.info("Starting of setContactInstitute method");
		
		this.txtContactInstitution.click();
        this.txtContactInstitution.sendKeys(strInstitue);
		
		logger.info("Ending of setContactInstitute method");
	}
	
}
