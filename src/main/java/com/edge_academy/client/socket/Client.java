package com.edge_academy.client.socket;

import com.sun.security.jgss.GSSUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    static private final int DEFAULT_PORT = 1969;

    private final String serverHost;
    private final int serverPort;

    private Socket server;

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

    public void init() throws IOException {
        try {
            server = new Socket(serverHost, serverPort);
        } catch (IOException e) {
            throw new ConnectException("Não foi possível iniciar conexão com o servidor");
        }
    }

    public void sendData(byte[] data) {
        try {
            OutputStream outputStream = server.getOutputStream();
            outputStream.write(data);
        } catch (IOException e) {
            System.out.println("Erro ao enviar os dados");
        }
    }

    public void close() {
        try {
            server.close();
        } catch (IOException e) {
            System.out.println("Não foi possível encerrar conexão com o servidor");
        }
    }


}
