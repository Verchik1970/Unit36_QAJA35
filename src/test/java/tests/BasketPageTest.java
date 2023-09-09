package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasketPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.BasketPage.*;


public class BasketPageTest {
    private static WebDriver driver;
    public static MainPage mainPage;
    private static BasketPage basketPage;

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver, mainPage);
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
    @DisplayName("Проверка при нажатии на кнопку ввести Код - откроется поле для ввода кода скидки")
    public void discountBtnCheck() {
        basketPage.addToCartItems(value);
        driver.findElement(DISCOUNT_CODE_BTN).click();
        String discountInput = driver.findElement(DISCOUNT_INPUT).getText();
        assertEquals("Введите DISCODe:", discountInput);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка что при нажатии на кнопку Очистить корзину - товары удаляются из корзины")
    public void deleteFromBasket() {
        basketPage.addToCartItems(value);
        driver.findElement(DELETE_FROM_CART).click();
        String noItems = driver.findElement(NO_ITEMS_INTHE_CART).getText();
        assertEquals("В корзине ничего нет.", noItems);

    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка правильного отображения суммы в корзине при увеличении количества товаров")
    public void checkSumInBasket() {
        basketPage.addToCartItems(value);
        int sum1 = basketPage.stringToIntWithSplit(SUMMA_IN_CART);
        basketPage.replaceValue(CART_INT);
        mainPage.timeOutDuration(2);
        driver.findElement(RECOUNT).click();
        int sum2 = basketPage.stringToIntWithSplit(SUMMA_IN_CART);
        mainPage.allureScreenshot("Итоговая сумма");
        assertEquals(sum1 * 3, sum2, "Итоговая сумма не изменилась ");
    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка возможности отложить товар из корзины")
    public void postponeItems() {
        basketPage.addToCartItems(value);
        basketPage.clickBTN(POSTPONE_BTN);
        String noItems = driver.findElement(NO_ITEMS_INTHE_CART).getText();
        mainPage.allureScreenshot("Нет товаров в корзине");
        assertEquals("В корзине ничего нет.", noItems);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка что при нажатии на кнопку крестик товары из корзины удалятся")
    public void deleteBtnChoice() {
        basketPage.addToCartItems(value);
        driver.findElement(DELETE_BTN).click();
        String noItems = driver.findElement(NO_ITEMS_INTHE_CART).getText();
        assertEquals("В корзине ничего нет.", noItems);

    }


    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кнопка Оформить заказ вызывает страницу оформления заказа ")
    public void placeOrder() {
        basketPage.addToCartItems(value);
        driver.findElement(PLACE_ORDER_BTN).click();
        String order = driver.findElement(PAGE_ORDER_INFO).getText();
        System.out.println(order);
        assertEquals("Оформление заказа /", order);
    }

    @AfterEach
    void tearDown() {
    }
}
