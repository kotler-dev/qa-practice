package hw2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTest {

    static WebDriver driver;
    static File fileLogo;

    @BeforeAll
    static void beforeAll() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("javascript.enabled", true);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        driver = new FirefoxDriver(firefoxOptions);
        WebDriverRunner.setWebDriver(driver);
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        fileLogo = new File("src/test/resources/toolsqalogo.png");
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @Test
    void fillStudentRegistrationFormTest() {
        open("/automation-practice-form");

        // Submit
        $("#submit").scrollTo().click();

        // Student data
        $("#firstName").setValue("Morty");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("morty@smith.qa");
        $(".custom-control", 0).click();
        $("#userNumber").setValue("1010101010");

        // Date of Birth
        // $(".react-datepicker__month-select").selectOption(0 ,2);
        // $(".react-datepicker__year-select").selectOption(0 ,85);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("11");
        $(".react-datepicker__year-select").selectOptionByValue("1999");
        $(".react-datepicker__day--003").click();

        // Subjects
        $("#subjectsInput").setValue("C").scrollTo();
        $(Selectors.byText("Computer Science")).click();

        // Hobbies
        // $("#hobbiesWrapper").$(".custom-control", 1).click();
        $("#hobbiesWrapper").$(".custom-control-label", 1).click();

        // Picture
        $("#uploadPicture").uploadFile(fileLogo);

        // Current Address
        $("#currentAddress").setValue("Earth");

        // State and City
        $("#react-select-3-input").setValue("H");
        $(Selectors.byText("Haryana")).click();
        $("#react-select-4-input").setValue("K");
        $(Selectors.byText("Karnal")).click();

        // Submit
        $("#submit").scrollTo().click();

        // Validate
        // AssertEquals.assertEquals("Thanks for submitting the form", $("#example-modal-sizes-title-lg").getText());
        // $(".table-responsive").getText();
        $(".table-responsive").shouldHave(
                Condition.text("Student Name Morty Smith"),
                Condition.text("Student Email morty@smith.qa"),
                Condition.text("Gender Male"),
                Condition.text("Mobile 1010101010"),
                Condition.text("Date of Birth 03 December,1999"),
                Condition.text("Subjects Computer Science"),
                Condition.text("Hobbies Reading"),
                Condition.text("Picture toolsqalogo.png"),
                Condition.text("Address Earth"),
                Condition.text("State and City Haryana Karnal")
        );

        $("#closeLargeModal").click();
    }
}
