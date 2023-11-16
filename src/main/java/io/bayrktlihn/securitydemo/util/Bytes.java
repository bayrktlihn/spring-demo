package io.bayrktlihn.securitydemo.util;

import java.util.Base64;
import java.util.Random;

public class Bytes {

    public static byte[] createByte(int byteSize) {
        byte[] bytes = new byte[byteSize];
        Random random = new Random();
        random.nextBytes(bytes);
        return bytes;
    }

    public static void main(String[] args) {
        byte[] aByte = createByte(32);


        String s = Base64.getEncoder().encodeToString(aByte);

        System.out.println(s);
    }

}
