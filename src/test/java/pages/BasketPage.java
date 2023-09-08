package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class BasketPage {
    public static WebDriver driver;
    public MainPage mainPage;
    public static String value = "Трудно быть богом";

    public BasketPage(WebDriver driver, MainPage mainPage) {
        BasketPage.driver = driver;
        PageFactory.initElements(driver, this);
        this.mainPage = mainPage;
    }

    public static By BOOK_TITLE_IN_BASKET = By.className("book_title");
    public static By CART_INT = By.id("cart_cnt_id"); //количество товара в корзине
    public static By SUMMA_IN_CART = By.cssSelector("h4.h4.check_price-value"); //сумма в корзине
    public static By RECOUNT = By.cssSelector( "button.button.button-order:nth-child(2)");
    public static By POSTPONE_BTN = By.cssSelector("a.dotted-link:nth-child(2)");
    public static By NO_ITEMS_INTHE_CART = By.cssSelector("div.col_1 ");
    public static By DELETE_FROM_CART = By.cssSelector("a.dotted-link:nth-child(3)");
    public static By DELETE_BTN = By.cssSelector("a.delete-btn");
    public static By DISCOUNT_CODE_BTN = By.cssSelector("#discode-input-trigger");
    public static By DISCOUNT_INPUT = By.cssSelector("label.label.coupon_label:nth-child(1)");
    public static By PLACE_ORDER_BTN = By.cssSelector("a.button.button-in-cart.check_price_btn");
    public static By PAGE_ORDER_INFO = By.xpath("/html/body/div[1]/div/main/div[1]/div/a[1]");

    public static By ITEM_TITLE = By.cssSelector(" a.custom-link.book-catalog_item_title ");
    public void addToCartItems(String value) {
        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.addToBasket();
        mainPage.timeOutDuration(5);
        mainPage.basketBtnClick();
    }

    public int stringToIntWithSplit(By locator) {
        String[] int_in_value = driver.findElement(locator).getText().split(" ");
        return Integer.parseInt(int_in_value[0]);
    }

    public void replaceValue(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '3')", element);

    }

    public void clickBTN(By locator) {
        driver.findElement(locator).click();
    }

}
