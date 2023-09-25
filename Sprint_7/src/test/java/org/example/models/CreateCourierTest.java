package org.example.models;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.courier.*;

import static org.junit.Assert.assertEquals;

public class CreateCourierTest {
    private CreateCourier courier;
    private CreateCourier courierCourierWithoutName;
    private ClientCourier client;
    private LogInCourier loginCourier;


    @Before
    public void setUp() {
        courier = CourierGenerator.randomCourier();
        courierCourierWithoutName = CourierGenerator.courierWithoutName();
        client = new ClientCourier();
        loginCourier = new LogInCourier(courier.getLogin(), courier.getPassword());
    }


    @After
    public void tearDown() {
        int num = client.login(loginCourier).extract().path("id");
        DeleteCourier deleteCourier = new DeleteCourier(num);
        client.delete(deleteCourier);
    }

    @Test
    @DisplayName("Создание курьера")
    public void checkCreateCourier() {
        ValidatableResponse response = client.create(courier);
        assertEquals(201, response.extract().statusCode());
        assertEquals(true, response.extract().path("ok"));
    }

    @Test
    @DisplayName("Создание курьера без имени")
    public void checkCreatingCouriersWithoutRequiredField() {
        client.create(courier);
        ValidatableResponse response = client.create(courierCourierWithoutName);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    public void checkCreatingCouriersWithSameLogin() {
        client.create(courier);
        ValidatableResponse response = client.create(courier);
        assertEquals(409, response.extract().statusCode());
        assertEquals("Этот логин уже используется", response.extract().path("message"));
    }
}