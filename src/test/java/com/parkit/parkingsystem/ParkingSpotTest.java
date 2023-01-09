package com.parkit.parkingsystem;

import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotTest {

    @Test
    public void equalsTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        ParkingSpot parkingSpot2 = new ParkingSpot(1, ParkingType.BIKE, false);
        ParkingSpot parkingSpot3 = parkingSpot;
        Ticket theTicket = new Ticket();
        assertTrue(parkingSpot.equals(parkingSpot3));
        assertFalse(parkingSpot.equals(theTicket));
        assertTrue(parkingSpot.equals(parkingSpot2));
    }

    @Test
    public void hashCodeTest (){
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        assertEquals(1, parkingSpot.hashCode());
    }
}
