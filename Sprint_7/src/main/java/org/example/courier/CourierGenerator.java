package org.example.courier;
import org.example.models.CreateCourier;

import static org.example.utils.Utils.randomString;

public class CourierGenerator {

    public static CreateCourier randomCourier() {
        return new CreateCourier()
                .withLogin(randomString(8))
                .withPassword(randomString(12))
                .withFirstName(randomString(10));
    }

    public static CreateCourier courierWithoutName() {
        return new CreateCourier()
                .withLogin(randomString(8))
                .withPassword(randomString(12));
    }
}