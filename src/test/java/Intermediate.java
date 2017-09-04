import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

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


    @Before
    public void setUP() {
        driver = new ChromeDriver();
    }


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


    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }

}
