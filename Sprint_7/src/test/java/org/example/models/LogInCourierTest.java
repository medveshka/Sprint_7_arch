package org.example.models;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.courier.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.example.utils.Utils;

public class LogInCourierTest {
    private CreateCourier courier;
    private ClientCourier client;
    private LogInCourier logInCourier;
    private LogInCourier logInCourierWithoutPassword;
    private LogInCourier logInIncorrectLogin;

    @Before
    public void setUp() {
        courier = CourierGenerator.randomCourier();;
        client = new ClientCourier();
        logInCourier = new LogInCourier(courier.getLogin(), courier.getPassword());
        logInCourierWithoutPassword = new LogInCourier(courier.getLogin(), "");
        logInIncorrectLogin = new LogInCourier(Utils.randomString(9), courier.getPassword());
    }

    @After
    public void tearDown() {
        int id = client.login(logInCourier).extract().path("id");
        DeleteCourier deleteCourier = new DeleteCourier(id);
        client.delete(deleteCourier);
    }

    @Test
    @DisplayName("Авторизация курьера")
    public void checkSuccessfulLogin() {
        client.create(courier);
        ValidatableResponse response = client.login(logInCourier);
        assertEquals(200, response.extract().statusCode());
        assertNotNull(response.extract().path("id"));
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    public void checkLoginWithoutPasswordField() {
        client.create(courier);
        ValidatableResponse response = client.login(logInCourierWithoutPassword);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для входа", response.extract().path("message"));
    }

    @Test
    @DisplayName("Авторизация с использованием неверного логина")
    public void checkLoginIncorrectLogin() {
        client.create(courier);
        ValidatableResponse response = client.login(logInIncorrectLogin);
        assertEquals(404, response.extract().statusCode());
        assertEquals("Учетная запись не найдена", response.extract().path("message"));
    }
}