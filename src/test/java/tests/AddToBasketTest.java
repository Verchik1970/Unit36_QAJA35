package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasketPage;
import pages.FeedbackPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.BasketPage.*;
import static pages.MainPage.*;


public class AddToBasketTest {
    private WebDriver driver;
    public static MainPage mainPage;
    private BasketPage basketPage;
    public FeedbackPage feedbackPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver, mainPage);
        feedbackPage= new FeedbackPage(driver, mainPage);
        mainPage.open();
    }


    @Test
    @DisplayName("Проверка, что после добавления товара, на корзине появляется значок уведомления")
    public void addToBasketNotification() {

        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.addToBasket();
        mainPage.timeOutDuration(5);
        mainPage.scrollPage(driver, 0, 0);
        mainPage.allureScreenshot("Значок уведомлений");
        WebElement element = driver.findElement(COUNT_BASKET_NOTIFICATION);
        String countText = element.getText().trim();
        System.out.println(countText);
        int count = Integer.parseInt(countText);

        assertTrue(count >= 1, "Товар в корзину не добавлен");

    }

    @Test
    @DisplayName("Проверка добавления в корзину выбранного товара")
    public void addItemToBasket() {
        basketPage.addToCartItems(value);
        String name_item_in_basket = driver.findElement(BOOK_TITLE_IN_BASKET).getText();
        System.out.println(name_item_in_basket);
        assertEquals(value, name_item_in_basket, "Добавлена не та книга");

    }

    @Test
    @DisplayName("Проверка соответствия количества добавленного товара и значку уведомлений")
    public void countItemInBasket() {
        basketPage.addToCartItems(value);
        String int_in_cart = driver.findElement(CART_INT).getAttribute("value");
        System.out.println(int_in_cart + " " + "in cart");
        String count_notif = driver.findElement(COUNT_BASKET_NOTIFICATION).getText();
        System.out.println(count_notif);
        assertEquals(int_in_cart, count_notif, "Количество товара разное");


    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
