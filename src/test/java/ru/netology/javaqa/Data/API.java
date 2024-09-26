package ru.netology.javaqa.Data;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.*;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class API {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private API() {
    }

    static DataGenerator.RegistrationDto sendRequest(DataGenerator.RegistrationDto user) {
        // сам запрос
        given() // "дано"
                // указываем, какую спецификацию используем
                .spec(requestSpec)
                // передаём в теле объект, который будет преобразован в JSON
                .body(user)
                // "когда"
                .when().log().all()
                // на какой путь относительно BaseUri отправляем запрос
                .post("/api/system/users")
                // "тогда ожидаем"
                .then().log().all()
                // код 200 OK
                .statusCode(200);

        return user;

    }
}











