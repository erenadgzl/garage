package com.kafein.garage.data;

/**
 * @author erenadiguzel
 */
public class Jeep extends Vehicle {

    public Jeep(String color, String licencePlate) {
        super(color, licencePlate);
        this.type = VehicleType.JEEP;
        this.slotsNeeded = 2;
    }
}
