package com.parkit.parkingsystem;

import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotTest {

    @Test
    public void equalsTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        ParkingSpot parkingSpot2 = new ParkingSpot(1, ParkingType.BIKE, false);
        Integer iNumber = 1;
        assertTrue(parkingSpot.equals(parkingSpot));
        assertFalse(iNumber.equals(null));
        assertFalse(iNumber.equals(parkingSpot2));
    }

    @Test
    public void hashCodeTest (){
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        assertEquals(1, parkingSpot.hashCode());
    }
}
