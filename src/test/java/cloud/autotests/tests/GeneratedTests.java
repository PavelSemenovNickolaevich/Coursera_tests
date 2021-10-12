package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static cloud.autotests.helpers.TestData.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class GeneratedTests extends TestBase {
    @Test
    @Description("Input word in search field and search")
    @DisplayName("Search Fields")
    void shouldSearchResults() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Input 'Java' in search field and find results", () -> {
            $("input[type='text']").setValue("Java").sendKeys(Keys.ENTER);
        });
    }

    @Test()
    @Flaky
    @Description("User should be authorized")
    @DisplayName("Authorization test")
    void authorizationTest() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Press button 'Log In'", () -> {
            $x("//a[@data-e2e='header-login-button']").click();
        });
        step("Fill the field: name, email, password", () -> {
            $(By.id("email")).setValue("TestUserQa@gmail.com");
            $(By.id("password")).setValue("newtestuser");
        });
        step("Confirm authorization", () -> {
            $x("//button[@type='submit']").click();
        });
        step("Check onboarding with greetings and user name", () -> {
            $(".rc-UserPortrait").shouldBe(Condition.visible);
        });
    }

    @Test()
    @Flaky
    @Description("New user should be registered")
    @DisplayName("Registration test")
    void shouldRegistrationTest() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Press button 'Join for FREE'", () -> {
            $x("//a[@data-e2e='header-signup-button']").click();
        });
        step("Fill the field: name, email, password", () -> {
            $(By.id("name")).setValue(generateName);
            $(By.id("email")).setValue(generateEmail);
            $(By.id("password")).setValue(generatePassword);
        });
        step("Confirm registration", () -> {
            $x("//button[@type='submit']").click();
        });
        step("Check onboarding with greetings and user name", () -> {
            String textWithGreeting = String.format("Welcome to Coursera, %s", generateName);
            $x("//h1[@class=\"head\"]").shouldHave(Condition.text(textWithGreeting));
        });
    }

    @Test
    @Description("Page title should have header text")
    @DisplayName("Page title test")
    void titleTest() {
        step("Open url 'https://www.coursera.org/'", () ->
                open("https://www.coursera.org/"));

        step("Coursera | Build Skills with Online Courses from Top Institutions", () -> {
            String expectedTitle = "Coursera | Build Skills with Online Courses from Top Institutions";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Description("Page console log should not have errors")
    @DisplayName("Page console log test")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://www.coursera.org/'", () ->
                open("https://www.coursera.org/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}