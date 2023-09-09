package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FeedbackPage {
    public static WebDriver driver;
    public MainPage mainPage;

    public FeedbackPage(WebDriver driver, MainPage mainPage) {
        FeedbackPage.driver = driver;
        BasketPage.driver = driver;
        PageFactory.initElements(driver, this);
        this.mainPage = mainPage;

    }

    public static By NAME_INPUT = By.xpath("//input[@id='textfield1']");
    public static By PHONE_INPUT = By.xpath("//input[@id='textfield2']");
    public static By EMAIL_INPUT = By.xpath("//input[@id='textfield3']");
    public static By TEXT_MESSAGE_INPUT = By.cssSelector("textarea.input");

    public static By CHECKBOX_NOT_ORDER = By.cssSelector("label.label.label-checkbox");
    public static By SEND_MESSAGE = By.cssSelector("button.button.button-in-cart ");


    public String getAttributeNamePrint(By locator, String attributeName) {
        driver.findElement(locator).getAttribute(attributeName);
        return attributeName;


    }

    public void sendTestMessageInput(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

}
