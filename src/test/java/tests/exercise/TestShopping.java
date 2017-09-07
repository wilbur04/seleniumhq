package tests.exercise;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import pages.automationpractice.ContactUs;
import pages.automationpractice.Index;
import utils.ExtentReportManager;
import utils.ReportDetails;
import utils.ScreenShot;
import utils.SpreadSheetReader;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class TestShopping {WebDriver driver;
    private static ExtentReportManager reportManager;
    Wait<WebDriver> wait;
    static SpreadSheetReader sheetReader;
    Index index;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\ShoppingTestReport",
                "Basic Extent Report", "Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
        sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/userDetails.xlsx");

    }

    @Before
    public void setUP() {
        driver = new ChromeDriver();
        index = new Index(driver);
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
        driver.navigate().to("http://automationpractice.com/index.php");

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
    public void testContact() {
        ContactUs cu = new ContactUs(driver);
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Testing if an object can be added to the basket.");
        extentTest.log(Status.DEBUG,
                "Something is wrong");
        String imagePath;

        int[] numberOfRows = {1, 2, 3, 4};
        for (int rowNo : numberOfRows) {
            List<String> list = sheetReader.readRow(rowNo, "contactus");

            try {
                driver.navigate().to("http://automationpractice.com/index.php");

                index.getContactUsLink().click();
                wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (driver.getTitle().equals("Contact us - My Store"));
                    }
                });
                cu.getSubjectHeading().sendKeys(list.get(0));
                cu.getSubjectHeading().click();
                cu.getEmail().sendKeys(list.get(1));
                cu.getOrderRefernce().sendKeys(list.get(2));
                cu.getMessage().sendKeys(list.get(3));
                cu.getSendButton().click();
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.xpath("//*[@id=\"center_column\"]/p"));

                    }
                });
                assertEquals("Your message has been successfully sent to our team.", cu.getSuccessmessage().getText());
                imagePath = ScreenShot.take(driver, "img\\testContactUsImage" + rowNo);
                extentTest.addScreenCaptureFromPath(imagePath);

            } catch (AssertionError e) {
                String details = "Contact Failed " + e.getMessage();
                extentTest.log(Status.WARNING, "Item has not been dropped in the box.");
                extentTest.fail(details);
                Assert.fail(details);
            } catch (IOException e) {
                e.printStackTrace();
                extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
            }
        }
    }


    @Test
    public void testBasket() {
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Testing if the user is able to contact us");
        extentTest.log(Status.DEBUG,
                "Something is wrong");
        String imagePath;


        try {

            index.getItem2().click();
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return (driver.getTitle().contains("My Store"));
                }
            });

            imagePath = ScreenShot.take(driver, "img\\addtoBasketImage");
            extentTest.addScreenCaptureFromPath(imagePath);

        } catch (AssertionError e) {
            String details = "Contact Failed " + e.getMessage();
            extentTest.log(Status.WARNING, "Item has not been dropped in the box.");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (IOException e) {
            e.printStackTrace();
            extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
        }

    }

    @Test
    public void testImageHover() {

        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Testing if the user is able to contact us");
        extentTest.log(Status.DEBUG,
                "Something is wrong");
        String imagePath;


        try {

            index.getItem2().click();
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return (driver.getTitle().equals("Contact us - My Store"));
                }
            });

            //todo do some stuff here

            imagePath = ScreenShot.take(driver, "img\\imageHoverImage");
            extentTest.addScreenCaptureFromPath(imagePath);

        } catch (AssertionError e) {
            String details = "Contact Failed " + e.getMessage();
            extentTest.log(Status.WARNING, "Item has not been dropped in the box.");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (IOException e) {
            e.printStackTrace();
            extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
        }


    }


}
