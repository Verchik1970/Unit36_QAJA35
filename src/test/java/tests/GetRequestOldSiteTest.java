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

    URL url = new URL("https://new.books.ru/");


    public GetRequestOldSiteTest() throws MalformedURLException {
    }


    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка кода ответа старого сайта")
    public void testGetRequest() {
        given()
                .when()
                .get(url)
                .then()
                .statusCode(200);
    }

}
