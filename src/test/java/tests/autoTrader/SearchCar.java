package tests.autoTrader;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import org.junit.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import pages.Selectable;
import pages.autoTrader.SearchResults;
import sun.rmi.log.LogInputStream;
import utils.ExtentReportManager;
import utils.ReportDetails;
import utils.ScreenShot;
import utils.SpreadSheetReader;
import pages.autoTrader.Index;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchCar {

    private WebDriver driver;
    private static ExtentReportManager reportManager;
    private Wait<WebDriver> wait;
    static SpreadSheetReader sheetReader;
    private Index index;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\ShoppingTestReport",
                "Basic Extent Report", "Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
        sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/AutoTrader.xlsx");

    }

    @Before
    public void setUP() {


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
    public void testSearchByPostCode() {

        pages.autoTrader.Index index = new pages.autoTrader.Index(driver);
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Trying to search for a car within 1 mile of post code");
        extentTest.log(Status.DEBUG,
                "Something is wrong");
        String imagePath;

        int[] numberOfRows = {1};
        for (int rowNo : numberOfRows) {
            List<String> list = sheetReader.readRow(rowNo, "sheet1");
            try {


                selectDriver(list.get(9));
                index = new Index(driver);
                wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(10, SECONDS)
                        .pollingEvery(1, SECONDS)
                        .ignoring(NoSuchElementException.class);
                driver.navigate().to("http://www.autotrader.co.uk/");
                Actions actions = new Actions(driver);


                wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (driver.getTitle().contains("Used Cars for Sale"));
                    }
                });

                index.getPostcodeSearchInput().sendKeys(list.get(0));
                index.getRadiusSearchInput().sendKeys(list.get(1));

                imagePath = ScreenShot.take(driver, "img\\SearchImage");
                extentTest.addScreenCaptureFromPath(imagePath);
//
                actions.moveToElement(index.getSearchButton()).click().perform();
                //index.getSearchButton().click();
                wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (driver.getCurrentUrl().equals("http://www.autotrader.co.uk/"));
                    }
                });

                final SearchResults sr = new SearchResults(driver);
                assertEquals(list.get(0), sr.getPostCode().getAttribute("value"));
                extentTest.log(Status.PASS, "It found a car in the right postCode");


            } catch (AssertionError e) {
                String details = "Search Failed " + e.getMessage();
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
    public void testSearchMake() {
        pages.autoTrader.Index index = new pages.autoTrader.Index(driver);
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Trying to search for a Ford Fiesta within 1 mile of PE1 5HW costing less than £1000");
        extentTest.log(Status.DEBUG,
                "Something is wrong");
        String imagePath;

        int[] numberOfRows = {1,2,3};
        for (int rowNo : numberOfRows) {
            List<String> list = sheetReader.readRow(rowNo, "sheet1");
            try {

                selectDriver(list.get(9));
                index = new Index(driver);
                wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(10, SECONDS)
                        .pollingEvery(1, SECONDS)
                        .ignoring(NoSuchElementException.class);
                driver.navigate().to("http://www.autotrader.co.uk/");
                Actions actions = new Actions(driver);

                wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (driver.getTitle().contains("Used Cars for Sale"));
                    }
                });

                index.getPostcodeSearchInput().sendKeys(list.get(0));
                index.getRadiusSearchInput().sendKeys(list.get(1));
//                  if (!list.get(2).equals("TRUE")) {
//                      index.getShowUseSearchInput().click();
//                   }
//                   if (!list.get(3).equals("TRUE")) {
//                        index.getShowNearlyUsedSearchInput().click();
//                    }
//                  if (!list.get(4).equals("TRUE")) {
//                      index.getShowNewSearchInput().click();
//                 }

                Select sel = new Select(index.getMakeSearchInput());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sel.selectByValue(list.get(5));

//                index.getModelSearchInput().click();
//                index.getModelSearchInput().sendKeys(list.get(5));
//                index.getModelSearchInput().click();
//                index.getMakeSearchInput().sendKeys(list.get(6));
//                    index.getMinPriceSearchInput().sendKeys(list.get(7));
//                    index.getMaxPriceSearchInput().sendKeys(list.get(8));
                //                   index.getSearchButton().click();
                actions.moveToElement(index.getSearchButton()).click().perform();
                wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !(driver.getCurrentUrl().equals("http://www.autotrader.co.uk/"));
                    }
                });

                final SearchResults sr = new SearchResults(driver);

                imagePath = ScreenShot.take(driver, "img\\ModelSearchImage");
                extentTest.addScreenCaptureFromPath(imagePath);

                assertTrue(driver.getCurrentUrl().contains("make="+list.get(5)));
                extentTest.log(Status.PASS, "Test "+ rowNo+": searching for "+ list.get(5)+" Cars.");

            } catch (AssertionError e) {
                String details = "Search Failed " + e.getMessage();
                extentTest.log(Status.WARNING, "Could not search this make.");
                extentTest.fail(details);
                Assert.fail(details);
            } catch (IOException e) {
                e.printStackTrace();
                extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
            }

        }

    }

    @Test
    public void testValuation(){
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Trying to search for a Ford Fiesta within 1 mile of PE1 5HW costing less than £1000");
        extentTest.log(Status.DEBUG,
                "Something is wrong");
        String imagePath;
    }


    public void selectDriver(String s) {
        switch (s) {
            case ("chrome"):
                driver = new ChromeDriver();
                break;
            case ("firefox"):
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
    }
}
