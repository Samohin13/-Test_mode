package ru.netology.javaqa.Data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.openqa.selenium.devtools.v85.dom.model.ShadowRootType;

import java.util.Locale;

public class DataGenerator {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataGenerator() {

    }

    // method generation LOGIN
    public static String randomLogin() {
        return FAKER.name().username();
    }

    // method generation Password
    public static String randomPassword() {
        return FAKER.internet().password();
    }
    public static class Registration {
        private Registration() {
        }
        // незарегистрированный
        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(randomLogin(), randomPassword(), status);
        }
        // регистрация
        public static RegistrationDto getRegistrationUser(String status){
            return API.sendRequest(getUser(status));
        }
    }

    @Value
    public static class RegistrationDto{
        String login;
        String password;
        String status;
    }

}

