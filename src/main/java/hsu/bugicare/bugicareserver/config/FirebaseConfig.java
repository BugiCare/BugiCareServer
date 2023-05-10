package hsu.bugicare.bugicareserver.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;


@Configuration
public class FirebaseConfig {
//
//    private static final String FIREBASE_CONFIG_PATH = "../../src/main/resources/key/bugicareserver-springboot-firebase-adminsdk-zws1k-a1c5042fae.json";
//
//    @PostConstruct
//    public void initFirebase() throws Exception {
//        FileInputStream serviceAccount = new FileInputStream(FIREBASE_CONFIG_PATH);
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        FirebaseApp.initializeApp(options);
//    }
}
