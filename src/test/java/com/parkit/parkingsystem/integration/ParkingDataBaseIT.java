package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    //AOU
    private static final Logger logger = LogManager.getLogger("ParkingDataBaseIT");

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
        dataBasePrepareService.clearDataBaseEntries();
    }

    @AfterAll
    private static void tearDown(){

    }

    @Test
    public void testParkingACar(){
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.processIncomingVehicle();
        //TODO: check that a ticket is actualy saved in DB and Parking table is updated with availability

        Ticket theTicket = ticketDAO.getTicket("ABCDEF");
        assertEquals("ABCDEF", theTicket.getVehicleRegNumber());
        assertNotNull(theTicket);  //Le ticket n'est pas nul => donc en BD


        // pour ce ticket supposé etre en BD, on regarde
        //On doit requeter dans la BD parking si l valeur attribuée est marquée comme oqp
        //On vérifie en BD que la place supposée etre utilisée  l'est vraiment en BD
        ParkingSpot theParkingSpot = parkingSpotDAO.getParkingSpot(theTicket.getParkingSpot().getId());
        //theTicket.getParkingSpot().getId();
        assertFalse(theParkingSpot.isAvailable());

        //assertEquals("ABCDEF", ticketDAO.getTicket("ABCDEF").getVehicleRegNumber());
        //assertTrue(parkingSpotDAO.updateParking(ticketDAO.getTicket("ABCDEF").getParkingSpot()));
        //assertNotNull(ticketDAO.getTicket("ABCDEF").getParkingSpot());
        // Il doit retourner une valeur
   }

    @Test
    public void testParkingLotExit()  {
        try {
            //testParkingACar();
            ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
            parkingService.processIncomingVehicle();
            Thread.sleep(2000);
            parkingService.processExitingVehicle();
            //TODO: check that the fare generated and out time are populated correctly in the database
            // On regarde s'il ya eu une mise à jour du ticket suite au processus de sortie.
            Ticket theTicket = ticketDAO.getTicket("ABCDEF");
            assertTrue (ticketDAO.updateTicket(theTicket));
            //ou bien ?
            //on va vérifier que pour cette variable  qui prend en paramètre un num de véhicule
            // que dnas la BD on a une MAJ des in, out, price
            assertNotNull(theTicket.getInTime());
            assertNotNull(theTicket.getOutTime());
            assertEquals(0.0,theTicket.getPrice());
        }
        catch (InterruptedException e)
        {
            logger.error("Error sleep : ",e);
        }
    }

}
