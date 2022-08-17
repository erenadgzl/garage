package com.kafein.garage.data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author erenadiguzel
 */
public class Ticket {

    private Vehicle vehicle;
    private String ticketNo;
    private TicketStatus status;
    private long amount;
    private LocalDateTime enteredDate;
    private LocalDateTime exitedDate;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.ticketNo = UUID.randomUUID().toString();
        this.status = TicketStatus.CREATED;
        this.enteredDate = LocalDateTime.now();
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEnteredDate() {
        return enteredDate;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public long getAmount() {
        return amount;
    }

    public void payTicket(){
        status = TicketStatus.PAID;
        exitedDate = LocalDateTime.now();
        amount = calculateAmount();
    }

    private long calculateAmount() {
        Duration diff = Duration.between(enteredDate, exitedDate);
        return (diff.toHours() + 1) * vehicle.getSlotsNeeded();
    }
}
