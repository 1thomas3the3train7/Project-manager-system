package com.example.authservice;

import com.example.authservice.dto.RoleDTO;
import com.example.authservice.dto.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class JwtUtils {
    private final SecretKey jwtAccessToken;
    private final SecretKey jwtRefreshToken;

    public JwtUtils(@Value("${jwt.accessToken}")String jwtAccessToken, @Value("${jwt.refreshToken}") String jwtRefreshToken) {
        this.jwtAccessToken = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessToken));
        this.jwtRefreshToken = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshToken));
    }
    public String generateAccessToken(final UserDTO userDTO){
        final LocalDateTime time = LocalDateTime.now();
        final Instant accessInst = time.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessTime = Date.from(accessInst);
        return Jwts.builder()
                .setSubject(userDTO.getEmail())
                .setExpiration(accessTime)
                .claim("Roles",userDTO.getRoles())
                .signWith(jwtAccessToken)
                .compact();
    }
    public String generateRefreshToken(final UserDTO userDTO){
        final LocalDateTime time = LocalDateTime.now();
        final Instant refreshInst = time.plusDays(15).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshTime = Date.from(refreshInst);
        return Jwts.builder()
                .setSubject(userDTO.getEmail())
                .setExpiration(refreshTime)
                .signWith(jwtRefreshToken)
                .compact();
    }
    public boolean validateAccessToken(final String accessToken){
        return validateToken(accessToken,jwtAccessToken);
    }
    public boolean validateRefreshToken(final String refreshToken){
        return validateToken(refreshToken,jwtRefreshToken);
    }
    private boolean validateToken(final String typeToken,final Key key){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(typeToken);
            return true;
        } catch (ExpiredJwtException expEx) {
            System.out.println("token expired");
        } catch (UnsupportedJwtException unsEx) {
            System.out.println(unsEx.getMessage());
        } catch (MalformedJwtException mjEx) {
            System.out.println(mjEx.getMessage());
        } /*catch (SignatureException sEx) {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        return false;
    }
    public Claims getAccessClaims(final String token) {
        return getClaims(token, jwtAccessToken);
    }

    public Claims getRefreshClaims(final String token) {
        return getClaims(token, jwtRefreshToken);
    }

    private Claims getClaims(final String token, final Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public JwtAuth generate(final Claims claims){
        final JwtAuth jwtAuth = new JwtAuth();
        jwtAuth.setRoles(getRoles(claims));
        jwtAuth.setEmail(claims.getSubject());
        return jwtAuth;
    }
    private RoleDTO[] getRoles(final Claims claims){
        try {
            final List<String> roles = claims.get("roles",List.class);
            final RoleDTO[] rolesDTO = new RoleDTO[roles.size()];
            for(int i = 0;i < roles.size() - 1;i++){
                rolesDTO[i] = new RoleDTO(roles.get(i));
            }
            return rolesDTO;
        } catch (NullPointerException n){
            return null;
        }
    }

}
