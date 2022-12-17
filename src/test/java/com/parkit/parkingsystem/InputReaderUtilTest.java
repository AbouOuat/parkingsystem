package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.service.FareCalculatorService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InputReaderUtilTest {
    @Mock
    private static InputReaderUtil InputReaderUtil;

    @Mock
    private static Scanner scan;


@Test
    public void readSelectionTest () {


    String sStringScan = "1"+System.lineSeparator();
    scan = new Scanner(sStringScan);
    InputReaderUtil = new InputReaderUtil();
    InputReaderUtil.setScan(scan);
   assertEquals(1,InputReaderUtil.readSelection());


    sStringScan = System.lineSeparator();
    scan = new Scanner(sStringScan);
    InputReaderUtil = new InputReaderUtil();
    InputReaderUtil.setScan(scan);
    assertEquals (-1,InputReaderUtil.readSelection());




    // assertEquals("CA",InputReaderUtil.readSelection());
        /*assertEquals("",InputReaderUtil.readSelection());
        assertEquals(1,InputReaderUtil.readSelection());*/
       /* when(inputReaderUtil.readSelection()).thenReturn(1);
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
        boolean bProcessOk = parkingService.processIncomingVehicle();*/

    }
    @Test
    public void readVehicleRegistrationNumberTest() {

        String sStringScan = "ABCDEFG"+System.lineSeparator();
        scan = new Scanner(sStringScan);
        InputReaderUtil = new InputReaderUtil();
        InputReaderUtil.setScan(scan);
        assertEquals("ABCDEFG",InputReaderUtil.readVehicleRegistrationNumber());


        sStringScan = System.lineSeparator();
        scan = new Scanner(sStringScan);
        InputReaderUtil = new InputReaderUtil();
        InputReaderUtil.setScan(scan);
        assertThrows(java.lang.IllegalArgumentException.class, ()->InputReaderUtil.readVehicleRegistrationNumber());
}

}
