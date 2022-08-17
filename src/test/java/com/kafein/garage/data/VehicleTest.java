package com.kafein.garage.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author erenadiguzel
 */
class VehicleTest {

    String color = "RED";
    String licencePlate = "34-TRUCK-3354";
    private Vehicle vehicle;

    @BeforeEach
    void init() {
        vehicle = new Truck(color, licencePlate);
    }

    @Test
    void getSlotsNeeded_success() {
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(4, vehicle.getSlotsNeeded())
        );
    }

    @Test
    void getType_success() {
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(VehicleType.TRUCK, vehicle.getType())
        );
    }

    @Test
    void getColor_success() {
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(color, vehicle.getColor())
        );
    }

    @Test
    void getLicencePlate_success() {
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(licencePlate, vehicle.getLicencePlate())
        );
    }

    @Test
    void getParkingSlots_success() {
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(0, vehicle.getParkingSlots().size())
        );
    }

    @Test
    void getTicket_success() {
        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertNotNull( vehicle.getTicket())
        );
    }

    @Test
    void parkInSlot_success() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        vehicle.parkInSlot(parkingSlot);

        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(1, vehicle.getParkingSlots().size())
        );
    }

    @Test
    void getParkingSlotNumbers_success() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        vehicle.parkInSlot(parkingSlot);

        List<Integer> parkingSlotNumbers = new ArrayList<>();
        parkingSlotNumbers.add(1);

        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(parkingSlotNumbers, vehicle.getParkingSlotNumbers())
        );
    }

    @Test
    void leaveTheParkingSlot_success() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        vehicle.parkInSlot(parkingSlot);

        vehicle.leaveTheParkingSlot();

        assertAll(
                () -> assertNotNull(vehicle),
                () -> assertEquals(0, vehicle.getParkingSlotNumbers().size())
        );
    }
}