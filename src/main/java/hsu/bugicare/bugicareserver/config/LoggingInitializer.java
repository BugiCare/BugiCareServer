package hsu.bugicare.bugicareserver.config;
import org.slf4j.LoggerFactory;

public class LoggingInitializer {

    public static void init() {
        // Logback 초기화
        ch.qos.logback.classic.LoggerContext context = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();
        context.start();
    }
}

