package pages.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUs {
    private WebDriver driver;

    public ContactUs(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public WebElement getSubjectHeading() {
        return subjectHeading;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getOrderRefernce() {
        return orderRefernce;
    }

    public WebElement getAttachFile() {
        return attachFile;
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getSendButton() {
        return sendButton;
    }
    public WebElement getSuccessmessage() {
        return successmessage;
    }


    @FindBy(id = "id_contact")
    WebElement subjectHeading;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "id_order")
    WebElement orderRefernce;

    @FindBy(id = "fileUpload")
    WebElement attachFile;

    @FindBy(id = "message")
    WebElement message;

    @FindBy(id = "submitMessage")
    WebElement sendButton;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    WebElement successmessage;


}
