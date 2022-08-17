package com.kafein.garage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author erenadiguzel
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Garage is full!")
public class GarageIsFullException extends Exception {
    public GarageIsFullException(String message) {
        super(message);
    }
}