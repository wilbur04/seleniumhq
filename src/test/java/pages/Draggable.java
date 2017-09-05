package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.servlet.Registration;
import java.sql.Driver;

public class Draggable {
    private WebDriver driver;

    public Draggable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "draggable")
    WebElement mydraggable;

    public WebElement getMydraggable() {
        return mydraggable;
    }


}
