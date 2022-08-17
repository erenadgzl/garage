package com.kafein.garage.model;

import javax.validation.constraints.NotNull;

/**
 * @author erenadiguzel
 */
public class LeaveRequestModel {

    @NotNull(message = "Ticket no can not be null!")
    private String ticketNo;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
