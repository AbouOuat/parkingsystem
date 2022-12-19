package com.parkit.parkingsystem;

import com.parkit.parkingsystem.service.InteractiveShell;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InteractiveShellTest {

    @Mock
    private InteractiveShell interactiveShell;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @Mock
    private static ParkingService parkingService;

    @Test
    public void loadInterfaceTest (){
        when (inputReaderUtil.readSelection()).thenReturn(1,2,3);
        interactiveShell = new InteractiveShell();
        interactiveShell.setInputReaderUtil(inputReaderUtil);
        interactiveShell.setParkingService(parkingService);

        interactiveShell.loadInterface();

        verify(interactiveShell.parkingService, Mockito.times(1)).processIncomingVehicle();
        verify(interactiveShell.parkingService, Mockito.times(1)).processExitingVehicle();
    }

}
