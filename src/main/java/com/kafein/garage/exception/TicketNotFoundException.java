package com.kafein.garage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author erenadiguzel
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Ticket not found!")
public class TicketNotFoundException extends Exception {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
