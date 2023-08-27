package org.example.courier;
import org.example.utils.Utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.models.CreateCourier;
import org.example.models.DeleteCourier;
import org.example.models.LogInCourier;
import static io.restassured.RestAssured.given;


public class ClientCourier {
    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    public static final String CREATE_URL = "/api/v1/courier";
    public static final String LOGIN_URL = "/api/v1/courier/login";
    private static final String DELETE_URL = "/api/v1/courier/:id";
    public ClientCourier() {RestAssured.baseURI = BASE_URI;}

    @Step("Создание клиента")
    public ValidatableResponse create(CreateCourier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(CREATE_URL)
                .then();
    }

    @Step("Логин клиента")
    public ValidatableResponse login(LogInCourier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(loginCourier)
                .post(LOGIN_URL)
                .then();
    }

    @Step("Удаление клиента")
    public ValidatableResponse delete(DeleteCourier deleteCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(deleteCourier)
                .delete(DELETE_URL)
                .then();
    }
}


