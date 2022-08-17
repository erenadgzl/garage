package com.kafein.garage.data;

/**
 * @author erenadiguzel
 */
public class ParkingSlot {

    private final int number;
    private boolean isEmpty;
    private Vehicle vehicle;

    public ParkingSlot(int number) {
        this.number = number;
        this.isEmpty = true;
    }

    public int getNumber() {
        return number;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void park(Vehicle vehicle) {
        this.isEmpty = false;
        this.vehicle = vehicle;
        vehicle.parkInSlot(this);
    }

    public void leaveVehicle(){
        isEmpty = true;
        vehicle = null;
    }
}
