package com.example.crudOperations.demoCrudOperation.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private int accessTokenValidity = 1000 * 60 * 5; // 5 minutes
    private int refreshTokenValidity = 1000 * 60 * 60 ; // 1 hour

    private String secretKey;
    public JwtService()
    {
        try {
            KeyGenerator generator=KeyGenerator.getInstance("HmacSHA256");
            SecretKey key=generator.generateKey();
            secretKey= Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String identifier,String email,String phonenumber,String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email",email);
        claims.put("phonenumber",phonenumber);
        claims.put("role",role);
        return createToken(claims, identifier,accessTokenValidity);
    }

    public String generateRefreshToken(String username, String email, String phoneNumber,String role) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, refreshTokenValidity);
    }
    private String createToken(Map<String, Object> claims, String subject, int validity) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte [] keys= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keys);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    // Validate if the token is expired or not
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from JWT
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // Extract any claim from JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // Extract all claims from JWT
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /*public String extractRole(String token) {
        Claims claims = extractAllClaims(token);  // Extract all claims
        return claims.get("role", String.class);

    }*/

    /*public boolean isRefreshToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String tokenType = claims.get("tokenType", String.class);
        System.err.println(tokenType);
        return "refresh".equals(tokenType);
    }
*/
}
