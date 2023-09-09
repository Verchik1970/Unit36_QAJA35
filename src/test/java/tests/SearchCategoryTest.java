package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.MainPage.*;

public class SearchCategoryTest {
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

    final String value = "Трудно быть богом";

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка поиска по категориям")
    public void CategorySearchValue() {
        mainPage.inputSearchInput(value);
        mainPage.clickSearchButton();
        mainPage.allureScreenshot("ВВод тестового слова");
        mainPage.timeOutDuration(5);
        driver.findElement(DROPDOWN_CATEGORY_SEARCH).click(); // выпадающий список
        driver.findElement(DROPDOWN_SEARCH);
        driver.findElement(CATEGORY_ELECTROBOOKS).click();
        mainPage.clickSearchButton();
        mainPage.allureScreenshot("Поиск по категории");
        String result = driver.findElement(CATEGORY_RESULT_EBOOKS).getText();
        assertEquals(result, "электронная книга", "Категория не нашлась");

    }

    @AfterEach
    public void tearDown() {
    }

}
