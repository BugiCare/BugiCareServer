package hsu.bugicare.bugicareserver;

import hsu.bugicare.bugicareserver.config.LoggingInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
public class BugiCareServerApplication {
    public static void main(String[] args) throws IOException {
        // 로깅 설정 초기화
        LoggingInitializer.init();

        SpringApplication.run(BugiCareServerApplication.class, args);
    }

}
