package com.kafein.garage.data;

import com.kafein.garage.exception.GarageIsFullException;

import java.util.*;

/**
 * @author erenadiguzel
 */

public class Garage {

    private static final int NUMBER_OF_SLOTS = 10;
    private int availableSlots = 10;
    private final List<ParkingSlot> parkingSlots;

    public Garage() {
        parkingSlots = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            parkingSlots.add(new ParkingSlot(i + 1));
        }
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public boolean garageIsAvailable(Vehicle vehicle) {
        return vehicle.getSlotsNeeded() < availableSlots;
    }

    public boolean parkTheVehicle(Vehicle vehicle) throws GarageIsFullException {
        int slotNumber = getAvailableParkingSlotNumber(vehicle);
        parkInParkSlot(vehicle, slotNumber);
        return true;
    }

    private int getAvailableParkingSlotNumber(Vehicle vehicle) throws GarageIsFullException {
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (i + vehicle.slotsNeeded <= parkingSlots.size() && parkingSlots.get(i).isEmpty() && isEmptyBeforeParkingSlotOrFirstSlot(i)) {
                for (int j = 0; j < vehicle.getSlotsNeeded(); j++) {
                    if (!parkingSlots.get(i + j).isEmpty()) {
                        break;
                    }
                    if (isTheLastParkingSlotVehicleOccupies(vehicle, j) && isEmptyAfterParkingSlotOrLastSlot(i, j)) {
                        return i + 1;
                    }
                }
            }
        }
        throw new GarageIsFullException("Garage is full!");
    }

    private boolean isTheLastParkingSlotVehicleOccupies(Vehicle vehicle, int j) {
        return j + 1 == vehicle.getSlotsNeeded();
    }

    private boolean isEmptyAfterParkingSlotOrLastSlot(int i, int j) {
        return i + j == parkingSlots.size() - 1 || parkingSlots.get(i + j + 1).isEmpty();
    }

    private boolean isEmptyBeforeParkingSlotOrFirstSlot(int i) {
        return i == 0 || parkingSlots.get(i - 1).isEmpty();
    }

    private void parkInParkSlot(Vehicle vehicle, int slotNumber) {
        for (int i = 0; i < vehicle.getSlotsNeeded(); i++) {
            parkingSlots.get((slotNumber - 1) + i).park(vehicle);
        }
        availableSlots -= vehicle.getSlotsNeeded();
    }

    public Set<Vehicle> getVehicles() {
        Set<Vehicle> vehicles = new HashSet<>();
        parkingSlots.forEach(parkingSlot -> {
            if (!parkingSlot.isEmpty()) {
                vehicles.add(parkingSlot.getVehicle());
            }
        });
        return vehicles;
    }

    public Ticket getTicketByTicketNo(String ticketNo){
        Set<Vehicle> vehicles = getVehicles();
        Optional<Vehicle> vehicle = vehicles.stream().filter(vehicleTemp -> vehicleTemp.getTicket().getTicketNo().equals(ticketNo)).findFirst();
        return vehicle.map(Vehicle::getTicket).orElse(null);
    }

    public void leaveTheVehicle(Ticket ticket){
        ticket.payTicket();
        ticket.getVehicle().leaveTheParkingSlot();
        availableSlots += ticket.getVehicle().getSlotsNeeded();
    }

}
