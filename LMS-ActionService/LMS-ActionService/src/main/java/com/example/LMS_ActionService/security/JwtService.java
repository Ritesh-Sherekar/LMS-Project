package com.example.LMS_ActionService.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secreteKey = "nTcHbTBzfqSkbedG/pIHMVNYvi3M/4PCW69iVdXWaHA=";

    public String extractUsername(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T>T extractClaims(String token, Function<Claims,T>claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public boolean validateToken(String token,UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpireDate(token).before(new Date());
    }

    public Date extractExpireDate(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }

    public Key getSignInKey()
    {
        byte[]keyBytes= Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractTokenType(String token){
        return extractClaims(token, c-> c.get("tokenType")).toString();
    }
}
