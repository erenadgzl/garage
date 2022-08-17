package com.kafein.garage.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author erenadiguzel
 */
@SpringBootTest
class TicketTest {

    private Ticket ticket;

    @BeforeEach
    void init(){
        String color = "RED";
        String licencePlate = "34-TRUCK-3354";
        Vehicle vehicle = new Truck(color, licencePlate);

        ticket = new Ticket(vehicle);
    }

    @Test
    void getTicketNo_success() {
        assertAll(
                () -> assertNotNull(ticket),
                () -> assertNotNull(ticket.getTicketNo())
        );
    }

    @Test
    void getVehicle_success() {
        assertAll(
                () -> assertNotNull(ticket),
                () -> assertNotNull(ticket.getVehicle())
        );
    }

    @Test
    void getEnteredDate_success() {
        assertAll(
                () -> assertNotNull(ticket),
                () -> assertNotNull(ticket.getEnteredDate())
        );
    }

    @Test
    void payTicket_success() {
        ticket.payTicket();

        assertAll(
                () -> assertNotNull(ticket),
                () -> assertEquals(TicketStatus.PAID, ticket.getStatus()),
                () -> assertEquals(ticket.getVehicle().getSlotsNeeded(), ticket.getAmount())
        );
    }
}