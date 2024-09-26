package ru.netology.javaqa.test;

import com.codeborne.selenide.Condition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.javaqa.Data.DataGenerator.Registration.getRegistrationUser;
import static ru.netology.javaqa.Data.DataGenerator.Registration.getUser;
import static ru.netology.javaqa.Data.DataGenerator.randomLogin;

public class test {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should be registered and active ")
    void ShouldBeRegisteredAndActive() {
        var registrationUser = getRegistrationUser("active");
        $("[data-test-id= login] input").setValue(registrationUser.getLogin());
        $("[data-test-id= password] input").setValue(registrationUser.getPassword());
        $("button.button").click();
        $("h2").shouldHave(Condition.exactText("  Личный кабинет ")).shouldBe(Condition.visible);

    }

    @Test
    @DisplayName("Should be blocked user ")
    void ShouldBeBlocked() {
        var blockedUser = getRegistrationUser(("blocked"));
        $("[data-test-id= login] input").setValue(blockedUser.getLogin());
        $("[data-test-id= password] input").setValue(blockedUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"), Duration.ofSeconds(10))
                .shouldBe((Condition.visible));

    }

    @Test
    @DisplayName("Should be not registration user")
    void ShouldBeNotRegistrationUser() {
        var notRegistrationUser = getUser(("active"));
        $("[data-test-id='login'] input").setValue(notRegistrationUser.getLogin());
        $("[data-test-id='password'] input").setValue(notRegistrationUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.text("Неверно указан логин или пароль"), Duration.ofSeconds(10))
                .shouldBe((Condition.visible));

    }

    @Test
    @DisplayName("ShouldBeIthIncorrectLogin")
    void ShouldBeIthIncorrectlogin() {
        var notRegistrationUser = getUser(("active"));
        var wrongLogin = randomLogin();
        $("[data-test-id='login'] input").setValue(wrongLogin);
        $("[data-test-id='password'] input").setValue(notRegistrationUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.text("Неверно указан логин или пароль"), Duration.ofSeconds(10))
                .shouldBe((Condition.visible));

    }
}
