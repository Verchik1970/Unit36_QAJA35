package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
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
        driver.quit();
    }

    @Test
    @DisplayName("Проверка кода ответа старого сайта")
    public void testGetRequest() {
        given()
                .when()
                .get(getLinkOldSite())
                .then()
                .statusCode(200);
    }

}
