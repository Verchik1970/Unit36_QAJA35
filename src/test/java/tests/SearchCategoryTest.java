package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.*;
import static pages.MainPage.*;

public class SearchCategoryTest {
    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }


    final String value = "Трудно быть богом";

    @Test
    @DisplayName("Проверка поиска по категориям")
    public void CategorySearchValue() {
        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.allureScreenshot("ВВод тестового слова");
        mainPage.timeOutDuration(5);
        driver.findElement(DROPDOWN_CATEGORY_SEARCH).click(); // выпадающий список
        driver.findElement(DROPDOWN_SEARCH);
        driver.findElement(CATEGOTY_ELECTROBOOKS).click();
        mainPage.clickSearhButton();
        mainPage.allureScreenshot("Поиск по категории");
        String result = driver.findElement(CATEGORY_RESULT_EBOOKS).getText();
        assertTrue(Objects.equals(result, "электронная книга"), "Категория не нашлась");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
