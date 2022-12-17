package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ParkingServiceTest {

    private static ParkingService parkingService;

    @Mock
    private static InputReaderUtil inputReaderUtil;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO;

    @BeforeEach
    private void setUpPerTest() {
        try {
            when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");

            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
            Ticket ticket = new Ticket();
            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
            ticket.setParkingSpot(parkingSpot);
            ticket.setVehicleRegNumber("ABCDEF");
            //when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
            when(ticketDAO.getTicket("ABCDEF")).thenReturn(ticket);
            when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);

            when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);

            parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
    }

    @Test
    public void processExitingVehicleTest(){
        parkingService.processExitingVehicle();
        verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
    }

    @Test
    public void checkRecurringUserTest(){
        boolean isPresent = parkingService.checkRecurringUser("ABCDEF");
        assertTrue(isPresent, "Le véhicule n'est pas répertorié.");

       //when(ticketDAO.getTicket("AB")).thenReturn(null);
        boolean isAbsent = parkingService.checkRecurringUser("AB");
        assertFalse(isAbsent, "Le véhicule est déjà répertorié.");


    }

    @Test
    public void processIncomingVehicleCARTest()
    {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
        boolean bProcessOk = parkingService.processIncomingVehicle();
        assertTrue(bProcessOk);
    }

    @Test
    public void processIncomingVehicleBIKETest()
    {
        when(inputReaderUtil.readSelection()).thenReturn(2);
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
        boolean bProcessOk = parkingService.processIncomingVehicle();
        assertTrue(bProcessOk);
    }
    @Test
    public void getNextParkingNumberIfAvailableTestOK(){
        when(inputReaderUtil.readSelection()).thenReturn(1);
        //when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
        ParkingSpot theParkingSpot = parkingService.getNextParkingNumberIfAvailable();
        assertNotNull(theParkingSpot, "Le test sur getNextParkingNumberIfAvailable est KO.");
    }
    @Test
    public void getNextParkingNumberIfAvailableTestKO(){
        when(inputReaderUtil.readSelection()).thenReturn(3);
        //when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);

        //AAA  when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(0);

        //ParkingSpot theParkingSpot = parkingService.getNextParkingNumberIfAvailable();
        //assertThrows(Exception.class, () -> parkingService.getNextParkingNumberIfAvailable());
        //assertThrows(Exception.class, () -> parkingService.getNextParkingNumberIfAvailable());

        //
        //assertThrows(java.lang.IllegalArgumentException.class, () -> parkingService.getNextParkingNumberIfAvailable());

        assertNull(parkingService.getNextParkingNumberIfAvailable());

    }

   // @Test
    //public void getNextParkingNumberIfAvailableTestIllegalException(){
      //  when(inputReaderUtil.readSelection()).thenReturn(3);
        //assertThrows(IllegalArgumentException.class, () -> when(inputReaderUtil.readSelection()).thenReturn(3));

        //when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);
        //when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
        //ParkingSpot theParkingSpot = parkingService.getNextParkingNumberIfAvailable();
        //assertTrue(true);
        //assertThrows(Exception.class, () -> parkingService.getNextParkingNumberIfAvailable());
        //assertThrows(Exception.class, () -> parkingService.getNextParkingNumberIfAvailable());
        //assertNull(parkingService.getNextParkingNumberIfAvailable());

    //}


}