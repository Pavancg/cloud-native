package com.pavan.billservice.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {


    private String publicKey = "c0b4294d9fe934c499b85916a2b1ab04369a1d006d82e3ac2ba6391453fdb0b9";

    public Claims validateAndGetClaims(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }
}

