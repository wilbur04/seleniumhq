package pages.autoTrader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Index {
    private WebDriver driver;

    public Index(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath= "//*[@id=\"postcode\"]")
    WebElement postcodeSearchInput;
    @FindBy(xpath= "//*[@id=\"radius\"]")
    WebElement radiusSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehicles\"]/div/div[3]/fieldset[2]")
    WebElement showUseSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehicles\"]/div/div[3]/fieldset[3]/label")
    WebElement showNearlyUsedSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehicles\"]/div/div[3]/fieldset[4]/label")
    WebElement showNewSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehiclesMake\"]")
    WebElement makeSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehiclesModel\"]")
    WebElement modelSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehiclesPriceFrom\"]")
    WebElement minPriceSearchInput;
    @FindBy(xpath= "//*[@id=\"searchVehiclesPriceTo\"]")
    WebElement maxPriceSearchInput;
    @FindBy(css= "#search")
    WebElement searchButton;
//    @FindBy(xpath= "//*[@id=\"postcode\"]")
//    WebElement postcodeSearchInput;
//    @FindBy(xpath= "//*[@id=\"postcode\"]")
//    WebElement postcodeSearchInput;
//    @FindBy(xpath= "//*[@id=\"postcode\"]")
//    WebElement postcodeSearchInput;


    public WebElement getPostcodeSearchInput() {
        return postcodeSearchInput;
    }

    public WebElement getRadiusSearchInput() {
        return radiusSearchInput;
    }

    public WebElement getShowUseSearchInput() {
        return showUseSearchInput;
    }

    public WebElement getShowNearlyUsedSearchInput() {
        return showNearlyUsedSearchInput;
    }

    public WebElement getShowNewSearchInput() {
        return showNewSearchInput;
    }

    public WebElement getMakeSearchInput() {
        return makeSearchInput;
    }

    public WebElement getModelSearchInput() {
        return modelSearchInput;
    }

    public WebElement getMinPriceSearchInput() {
        return minPriceSearchInput;
    }

    public WebElement getMaxPriceSearchInput() {
        return maxPriceSearchInput;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }
}
