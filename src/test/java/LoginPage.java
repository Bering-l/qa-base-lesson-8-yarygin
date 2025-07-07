import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameField = $("#username");
    private final SelenideElement passwordField = $("[name='password']");
    private final SelenideElement loginButton = $("button[type='submit']");
    private final SelenideElement logoutButton = $(".mb-3");

    public void openPage() {
        String URL = "http://qa-stand-login.inzhenerka.tech/login";
        Selenide.open(URL);
    }

    public void setUsername(String username) {
        usernameField.setValue(username);
    }

    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    public void login() {
        loginButton.click();
    }

    public void logout() {
        logoutButton.click();
    }
}
