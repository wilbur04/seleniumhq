package pages.automationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product  {
    private WebDriver driver;

    public Product(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getImg5() {
        return img5;
    }

    public WebElement getImg6() {
        return img6;
    }

    public WebElement getImg8() {
        return img8;
    }

    public WebElement getQuantityInput() {
        return quantityInput;
    }

    public WebElement getSizeInput() {
        return sizeInput;
    }

    public WebElement getAddToCart() {
        return addToCart;
    }

    public WebElement getProceedToCheckout() {
        return proceedToCheckout;
    }

    public WebElement getContinueShopping() {
        return continueShopping;
    }


    @FindBy(xpath = "//*[@id=\"thumb_5\"]")
    WebElement img5;

    @FindBy(xpath = "//*[@id=\"thumb_6\"]")
    WebElement img6;

    @FindBy(xpath = "//*[@id=\"thumb_8\"]")
    WebElement img8;

    @FindBy(xpath = "//*[@id=\"quantity_wanted\"]")
    WebElement quantityInput;

    @FindBy(xpath = "//*[@id=\"group_1\"]")
    WebElement sizeInput;

    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button")
    WebElement addToCart;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span")
    WebElement continueShopping;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")
    WebElement proceedToCheckout;

    public WebElement getPopup() {
        return popup;
    }

    @FindBy(id = "layer_cart")
    WebElement popup;
}
