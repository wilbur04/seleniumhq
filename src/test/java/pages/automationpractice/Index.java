package pages.automationpractice;

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

    public WebElement getContactUsLink() {
        return contactUsLink;
    }

    public WebElement getItem1() {
        return Item1;
    }

    public WebElement getItem2() {
        return Item2;
    }

    public WebElement getItem3() {
        return Item3;
    }

    public WebElement getItem4() {
        return Item4;
    }

    @FindBy(xpath = "//*[@id=\"contact-link\"]/a")
    WebElement contactUsLink;
    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img")
    WebElement Item1;
    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[2]/img")
    WebElement Item2;
    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[3]/img")
    WebElement Item3;
    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[4]/img")
    WebElement Item4;

}
