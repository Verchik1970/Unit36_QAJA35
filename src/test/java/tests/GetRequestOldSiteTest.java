package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static pages.MainPage.*;

public class GetRequestOldSiteTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    URL url = new URL("https://new.books.ru/");

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.init();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach

    public void setUp() {

        mainPage.open();
    }

    public GetRequestOldSiteTest() throws MalformedURLException {
    }

    public URL getLinkOldSite() {
        driver.findElement(LINK_OLD_SITE).click();
        return url;
    }

    @AfterEach
    void tearDown() {
/*
        driver.quit();
*/
    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка кода ответа старого сайта")
    public void testGetRequest() {
        given()
                .when()
                .get(getLinkOldSite())
                .then()
                .statusCode(200);
    }

}
