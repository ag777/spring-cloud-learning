package com.example.demo.config.jwt.utils;

import com.ag777.util.lang.IOUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Component
public class JwtUtils {
    public PublicKey publicKey;

    /**
     * 重新读取公钥
     * @throws IOException 读取公钥文件异常
     * @throws InvalidKeySpecException 公钥不正确
     * @throws NoSuchAlgorithmException 没有rsa算法
     */
    public synchronized void reload() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String publicKeyStr = readPublicKey();
        publicKey = RsaUtils.toPublicKey(publicKeyStr);
    }

    /**
     * 用公钥解密token
     * @param token token
     * @return Claims 本质是个Map
     * @throws InvalidKeySpecException 公钥不正确
     * @throws NoSuchAlgorithmException 没有rsa算法
     * @throws IOException 其它token解密时发生的异常
     */
    public Claims decode(String token) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        return Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
    }

    private PublicKey getPublicKey() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        if(publicKey == null) {
            synchronized (JwtUtils.class) {
                if(publicKey == null) {
                    reload();
                }
            }
        }
        return publicKey;
    }

    private static String readPublicKey() throws IOException {
        InputStream in = new ClassPathResource("public.cert").getInputStream();
        StringBuilder sb = new StringBuilder();
        IOUtils.readLines(in, line->{
            if(!line.startsWith("-----")) {
                sb.append(line);
            }
        }, StandardCharsets.UTF_8);
        return sb.toString();
    }
}
