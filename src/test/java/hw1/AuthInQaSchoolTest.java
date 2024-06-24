package hw1;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.CdpVersionFinder;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

public class AuthInQaSchoolTest {
//
//    WebDriver driver;
//
//    @BeforeAll
//    static void setupClass() {
//        WebDriverManager.firefoxdriver().setup();
//    }
//
//    @BeforeEach
//    void setupTest() {
//        driver = new FirefoxDriver();
//    }
//
//    @AfterEach
//    void teardown() {
//        driver.quit();
//    }
//
//    @Test
//    void test() {
//        // Exercise
//        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
//        String title = driver.getTitle();
//
//        // Verify
//        assertThat(title).contains("Selenium WebDriver");
//    }

    @Test
    void authTest() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "/home/kotler/.cache/selenium/chrome/linux64/114.0.5735.133/chrome");
        Logger.getLogger(CdpVersionFinder.class.getName()).setLevel(Level.SEVERE);
        System.setProperty("webdriver.chromium.driver", "/usr/lib64/chromium-browser");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://school.qa.guru/cms/system/login");

        Thread.sleep(1000);

        if (driver.getPageSource().contains("I'm Feeling Lucky")) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        driver.quit();
    }

    @Test
    void checkLaunchFirefox() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.selenium.dev");
        driver.getTitle();
        driver.quit();
    }

    @Test
    void checkLaunchChromium() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev");
        driver.getTitle();
        driver.quit();
    }

    @Test
    void launchBrowser() throws InterruptedException {
        checkLaunchFirefox();
        open("https://www.selenium.dev");
        Thread.sleep(2000);
    }
}
