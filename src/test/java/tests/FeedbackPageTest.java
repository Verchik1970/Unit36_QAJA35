package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FeedbackPage;
import pages.MainPage;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.FeedbackPage.*;

public class FeedbackPageTest {
    private WebDriver driver;
    public static MainPage mainPage;
    private FeedbackPage feedbackPage;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        feedbackPage = new FeedbackPage(driver, mainPage);
        mainPage.open();
    }

    @Test
    @DisplayName("Проверка наличия placeholder в полях ввода формы обратной связи")
    void checkPlaceholder() {
        mainPage.feedbackBtn();
        feedbackPage.getAttributeNamePrint(NAME_INPUT, "placeholder");
        feedbackPage.getAttributeNamePrint(PHONE_INPUT, "placeholder");
        feedbackPage.getAttributeNamePrint(EMAIL_INPUT, "placeholder");

    }
    @Test
    @DisplayName("Проверка что отправка сообщения без ввода капчи невозможна")
    void sendMessageValid(){
        mainPage.feedbackBtn();
        feedbackPage.sendTestMessageInput(NAME_INPUT,"Vasiua Pupkin");
        feedbackPage.sendTestMessageInput(PHONE_INPUT,"+79111237843");
        feedbackPage.sendTestMessageInput(EMAIL_INPUT, "vvv@bbbb.ru");
        feedbackPage.sendTestMessageInput(TEXT_MESSAGE_INPUT, "Test message");
        driver.findElement(CHECKBOX_NOT_ORDER).click();
        driver.findElement(SEND_MESSAGE).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assertions.assertNotNull(alert);
        String alertText = alert.getText();
        assertEquals( "Пройдите проверку \"Я не робот\"", alertText);
        alert.accept();



    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
