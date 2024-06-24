package hw1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    @BeforeAll
    static void beforeAll() {
        // -Dselenide.browser=firefox
//        System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("javascript.enabled", true);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        WebDriverRunner.setWebDriver(driver);
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
        webdriver().driver().close();
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
//        WebElement col = Selenide.$$("script").getElements().getFirst();
//        executeJavaScript("$('script').remove()");
        $("#userName").setValue("userName");
        $("#userEmail").setValue("user@email.qa");
        $("#currentAddress").setValue("currentAddress");
        $("#permanentAddress").setValue("permanentAddress");
        Selenide.$("#submit").scrollTo();
        $("#submit").click();

        $("#output").$("#name").shouldHave(text("name"));
    }
}
