package in.geekster.springsecuritydemo.utils;

import in.geekster.springsecuritydemo.dtos.TokenDTO;
import in.geekster.springsecuritydemo.dtos.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private Long expiry;

    public TokenDTO generateToken(final UserDTO userDTO) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("account", userDTO.getId());

        // Setting Expiry
        final LocalDateTime currentTime = LocalDateTime.now();
        log.debug("Current Local Date Time: {}", currentTime);
        final LocalDateTime expiryLDT = currentTime.plus(expiry, ChronoUnit.MINUTES);
        log.debug("Expiry Local Date Time: {}", expiryLDT);
        final Date expiryDate = Date.from(expiryLDT.atZone(ZoneId.systemDefault()).toInstant());

        // Setting the Secret Key
        final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        // Generating actual token
        final String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDTO.getUsername())
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
        log.debug("Generated Token: {}", token);
        return new TokenDTO(token, expiryLDT);
    }


    public boolean validateToken(final String token, final UserDTO userDTO) {
        log.debug("Validating Token {} for User Details: {}", token, userDTO);
        final Claims claims = getAllClaims(token);
        final String username = claims.getSubject();
        final Date expiry = claims.getExpiration();
        final Date currentDate = new Date();

        return StringUtils.hasText(username) && username.equals(userDTO.getUsername()) && expiry.after(currentDate);
    }

    public String getUsername(final String token) {
        return getAllClaims(token).getSubject();
    }

    public Long getUserId(final String token) {
        return getAllClaims(token).get("account", Long.class);
    }

    private Claims getAllClaims(final String token) {
        final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        // JWT Parser
        final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        return claimsJws.getBody();
    }
}
