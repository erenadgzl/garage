package com.kafein.garage.service;

import com.kafein.garage.data.Ticket;
import com.kafein.garage.exception.GarageIsFullException;
import com.kafein.garage.data.Garage;
import com.kafein.garage.data.Vehicle;
import com.kafein.garage.exception.TicketNotFoundException;
import com.kafein.garage.model.TicketDTO;
import com.kafein.garage.model.VehicleDTO;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author erenadiguzel
 */

@Service
public class IGarageService implements GarageService {

    @Autowired
    private Garage garage;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TicketDTO park(Vehicle vehicle) throws GarageIsFullException {
        logger.info(String.format("Vehicle arrived: %s need %d parking slot.", vehicle.getLicencePlate(), vehicle.getSlotsNeeded()));
        if (!garage.garageIsAvailable(vehicle)) {
            throw new GarageIsFullException("Garage is full!");
        }
        if (!garage.parkTheVehicle(vehicle)) {
            throw new GarageIsFullException("Garage is full!");
        }
        logger.info(String.format("Allocated %d slot.", vehicle.getSlotsNeeded()));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vehicle.getTicket(), TicketDTO.class);
    }

    @Override
    public List<VehicleDTO> getStatus() {
        Set<Vehicle> vehicles = garage.getVehicles();
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Vehicle, VehicleDTO> propertyMapper = modelMapper.createTypeMap(Vehicle.class, VehicleDTO.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(Vehicle::getParkingSlotNumbers, VehicleDTO::setParkingSlotNumbers)
        );
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        vehicles.forEach(vehicle -> {
            VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
            vehicleDTOList.add(vehicleDTO);
        });
        return vehicleDTOList;
    }

    @Override
    public boolean leave(String ticketNo) throws TicketNotFoundException {
        Ticket ticket = garage.getTicketByTicketNo(ticketNo);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found!");
        }
        garage.leaveTheVehicle(ticket);
        logger.info(String.format("The vehicle (%s) left the parking lot.", ticket.getVehicle().getLicencePlate()));
        return true;
    }
}
