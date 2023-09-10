package com.edge_academy.client;

import com.edge_academy.AesKey;
import com.edge_academy.client.http.ApiConnections;
import com.edge_academy.cryptography.Encrypt;

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


    }
}
