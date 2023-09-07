package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasketPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.BasketPage.*;
import static pages.MainPage.*;


public class BasketPageTest {
    private WebDriver driver;
    public static MainPage mainPage;
    private BasketPage basketPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Проверка при нажатии на кнопку ввести Код - откроется поле для ввода кода скидки")
    public void discountBtnCheck() {
        basketPage.addToCartItems(value);
        driver.findElement(DISCOUNT_CODE_BTN).click();
        String discountInput = driver.findElement(DISCOUNT_INPUT).getText();
        assertEquals("Введите DISCODe:", discountInput);

    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
