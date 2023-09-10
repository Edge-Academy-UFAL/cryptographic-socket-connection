package com.edge_academy;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesKey {
    private static final byte[] KEY_SEED = "eHF9i/rPwPDyN/Hh".getBytes();
    public static final SecretKey AES_KEY = new SecretKeySpec(KEY_SEED, "AES");
}