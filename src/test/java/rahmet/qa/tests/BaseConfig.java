package rahmet.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;

public class BaseConfig {

    @BeforeAll
    public static void beforeTests() {

        Configuration.browserSize="1920x1080";
    }
}
