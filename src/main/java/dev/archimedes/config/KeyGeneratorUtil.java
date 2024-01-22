package dev.archimedes.config;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class KeyGeneratorUtil {
    public static String generate256BitKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error generating 256 bit key");
        }
    }



    public static String generate256SizeKey(){
        int stringLength = 64;
        String characters = "0123456789abcdef";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(stringLength);

        for (int i = 0; i < stringLength; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(generate256SizeKey());
        String key = "9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e93f64e338";
        System.out.println(key);
        System.out.println(generate256BitKey());
        System.out.println(key.length());
    }
}
