package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MainPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static pages.MainPage.*;

public class MainPageTest {
    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeAll

    static void beforeAll() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.init();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach

    public void setUp() {

        mainPage.open();
    }

    @Owner("Vera Razvarina")
    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка,что при нажатии кнопки выбора региона доставки " +
            "открывается всплывающее окно выбора региона ")
    public void choiceCity() {
        driver.findElement(CHOICE_CITY_BUTTON).click();
        String region = driver.findElement(REGION_NAME).getText();
        Pattern pattern = Pattern.compile(myRegion,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(region);
        assertTrue(matcher.find(), myRegion);

    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка, что кнопка выбора местоположения позволяет" +
            " выбрать нужный город и регион доставки")
    public void selectCityButton() {
        choiceCity();
        driver.findElement(CHANGE_BUTTON_CITY).click(); //кнопка изменить
        driver.findElement(DROPDOWN_CITIES).click(); // выпадающий список
        driver.findElement(CITY_LOCATION).click();
        WebElement selectElement = driver.findElement(RUSSIA_CITIES_CHANGE);
        mainPage.timeOutDuration(4);
        Select select = new Select(selectElement);
        select.selectByVisibleText("Алтай");
        String city_position = driver.findElement(ALTAY_REGION).getText();
        mainPage.allureScreenshot("Выбран город и регион доставки");
        assertEquals(test_city_value, city_position, "Выбор города не работает");
    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка, что кнопка ПОМОЩЬ открывает страницу со справочными материалами")
    public void help_btn() {
        driver.findElement(HELP_BTN).click();
        String helpTitle = driver.getTitle();
        System.out.println(helpTitle);
        mainPage.allureScreenshot("Открылась страница Справки");
        assert helpTitle.contains("Справка") : "Страница Справки не открылась";
    }


    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка, что нажатие на кнопку Вход ведет на страницу регистрации и входа")
    void registrationBTN() {
        mainPage.registrationBtnClick();
        String regTitle = driver.getTitle();
        System.out.println(regTitle);
        mainPage.allureScreenshot("Открылась страница регистрации");
        assert regTitle.contains("Вход") : "Страница Входа не открылась";

    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка, что нажатие на кнопку Корзина ведет на страницу корзины")
    void basketBTNCheck() {
        mainPage.basketBtnClick();
        String cart = driver.findElement(BASKET_BTN1).getText();
        mainPage.allureScreenshot("Открылась страница корзины");
        assertEquals(cart, "Корзина");

    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка, что нажатие на кнопку Обратная связь ведет на страницу Отправить сообщение")
    void feedbackBTNcheck() {
        driver.findElement(FEEDBACK_BTN).click();
        String feedback_name = driver.findElement(FEEDBACK_NAME_PAGE).getText();
        mainPage.allureScreenshot("Открылась страница отправки сообщения для обратной связи");
        assertEquals(feedback_name, "Обратная связь");
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @DisplayName("Проверка что при нажатии на кнопку Перейти на старый сайт - открывается старый сайт")
    void getLinkOldSite() {
        driver.findElement(LINK_OLD_SITE).click();
        String newSite = driver.getCurrentUrl();
        assertEquals("https://new.books.ru/", newSite);

    }

    @AfterEach
    public void tearDown() {
/*
        driver.quit();
*/
    }
}
