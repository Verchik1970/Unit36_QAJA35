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
import static org.openqa.selenium.By.xpath;
import static pages.BasketPage.*;
import static pages.MainPage.*;


public class AddToBasketTest {
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
        String name_item_inbasket = driver.findElement(BOOK_TITLE_IN_BASKET).getText();
        System.out.println(name_item_inbasket);
        assertEquals(value, name_item_inbasket, "Добавлена не та книга");

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

    @Test
    @DisplayName("Проверка что при нажатии на кнопку Очистить корзину - товары удаляются из корзины")
    public void deleteFromBasket() {
        basketPage.addToCartItems(value);
        driver.findElement(DELETE_FROM_CART).click();
        String noItems = driver.findElement(NO_ITEMS_INTHE_CART).getText();
        assertEquals("В корзине ничего нет.", noItems);


    }

    @Test
    @DisplayName("Проверка правильного отображения суммы в корзине при увеличении количества товаров")
    public void checkSuminBasket() {
        basketPage.addToCartItems(value);
        int sum1 = basketPage.stringToIntWithSplit(SUMMA_IN_CART);
        basketPage.replaceValue(CART_INT);
        mainPage.timeOutDuration(2);
        driver.findElement(RECOUNT).click();
        int sum2 = basketPage.stringToIntWithSplit(SUMMA_IN_CART);
        assertEquals(sum1 * 3, sum2, "Итоговая сумма не изменилась ");
    }

    @Test
    @DisplayName("Проверка возможности отложить товар из корзины")
    public void postponeItems() {
        basketPage.addToCartItems(value);
        basketPage.clickBTN(POSTPONE_BTN);
        String noItems = driver.findElement(NO_ITEMS_INTHE_CART).getText();
        assertEquals("В корзине ничего нет.", noItems);
    }

    @Test
    @DisplayName("Проверка что при нажатии на кнопку крестик товары из корзины удалятся")
    public void deleteBtnChoice() {
        basketPage.addToCartItems(value);
        driver.findElement(DELETE_BTN).click();
        String noItems = driver.findElement(NO_ITEMS_INTHE_CART).getText();
        assertEquals("В корзине ничего нет.", noItems);

    }

    @Test
    @DisplayName("Проверка при нажатии на кнопку ввести Код - откроется поле для ввода кода скидки")
    public void dicountBtnCheck() {
        basketPage.addToCartItems(value);
        driver.findElement(DISCOUNT_CODE_BTN).click();
        String discountInput = driver.findElement(DISCOUNT_INPUT).getText();
        assertEquals("Введите DISCODe:", discountInput);

    }

    @Test
    @DisplayName("Кнопка Оформить заказ вызывает страницу оформления заказа ")
    public void placeOrder() {
        basketPage.addToCartItems(value);
        driver.findElement(PLACE_ORDER_BTN).click();
        String order = driver.findElement(PAGE_ORDER_INFO).getText();
        System.out.println(order);
        assertEquals("Оформление заказа /", order);
    }

    @Test
    @DisplayName("Тест проверят, что найденные товары соответствуют искомой фразе")
    public void itemTitleAssert() {
        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.allureScreenshot("ВВод тестового слова");
        mainPage.timeOutDuration(5);
        String title = driver.findElement(ITEM_TITLE).getText();
        mainPage.allureScreenshot("Найденные книги");

        System.out.println(title + " " + ", a искомая книга " + value);
        assertEquals(title, value);


    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
