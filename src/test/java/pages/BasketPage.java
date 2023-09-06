package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static tests.AddToBasketTest.mainPage;

public class BasketPage {
    public static WebDriver driver;


    public BasketPage(WebDriver driver) {
        BasketPage.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public static String BOOK_TITLE_IN_BASKET = "book_title";
    public static String CART_INT = "cart_cnt_id"; //количество товара в корзине
    public static String SUMMA_IN_CART = "h4.h4.check_price-value"; //сумма в корзине
    public static String RECOUNT = "button.button.button-order:nth-child(2)";
    public static String POSTPONE_BTN = "a.dotted-link:nth-child(2)";
    public static String NO_ITEMS_INTHE_CART = "div.col_1 ";

    public void addToCartItems(String value){
        mainPage.inputSearchInput(value);
        mainPage.clickSearhButton();
        mainPage.addToBasket();
        mainPage.timeOutDuration(5);
        mainPage.basketBtnClick();
    }
    public int stringToIntWithSplit(By locator){
        String[] int_in_value = driver.findElement(locator).getText().split(" ");
        return Integer.parseInt(int_in_value[0]);
    }
    public void replaceValue(By locator){
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '3')", element);

    }
    public void clickBTN(By locator){
        driver.findElement(locator).click();
    }

}
