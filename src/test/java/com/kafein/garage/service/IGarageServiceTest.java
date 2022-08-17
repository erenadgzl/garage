package com.kafein.garage.service;

import com.kafein.garage.data.Garage;
import com.kafein.garage.data.Truck;
import com.kafein.garage.data.Vehicle;
import com.kafein.garage.exception.GarageIsFullException;
import com.kafein.garage.exception.TicketNotFoundException;
import com.kafein.garage.model.TicketDTO;
import com.kafein.garage.model.VehicleDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author erenadiguzel
 */

@SpringBootTest
class IGarageServiceTest {

    @Autowired
    private GarageService garageService;

    @Autowired
    private Garage garage;

    @AfterEach
    void init() {
        garage.getVehicles().forEach(vehicle -> {
            try {
                garageService.leave(vehicle.getTicket().getTicketNo());
            } catch (TicketNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void park_success() throws GarageIsFullException {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);

        TicketDTO ticketDTO = garageService.park(vehicle);

        assertAll(
                () -> assertNotNull(ticketDTO),
                () -> assertNotNull(ticketDTO.getTicketNo()),
                () -> assertNotNull(ticketDTO.getEnteredDate()),
                () -> assertEquals(4, vehicle.getParkingSlots().size())
        );
    }

    @Test
    void getStatus_success() throws GarageIsFullException {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);

        TicketDTO ticketDTO = garageService.park(vehicle);

        List<VehicleDTO> vehicleDTOList = garageService.getStatus();

        assertAll(
                () -> assertNotNull(ticketDTO),
                () -> assertNotNull(ticketDTO.getTicketNo()),
                () -> assertNotNull(ticketDTO.getEnteredDate()),
                () -> assertEquals(4, vehicle.getParkingSlots().size()),
                () -> assertEquals(1, vehicleDTOList.size()),
                () -> assertEquals(vehicle.getLicencePlate(), vehicleDTOList.get(0).getLicencePlate()),
                () -> assertEquals(vehicle.getColor(), vehicleDTOList.get(0).getColor()),
                () -> assertEquals(vehicle.getParkingSlotNumbers().size(), vehicleDTOList.get(0).getParkingSlotNumbers().size()),
                () -> assertEquals(vehicle.getType(), vehicleDTOList.get(0).getType())
        );
    }

    @Test
    void leave_success() {
        Exception exception = assertThrows(TicketNotFoundException.class, () -> {
            garageService.leave("test-ticket-number");
        });

        String expectedMessage = "Ticket not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}