package com.edge_academy.server;

import com.edge_academy.client.socket.Server;

import java.io.IOException;

public class Main {
    static private final int MAX_DATA_SIZE = 32 * 1024;
    public static void main(String[] args) {

        byte[] data = new byte[MAX_DATA_SIZE];
        Server socketReceive = new Server();
        do {
            try {
                data = socketReceive.receiveData();
                System.out.println(new String(data));
            } catch (IOException ignored) {
            }
        } while (true);

    }
}
