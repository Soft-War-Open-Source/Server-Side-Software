package com.appnutricare.security.jwt;

import com.appnutricare.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider{

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //value es para que sea reconocida en cualquier parte de la aplicación
    @Value("${appnutricare.jwtSecret}")
    private String jwtSecret;

    @Value("${appnutricare.jwtExpiration}")
    private int jwtExpiration; //Tiempo de expiración del token

    public String generateJwtToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken (String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException ex){
            logger.error("Invalid Jwt Signature -> Message: {}", ex.getMessage());
        }catch (MalformedJwtException ex){
            logger.error("Invalid Jwt Token -> Message: {}", ex.getMessage());
        }catch (ExpiredJwtException ex){
            logger.error("Expired Jwt Token -> Message: {}", ex.getMessage());
        }catch (UnsupportedJwtException ex){
            logger.error("Unsupported Jwt Token -> Message: {}", ex.getMessage());
        }catch (IllegalArgumentException ex){
            logger.error("JWT Claims String is Empty -> Message: {}", ex.getMessage());
        }

        return false;
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
