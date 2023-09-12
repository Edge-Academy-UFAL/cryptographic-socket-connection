package com.edge_academy.client.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static private final int DEFAULT_PORT = 1969;

    private final String serverHost;
    private final int serverPort;

    public Client() {
        this.serverHost = "localhost";
        this.serverPort = DEFAULT_PORT;
    }

    public Client(String serverHost) {
        this.serverHost = serverHost;
        this.serverPort = DEFAULT_PORT;
    }

    public Client(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void sendData(byte[] data) {
        try (
                Socket echoSocket = new Socket(serverHost, serverPort);
                DataOutputStream outputStream = new DataOutputStream(echoSocket.getOutputStream());
        ) {
            outputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
