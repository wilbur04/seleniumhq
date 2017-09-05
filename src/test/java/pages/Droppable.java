package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.servlet.Registration;
import java.sql.Driver;

public class Droppable {
    private WebDriver driver;

    public Droppable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }






    @FindBy(id="draggableview")
    WebElement mydragableview;

    public WebElement getMydragableview() {
        return mydragableview;
    }


    @FindBy(id="droppableview")
    WebElement mydroppableview;

    public WebElement getMydroppableview() {
        return mydroppableview;
    }
}
