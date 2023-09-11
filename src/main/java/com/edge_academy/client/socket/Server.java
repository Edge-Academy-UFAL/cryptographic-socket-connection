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

    private Socket connectClient() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Waiting connections in port " + serverPort);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            return clientSocket;
        }
    }


    public byte[] receiveData() throws IOException {
        Socket clientSocket = null;

        while (true) {
            try {
                clientSocket = connectClient();
                if (clientSocket != null) {
                    InputStream inputStream = clientSocket.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    return reader.readLine().getBytes();
                }

                // Aguarde algum tempo antes de tentar novamente
                Thread.sleep(1000); // Pausa de 1 segundo (você pode ajustar o tempo de espera)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
