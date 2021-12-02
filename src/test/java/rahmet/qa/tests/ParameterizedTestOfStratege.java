package rahmet.qa.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.IOException;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTestOfStratege extends BaseConfig {

    @DisplayName("Отображение главных разделов в верхнем меню")
    @Tag("blocker")
    @ValueSource(strings = {"Sony", "Nintendo", "Xbox", "PREMIUM"})
    @ParameterizedTest
    void checkMainNavigationTitles(String mainTitle) {
        open("https://www.stratege.ru");
        $("#menu")
                .find(byTitle(mainTitle))
                .shouldBe(Condition.visible);
    }

    @DisplayName("Отображение названии игр в списке игр после выбора буквы")
    @Tag("major")
    @CsvSource(value = {"A - A Boy and His Blob",
                        "B - Babel Rising",
                        "C - C14 Dating",
                        "D - Dadish",
                        "E - EA SPORTS FIFA Football",
                        "F - F1 2010",
                        "G - Gabbuchi",
                        "H - Hades"
    },
                        delimiter = '-')
    @ParameterizedTest
    void checkGameTitlesThroughTheLetters(String letter, String gameTitle) {
        open("https://www.stratege.ru/playstation/games");
        $(".abc_filter").find(byText(letter)).click();
        $(".listGamesList")
                .find(byText(gameTitle))
                .shouldBe(Condition.visible);
    }

    static Stream<Arguments> checkPopularFilteredGames() {
        return Stream.of(
                Arguments.of("The Legend of Zelda: Breath of the Wild"),
                Arguments.of("Super Mario Odyssey"),
                Arguments.of("Mario Kart 8 Deluxe"),
                Arguments.of("Astral Chain"),
                Arguments.of("Bayonetta 2"),
                Arguments.of("Splatoon 2"),
                Arguments.of("Animal Crossing: New Horizons"),
                Arguments.of("Fire Emblem: Awakening"),
                Arguments.of("New Super Mario Bros. 2"),
                Arguments.of("ZombiU")
        );
    }

    @MethodSource
    @DisplayName("Отображение названии игр в списке игр после фильтрации в разделе Nintendo")
    @Tag("minor")
    @ParameterizedTest
    void checkPopularFilteredGames(String gameTitle) {
        open("https://www.stratege.ru/nintendo/games#args:ajax=1");
        $("#sortFilterSelect").click();
        $("#sortFilterSelect_box_link_sx_5").click();
        $(".listGamesList")
                .find(byText(gameTitle))
                .shouldBe(Condition.visible);
    }

    @EnumSource(Platform.class)
    @DisplayName("Отображение списка результатов после поиска по названию платформы")
    @Tag("minor")
    @ParameterizedTest
    void checkListOfResultsAfterSearch(Platform platform) {
        open("https://www.stratege.ru");
        $("#gb_ids_site_search_input")
                .setValue(platform.getPlatform())
                .submit();
        $("#ssid_search_bx_list").shouldBe(Condition.visible);
    }

    @Disabled
    @DisplayName("Тест который не запустится")
    @Tag("trivial")
    @Test
    void checkForPlacebo() throws IOException {
        Assertions.assertTrue(true);
    }
}
