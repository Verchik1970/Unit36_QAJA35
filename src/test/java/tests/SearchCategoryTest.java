package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MainPage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.xpath;
import static pages.MainPage.*;
public class SearchCategoryTest {
    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeAll
    static void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }


    private String value = "каштанка";

    @Test
    @DisplayName("Проверка поиска по категориям")
    public void CategorySearchValue() {
        driver.findElement(xpath(SEARCH_INPUT)).sendKeys(value + "\n");
        Allure.addAttachment("ВВод тестового слова",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(DROPDOWN_CATEGORY_SEARCH)).click(); // выпадающий список
        driver.findElement(By.xpath(DROPDOWN_SEARCH));
        driver.findElement(By.cssSelector(CATEGOTY_ELECTROBOOKS)).click();
        mainPage.clickSearhButton();
        String result = driver.findElement(By.cssSelector(CATEGORY_RESULT_EBOOKS)).getText();
        assertTrue(Objects.equals(result, "электронная книга"), "Категория не нашлась");

    }
    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
