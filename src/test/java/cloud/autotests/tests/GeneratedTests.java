package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.Assertions;
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
    void shouldFindResultsByFilters() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Input 'Java' in search field and find results", () -> {
            $("input[type='text']").setValue("Java").sendKeys(Keys.ENTER);
        });
        step("Showing  total results for java", () -> {
            String results = $(".rc-NumberOfResultsSection").getText();
            Assertions.assertEquals("Showing 1455 total results for \"Java\"", results);

        });
        step("First element contains 'Java' in the search results", () -> {
            $$x("//li[@class='ais-InfiniteHits-item']")
                    .get(0).find(".card-title").shouldHave(Condition.text("java"));
        });
    }

    @Test
    @Description("Find results by filters tags")
    @DisplayName("Find results")
    void shouldSearchResults() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Input 'Java' in search field and find results", () -> {
            $("input[type='text']").setValue("Java").sendKeys(Keys.ENTER);
        });
        step("Filter by ...", () -> {
            int size = $$x("//div[@class='rc-SearchFilter css-7473qq']").size();
            for (int i = 0; i < size; i++) {
                $$x("//div[@class='rc-SearchFilter css-7473qq']").get(i).click();
                $$("input[type='checkbox']").get(0).click();
            }
        });
        step("List with course should contains courses with filtered tags", () -> {
            int size = $$("li[class='ais-InfiniteHits-item']").size();
            for (int i = 0; i < size; i++) {
                $$(".partner-name").get(i).shouldHave(Condition.text("Beginner"));
                $$(".partner-name").get(i).shouldHave(Condition.text("Google Cloud"));
                $$(".pillContainer").get(i).shouldHave(Condition.text("Course"));
            }
        });
    }




    @Test
    @Description("Click the button 'Clear all' should delete all filters")
    @DisplayName("Clear all filters")
    void shouldClearAllFilters() {
        step("open 'https://www.coursera.org/'", () -> {
            open("https://www.coursera.org/");
        });
        step("Input 'Java' in search field and find results", () -> {
            $("input[type='text']").setValue("Java").sendKeys(Keys.ENTER);
        });
        step("Filter by ...", () -> {
           int size = $$x("//div[@class='rc-SearchFilter css-7473qq']").size();
           for(int i = 0 ; i < size; i++) {
               $$x("//div[@class='rc-SearchFilter css-7473qq']").get(i).click();
               $$("input[type='checkbox']").get(0).click();
           }
        });
        step("Click the button 'Clear All' ", () -> {
            $("button[class='ais-ClearRefinements-button']").click();
        });
        step("Box with selected filters should disappear", () -> {
            $("div[class='rc-CurrentAppliedFilters horizontal-box']").should(Condition.disappear);
        });
        step("Default first course should be - 'Java Programming and Software Engineering Fundamentals'", () -> {
            $$x("//li[@class='ais-InfiniteHits-item']")
                    .get(0).find(".card-title").shouldHave(Condition.text("Java Programming and Software Engineering Fundamentals"));
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
            $x("//h1[@class='head']").shouldHave(Condition.text(textWithGreeting));
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