package org.example.order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import org.example.models.CreateOrder;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ClientOrder {
    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    public static final String CREATE_ORDER_URL = "/api/v1/orders";
    public static final String GET_ORDER_URL = "/api/v1/orders/track";
    private static final String ACCEPT_ORDER_URL = "/api/v1/orders/accept/";

    public ClientOrder() {
        RestAssured.baseURI = BASE_URI;

    }

    public File json = new File("src/test/java/resources/order_1.json");
    private final CreateOrder order = given()
            .header("Content-type", "application/json")
            .body(json)
            .post(CREATE_ORDER_URL)
            .body()
            .as(CreateOrder.class);

    @Step("Создание заказа")
    public ValidatableResponse createOrder() {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post(CREATE_ORDER_URL)
                .then();
    }

    @Step("Получить заказ по его номеру")
    public ValidatableResponse getOrderByNumber(int track) {
        return given()
                .header("Content-type", "application/json")
                .get(GET_ORDER_URL + "?t=" + track)
                .then();
    }

    @Step("Принять заказ")
    public void acceptOrder(int orderId, int courierId) {
        given()
                .header("Content-type", "application/json")
                .get(ACCEPT_ORDER_URL + orderId + "?courierId=" + courierId)
                .then();
    }



    @Step("Получить список заказов курьера")
    public ValidatableResponse getCourierOrderList(int courierId) {
        return given()
                .header("Content-type", "application/json")
                .get(GET_ORDER_URL + "?courierId=" + courierId)
                .then();
    }
}
