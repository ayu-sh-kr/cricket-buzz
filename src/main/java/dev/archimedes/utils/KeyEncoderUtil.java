package dev.archimedes.utils;

import java.util.Base64;

public class KeyEncoderUtil {

//    public static String encodeKey(int key) {
//        String keyString = Integer.toString(key);
//        return Base64.getEncoder().encodeToString(keyString.getBytes());
//    }
//
//    public static int decodeKey(String encodedKey) {
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedKey);
//        String decodedString = new String(decodedBytes);
//        return Integer.parseInt(decodedString);
//    }

    public static String encodeKey(int key) {
        String keyString = String.format("%16s", Integer.toBinaryString(key)).replace(' ', '0');
        return Base64.getEncoder().encodeToString(keyString.getBytes());
    }

    public static int decodeKey(String encodedKey) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedKey);
        String decodedString = new String(decodedBytes);
        return Integer.parseInt(decodedString, 2);
    }

}