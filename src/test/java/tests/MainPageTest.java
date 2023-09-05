package tests;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MainPage;
import java.io.ByteArrayInputStream;

import java.util.Objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static pages.MainPage.*;

public class MainPageTest  {
    private static WebDriver driver;
    private MainPage mainPage;


    @BeforeEach

    public  void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Проверка работы кнопки выбора региона доставки")
    public void choiceCity(){
        driver.findElement(By.cssSelector(CHOICE_CITY_BUTTON)).click();
        String region = driver.findElement(By.xpath(REGION_NAME)).getText();
        Pattern pattern = Pattern.compile(myRegion,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(region);
        assertTrue(matcher.find(), myRegion);

    }
    @Test
     @DisplayName("Проверка, что кнопка выбора местоположения позволяет выбрать нужный город и регион доставки")
    public void selectCityButton(){
        choiceCity();
        driver.findElement(By.cssSelector(CHANGE_BUTTON_CITY)).click(); //кнопка изменить
        driver.findElement(By.cssSelector(DROPDOWN_CITIES)).click(); // выпадающий список

        driver.findElement(By.xpath(CITY_LOCATION)).click();
        WebElement selectElement = driver.findElement(By.xpath(RUSSIA_CITIES_CHANGE));
        Select select = new Select(selectElement);
        select.selectByVisibleText("Алтай");
        String city_position = driver.findElement(By.xpath(ALTAY_REGION)).getText();
        assertTrue(Objects.equals(test_city_value, city_position));
    }

    @Test
    @DisplayName("Проверка, что кнопка ПОМОЩЬ открывает страницу со справочными материалами")
    public void help_btn (){
        driver.findElement(By.xpath(HELP_BTN)).click();
        String helpTitle = driver.getTitle();
        System.out.println(helpTitle);
        Allure.addAttachment("Открылась страница Справки",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        assert helpTitle.contains("Справка") : "Страница Справки не открылась";
    }


    @Test
    @DisplayName("Проверка, что нажатие на кнопку Вход ведет на страницу регистрации и входа")
    void registrationBTN(){
        mainPage.registrationBtnClick();
        String regTitle = driver.getTitle();
        System.out.println(regTitle);
        Allure.addAttachment("Открылась страница регистрации",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        assert  regTitle.contains("Вход") : "Страница Входа не открылась";

    }
    @Test
    @DisplayName("Проверка, что нажатие на кнопку Корзина ведет на страницу корзины")
    void basketBTNCheck() throws InterruptedException {
        mainPage.basketBtnClick();
/*
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
*/
        String cart= driver.findElement(By.xpath("//h1[contains(text(),'Корзина')]")).getText();

        Allure.addAttachment("Открылась страница корзины",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        assertEquals(cart, "Корзина");

    }
    @AfterEach
   void tearDown() {
        driver.quit();
    }
}
