package hsu.bugicare.bugicareserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
public class BugiCareServerApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(BugiCareServerApplication.class, args);
    }

}
