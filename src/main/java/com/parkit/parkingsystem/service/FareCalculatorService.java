package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket, boolean isOldUser){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        //TODO: Some tests are failing here. Need to check if this logic is correct

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
        double duration =  (outHour - inHour)/3600000.0;
        double dPrice;

        if (duration < 0.50)
        {
           dPrice = Fare.RATE_UNDER_HALF_HOUR;
        }
        else {
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    dPrice =duration * Fare.CAR_RATE_PER_HOUR;
                    break;
                }
                case BIKE: {
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