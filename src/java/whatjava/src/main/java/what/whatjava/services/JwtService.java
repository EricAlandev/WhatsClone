package what.whatjava.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import what.whatjava.entitys.users.EntityUser;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    public String generateToken(EntityUser user){

        //generate the key
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims verifyToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

            return Jwts.parserBuilder()
                    .setSigningKey(key) // Set the key used for signature
                    .build()
                    .parseClaimsJws(token) // This line validates signature and expiration
                    .getBody(); // Returns the data inside (Subject, Roles, etc.)
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired JWT token");
        }
    } 
}
