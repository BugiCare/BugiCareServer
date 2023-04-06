package hsu.bugicare.bugicareserver.raspi;

import hsu.bugicare.bugicareserver.service.impl.FurnitureGraphService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class InfoFromRaspi {

    private final FurnitureGraphService furnitureGraphService;

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
            if(receivedText.contains("openRefrigerator")) {
                furnitureGraphService.saveRefrigerator();
            }
            if (receivedText.contains("openDoor")) {
                furnitureGraphService.saveDoor();
            }
            System.out.println("socket으로부터 받은 메시지 : " + receivedText);
        }
        in.close();

        clientSocket.close();
        serverSocket.close();
    }
}
