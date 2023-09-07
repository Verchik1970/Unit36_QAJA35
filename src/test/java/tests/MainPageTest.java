package tests;

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
    private MainPage mainPage;


    @BeforeEach

    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Проверка работы кнопки выбора региона доставки")
    public void choiceCity() {
        driver.findElement(CHOICE_CITY_BUTTON).click();
        String region = driver.findElement(REGION_NAME).getText();
        Pattern pattern = Pattern.compile(myRegion,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(region);
        assertTrue(matcher.find(), myRegion);

    }

    @Test
    @DisplayName("Проверка, что кнопка выбора местоположения позволяет выбрать нужный город и регион доставки")
    public void selectCityButton() {
        choiceCity();
        driver.findElement(CHANGE_BUTTON_CITY).click(); //кнопка изменить
        driver.findElement(DROPDOWN_CITIES).click(); // выпадающий список
        driver.findElement(CITY_LOCATION).click();
        WebElement selectElement = driver.findElement(RUSSIA_CITIES_CHANGE);
        Select select = new Select(selectElement);
        select.selectByVisibleText("Алтай");
        String city_position = driver.findElement(ALTAY_REGION).getText();
        assertEquals(test_city_value, city_position, "Выбор города не работает");
    }

    @Test
    @DisplayName("Проверка, что кнопка ПОМОЩЬ открывает страницу со справочными материалами")
    public void help_btn() {
        driver.findElement(HELP_BTN).click();
        String helpTitle = driver.getTitle();
        System.out.println(helpTitle);
        mainPage.allureScreenshot("Открылась страница Справки");
        assert helpTitle.contains("Справка") : "Страница Справки не открылась";
    }


    @Test
    @DisplayName("Проверка, что нажатие на кнопку Вход ведет на страницу регистрации и входа")
    void registrationBTN() {
        mainPage.registrationBtnClick();
        String regTitle = driver.getTitle();
        System.out.println(regTitle);
        mainPage.allureScreenshot("Открылась страница регистрации");
        assert regTitle.contains("Вход") : "Страница Входа не открылась";

    }

    @Test
    @DisplayName("Проверка, что нажатие на кнопку Корзина ведет на страницу корзины")
    void basketBTNCheck() {
        mainPage.basketBtnClick();
        String cart = driver.findElement(By.xpath("//h1[contains(text(),'Корзина')]")).getText();
        mainPage.allureScreenshot("Открылась страница корзины");
        assertEquals(cart, "Корзина");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
