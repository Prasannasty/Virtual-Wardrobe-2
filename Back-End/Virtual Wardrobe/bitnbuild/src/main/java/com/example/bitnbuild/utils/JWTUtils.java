package com.example.bitnbuild.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTUtils {

    private static final long EXPIRATION_TIME=1000*60*24*7; //7 days

    //This is a SecretKey object used for signing the JWTs. It is initialized in the constructor.
    private final SecretKey Key;

    //This constructor is used for Setting Up the Secret Key
    public JWTUtils() {
        String secreteString = "qzhJ2Q8uF3CZT1lkrmWMBWy5jRBsSFWjx9UKRhTHKQ8="; // Base64-encoded string
        byte[] keyBytes = Base64.getDecoder().decode(secreteString); // Directly decode the Base64 string
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256"); // Generate SecretKey using HMAC-SHA256
    }

    //This is used to generate token
    public String generateToken(UserDetails userDetails)
    {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }
    //This extracts password(subject) from the token
    public String extractUsername(String token)
    {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
    }


    public boolean isValid(String token,UserDetails userDetails)
    {
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}

