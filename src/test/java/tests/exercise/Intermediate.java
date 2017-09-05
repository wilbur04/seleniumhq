package tests.exercise;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import utils.ExtentReportManager;
import utils.ReportDetails;
import utils.SpreadSheetReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class Intermediate {
    WebDriver driver;
    private static ExtentReportManager reportManager;


    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\TestReport",
                "Basic Extent Report", "Basic Report");

        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
    }

 /*
    @Before
    public void setUP() {

        //driver = new ChromeDriver();
    }

/*
    @Test
    public void testLogin() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "This will try to connect to http://thedemosite.co.uk, add a new user and login as the user.");
        extentTest.log(Status.DEBUG,
                "Debug level message to display any information a TECHNICAL person might need to know.");
        String imagePath = ScreenShot.take(driver, "image");
        extentTest.addScreenCaptureFromPath(imagePath);

        try {
            driver.navigate().to("http://thedemosite.co.uk");
            driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys("ayaz");
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys("ayaz");
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
            driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys("ayaz");
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys("ayaz");
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();

            assertEquals("**Successful Login**", driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
            extentTest.pass("Passed");
        } catch (AssertionError e){
            String details = "Login wasn't Successful: " + e.getMessage();
            extentTest.log(Status.WARNING, "There could be an error with trying to login");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (Exception e){
            String details = "Couldn't Find Element: " + e.getMessage();
            extentTest.log(Status.ERROR, "There could be a problem finding an element");
            extentTest.fail(details);
            Assert.fail(details);
        }
    }
    */

    @Test
    public void testInputData() throws IOException {


        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "This will try to connect to http://thedemosite.co.uk, add a new user and login as the user.");
        extentTest.log(Status.DEBUG,
                "Debug level message to display any information a TECHNICAL person might need to know.");
        //   String imagePath = ScreenShot.take(driver, "image");
        //  extentTest.addScreenCaptureFromPath(imagePath);
//
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/sheet.xlsx");

        int[] numberOfRows = {1, 2, 3, 4};
        for (int rowNo : numberOfRows) {
            List<String> list = sheetReader.readRow(rowNo, "sheet1");
            try {
                switch (list.get(0)) {
                    case ("chrome"):
                        driver = new ChromeDriver();
                        break;
                    case ("firefox"):
                        driver = new FirefoxDriver();
                        break;
                    default:
                        driver = new ChromeDriver();
                }

                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(30, SECONDS)
                        .pollingEvery(1, SECONDS)
                        .ignoring(NoSuchElementException.class);
//        WebElement foo = wait.until(new Function<WebDriver, WebElement>()
//        {
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.id("exampleID"));
//            }
//        });
                driver.navigate().to("http://thedemosite.co.uk");
                driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.name("username"));
                    }
                });
                driver.findElement(By.name("username")).sendKeys(list.get(1));
                driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys(list.get(2));
                driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();


                driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(".auto-style1 > big:nth-child(6) > blockquote:nth-child(1) > blockquote:nth-child(1) > font:nth-child(1) > center:nth-child(1) > b:nth-child(1)"));
                    }
                });
                driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys(list.get(1));
                driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys(list.get(2));
                driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
                wait.until((ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".auto-style1 > big:nth-child(6) > blockquote:nth-child(1) > blockquote:nth-child(1) > font:nth-child(1) > center:nth-child(1) > b:nth-child(1)"), "**No login attempted**")));
                assertEquals(list.get(3), driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
                extentTest.pass(list.get(1) + "Passed in " + list.get(0));

                driver.quit();
            } catch (AssertionError e) {
                String details = "Login wasn't Successful: " + e.getMessage();
                extentTest.log(Status.WARNING, "There could be an error with trying to login");
                extentTest.fail(details);
                Assert.fail(details);
            } catch (WebDriverException e) {
                String details = "Couldn't Find Element: " + e.getMessage();
                extentTest.log(Status.WARNING, " Exception while trying to close firefox");
            } catch (Exception e) {
                String details = "Couldn't Find Element: " + e.getMessage();
                extentTest.log(Status.ERROR, "There could be a problem finding an element");
                extentTest.fail(details);
                Assert.fail(details);
            }

        }
    }

/*
    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }
    */

    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }

}
