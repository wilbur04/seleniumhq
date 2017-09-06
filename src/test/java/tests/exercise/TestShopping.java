package tests.exercise;


import org.junit.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import utils.ExtentReportManager;
import utils.ReportDetails;
import utils.ScreenShot;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestShopping {WebDriver driver;
    private static ExtentReportManager reportManager;
    Wait<WebDriver> wait;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\ShoppingTestReport",
                "Basic Extent Report", "Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);


    }

    @Before
    public void setUP() {
        driver = new ChromeDriver();
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }

    @Test
    public void testDraggable() {
        driver.navigate().to("http://automationpractice.com/index.php");
        Actions builder = new Actions(driver);
    }



}
