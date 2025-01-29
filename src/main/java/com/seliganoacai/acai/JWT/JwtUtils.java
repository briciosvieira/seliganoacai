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
    public static final String KEY =  "tlAum)J^F`V^HA421c0l=kqq3aR/O+0wn*nU;C";
    public static final String AUTHORIZATION =  "Authorization";
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

    //metodo de criação do token
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

        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);

        return jwtToken;
    }


    //remove o valor que tem na variavel BEARER
    public static String removePrefixBearer(String token){
        if (token.contains(BEARER)){
            return token.substring(BEARER.length()).trim();
        }
        return token;
    }

    //pega os dados que estão no cabeçalho, ou seja, no Cliams
    public static Claims getCliamsFromToken(String token){

        try {
            return Jwts.parser()
                    .setSigningKey(generateKey()).build()
                    .parseClaimsJws(removePrefixBearer(token)).getBody();
        } catch (JwtException e) {
            System.out.println("Corrigir, Usuario não encontrado");
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
