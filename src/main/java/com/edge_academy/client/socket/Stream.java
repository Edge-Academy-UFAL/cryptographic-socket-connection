package com.edge_academy.client.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Stream {
    static private final int DEFAULT_PORT = 1969;

    private final String serverHost;
    private final int serverPort;

    public Stream() {
        this.serverHost = "localhost";
        this.serverPort = DEFAULT_PORT;
    }

    public Stream(String serverHost) {
        this.serverHost = serverHost;
        this.serverPort = DEFAULT_PORT;
    }

    public Stream(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void sendData(byte[] data) throws IOException {
        Socket socket = null;
        DataOutputStream outputStream = null;

        try {
            socket = new Socket(serverHost, serverPort);
            outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
