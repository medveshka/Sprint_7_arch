package org.example.models;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.example.order.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetOrderListTest {
    private CreateCourier createCourier;
    private ClientCourier clientCourier;
    private ClientOrder clientOrder;
    private LogInCourier logInCourier;

    @Before
    public void setUp() {
        createCourier = CourierGenerator.randomCourier();
        clientCourier = new ClientCourier();
        clientOrder = new ClientOrder();
        logInCourier = new LogInCourier(createCourier.getLogin(), createCourier.getPassword());
    }

    @Test
    @DisplayName("Список заказов курьера не пустой")
    public void checkOrderListNotEmpty() {
        clientCourier.create(createCourier);
        int courierId = clientCourier.login(logInCourier).extract().path("id");
        int orderTrack = clientOrder.createOrder().extract().path("track");
        int orderId = clientOrder.getOrderByNumber(orderTrack).extract().path("order.id");
        clientOrder.acceptOrder(orderId, courierId);
        ValidatableResponse response = clientOrder.getCourierOrderList(courierId);
        assertEquals(200, response.extract().statusCode());
        assertNotNull(response.extract().path("orders"));
    }

}