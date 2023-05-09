package hsu.bugicare.bugicareserver.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    public void sendPushNotification(String token) throws Exception {
        Message fcmMessage = Message.builder()
                .setToken(token)
                .build();

        FirebaseMessaging.getInstance().send(fcmMessage);
    }
}
