package com.edge_academy.client.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static private final int DEFAULT_PORT = 1969;
    static private final int MAX_DATA_SIZE = 32 * 1024;

    private final String serverHost;
    private final int serverPort;

    public Server() {
        this.serverHost = "localhost";
        this.serverPort = DEFAULT_PORT;
    }

    public Server(String serverHost) {
        this.serverHost = serverHost;
        this.serverPort = DEFAULT_PORT;
    }

    public Server(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }


    public String receiveData() {
        try (
                ServerSocket serverSocket = new ServerSocket(serverPort);
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            StringBuilder receivedData = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                receivedData.append(line);
            }
            return receivedData.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
