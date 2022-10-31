package com.apiPetshop.apiPetshop.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static String md5(String senha) throws NoSuchAlgorithmException{

        MessageDigest messagedig = MessageDigest.getInstance("MD5");
        BigInteger has = new BigInteger(1, messagedig.digest(senha.getBytes()));
        return has.toString(16);
    }
}
