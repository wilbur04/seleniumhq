import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestSomething {
    WebDriver driver;

    @Before
    public void setUP(){
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        //driver.close();
        driver.quit();
    }

    @Test
    public void testOne(){
        driver.navigate().to("https://wilbur04.github.io");
    }

}
