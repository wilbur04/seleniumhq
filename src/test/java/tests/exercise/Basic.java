package tests.exercise;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;


public class Basic {
    WebDriver driver;

    @BeforeClass
    public static void onlyOnce() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void setUP(){
        System.out.println("@Before");
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        System.out.println("@Test");
    }

    @Test
    public void testSelenium(){
        System.out.println("@Test");
        driver.navigate().to("http://qa.com");
        assertEquals("https://www.qa.com/",driver.getCurrentUrl());
    }

    @After
    public void tearDown(){
        System.out.println("@After");
        driver.quit();
    }



    @AfterClass
    public static void logout() {
        System.out.println("@AfterClass");
    }


}
