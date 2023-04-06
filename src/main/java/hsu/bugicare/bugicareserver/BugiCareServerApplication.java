package hsu.bugicare.bugicareserver;

import hsu.bugicare.bugicareserver.raspi.InfoFromRaspi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BugiCareServerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BugiCareServerApplication.class, args);
        InfoFromRaspi server = new InfoFromRaspi();
        server.start();
    }

}
