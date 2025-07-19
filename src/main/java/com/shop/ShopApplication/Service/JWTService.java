package com.shop.ShopApplication.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    @SuppressWarnings("FieldMayBeFinal")
    private String secretKey;

    public JWTService(){
        // This generates a key to be used by secretKey.
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {
        List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN"); // Create a roles. We will do a helper method to get the role from the database.
        Map<String, Object> claims = new HashMap<>(); // used to store the JWT into JSON object
        claims.put("roles", roles); // inserting the roles inside the claims object.

        return Jwts.builder()
                .claims()// starts the nested claims
                .add(claims) // uses the map to set the key-value pairs below.
                .subject(username) // sets sub - username = "sub": "username"
                .issuedAt(new Date(System.currentTimeMillis())) // sets iat - date = "iat": "1752848877"
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30)) // sets exp - date = "exp": "1752848985"
                .and() // exits the nested claims
                .signWith(getKey()) // signs the JWT using a secret key
                .compact(); // finalizes JWT and returns it as a String.
    }

    // This is a helper method for signWith() method for generating a token.
    private SecretKey getKey() {
        System.out.println(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes); // returns a secret key based on the passed bytes. This uses HMAC-SHA algorithm
    }

    // This method is used to extract the username from the token and return it to validateToken method.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // This calls the extractAllClaims to parse the token and applies it to the given function, the claimResolver.
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims); // This uses the Claims:getSubject and returns the subject. Same with Claims::getExpiration
    }

    // This will parse and verifies the JWT. This verifies the token using the verifyWith method and parseSignedClaims method. Then, it returns the claims part of the token using getPayload method.
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // This checks if the username from the token and username from the UserDetails is equal
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // This checks if the date from the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // This method is used to extract the date from the token and return it to isTokenExpired method.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
