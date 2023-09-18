package com.edge_academy.client.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static private final int DEFAULT_PORT = 1969;
    static private final int MAX_DATA_SIZE = 32 * 1024;
    private final String serverHost;
    private final int serverPort;

    private ServerSocket server;
    private Socket client;

    public Server() {
        this.serverHost = "localhost";
        this.serverPort = DEFAULT_PORT;
    }
    public Server(int serverPort) {
        this.serverHost = "localhost";
        this.serverPort = serverPort;
    }

    public void init() {
        try {
            this.server = new ServerSocket(serverPort);
            System.out.println("Servidor inicializado na porta " + serverPort);
        } catch (IOException e) {
            System.out.println("Servidor não pôde ser inicializado");
        }
    }

    public void waitForClientConnection() {
        try {
            System.out.println("Aguardando nova conexão");
            client = server.accept();
            System.out.println("Conexão feita com " + client.getInetAddress() + ":" + client.getLocalPort());
        } catch (IOException e) {
            System.out.println("Erro ao escutar na porta " + serverPort);
        }
    }

    public byte[] receiveData() throws IOException {
        try {
            InputStream inputStream = client.getInputStream();
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new IOException("Erro ao receber os dados");
        }
    }

    public void closeClientConnection() throws IOException { client.close(); }


}
