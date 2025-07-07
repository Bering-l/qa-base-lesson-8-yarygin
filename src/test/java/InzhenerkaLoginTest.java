import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InzhenerkaLoginTest {
    private static final LoginPage LOGIN_PAGE = new LoginPage();
    @BeforeAll
    public static void setUp() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/selenide.properties"));
            Configuration.browser = properties.getProperty("selenide.browser");
            Configuration.browserSize = "1920x1080";
            Configuration.headless = Boolean.parseBoolean(properties.getProperty("selenide.headless"));
            Configuration.remote = properties.getProperty("selenide.remote");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Авторизация существующего пользователя")
    public void loginTest() {
        LOGIN_PAGE.openPage();
        LOGIN_PAGE.setUsername("admin");
        LOGIN_PAGE.setPasswordField("admin123");
        LOGIN_PAGE.login();
        LOGIN_PAGE.logout();
    }

    @Test
    @DisplayName("Авторизация с пустой формой")
    public void authorizationWithoutEnteringLoginAndPasswordTest() {
        LOGIN_PAGE.openPage();
        LOGIN_PAGE.setUsername("");
        LOGIN_PAGE.setPasswordField("");
        LOGIN_PAGE.login();

        $(".card.shadow.alert.alert-danger").shouldBe(visible);
        String alertText = $(".card.shadow.alert.alert-danger h2.card-title").getText();
        assertEquals("Вход не выполнен", alertText);
    }

    @Test
    @DisplayName("Авторизация несуществующего пользователя")
    public void authorizationWithIncorrectLoginAndPasswordTest() {
        LOGIN_PAGE.openPage();
        LOGIN_PAGE.setUsername("incorrectName");
        LOGIN_PAGE.setPasswordField("incorrectPassword");
        LOGIN_PAGE.login();

        $(".card.shadow.alert.alert-danger").shouldBe(visible);
        String alertText = $(".card.shadow.alert.alert-danger h2.card-title").getText();
        assertEquals("Вход не выполнен", alertText);
    }
}