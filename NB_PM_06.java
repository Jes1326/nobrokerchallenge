package com.capgemini.tests;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.capgemini.driversetup.SetupDriver;
import com.capgemini.pages.HomePage;
import com.capgemini.pages.Intra_City;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.ExcelReader;
import com.capgemini.utils.Screenshots;

public class NB_PM_06 extends BaseReport {

	WebDriver drv;
	WebDriverWait wait;
	String baseURL;
	HomePage HP;
	Packer_N_Movers PM;
	Intra_City IC;
	String excelPath;
	String SC_Path;
	
	@BeforeClass
	public void setUpClass() {
		excelPath = "src/test/resources/ExcelData/TestData.xlsx";
		baseURL = "https://www.nobroker.in/";
		drv = SetupDriver.getDriver("edge");
		drv.navigate().to(baseURL);
		drv.manage().window().maximize();
		drv.manage().deleteAllCookies();
		drv.navigate().refresh();
		drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(drv, Duration.ofSeconds(10));
		parentTest = extent.createTest(getClass().getSimpleName());
		
		HP = new HomePage(drv);
		PM = new Packer_N_Movers(drv);
		IC = new Intra_City(drv);
	}
	
	@Test
    public void LoginIn() throws Exception {
        String phonenum = ExcelReader.getData(excelPath, "Login", 0, 1);

        // Step 1 - Click on login link
        HP.getlogin_link().click();
        Reporter.log("Clicked on Login link", true);
        SC_Path = Screenshots.takeScreenShot(driver, "LoginClicked");
        generateReportWithScreenshot("Click on login link", SC_Path);

        // Step 2 - Enter phone number
        HP.getphn_num_txt().sendKeys(phonenum);
        Reporter.log("Entered Phone number: " + phonenum, true);
        SC_Path = Screenshots.takeScreenShot(driver, "PhonenumEntered");
        generateReportWithScreenshot("Enter phone num", SC_Path);

        // Step 3 - Submit (manual OTP wait)
        Thread.sleep(30000);
        HP.getsubmit_btn().click();
        Reporter.log("Submitted", true);
        SC_Path = Screenshots.takeScreenShot(driver, "SubmitButtonClicked");
        generateReportWithScreenshot("submit", SC_Path);

        // Verify profile element is visible
        WebElement profileElement = HP.getProfile();
        if (profileElement.isDisplayed()) {
            test.pass("Profile element is visible.");
        } else {
            test.fail("Profile element is not visible.");
            Assert.fail("Profile element is not displayed");
        }
    }

		
	@Test(dependsOnMethods = "LoginIn")
	private void packersNmovers() throws Exception {

	    wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
	    Reporter.log("Menu Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(drv, "MenuClicked");
	    generateReportWithScreenshot("Click on Menu", SC_Path);
	    Assert.assertTrue(true);
	    
	 // Verify Packers button is visible
        WebElement Packers_btn = HP.getpackers_btn();
        if (Packers_btn.isDisplayed()) {
            test.pass("Packers button is visible.");
        } else {
            test.fail("Packers button is not visible.");
            Assert.fail("Packers button is not visible");
        }

	    wait.until(ExpectedConditions.elementToBeClickable(HP.getpackers_btn())).click();
	    Reporter.log("PackernMovers Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(drv, "PackernMoversClicked");
	    generateReportWithScreenshot("Click on Packers and Movers", SC_Path);
	    Assert.assertTrue(true);

	    wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_from_inp())).click();
	    Reporter.log("Cleared From", true);
	    SC_Path = Screenshots.takeScreenShot(drv, "ClearedFrom");
	    generateReportWithScreenshot("Clear From Input", SC_Path);
	    Assert.assertTrue(true);

	    wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_to_inp())).click();
	    Reporter.log("Cleared To", true);
	    SC_Path = Screenshots.takeScreenShot(drv, "ClearedTo");
	    generateReportWithScreenshot("Clear To", SC_Path);
	    Assert.assertTrue(true);
	    
	 // Verify Price button is visible
	    WebElement Price_btn = PM.getPrices();
	    if (Price_btn.isDisplayed()) {
	        test.pass("Price button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Price button is not visible.");
	        Assert.fail("Price button is not visible");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(PM.getPrices())).click();
	    Reporter.log("Click Prices", true);
	    SC_Path = Screenshots.takeScreenShot(drv, "ClickPrices");
	    generateReportWithScreenshot("Click on Prices", SC_Path);
	    Assert.assertTrue(true);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownMethod() {
	    if (driver != null) {
	        driver.quit();
	        Reporter.log("Browser closed after test method", true);
	    }
	}

}
