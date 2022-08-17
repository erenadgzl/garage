package com.kafein.garage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kafein.garage.data.VehicleType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erenadiguzel
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {

    private VehicleType type;
    private String color;
    private String licencePlate;
    private TicketDTO ticket;

    private List<Integer> parkingSlotNumbers = new ArrayList<>();

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }

    public List<Integer> getParkingSlotNumbers() {
        return parkingSlotNumbers;
    }

    public void setParkingSlotNumbers(List<Integer> parkingSlotNumbers) {
        this.parkingSlotNumbers = parkingSlotNumbers;
    }
}
