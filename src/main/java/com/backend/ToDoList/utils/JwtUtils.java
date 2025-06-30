package com.backend.ToDoList.utils;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtils {
//    @Value("${jwt.signer-key}")
    private final String SIGNER_KEY= "izWuHt/BjnkL/ngNB8UlVWh25ibcQRE1ypw++V+UOP6PuBn2y7CSb47cKbKCgd1F";
    private final static int EXPIRATION_TIME = 100000;
    private final SecretKey secretKey;
    public JwtUtils() {
        System.out.println("JwtUtils constructor "+ SIGNER_KEY);
        this.secretKey = new SecretKeySpec(SIGNER_KEY.getBytes(),SignatureAlgorithm.HS512.getJcaName());
    }
    public String generateToken(int userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(this.secretKey)
                .compact();
    }
    public int getUserId(String token) {
        return Integer.parseInt(Jwts.parserBuilder().setSigningKey(this.secretKey).build()
                .parseClaimsJws(token).getBody().getSubject());
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
