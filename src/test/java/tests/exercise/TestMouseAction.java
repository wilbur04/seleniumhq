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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.*;
import utils.ExtentReportManager;
import utils.ReportDetails;
import utils.ScreenShot;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMouseAction {
    WebDriver driver;
    private static ExtentReportManager reportManager;
    Wait<WebDriver> wait;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\MouseActionTestReport",
                "Basic Extent Report", "Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);


    }

    @Before
    public void setUP() {
        driver = new ChromeDriver();
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(5, SECONDS)
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
        driver.navigate().to("http://demoqa.com/draggable/");
        Actions builder = new Actions(driver);
        builder.moveByOffset(400, 400).clickAndHold().moveByOffset(200, 200).release().perform();
    }

    @Test
    public void testDroppable() {
        Droppable dr = new Droppable(driver);
        driver.navigate().to("http://demoqa.com/droppable/");
        Actions builder = new Actions(driver);
        builder.moveByOffset(dr.getMydragableview().getLocation().getX(), dr.getMydragableview().getLocation().getY()).clickAndHold().moveToElement(dr.getMydroppableview()).release().perform();

        ExtentTest extentTest = reportManager.setUpTest();
        extentTest.log(Status.INFO, "Will try to drab one box and try to drop it on the other.");
        extentTest.log(Status.DEBUG,
                "Check to see if it picking the right object and  moving it on the right object.");
        String imagePath;
        try {
            wait.until((ExpectedConditions.invisibilityOfElementWithText(By.id("droppableview"), "Drop here")));
            imagePath = ScreenShot.take(driver, "img\\droppableImage");
            extentTest.addScreenCaptureFromPath(imagePath);
            assertEquals("testing Droppable", "Dropped!", dr.getMydroppableview().getText());
            extentTest.log(Status.PASS, "Item has been dropped in box.");
        } catch (AssertionError e) {
            String details = "Drop failed " + e.getMessage();
            extentTest.log(Status.WARNING, "Item has not been dropped in the box.");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (IOException e) {
            e.printStackTrace();
            extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
        }
    }

    @Test
    public void testResizable() {
        final Resizable r = new Resizable(driver);
        driver.navigate().to("http://demoqa.com/resizable/");
        int x = r.getMyresizeIcon().getLocation().getX();
        int Y = r.getMyresizeIcon().getLocation().getY();
        Actions builder = new Actions(driver);


        ExtentTest extentTest = reportManager.setUpTest();
        extentTest.log(Status.INFO, "Will try clicking on the box and changing its size.");
        extentTest.log(Status.DEBUG,
                "Check the location of the resize icon.");
        String imagePath = null;
        try {
            builder.moveToElement(r.getMyresizeIcon()).clickAndHold().moveByOffset(400, 400).release().perform();

            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    if (!r.getMyresizeIcon().isSelected()) {
                        return driver.findElement(By.id("resizable"));
                    } else return null;
                }
            });
            imagePath = ScreenShot.take(driver, "img\\resizableImage");
            extentTest.addScreenCaptureFromPath(imagePath);
            assertNotEquals("testing resizable ", x, r.getMyresizeIcon().getLocation().getX());
            extentTest.log(Status.PASS, "The box has been resized.");
        } catch (AssertionError e) {
            String details = "Resizable :" + e.getMessage();
            extentTest.log(Status.FAIL, "Could not resize.");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (IOException e) {
            e.printStackTrace();
            extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
        }

    }

    @Test
    public void testSelectable() {
        final Selectable s = new Selectable(driver);
        driver.navigate().to("http://demoqa.com/selectable/");
        Actions builder = new Actions(driver);

        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "");
        extentTest.log(Status.DEBUG,
                "");
        String imagePath = null;
        try {
            builder.dragAndDrop(s.getItem1(), s.getItem2()).perform();

            imagePath = ScreenShot.take(driver, "img\\selectableImage");
            extentTest.addScreenCaptureFromPath(imagePath);
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    if (s.getItem1().getCssValue("background").toString().contains("rgb(243, 152, 20)")) {
                        return driver.findElement(By.id("selectable"));
                    } else {
                        return null;
                    }
                }
            });
            assertEquals("rgba(255, 255, 255, 1)", s.getItem1().getCssValue("color"));
            extentTest.log(Status.PASS, "successfully selected the item 1.");
            assertEquals("rgba(255, 255, 255, 1)", s.getItem2().getCssValue("color"));
            extentTest.log(Status.PASS, "successfully selected the item 2.");
            assertEquals("rgba(34, 34, 34, 1)", s.getItem3().getCssValue("color"));
            extentTest.log(Status.PASS, "successfully not selected the item 3.");
            assertEquals("rgba(34, 34, 34, 1)", s.getItem4().getCssValue("color"));
            extentTest.log(Status.PASS, "successfully not selected the item 4.");


        } catch (AssertionError e) {
            String details = "faild to selectt he items" + e.getMessage();
            extentTest.log(Status.WARNING, "");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (IOException e) {
            e.printStackTrace();
            extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
        }
    }

    @Test
    public void testSortable() {
        final Sortable s = new Sortable(driver);
        driver.navigate().to("http://demoqa.com/sortable/");
        Actions builder = new Actions(driver);
        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "try rearranging the items in the list. ");
        extentTest.log(Status.DEBUG,
                "the items may not be draggable.");
        String imagePath ;
        try {
            builder.moveToElement(s.getItem1()).clickAndHold().moveByOffset(0, 200).release().perform();

            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    if (s.getItem1().getText().equals("Item 2")) {
                        return driver.findElement(By.id("sortable"));
                    } else {
                        return null;
                    }
                }
            });
            imagePath = ScreenShot.take(driver, "img\\sortableImage");
            extentTest.addScreenCaptureFromPath(imagePath);

            assertEquals("Checking first item","Item 2", s.getItem1().getText());
            extentTest.log(Status.PASS,"The first item is Item2");
            assertEquals("Checking last item","Item 1", s.getItem7().getText());
            extentTest.log(Status.PASS,"The second item is Item1");

        } catch (AssertionError e) {
            String details = "Sortable: " + e.getMessage();
            extentTest.log(Status.WARNING, " Failed to successfully sort items.");
            extentTest.fail(details);
            Assert.fail(details);
        } catch (IOException e) {
            e.printStackTrace();
            extentTest.log(Status.WARNING, " IOException: " + e.getMessage());
        }
    }


}
