package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class ParseJwtTest {

    /**
     *
     */
    @Test
    public void test9() throws Exception {
        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZGRyZXNzIjoiY3EiLCJuYW1lIjoibGRsIiwiYWdlIjoiMTgifQ" +
                ".kOEkIj2fz_7aWw4yG-OeGjqmL70Xjhbkn4KqicEWACEm0ADyLTHpk9MYmZ5g_ZgCH7XgSI-GbmqFUBOUszAq4si64oqYIgYYnjTaOudoIUKlHHYV3w7t7eHKGaSF8X_EIeNEXumahUj8Lo_mBC1ez4ppn9I8eZ-2SI8GcDUkUIfb7fnq5LySoGru_ISgfKg_jNhgTeXkC8fkQTtuNiOwU5Lt0xUIHv-ozzq5DUilVfQiDSiNI3z2tm1thKizHUoPJntvGjnJlMzqgnM9UMTXgyPXS3N3uzmWjZUaWvo7HfnNoBZZ2B3MeDqMjKXDI53qQucEHJoLacQeHQpkyh3nnA";
        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";
        //基于公钥解析jwt
        Jwt decode = JwtHelper.decode(jwt);
        // decode.verifySignature(new RsaVerifier(publicKey));
        String claims = decode.getClaims();
        System.out.println("claims = " + claims);
    }
}
