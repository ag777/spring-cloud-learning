package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

//@SpringBootTest(classes = Oauth2JwtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Slf4j
public class JwtTest {


//    @Test
    public static void test2() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJ1c2VyIiwidGVzdF9kYXRhIjoi5YGH6KOF6YCa6L-H55So5oi357yW5Y-36I635Y-W5pWw5o2uOjEiLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjA1NzU1NzY0LCJqdGkiOiIxMmNlMDU2MS0zMDJkLTRkODAtODY3YS1kNjE5MDJmMGFlOGYiLCJjbGllbnRfaWQiOiJjMSJ9.dVEgAq-zXAQ2gu0nwoiIE0HBTE1II1wyYReGXS0RDww";
        Jwt jwt = JwtHelper.decode(token);
        System.out.println(jwt.getClaims());
        System.out.println(jwt.getEncoded());
    }


    public static void test3() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJ1c2VyIiwidGVzdF9kYXRhIjoi5YGH6KOF6YCa6L-H55So5oi357yW5Y-36I635Y-W5pWw5o2uOjEiLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjA2MTgzMjMyLCJqdGkiOiI3YTVkMGNjMS1jMDFlLTRiMmYtYWY0MC0wNjUwOTc2NWE2MTMiLCJjbGllbnRfaWQiOiJjMSJ9.Pr_kHKOe9bjbw-bgztfE-EG-f0TfXKzEm6MSv500GyXjg4z4lMcf2bLOnFf-Vr6rGA1C_cS1xJ9emYmRb_O2k3ywsyPrI2ux1ZazqbFTbbP-xAcz1fQGR6FvokOMgTYATuqQ3Usl2NIXsiWUwN6JoykKYiz7hphEa7luo7ntyaIWs8k4AEisLGawvu1ZASIUDe2EBlq4U2ttC3antxZ_jVtKoU4P-_LlNSSNaMFQbu98Kx6w8r-7FnZOgSEinxEir54BLnO59qJHFCowdVU-u3KuKhUvHvnTO4G288joyNSFJ2VemzowO135m8sUk-TqjOTjhort5d20BVHoZYb2uA";
        SignatureVerifier verifier = new RsaVerifier("-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApm5un51TnSk/CZ76DCk2\n" +
                "ZB3QqZzPAciPjgLAYyHavuketQXcu11ZOM3bUSq0C+W1K9UzHuNSYdms9OsSvHvo\n" +
                "7o7v3E2tVzE3FJa8abCDbOeyS6UZ15ZMLjkkS9Tne5QT9hlEZdmfW3+trlNNbo/V\n" +
                "VGzEcCPgzxn62hETPcbNFHc7aqH1vyA6FauVKmVgYFm/5XFNlVbwsamk2Itrs0p0\n" +
                "u8CGJSK82XNFn7NAUDwzLv0jOo2jJ25ATII1iNne3htqdv23KNVAa8JglQ7pTfYx\n" +
                "VHrqvtOEX+uQpzl6/3dQSvXHz4ZnLAXBw5HW/fQtp+trtQwwe75ZRSlIe2YXYGsL\n" +
                "4wIDAQAc\n" +
                "-----END PUBLIC KEY-----");
        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
        System.out.println(jwt.getClaims());
    }

    public static void main(String[] args) {

        try {
            test3();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            e.printStackTrace();
        }

    }
}
