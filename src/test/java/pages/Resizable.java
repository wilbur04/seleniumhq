package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.servlet.Registration;
import java.sql.Driver;

public class Resizable {

    public Resizable(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getMyresizeIcon() {
        return myresizeIcon;
    }

    @FindBy(xpath = "//*[@id=\"resizable\"]/div[3]")
    WebElement myresizeIcon;

}
