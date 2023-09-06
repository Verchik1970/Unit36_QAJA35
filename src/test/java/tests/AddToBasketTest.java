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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.BasketPage.*;
import static pages.MainPage.*;


public class AddToBasketTest {
    private  WebDriver driver;
    public  static MainPage mainPage;
    private  BasketPage basketPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver);
        mainPage.open();
    }

    private String value = "Трудно быть богом";

    @Test
    @DisplayName("Проверка, что после добавления товара, на корзине появляется значек уведомления")
    public void addToBasketNotification() {

        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.addToBasket();
        mainPage.timeOutDuration(5);
        mainPage.scrollPage(driver, 0,0);
        mainPage.allureScreenshot("Значек уведомлений");
        WebElement element = driver.findElement(By.xpath(COUNT_BASKET_NOTIFICATION));
        String countText = element.getText().trim();
        System.out.println(countText);
        int count = Integer.parseInt(countText);

        assertTrue(count >= 1, "Товар в корзину не добавлен");

    }

    @Test
    @DisplayName("Проверка добавления в корзину выбранного товара")
    public void addItemToBasket() {
        basketPage.addToCartItems(value);
        String name_item_inbasket = driver.findElement(By.className(BOOK_TITLE_IN_BASKET)).getText();
        System.out.println(name_item_inbasket);
        assertTrue(value.equals(name_item_inbasket), "Добавлена не та книга");

    }

    @Test
    @DisplayName("Проверка соответствия количества добавленного товара и значку уведомлений")
    public void countItemInBasket() {
        basketPage.addToCartItems(value);
        String int_in_cart = driver.findElement(By.id(CART_INT)).getAttribute("value");
        System.out.println(int_in_cart + " " + "incart");
        String count_notif = driver.findElement(By.xpath(COUNT_BASKET_NOTIFICATION)).getText();
        System.out.println(count_notif);
        assertTrue(int_in_cart.equals(count_notif), "Количество товара разное");


    }
    @Test
    @DisplayName("Проверка правильного отображения суммы в корзине при увеличении количества товаров")
    public void checkSuminBasket(){
        basketPage.addToCartItems(value);
        int sum1 = basketPage.stringToIntWithSplit(By.cssSelector(SUMMA_IN_CART));
        basketPage.replaceValue(By.id(CART_INT));
        mainPage.timeOutDuration(2);
        driver.findElement(By.cssSelector(RECOUNT)).click();
        int sum2 = basketPage.stringToIntWithSplit(By.cssSelector(SUMMA_IN_CART));
        assertEquals(sum1*3, sum2, "Итоговая сумма не изменилась ");
    }

    @Test
    @DisplayName("Проверка возможности отложить товар из корзины")
    public void postponeItems(){
        basketPage.addToCartItems(value);
        basketPage.clickBTN(By.cssSelector(POSTPONE_BTN));
        String noItems = driver.findElement(By.cssSelector(NO_ITEMS_INTHE_CART)).getText();
        assertEquals("В корзине ничего нет.", noItems);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
