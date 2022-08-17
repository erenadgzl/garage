package com.kafein.garage.data;

/**
 * @author erenadiguzel
 */
public class Car extends Vehicle {

    public Car(String color, String licencePlate) {
        super(color, licencePlate);
        this.type = VehicleType.CAR;
        this.slotsNeeded = 1;
    }
}
