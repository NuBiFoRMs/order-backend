package com.nubiform.order.config.security.jwt;

import com.nubiform.order.domain.Account;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider implements InitializingBean {

    public static final String AUTHORITIES_KEY = "auth";
    public static final String DELIMITER = ",";

    private final JwtProperties jwtProperties;

    private final RedisTemplate<String, Object> redisTemplate;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("afterPropertiesSet");
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(DELIMITER));

        Date now = new Date();

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getTokenValidityInSecond() * 1000))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        log.debug("token: {}", claimsJws);

        Claims claims = claimsJws.getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(DELIMITER))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new Account(claims.getSubject(), "", authorities);
        log.debug("principal: {}", principal);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        if (Objects.nonNull(redisTemplate.opsForValue().get(token))) {
            log.debug("logout token");
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.debug("ExpiredJwtException");
        } catch (UnsupportedJwtException e) {
            log.debug("UnsupportedJwtException");
        } catch (MalformedJwtException e) {
            log.debug("MalformedJwtException");
        } catch (SignatureException e) {
            log.debug("SignatureException");
        } catch (IllegalArgumentException e) {
            log.debug("IllegalArgumentException");
        }
        return false;
    }

    public Date getExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }
}
