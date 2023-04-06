package hsu.bugicare.bugicareserver.raspi;

import hsu.bugicare.bugicareserver.service.impl.FurnitureGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class InfoFromRaspi {

    private final FurnitureGraphService furnitureGraphService;

    private String oldRefrigeratorStatus = "closeRefrigerator";
    private String oldDoorStatus = "closeDoor";

    @Autowired
    public InfoFromRaspi(FurnitureGraphService furnitureGraphService) {
        this.furnitureGraphService = furnitureGraphService;
    }

    public void start() throws IOException {

        // Socket 연결
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("Socket 기다리는 중... 포트번호는 " + serverSocket.getLocalPort());

        Socket clientSocket = serverSocket.accept();
        System.out.println("Socket 연결 : " + clientSocket.getRemoteSocketAddress());

        // Socket이 보낸 문자열
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String receivedText = null;
        while ((receivedText = in.readLine()) != null) {
            // Refrigerator
            if(receivedText.contains("openRefrigerator")) {
                if(oldRefrigeratorStatus.equals("closeRefrigerator")) {
                    furnitureGraphService.saveRefrigerator();
                }
                oldRefrigeratorStatus = "openRefrigerator";
            } else if (receivedText.contains("closeRefrigerator")) {
                oldRefrigeratorStatus = "closeRefrigerator";
            }

            // Door
            if (receivedText.contains("openDoor")) {
                if (oldRefrigeratorStatus.equals("closeDoor")) {
                    furnitureGraphService.saveDoor();
                }
                oldRefrigeratorStatus = "openDoor";
            } else if (receivedText.contains("closeDoor")) {
                oldRefrigeratorStatus = "closeDoor";
            }

            System.out.println("socket으로부터 받은 메시지 : " + receivedText);
        }
        in.close();

        clientSocket.close();
        serverSocket.close();
    }
}
