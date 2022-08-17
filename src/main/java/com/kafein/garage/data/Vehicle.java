package com.kafein.garage.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erenadiguzel
 */
public abstract class Vehicle {

    protected VehicleType type;
    protected int slotsNeeded;
    protected String color;
    protected String licencePlate;
    protected Ticket ticket;

    private List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();

    protected Vehicle(String color, String licencePlate) {
        this.color = color;
        this.licencePlate = licencePlate;
        this.ticket = new Ticket(this);
    }

    public int getSlotsNeeded() {
        return slotsNeeded;
    }

    public VehicleType getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void parkInSlot(ParkingSlot s) {
        parkingSlots.add(s);
    }

    public List<Integer> getParkingSlotNumbers() {
        List<Integer> parkingSlotNumbers = new ArrayList<>();
        parkingSlots.forEach(parkingSlot -> parkingSlotNumbers.add(parkingSlot.getNumber()));
        return parkingSlotNumbers;
    }

    public void leaveTheParkingSlot() {
        parkingSlots.forEach(ParkingSlot::leaveVehicle);
        parkingSlots.clear();
    }
}
