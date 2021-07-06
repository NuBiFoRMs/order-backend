package com.nubiform.order.config.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class JwtPropertiesTest {

    public static final String SECRET = "Development|OrderManagementProject|com.nubiform|order-backend|8a79f833-6c60-4c3e-a5a5-6420136e1861";

    @Autowired
    JwtProperties jwtProperties;

    @Test
    public void encodePropertyTest() {
        String secret = Encoders.BASE64.encode(SECRET.getBytes(StandardCharsets.UTF_8));
        assertThat(secret).isEqualTo(jwtProperties.getSecret());
    }

    @Test
    public void decodePropertyTest() {
        String secret = new String(Decoders.BASE64.decode(jwtProperties.getSecret()));
        assertThat(secret).isEqualTo(SECRET);
    }
}