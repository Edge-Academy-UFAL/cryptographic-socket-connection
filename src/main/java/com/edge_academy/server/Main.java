package com.edge_academy.server;

import com.edge_academy.AesKey;
import com.edge_academy.client.socket.Server;
import com.edge_academy.compression.Decompressor;
import com.edge_academy.cryptography.Decrypt;
import com.edge_academy.cryptography.Encrypt;

import java.io.IOException;

public class Main {
    static private final int MAX_DATA_SIZE = 32 * 1024;
    public static void main(String[] args) {

        Server socketReceive = new Server();
        while (true) {
            try {
                String data = socketReceive.receiveData();
                Decompressor decompressor = new Decompressor(data);
                byte [] dataDecompressed = decompressor.decompress();
                String decryptedString = Decrypt.decryptString(new String(dataDecompressed), AesKey.AES_KEY);
                System.out.println(decryptedString);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
