package com.changgou.oauth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;

public class CreateJwtTest {

    /**
     *
     */
    @Test
    public void testCreateJwt() throws Exception {
        ClassPathResource pathResource = new ClassPathResource("changgou.jks");
        String keyPass = "changgou";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(pathResource, keyPass.toCharArray());

        String alias = "changgou";
        String password = "changgou";
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "ldl");
        map.put("address", "cq");
        map.put("age", "18");

        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));
        String encoded = jwt.getEncoded();
        System.out.println("encoded = " + encoded);
    }
}
