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
import static pages.MainPage.driver;


public class BasketPageTest {
    public  static MainPage mainPage;
    private  BasketPage basketPage;

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver);
        mainPage.open();
    }
    @Test
    @DisplayName("Проверка что при нажатии на кнопку крестик товары из корзины удалятся")
    public void deleteBtnChoice(){
        basketPage.addToCartItems(value);
        driver.findElement(By.cssSelector(DELETE_BTN)).click();
        String noItems = driver.findElement(By.cssSelector(NO_ITEMS_INTHE_CART)).getText();
        assertEquals("В корзине ничего нет.", noItems);

    }


}
