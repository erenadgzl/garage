package com.kafein.garage.data;

/**
 * @author erenadiguzel
 */
public class Truck extends Vehicle {

    public Truck(String color, String licencePlate) {
        super(color, licencePlate);
        this.type = VehicleType.TRUCK;
        this.slotsNeeded = 4;
    }
}
