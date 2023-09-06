package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public class MainPage {
    public static WebDriver driver;

    final String index = "https://books.ru/";
    private final String popUpCloseButton = "//div[@class='closeX']";

    public static final String SEARCH_INPUT = "//header/div[2]/div[1]/div[1]/form[1]/input[2]"; //поле ввода
   public static final String SEARCH_BUTTON_HEADER = "//header/div[2]/div[1]/div[1]/form[1]/button[1]"; //кнопка лупа
    private static final String SEARCH_RESULT_COUNT = "//body/div[1]/div[1]/main[1]/div[3]/div[1]/div[1]";
    public static final String ERROR_FOUND_ITEMS = "p.p.search-result > b:nth-child(2)";
    private static final String REGISTRATION_BTN ="//header/div[2]/div[1]/div[2]/a[1]";
    private static final String BASKET_BTN ="//header/div[2]/div[1]/div[2]/a[2]";
    public static final String CHOICE_CITY_BUTTON = "a.link-icon.link-icon-region.header_nav-top_item";
    public static final String REGION_NAME = "//label[contains(text(),'Мой регион:')]";
    public static final String myRegion = "Мой регион";
    public static final String CHANGE_BUTTON_CITY ="#header_mainregion_trigger"; //выбор изменить
    public static final String DROPDOWN_CITIES = "#header_mainregion_other"; //открытие списка
    public static final String CITY_LOCATION = "//option[contains(text(),'Россия')]"; //Россия //*[@id="header_mainregion_other"]/option[4]
    public static final String RUSSIA_CITIES_CHANGE = "//select[@id='header_mainregion_other2']"; // алтай //*[@id="header_mainregion_other2"]/option[8]
    public static final String ALTAY_REGION = "/html/body/header/div[1]/nav/div/a";
    public  static final String test_city_value = "Россия, Алтай";
    public static final String HELP_BTN = "//header/div[1]/nav[1]/a[2]"; //div[@id='search-list-popup']
    public static final String DROPDOWN_CATEGORY_SEARCH = "a.header_search-filter.everywhere:nth-child(1)"; //кнопка выпадающего списка
            public static final String DROPDOWN_SEARCH = "//div[@id='search-list-popup']"; //выпадающий список категори1
    public static final String CATEGOTY_ELECTROBOOKS = "#inlinesearch_where_file"; //категория электронные книги
    public static final String CATEGORY_RESULT_EBOOKS = "a.viewed-items-book.file.viewed-items-book-card";
    public static final String TO_BASKET = "#add_to_cart";
    public static final String COUNT_BASKET_NOTIFICATION = "/html/body/header/div[2]/div/div[2]/a[2]/span";
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(index);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        closePopUp();
    }

    public void closePopUp() {
        driver.findElement(By.xpath(popUpCloseButton)).click();
    }

    public void inputSearchInput(String value){
        //ввод поисковой фразы
        driver.findElement(xpath(SEARCH_INPUT)).sendKeys(value);
    }
    public void timeOutDuration(long sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

    }


    public void scrollPage(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + ", " + y + ")");
    }



    public void allureScreenshot(String description){
        Allure.addAttachment(description,
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    public void clickSearhButton (){
        //нажатие кнопки поиска -лупы
        driver.findElement(By.xpath(SEARCH_BUTTON_HEADER)).click();

    }
    public int getResultsCount() {// подсчет количества элементов поиска
        List<WebElement> resultSearch = driver.findElements(By.xpath(SEARCH_RESULT_COUNT));
        return resultSearch.size();

    }
    /*public void findElement(By.name, ){
        driver.findElement(By.cssSelector(CHOICE_CITY_BUTTON)).click();

    }*/
    public void clearSearchInput(){
        driver.findElement(By.xpath(SEARCH_INPUT)).clear();
    }
    public void registrationBtnClick(){
        driver.findElement(By.xpath(REGISTRATION_BTN)).click();
    }

    public void basketBtnClick(){
        driver.findElement(By.xpath(BASKET_BTN)).click();
    }
    public void addToBasket(){
        driver.findElement(By.cssSelector(TO_BASKET)).click();
    }

}



