package com.seliganoacai.acai.JWT;

 import io.jsonwebtoken.security.Keys;

 import java.nio.charset.StandardCharsets;
 import java.security.Key;


public class JwtUtils {

    public static final String BEARER =  "Beare ";
    public static final String KEY =  "tlAum)J^F`V^HA421c0l=kqq3aR/O+0wn*nU;C";
    public static final String AUTHORIZATION =  "AUTHORIZATION";
    public static final long day =  0;
    public static final long huros =  0;
    public static final long minutes =  1;

    public JwtUtils() {
    }

    public static Key generateKey(){
        return Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
    }


}
