package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket, boolean isOldUser){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

//        int inHour = ticket.getInTime().getHours();
 //       int outHour = ticket.getOutTime().getHours();
        
        //TODO: Some tests are failing here. Need to check if this logic is correct
   //     int duration = outHour - inHour;


        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
        double duration =  (outHour - inHour)/3600000.0;
        double dPrice;

        if (duration < 0.50)
        {
           //ticket.setPrice(Fare.RATE_UNDER_HALF_HOUR);
            dPrice = Fare.RATE_UNDER_HALF_HOUR;
        }
        else {
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    //ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                    dPrice =duration * Fare.CAR_RATE_PER_HOUR;
                    break;
                }
                case BIKE: {
                    //ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                    dPrice =duration * Fare.BIKE_RATE_PER_HOUR;
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown Parking Type");
            }
            if (isOldUser) {
                ticket.setPrice(dPrice*0.095);
            }
            else {
                ticket.setPrice(dPrice);
            }

        }
    }
}