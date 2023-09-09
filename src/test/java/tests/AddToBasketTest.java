package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
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
    private static WebDriver driver;
    public static MainPage mainPage;
    private static BasketPage basketPage;
    public static FeedbackPage feedbackPage;

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver, mainPage);
        feedbackPage = new FeedbackPage(driver, mainPage);
        mainPage.open();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach

    public void setUp() {

        mainPage.open();
    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
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
    @Severity(value = SeverityLevel.CRITICAL)

    @DisplayName("Проверка добавления в корзину выбранного товара")
    public void addItemToBasket() {
        basketPage.addToCartItems(value);
        String name_item_in_basket = driver.findElement(BOOK_TITLE_IN_BASKET).getText();
        System.out.println(name_item_in_basket);
        assertEquals(value, name_item_in_basket, "Добавлена не та книга");

    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
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
/*
        driver.quit();
*/
    }
}
