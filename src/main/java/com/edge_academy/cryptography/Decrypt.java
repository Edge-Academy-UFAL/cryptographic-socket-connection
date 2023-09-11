package com.edge_academy.cryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Decrypt {
    public static String decryptString(String encryptedText, SecretKey secretKey) throws Exception {
        // TODO: Fazer o método de descriptografar a String
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }
}
