package com.seliganoacai.acai.JWT;


 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.JwtException;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import io.jsonwebtoken.security.Keys;
 import lombok.extern.slf4j.Slf4j;

 import java.nio.charset.StandardCharsets;
 import java.security.Key;
 import java.time.LocalDateTime;
 import java.time.ZoneId;
 import java.util.Date;


public class JwtUtils {

    public static final String BEARER =  "Bearer ";
    public static final String KEY =  "M5r8Q!zGdX2@fDk8Yb9#pLt7CwVxN0JqCdas";
    public static final String AUTHORIZATION =  "Authorization";
    public static final long day =  0;
    public static final long hours =  1;
    public static final long minutes =  0;

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

    //metodo de criação do token
    public static JwtToken createToken(String username, String role){
        Date issueAt = new Date();
        Date limit = expireTimeToken(issueAt);

        String token = Jwts.builder().setHeaderParam("typ","JWT")
                .subject(username)
                .issuedAt(issueAt)
                .expiration(limit)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .claim("role", role)
                .compact();

        return new JwtToken(token);
    }



    //remove o valor que tem na variavel BEARER
    public static String removePrefixBearer(String token){
        if (token.contains(BEARER)){
            return token.substring(BEARER.length());
        }
        return token;
    }

    //pega os dados que estão no cabeçalho, ou seja, no Cliams
    private static Claims getCliamsFromToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(removePrefixBearer(token)).getBody();

        } catch (JwtException e){

        }
        return null;
    }

    public static boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(removePrefixBearer(token));
            return true;
        }catch (JwtException e){
            System.out.println("Corrigir, Token autenticação invalido");
        }
        return false;
    }

    public static String getUsernameFromToken( String token){
        return getCliamsFromToken(token).getSubject();
    }


}
