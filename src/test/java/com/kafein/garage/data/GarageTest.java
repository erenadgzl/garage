package com.kafein.garage.data;

import com.kafein.garage.exception.GarageIsFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author erenadiguzel
 */
class GarageTest {

    private Garage garage;

    @BeforeEach
    void init() {
        garage = new Garage();
    }

    @Test
    void garageIsAvailable_success() {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);

        assertAll(
                () -> assertNotNull(garage),
                () -> assertTrue(garage.garageIsAvailable(vehicle))
        );
    }

    @Test
    void parkTheVehicle_success() {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);

        assertAll(
                () -> assertNotNull(garage),
                () -> assertTrue(garage.parkTheVehicle(vehicle))
        );
    }

    @Test
    void getVehicles_success() throws GarageIsFullException {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);
        garage.parkTheVehicle(vehicle);

        Set<Vehicle> vehicles = new HashSet<>();
        vehicles.add(vehicle);

        assertAll(
                () -> assertNotNull(garage),
                () -> assertEquals(vehicles, garage.getVehicles())
        );
    }

    @Test
    void getTicketByTicketNo_success() {
        assertAll(
                () -> assertNotNull(garage),
                () -> assertNull(garage.getTicketByTicketNo("test-ticket-no"))
        );
    }

    @Test
    void leaveTheVehicle_success() throws GarageIsFullException {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);
        garage.parkTheVehicle(vehicle);

        garage.leaveTheVehicle(vehicle.ticket);
        assertAll(
                () -> assertNotNull(garage),
                () -> assertEquals(10,garage.getAvailableSlots())
        );
    }
}