package pages.autoTrader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResults {

    private WebDriver driver;


    public SearchResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy( css ="#postcode")
    WebElement postCode;
    @FindBy( css ="body > main > section.search-page__left > div.search-form > form > ul > li:nth-child(2) > fieldset > div:nth-child(1) > div > select")
    WebElement radius;

    @FindBy(xpath = "//*[@id=\"201708228566484\"]/article/section[1]") WebElement firstResult;


    public WebElement getPostCode() {
        return postCode;
    }

    public WebElement getRadius() {
        return radius;
    }

    public WebElement getFirstResult() {
        return firstResult;
    }
}
