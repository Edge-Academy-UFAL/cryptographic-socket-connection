package com.edge_academy.cryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Encrypt {
    public static String encryptString(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
