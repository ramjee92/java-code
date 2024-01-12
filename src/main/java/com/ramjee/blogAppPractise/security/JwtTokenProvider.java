package com.ramjee.blogAppPractise.security;

import com.ramjee.blogAppPractise.exception.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtTokenProvider {
    // we need to get Jwt properties here
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;
    // utility method to generate token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        // next we need to set expiration date for token
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime()+jwtExpirationDate);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
        // compact method will club all parameters and create jwt token
        return token;
    }

    // get username from jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parser()
                        .setSigningKey(key())
                        .build()
                        .parseClaimsJwt(token)
                        .getBody();
        String username = claims.getSubject();
        return username;
    }

   // utility method to validate Jwt token

    public boolean validateToken(String token){

        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex){
            throw  new BlogApiException("Invalid Jwt Token");
        } catch (ExpiredJwtException ex) {
            throw new BlogApiException("Expired Jwt Token");
        } catch (UnsupportedJwtException ex){
            throw new BlogApiException("Unsupported Jwt token");
        } catch (IllegalArgumentException ex){
            throw new BlogApiException("Jwt claims String is empty");
        }

    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
