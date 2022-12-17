package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InteractiveShell {

    private static final Logger logger = LogManager.getLogger("InteractiveShell");

    //On ne parvient pas à simuler les appels à l'intérieur, du coup on fait comme si
    //
    //
    private InputReaderUtil inputReaderUtil ;
    private ParkingSpotDAO parkingSpotDAO ;
    private TicketDAO ticketDAO ;
    public ParkingService parkingService;
    public InteractiveShell()
    {
        this.inputReaderUtil = new InputReaderUtil();
        this.parkingSpotDAO = new ParkingSpotDAO();
        this.ticketDAO = new TicketDAO();
        this.parkingService = new ParkingService(inputReaderUtil,parkingSpotDAO,ticketDAO);
    }

    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void setInputReaderUtil(InputReaderUtil inputReaderUtil) {
        this.inputReaderUtil = inputReaderUtil;
    }

    public void loadInterface(){
    //public static void loadInterface(){
        logger.info("App initialized!!!");
        System.out.println("Welcome to Parking System!");

        boolean continueApp = true;
        /*InputReaderUtil inputReaderUtil = new InputReaderUtil();
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        TicketDAO ticketDAO = new TicketDAO();
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
*/
        while(continueApp){
            loadMenu();
            int option = inputReaderUtil.readSelection();
            //int option = this.inputReaderUtil.readSelection();
            switch(option){
                case 1: {
                	
                    parkingService.processIncomingVehicle();
                    break;
                }
                case 2: {
                    parkingService.processExitingVehicle();
                    break;
                }
                case 3: {
                    System.out.println("Exiting from the system!");
                    continueApp = false;
                    break;
                }
                default: System.out.println("Unsupported option. Please enter a number corresponding to the provided menu");
            }
        }
    }

    private static void loadMenu(){
        System.out.println("Please select an option. Simply enter the number to choose an action");
        System.out.println("1 New Vehicle Entering - Allocate Parking Space");
        System.out.println("2 Vehicle Exiting - Generate Ticket Price");
        System.out.println("3 Shutdown System");
    }



}