package com.kafein.garage.controller;

import com.kafein.garage.data.*;
import com.kafein.garage.exception.GarageIsFullException;
import com.kafein.garage.exception.TicketNotFoundException;
import com.kafein.garage.model.LeaveRequestModel;
import com.kafein.garage.model.ParkRequestModel;
import com.kafein.garage.model.TicketDTO;
import com.kafein.garage.model.VehicleDTO;
import com.kafein.garage.service.GarageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author erenadiguzel
 */

@RestController
@RequestMapping("/garage")
public class GarageController {

    private GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping("/park")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketDTO> parkTheVehicle(@Valid @RequestBody ParkRequestModel parkRequestModel) throws GarageIsFullException {
        Vehicle vehicle = VehicleFactory.createVehicle(parkRequestModel);
        return ResponseEntity.ok(garageService.park(vehicle));
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VehicleDTO>> garageStatus() {
        return ResponseEntity.ok(garageService.getStatus());
    }

    @PostMapping("/leave")
    public ResponseEntity<Boolean> leave(@RequestBody LeaveRequestModel leaveRequestModel) throws TicketNotFoundException {
        return ResponseEntity.ok(garageService.leave(leaveRequestModel.getTicketNo()));
    }
}


