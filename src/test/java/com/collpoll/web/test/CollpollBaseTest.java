package com.collpoll.web.test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CollpollBaseTest {

	protected static String browserDriverPath = null;
	protected WebDriver childDriver = null;

	protected static Map<String, String> chromeDriverMap = new HashMap<String, String>();
	
	private static final Logger logger = Logger.getLogger(CollpollBaseTest.class.getName());

	
	protected String loginURL = null;

	protected static String BatchID = null;

	protected static Properties testDataProp = null;
	protected static Properties expectedAssertionsProp = null;

	protected static final String BASE_DIR = System.getProperty("user.dir");
	protected static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	@BeforeSuite
	@Parameters({ "siteURL" })
	public void initTestData(String siteURL) {

		if (siteURL != null) {
			this.loginURL = siteURL;
		}

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;
			try {
				testDataReader = new FileReader("src/main/resources/testdata.properties");
				assertionsReader = new FileReader("src/main/resources/expectedassertions.properties");

				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					testDataReader.close();
					assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected synchronized WebDriver getWebDriver(String browser) {
		logger.info("Starting of method getWebDriver");

		String osPath = System.getProperty("os.name");
		WebDriver webDriver = null;

		if (osPath.contains("Linux")) {

			if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();

				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				options.addArguments("--no-sandbox");

				webDriver = new FirefoxDriver(options);

			} else if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.setHeadless(true);
				options.addArguments("--headless"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				options.addArguments("--window-size=1920,1080");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
				options.addArguments("--disable-browser-side-navigation"); // del
				options.addArguments("--disable-dev-shm-usage"); // del
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("CHROME");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
				capabilities.setCapability("applicationCacheEnabled", "true");

				webDriver = new ChromeDriver(options);
			}

		} else if (osPath.contains("Mac OS X")) {

			browserDriverPath = "/usr/bin/safaridriver";

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				webDriver = new ChromeDriver();

			} else if (browserDriverPath.contains("safaridriver")) {

				System.setProperty("webdriver.safari.driver", browserDriverPath);
				webDriver = new SafariDriver();

				logger.debug("Safari driver path " + browserDriverPath);
			}

		} else {

			if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				//options.setHeadless(isHeadless());
				// options.addArguments("--no-sandbox");

				webDriver = new ChromeDriver(options);



			} else if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();
				webDriver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("Chromium")) {

				WebDriverManager.chromiumdriver().setup();
				webDriver = new EdgeDriver();

			} else if (browser.equalsIgnoreCase("IEDriverServer")) {

				WebDriverManager.iedriver().setup();
				webDriver = new InternetExplorerDriver();
			}
		}
		
		webDriver.manage().window().maximize();
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		logger.info("**************** Driver Successfully Created *************** " + webDriver.getTitle());
		logger.info("End of method getWebDriver");

		return webDriver;
	}

	public void goToSite(String siteURL, WebDriver driver) throws Exception {
		logger.info("Starting of goToSite method");

		this.childDriver = driver;
		logger.debug("Login URL" + siteURL);
		driver.get(siteURL);

		logger.info("Ending of goToSite method");
	}
	
	public void collpollSiteLogin(String siteURL, WebDriver driver) {
		logger.info("Starting of cmsSiteLogin method");
		
		this.childDriver = driver;
		driver.get(siteURL);

		logger.info("Ending of cmsSiteLogin method");
	}

	public String getChromeDriverVersion(String driverInfo) {
		logger.info("Starting of getChromeDriverVersion method ");

		String tVersion = driverInfo.split("is")[2];
		tVersion = tVersion.split("with")[0];
		tVersion = (tVersion.split("\\.")[0]).trim();

		logger.debug("Chrome browser Version : " + tVersion);
		logger.info("Ending of getChromeDriverVersion method ");

		return tVersion;
	}

	public WebDriver getDriver() {
		logger.info("Starting of WebDriver method ");
		logger.info("Ending of WebDriver method ");

		return childDriver;
	}

}
