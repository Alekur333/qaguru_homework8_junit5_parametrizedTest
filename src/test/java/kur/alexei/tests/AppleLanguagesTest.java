package kur.alexei.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import kur.alexei.pages.BasePage;
import kur.alexei.testData.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

public class AppleLanguagesTest extends BasePage {

    TestData testData = new TestData();
    SelenideElement headline = $("div[data-unit-id=\"holiday-2021\"] .headline");

    @ValueSource(strings = {"it", "fr"})
    @ParameterizedTest(name = "Страница есть на языке {0}")
    @DisplayName("Проверка загрузки главной страницы по выбранному языку")
    void urlLanguageValueSourceTest(String localUrlEnd) {

        open(testData.startUrl + localUrlEnd);
        assertThat(url()).contains(localUrlEnd);
        takeScreenshot();
    }

    @CsvSource(
            delimiter = '!',
            value = {
                    "it! Regala un Natale meraviglioso a tutte e tutti.",
                    "fr! Cette année, tout le monde est de la fête.",
                    "de! Mach Weihnachten zum Fest für alle."}
    )
    @ParameterizedTest(name = "На языке {0} в блоке holiday-2021 есть заголовок {1}")
    @DisplayName("Проверка соотвествия заголовка блока holiday-2021 главной страницы выбранному языку")
    void holiday2021HeadingsLanguageCsvSourceTest(String localUrlEnd, String headingsText) {

        open(testData.startUrl + localUrlEnd);
        headline.shouldBe(visible);
        headline.shouldHave(text(headingsText));
        takeScreenshot();
    }

    static Stream<Arguments> holiday2021HeadingsLanguageMethodSourceTest() {
        return Stream.of(
                Arguments.of("it", "Regala un Natale meraviglioso a tutte e tutti."),
                Arguments.of("fr", "Cette année, tout le monde est de la fête."),
                Arguments.of("de", "Mach Weihnachten zum Fest für alle.")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "На языке {0} в блоке holiday-2021 есть заголовок {1}")
    @DisplayName("Проверка соотвествия заголовка блока holiday-2021 главной страницы выбранному языку")
    void holiday2021HeadingsLanguageMethodSourceTest(String localUrlEnd, String headingsText) {

        open(testData.startUrl + localUrlEnd);
        headline.shouldBe(visible);
        headline.shouldHave(text(headingsText));
        takeScreenshot();
    }
}
