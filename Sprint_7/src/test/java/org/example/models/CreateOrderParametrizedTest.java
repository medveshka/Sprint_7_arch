package org.example.models;

import static org.junit.Assert.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;

import static io.restassured.RestAssured.given;


@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {
    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    public static final String CREATE_ORDER_URL = "/api/v1/orders";

    private final String colors;
    private final int expected;

    public CreateOrderParametrizedTest(String colorList, int expected) {
        this.colors = colorList;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "Создание заказа: Наборы данных: {0} {1}")
    public static Object[] getSumData() {
        return new Object[][] {
                {"src/test/java/resources/order_1.json", 201},
                {"src/test/java/resources/order_2.json", 201},
                {"src/test/java/resources/order_3.json", 201},
        };
    }

    @Test
    @DisplayName("Создание заказа с разными цветами")
    public void checkStatusCode201() {
        RestAssured.baseURI = BASE_URI;
        File json = new File(colors);
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .body(json)
                .post(CREATE_ORDER_URL)
                .then();
        assertEquals(expected, response.extract().statusCode());
        assertNotNull(response.extract().path("track"));
    }
}