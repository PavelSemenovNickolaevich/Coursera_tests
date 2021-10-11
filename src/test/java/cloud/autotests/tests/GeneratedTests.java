package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class GeneratedTests extends TestBase {
    @Test
    @Description("Soon to be implemented by you (or QA.GURU engineers)")
    @DisplayName("Coursera tests")
    void generatedTest() {
        step("open 'https://www.coursera.org/'", () -> {
            step("// todo: just add selenium action");
        });
    }

    @Test
    @Description("Soon to be implemented by you (or QA.GURU engineers)")
    @DisplayName("Coursera tests")
    void shouldRegistrationTest() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Press button 'Join for FREE'", () -> {
            $x("//a[@data-e2e='header-signup-button']").click();
        });
        step("Fill the field: name, email, password", () -> {
            $(By.id("name")).setValue("test1");
            $(By.id("email")).setValue("Warhammer1987-@gmail.com");
            $(By.id("password")).setValue("Rjcvjltcfyn1987-");
        });
        step("Confirm registration", () -> {
            $x("//button[@type='submit']").click();
        });
    }

    @Test
    @Description("Autogenerated test")
    @DisplayName("Page title should have header text")
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
    @Description("Autogenerated test")
    @DisplayName("Page console log should not have errors")
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