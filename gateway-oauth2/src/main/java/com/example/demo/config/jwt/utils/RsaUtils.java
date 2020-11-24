package com.example.demo.config.jwt.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {

    private RsaUtils() {}

    /**
     * 将字符串类型的公钥转化为PublicKey对象
     * @param key
     * @return PublicKey
     * @throws InvalidKeySpecException 公钥不正确
     * @throws NoSuchAlgorithmException 没有rsa算法
     */
    public static PublicKey toPublicKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] byteKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }


}
