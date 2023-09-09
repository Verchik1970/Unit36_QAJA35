package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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

import static org.junit.jupiter.api.Assertions.*;
import static pages.FeedbackPage.*;

public class FeedbackPageTest {
    private static WebDriver driver;
    public static MainPage mainPage;
    private static FeedbackPage feedbackPage;
    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        feedbackPage = new FeedbackPage(driver, mainPage);
        mainPage.open();
    }
    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void setUp() {
        mainPage.open();
    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка наличия placeholder в полях ввода формы обратной связи")
    void checkPlaceholder() {
        mainPage.feedbackBtn();
        String name = feedbackPage.getAttributeNamePrint(NAME_INPUT, "placeholder");
        String phone = feedbackPage.getAttributeNamePrint(PHONE_INPUT, "placeholder");
        mainPage.scrollPage(driver, 0, 137);
        String email = feedbackPage.getAttributeNamePrint(EMAIL_INPUT, "placeholder");
        assertFalse(name.isEmpty(), "Пустой placeholder");
        assertFalse(phone.isEmpty(), "Пустой placeholder");
        assertFalse(email.isEmpty(), "Пустой placeholder");
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка что отправка сообщения без ввода капчи невозможна")
    void sendMessageValid() {
        mainPage.feedbackBtn();
        feedbackPage.sendTestMessageInput(NAME_INPUT, "Vasia Pupkin");
        feedbackPage.sendTestMessageInput(PHONE_INPUT, "+79111237843");
        feedbackPage.sendTestMessageInput(EMAIL_INPUT, "vvv@bbbb.ru");
        feedbackPage.sendTestMessageInput(TEXT_MESSAGE_INPUT, "Test message");
        driver.findElement(CHECKBOX_NOT_ORDER).click();
        driver.findElement(SEND_MESSAGE).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assertions.assertNotNull(alert);
        String alertText = alert.getText();
        assertEquals("Пройдите проверку \"Я не робот\"", alertText);
        alert.accept();

    }

    @AfterEach
    void tearDown() {
    }
}
