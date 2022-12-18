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

        //On crée une instancede interctive shell
        /*
        on lui passe les informations dont on a besoin pour faire le mock
        tout ce qui est utile pour fair le mock doit être transmis et impact donc l'existant à coder
       inputReaderUtil doit être passé à InteractiveShell pour être mocké
         */


        when (inputReaderUtil.readSelection()).thenReturn(1,2,3);
        // On met 1,2 et surtout 3 pour pouvoir ensuite sortir du "while(continueApp){"
        interactiveShell = new InteractiveShell();
        interactiveShell.setInputReaderUtil(inputReaderUtil);
        interactiveShell.setParkingService(parkingService);

        interactiveShell.loadInterface();

        verify(interactiveShell.parkingService, Mockito.times(1)).processIncomingVehicle();
        verify(interactiveShell.parkingService, Mockito.times(1)).processExitingVehicle();


    }

}
