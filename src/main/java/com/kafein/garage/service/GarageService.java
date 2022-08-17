package com.kafein.garage.service;

import com.kafein.garage.exception.GarageIsFullException;
import com.kafein.garage.data.Vehicle;
import com.kafein.garage.exception.TicketNotFoundException;
import com.kafein.garage.model.TicketDTO;
import com.kafein.garage.model.VehicleDTO;

import java.util.List;

/**
 * @author erenadiguzel
 */
public interface GarageService {
    TicketDTO park(Vehicle vehicle) throws GarageIsFullException;

    List<VehicleDTO> getStatus();

    boolean leave(String ticketNo) throws TicketNotFoundException;
}
