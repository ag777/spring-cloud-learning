package com.example.demo;

import com.ag777.util.lang.Console;
import com.ag777.util.lang.DateUtils;
import com.ag777.util.lang.IOUtils;
import com.ag777.util.lang.collection.ListUtils;
import com.ag777.util.lang.collection.MapUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

public class JwtTest {

    public static void main(String[] args) throws Exception {
        PublicKey publicKey = getPublicKey();
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJ1c2VyIiwidGVzdF9kYXRhIjoi5YGH6KOF6YCa6L-H55So5oi357yW5Y-36I635Y-W5pWw5o2uOjEiLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjA1NjQ0NTgxLCJqdGkiOiJhZWFmY2Y4Yy1lZjMzLTRlZjUtOWYxMS0zMWVlNGQzMzJlNTciLCJjbGllbnRfaWQiOiJjMSJ9.hrtGYJb81eaR6yasctXhV2TwmoNZB_prnxESBnzC4Ef0sKjNCMEEEEePJ4AWuxEL172dOlTiHVgzo0waD5DF8dzZ0MKeaT_T8HvSMqmGrmQPx5aFLZzZp7MVXWICwv1N1Rhm7HF4tTS4Y5MhJcJwNTVjl3o1tOExSrOCxMLUKGEs1N4D_k6om4r3ABlh1Iw91DNe3WufWpsiMrJEa1nact88-uHt-B0s7XINUfhCgmO53t8CHLdZK8kACtmbl5SJZIRBxyJ0vgK5uPFwttpr0f83MKI8fvUE9bTQphi4XoY2trTc9Cr4ssIpjxMMzFFqRKnioVT_g7qrLQkm9QIV5g";

//        System.out.println(getClaims(token, readPublicKey()));
        Jws<Claims> jws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        Claims body = jws.getBody();
        Console.prettyLog(body);
        System.out.println(DateUtils.toString(body.getExpiration(), DateUtils.DEFAULT_TEMPLATE_TIME));
        System.out.println(DateUtils.toString(body.getNotBefore(), DateUtils.DEFAULT_TEMPLATE_TIME));
        System.out.println(DateUtils.toString(body.getIssuedAt(), DateUtils.DEFAULT_TEMPLATE_TIME));
        System.out.println(body.getAudience());
//        Console.prettyLog(body);
//        SignatureVerifier verifier = new RsaVerifier("-----BEGIN PUBLIC KEY-----\n" +
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApm5un51TnSk/CZ76DCk2\n" +
//                "ZB3QqZzPAciPjgLAYyHavuketQXcu11ZOM3bUSq0C+W1K9UzHuNSYdms9OsSvHvo\n" +
//                "7o7v3E2tVzE3FJa8abCDbOeyS6UZ15ZMLjkkS9Tne5QT9hlEZdmfW3+trlNNbo/V\n" +
//                "VGzEcCPgzxn62hETPcbNFHc7aqH1vyA6FauVKmVgYFm/5XFNlVbwsamk2Itrs0p0\n" +
//                "u8CGJSK82XNFn7NAUDwzLv0jOo2jJ25ATII1iNne3htqdv23KNVAa8JglQ7pTfYx\n" +
//                "VHrqvtOEX+uQpzl6/3dQSvXHz4ZnLAXBw5HW/fQtp+trtQwwe75ZRSlIe2YXYGsL\n" +
//                "4wIDAQAB\n" +
//                "-----END PUBLIC KEY-----");
//        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
//        System.out.println(jwt.getClaims());
//        Jwt jwt = JwtHelper.decode(token);
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//        accessTokenConverter.setSigningKey("123");
//        accessTokenConverter.getAccessTokenConverter().


//        String token
//                ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHQiOiIxIiwicm9sZXMiOiJyMDEscjAyIiwibmFtZSI6Im1ydCIsImlkIjoiMTIzIn0.KK7_67N5d1Dthd1PgDHMsbi0UlmjGRcm_XJUUwseJ2eZyJJWoPP2IcEZgAU3tUaaKEHUf9wSRwaDgwhrwfyIcSHbs8oy3zOQEL8j5AOjzBBs7vnRmB7DbSaQD7eJiQVJOXO1QpdmEFgjhc_IBCVTJCVWgZw60IEW1_Lg5tqaLvCiIl26K48pJB5f‐le2zgYMzqR1L2LyTFkq39rG57VOqqSCi3dapsZQd4ctq95SJCXgGdrUDWtD52rp5o6_0uq‐mrbRdRxkrQfsa1j8C5IW2‐T4eUmiN3f9wF9JxUK1__XC1OQkOn‐ZTBCdqwWIygDFbU7sf6KzfHJTm5vfjp6NIA";
//        //公钥
//        String publickey = "‐‐‐‐‐BEGIN PUBLIC KEY‐‐‐‐‐" +
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAijyxMdq4S6L1Af1rtB8SjCZHNgsQG8JTfGy55eYvzG0B/E4AudR2prSRBvF7NYPL47scRCNPgLnvbQczBHbBug6uOr78qnWsYxHlW6Aa5dI5NsmOD4DLtSw8eX0hFyK5Fj6ScYOSFBz9cd1nNTvx2+oIv0lJDcpQdQhsfgsEr1ntvWterZt/8r7xNN83gHYuZ6TM5MYvjQNBc5qC7Krs9wM7UoQuL+s0X6RlOib7/mcLn/lFLsLDdYQAZkSDx/6+t+1oHdMarChIPYT1sx9Dwj2j2mvFNDTKKKKAq0cv14Vrhz67Vjmz2yMJePDqUi0JYS2r0iIo7n8vN7s83v5uOQIDAQAB\n" +
//                "‐‐‐‐‐END PUBLIC KEY‐‐‐‐‐";
//        //校验jwt（如果jwt令牌错误，执行下面的代码会报错）
//        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey)); //获取jwt原始内容
//        String claims = jwt.getClaims();
//        //jwt令牌
//        String encoded = jwt.getEncoded();
//        System.out.println(encoded);
    }

    public static String getClaims(String token, String publicKey) {
        SignatureVerifier verifier = new RsaVerifier(publicKey);
        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
        return jwt.getClaims();
    }

    public static PublicKey getPublicKey() throws Exception {
        return getPublicKey(readPublicKey());
    }


    /**
     * 解码PublicKey
     * @param key
     * @return
     */
    public static PublicKey getPublicKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] byteKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    private static String readPublicKey() throws IOException {
        InputStream in = new ClassPathResource("public.cert").getInputStream();
        List<String> lines = ListUtils.newArrayList();
        IOUtils.readLines(in, line->{
            if(!line.contains("PUBLIC KEY")) {
                lines.add(line);
            }
        }, StandardCharsets.UTF_8);
        return ListUtils.toString(lines, null);
    }

//    public static PublicKey getPublicKey(byte[] bytes) throws Exception {
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
//        KeyFactory factory = KeyFactory.getInstance("RSA");
//        return factory.generatePublic(spec);
//    }
//
//
//    private static byte[] readPublicKey() throws IOException {
//        InputStream in = new ClassPathResource("public.cert").getInputStream();
//        return IOUtils.readBytes(in);
//    }

}
