package com.kafein.garage.data;

import com.kafein.garage.model.ParkRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author erenadiguzel
 */
class VehicleFactoryTest {

    private Vehicle vehicle;
    private ParkRequestModel requestModel;
    private String color = "RED";
    private String licencePlate = "34-TRUCK-3354";
    private VehicleType type = VehicleType.TRUCK;

    @BeforeEach
    void init(){
        requestModel = new ParkRequestModel();
        requestModel.setColor(color);
        requestModel.setType(type);
        requestModel.setLicencePlate(licencePlate);
        vehicle = new Truck(color, licencePlate);

    }

    @Test
    void createVehicle_success() {

        Vehicle vehicle = VehicleFactory.createVehicle(requestModel);
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(type, vehicle.getType()),
                () -> assertEquals(color, vehicle.getColor()),
                () -> assertEquals(licencePlate, vehicle.getLicencePlate()),
                () -> assertEquals(4, vehicle.getSlotsNeeded())
        );
    }
}