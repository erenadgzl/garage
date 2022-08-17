package com.kafein.garage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

/**
 * @author erenadiguzel
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {

    private String ticketNo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enteredDate;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public LocalDateTime getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(LocalDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }
}
