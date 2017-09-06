package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sortable {
    public Sortable(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getItem1() {
        return item1;
    }

    public WebElement getItem2() {
        return item2;
    }

    public WebElement getItem3() {
        return item3;
    }

    public WebElement getItem4() {
        return item4;
    }

    public WebElement getItem5() {
        return item5;
    }

    public WebElement getItem6() {
        return item6;
    }

    public WebElement getItem7() {
        return item7;
    }

    @FindBy(xpath="//*[@id=\"sortable\"]/li[1]")
    WebElement item1;

    @FindBy(xpath="//*[@id=\"sortable\"]/li[2]")
    WebElement item2;

    @FindBy(xpath="//*[@id=\"sortable\"]/li[3]")
    WebElement item3;

    @FindBy(xpath="//*[@id=\"sortable\"]/li[4]")
    WebElement item4;

    @FindBy(xpath="//*[@id=\"sortable\"]/li[5]")
    WebElement item5;

    @FindBy(xpath="//*[@id=\"sortable\"]/li[6]")
    WebElement item6;

    @FindBy(xpath="//*[@id=\"sortable\"]/li[7]")
    WebElement item7;




}
