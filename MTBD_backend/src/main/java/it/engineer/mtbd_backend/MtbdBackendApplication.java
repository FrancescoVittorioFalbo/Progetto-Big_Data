package it.engineer.mtbd_backend;

import it.engineer.mtbd_backend.model.GeneralQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import real_class.GestioneVolo;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class MtbdBackendApplication {

    private static void avvia(){
        String path = "C:\\Users\\CiccioFa\\Downloads\\Voli";
        String pathCompagnie = "C:\\Users\\CiccioFa\\Downloads\\carrier.csv";
        GestioneVolo.start(path, pathCompagnie);
    }

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SpringApplication.run(MtbdBackendApplication.class, args);

        avvia();
    }

}
