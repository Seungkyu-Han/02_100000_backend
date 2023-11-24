package Hackerton.Backend.Config.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final Long accessTokenValidTime = Duration.ofDays(10).toMillis();
    private static final Long refreshTokenValidTime = Duration.ofDays(20).toMillis();

    @Value("${jwt.secret.key}")
    private String secretKey;


    public Integer getUserId(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Integer.class);
    }

    public boolean isAccessToken(String token) throws MalformedJwtException {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getHeader().get("type").toString().equals("access");
    }


    public String createAccessToken(Integer userId){
        return createJwtToken(userId, secretKey, "access", accessTokenValidTime);
    }

    public String createRefreshToken(Integer userId){
        return createJwtToken(userId, secretKey, "refresh", refreshTokenValidTime);
    }

    private String createJwtToken(Integer userId, String secretKey, String type, Long tokenValidTime){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam("type", type)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
