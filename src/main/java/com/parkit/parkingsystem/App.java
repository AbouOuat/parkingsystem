package com.parkit.parkingsystem;

import com.parkit.parkingsystem.service.InteractiveShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger("App");
    public static void main(String args[]){
        logger.info("Initializing Parking System");
        //InteractiveShell.loadInterface(); //
        // On fait appel à une instance de classe au lieu de l'instance Static. On met new à cause du souci de static enlevé dans InteractivShell
        new InteractiveShell().loadInterface();
    }
}
