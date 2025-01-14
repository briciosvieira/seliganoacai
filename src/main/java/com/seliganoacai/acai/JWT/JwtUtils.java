package com.seliganoacai.acai.JWT;


 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import io.jsonwebtoken.security.Keys;

 import java.nio.charset.StandardCharsets;
 import java.security.Key;
 import java.time.LocalDateTime;
 import java.time.ZoneId;
 import java.util.Date;


public class JwtUtils {

    public static final String BEARER =  "Beare ";
    public static final String KEY =  "tlAum)J^F`V^HA421c0l=kqq3aR/O+0wn*nU;C";
    public static final String AUTHORIZATION =  "AUTHORIZATION";
    public static final long day =  0;
    public static final long hours =  0;
    public static final long minutes =  1;

    public JwtUtils() {
    }

    // Gerar Key com UTF8
    public static Key generateKey(){
        return Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
    }

    // convertendo Date em LocalDateTime para pegar dia, horas e minutos, depois disso converter
    // novamente para Date
    private static Date expireTimeToken(Date start){

        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime expireTime = dateTime.plusDays(day).plusHours(hours).minusDays(minutes);

        return Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    public static JwtToken createJwtToken(String username, String role){

        Date issuiAt = new Date();
        Date limitExpire = expireTimeToken(issuiAt);

        String token = Jwts.builder().setHeaderParam("TYPE","JWT")
                .subject(username)
                .issuedAt(issuiAt)
                .expiration(limitExpire)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .claim("role", role)
                .compact();
        return new JwtToken();
    }
}
