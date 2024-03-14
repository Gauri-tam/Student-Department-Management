package com.StudDept.services;

import com.StudDept.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtHelperServices {

    @Value("${application.security.jwt.secrete-key}")
    private String secreteKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtToken;

    @Value("${application.security.jwt.refresh-key.expiration}")
    private long refreshToken;

    // extract the user name

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] byteCodes = Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(byteCodes);
    }

    // generate The Token

    public String generateAccessToken(User user){
        return buildToken(new HashMap<>(), user, jwtToken);
    }

    public String generateRefreshToken(User user){
        return buildToken(new HashMap<>(), user, refreshToken); // we can generate it differently
    }

    private  String buildToken(Map<String , Object>claims, User user, long expiration){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(user.getEmail())
                .claim("name", user.getFirstName()+" "+user.getLastName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // validate The token

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
