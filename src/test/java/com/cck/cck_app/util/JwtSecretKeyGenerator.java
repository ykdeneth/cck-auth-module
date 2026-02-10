package com.cck.cck_app.util;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretKeyGenerator {

    public static void main(String[] args) {

        SecureRandom secureRandom = new SecureRandom();

        byte[] keyBytes = new byte[64];
        secureRandom.nextBytes(keyBytes);

        String secretKey = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("JWT SECRET KEY:");
        System.out.println(secretKey);
    }

}
