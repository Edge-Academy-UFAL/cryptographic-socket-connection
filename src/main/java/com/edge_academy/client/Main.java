package com.edge_academy.client;

import com.edge_academy.client.http.ApiConnections;
import com.edge_academy.client.socket.Stream;
import com.edge_academy.compression.Compressor;
import com.edge_academy.compression.Decompressor;
import com.edge_academy.cryptography.Decrypt;
import com.edge_academy.cryptography.Encrypt;

import java.io.IOException;

import static com.edge_academy.AesKey.AES_KEY;

public class Main {
    static final String API_URL = "https://random-data-api.com/api/v2/users";


    public static void main(String[] args) throws Exception {
        ApiConnections randomJson = new ApiConnections(API_URL);
        String json = "";

        try {
            json = randomJson.requestJson();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String encryptedJson = Encrypt.encryptString(json, AES_KEY);

        System.out.println(encryptedJson);
        Compressor compressor = new Compressor(encryptedJson.getBytes());
        byte[] compressedJson = compressor.compress();

        //System.out.println(new String(compressedJson));
        System.out.println(encryptedJson.getBytes().length);
        //System.out.println(compressedJson.length);

        Decompressor decompressor = new Decompressor(compressedJson);
        byte[] decompressedJson = decompressor.decompress();
        String stringDecompressedJson = new String(decompressedJson);
        //System.out.println(stringDecompressedJson);

        String decryptedJson = Decrypt.decryptString(stringDecompressedJson, AES_KEY);
        //System.out.println(decryptedJson);

        Stream socketStream = new Stream();
        while (true) {
            try {
                socketStream.sendData(compressedJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
