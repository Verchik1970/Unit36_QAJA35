package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.xpath;
import static pages.MainPage.ERROR_FOUND_ITEMS;
import static pages.MainPage.SEARCH_INPUT;

public class SearchTest {
    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }


    @ParameterizedTest
    @DisplayName("Проверка поиска по валидным значениям")
    @ValueSource(strings = {"Чехов", " Толстой ", "ПРЕСТУПЛЕНИЕ И НАКАЗАНИЕ", "ТОлсТой", "TolStoy"})
    public void inputValidSearchValue(String value) throws IOException {

        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();

        mainPage.timeOutDuration(10);
        mainPage.allureScreenshot("ВВод тестового слова");
        mainPage.clearSearchInput();
        assertTrue(mainPage.getResultsCount() > 0, "Ничего не найдено");
    }

    @ParameterizedTest
    @DisplayName("Проверка поиска по невалидным значениям")
    @ValueSource(strings = {"2231273957295", "  416  860  ", ";%??:(*:%:?;%?:(%", "<script>alert(\"Поле input уязвимо!\")</script>"})
    public void inputInvalidSearchValue(String value) throws IOException {
        driver.findElement(xpath(SEARCH_INPUT)).sendKeys(value);
        mainPage.clickSearhButton();
        mainPage.timeOutDuration(10);
       mainPage.allureScreenshot("ВВод тестового слова");

        mainPage.clearSearchInput();
        String error = driver.findElement(By.cssSelector(ERROR_FOUND_ITEMS)).getText();
        mainPage.timeOutDuration(10);
        mainPage.allureScreenshot("Ошибка при вводе невалидного значения в поиск");
        assertTrue(Objects.equals(error, "0"), "Поиск товара по невалидным данным происходит.");


    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
