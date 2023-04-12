package br.com.deliverymental.deliverymental.cryptography;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class SHA256 {
    public static String encrypt(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public static boolean validate(String password, String hash) {
        try {
            String result = encrypt(password);
            return result.equals(hash);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
