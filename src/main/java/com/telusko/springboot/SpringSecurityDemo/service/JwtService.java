package com.telusko.springboot.SpringSecurityDemo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {

    private final String sKey;

      //      = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public JwtService () {
        sKey = generateKey();
    }



    public String generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA384");
            SecretKey secretKey  = keyGenerator.generateKey();
            System.out.println("SecretKEy : " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error Generation secret Key" , e);
        }
    }

    public String generateToken(String username) {

    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000*60*3))
            .signWith(getKey()).compact();
    }


    private Key getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(sKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {

    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
    }



    private Claims extractAllClaims(String token) {
        return Jwts.parser()                    // 1. parserBuilder() -> parser()
                .verifyWith((SecretKey) getKey())           // 2. setSigningKey() -> verifyWith()
                .build()
                .parseSignedClaims(token)       // 3. parseClaimsJws() -> parseSignedClaims()
                .getPayload();                  // 4. getBody() -> getPayload()
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
