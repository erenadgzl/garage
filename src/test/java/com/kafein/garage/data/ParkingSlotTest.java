package com.kafein.garage.data;

import com.kafein.garage.model.ParkRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author erenadiguzel
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ParkingSlotTest {

    int parkingSlotNumber = 1;
    private ParkingSlot parkingSlot;

    @BeforeEach
    void init(){
        parkingSlot = new ParkingSlot(parkingSlotNumber);
    }

    @Test
    void getNumber_success() {
        assertAll(
                () -> assertNotNull(parkingSlot),
                () -> assertEquals(parkingSlotNumber, parkingSlot.getNumber())
        );
    }

    @Test
    void getVehicle_success() {
        ParkRequestModel requestModel = new ParkRequestModel();
        String color = "RED";
        requestModel.setColor(color);
        requestModel.setType(VehicleType.TRUCK);
        String licencePlate = "34-TRUCK-3354";
        requestModel.setLicencePlate(licencePlate);
        Vehicle vehicle = new Truck(color, licencePlate);

        try (MockedStatic<VehicleFactory> utilities = Mockito.mockStatic(VehicleFactory.class)) {
            utilities.when(() -> VehicleFactory.createVehicle(requestModel)).thenReturn(vehicle);
            parkingSlot.park(VehicleFactory.createVehicle(requestModel));

            assertAll(
                    () -> assertNotNull(parkingSlot),
                    () -> assertEquals(vehicle, parkingSlot.getVehicle())
            );
        }

    }

    @Test
    void isEmpty_success() {
        assertAll(
                () -> assertNotNull(parkingSlot),
                () -> assertTrue(parkingSlot.isEmpty())
        );
    }

    @Test
    void park_success() {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);

        parkingSlot.park(vehicle);
        assertAll(
                () -> assertNotNull(parkingSlot),
                () -> assertEquals(vehicle,parkingSlot.getVehicle()),
                () -> assertFalse(parkingSlot.isEmpty())
        );
    }

    @Test
    void leaveVehicle() {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);
        parkingSlot.park(vehicle);
        parkingSlot.leaveVehicle();

        assertAll(
                () -> assertNotNull(parkingSlot),
                () -> assertNull(parkingSlot.getVehicle()),
                () -> assertTrue(parkingSlot.isEmpty())
        );
    }
}