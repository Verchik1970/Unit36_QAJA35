package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import static pages.BasketPage.ITEM_TITLE;
import static pages.BasketPage.value;
import static pages.MainPage.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
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


    @ParameterizedTest
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка поиска по валидным значениям")
    @ValueSource(strings = {"Чехов", " Толстой ", "ПРЕСТУПЛЕНИЕ И НАКАЗАНИЕ", "ТОлсТой", "TolStoy"})
    public void inputValidSearchValue(String value) {

        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();

        mainPage.timeOutDuration(10);
        mainPage.allureScreenshot("ВВод тестового слова");
        mainPage.clearSearchInput();
        assertTrue(mainPage.getResultsCount() > 0, "Ничего не найдено");
    }

    @ParameterizedTest
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяет, что при вводе невалидного значения поиск не находит товар")
    @ValueSource(strings = {"2231273957295", "  416  860  ", ";%??:(*:%:?;%?:(%", "<script>alert(\"Поле input уязвимо!\")</script>"})
    public void inputInvalidSearchValue(String value) {
        driver.findElement(SEARCH_INPUT).sendKeys(value);
        mainPage.clickSearhButton();
        mainPage.timeOutDuration(10);
        mainPage.allureScreenshot("ВВод тестового слова");

        mainPage.clearSearchInput();
        String error = driver.findElement(ERROR_FOUND_ITEMS).getText();
        mainPage.timeOutDuration(10);
        mainPage.allureScreenshot("Ошибка при вводе невалидного значения в поиск");
        assertEquals("0", error, "Поиск товара по невалидным данным происходит.");


    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Тест проверят, что найденные товары соответствуют искомой фразе")
    public void itemTitleAssert() {
        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.allureScreenshot("ВВод тестового слова");
        mainPage.timeOutDuration(5);
        String title = driver.findElement(ITEM_TITLE).getText();
        mainPage.scrollPage(driver, 0, 137);

        mainPage.allureScreenshot("Найденные книги");

        System.out.println(title + " " + ", a искомая книга " + value);
        assertEquals(title, value);


    }

    @AfterEach
    void tearDown() {
        /* driver.quit();*/
    }
}
