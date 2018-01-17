package com.xsl.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
public class JsonWebTokenUtil {

    private SignatureAlgorithm signatureAlgorithm;

    private Key secretKey;

    /**
     * 初始化基本信息
     *
     * @param encodedKey
     */
    public JsonWebTokenUtil(String encodedKey) {
        signatureAlgorithm = SignatureAlgorithm.HS512;
        secretKey = deserializeKey(encodedKey);
    }

    /**
     * 获得key
     *
     * @param encodedKey
     * @return
     */
    public static Key deserializeKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        Key key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());
        return key;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * 获得加密后的auth
     *
     * @param subject
     * @param expiry
     * @return
     */
    public String getJWTString(String subject, Integer expiry) {
        JwtBuilder jwtBuilder = Jwts.builder();
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", "HS512");
        jwtBuilder.setHeader(header);
        jwtBuilder.setIssuer("duoxieyun");
        jwtBuilder.setExpiration(DateUtil.getExpiryDate(expiry));
        jwtBuilder.setSubject(subject);
        jwtBuilder.setId("goverment");
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);
        return jwtBuilder.compact();
    }

    /**
     * 获得auth中的信息
     *
     * @param jsonWebToken
     * @return
     */
    public Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }
}
