package hsu.bugicare.bugicareserver;

import hsu.bugicare.bugicareserver.raspi.InfoFromRaspi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BugiCareServerApplication {

    private static InfoFromRaspi server = null;

    @Autowired
    public BugiCareServerApplication(InfoFromRaspi server) throws IOException {
        BugiCareServerApplication.server = server;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BugiCareServerApplication.class, args);
        server.start();
    }

}
