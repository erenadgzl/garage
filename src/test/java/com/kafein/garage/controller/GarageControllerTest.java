package com.kafein.garage.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafein.garage.data.*;
import com.kafein.garage.exception.TicketNotFoundException;
import com.kafein.garage.model.LeaveRequestModel;
import com.kafein.garage.model.ParkRequestModel;
import com.kafein.garage.model.TicketDTO;
import com.kafein.garage.model.VehicleDTO;
import com.kafein.garage.service.GarageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author erenadiguzel
 */

@SpringBootTest
@AutoConfigureMockMvc
class GarageControllerTest {

    @Autowired
    private GarageService garageService;

    @Autowired
    private Garage garage;

    private MockMvc mockMvc;


    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
        final GarageController garageController = new GarageController(garageService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(garageController).build();
    }

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
    void parkTheVehicle_success() throws Exception {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        ParkRequestModel requestModel = new ParkRequestModel();
        requestModel.setColor(color);
        requestModel.setType(VehicleType.TRUCK);
        requestModel.setLicencePlate(licencePlate);

        String json = mapper.writeValueAsString(requestModel);

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/garage/park/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TicketDTO ticketDTO = mapper.readValue(response, TicketDTO.class);

        assertAll(
                () -> assertNotNull(ticketDTO),
                () -> assertNotNull(ticketDTO.getTicketNo()),
                () -> assertNotNull(ticketDTO.getEnteredDate())
        );
    }

    @Test
    void garageStatus_success() throws Exception {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);
        garageService.park(vehicle);

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/garage/status/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<VehicleDTO> vehicleDTOList = mapper.readValue(response, new TypeReference<>(){});

        assertAll(
                () -> assertNotNull(vehicleDTOList),
                () -> assertNotNull(vehicleDTOList.get(0)),
                () -> assertEquals(licencePlate, vehicleDTOList.get(0).getLicencePlate()),
                () -> assertEquals(color, vehicleDTOList.get(0).getColor())
        );
    }

    @Test
    void leave_success() throws Exception {
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);
        garageService.park(vehicle);

        LeaveRequestModel requestModel = new LeaveRequestModel();
        requestModel.setTicketNo(vehicle.getTicket().getTicketNo());
        String json = mapper.writeValueAsString(requestModel);

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/garage/leave/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue( Boolean.parseBoolean(response))
        );
    }
}