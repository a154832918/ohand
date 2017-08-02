
package com.spring.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.filter.ErrorFilter;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Token生成工具类
 * <p/>
 */
@Slf4j
public class JsonWebTokenUtility {
    private SignatureAlgorithm signatureAlgorithm;
    private Key secretKey;
    Logger log= LoggerFactory.getLogger(JsonWebTokenUtility.class);

    public JsonWebTokenUtility() {
        //算法
        signatureAlgorithm = SignatureAlgorithm.HS512;
        //加密后密钥，到时候要放到外面
        String encodedKey =
                "L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg==";
        //密钥
        secretKey = deserializeKey(encodedKey);
    }

    /**
     * 创建jwt token
     * @param authTokenDetails
     * @return
     */
    public String createJsonWebToken(AuthTokenDetails authTokenDetails) {
        String token =
                Jwts.builder().setSubject(authTokenDetails.getId().toString())
                        .claim("username", authTokenDetails.getUsername())
                        .claim("roleNames", authTokenDetails.getRoleNames())
                        .setExpiration(authTokenDetails.getExpirationDate())
                        .signWith(getSignatureAlgorithm(),
                                getSecretKey()).compact();
        return token;
    }

    private Key deserializeKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        Key key =
                new SecretKeySpec(decodedKey, getSignatureAlgorithm().getJcaName());
        return key;
    }

    private Key getSecretKey() {
        return secretKey;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public AuthTokenDetails parseAndValidate(String token) {
        AuthTokenDetails authTokenDetails = null;
        try {
            Claims claims =
                    Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
            String userId = claims.getSubject();
            String username = (String) claims.get("username");
            List<String> roleNames = (List) claims.get("roleNames");
            Date expirationDate = claims.getExpiration();

            authTokenDetails = new AuthTokenDetails();
            authTokenDetails.setId(Long.valueOf(userId));
            authTokenDetails.setUsername(username);
            authTokenDetails.setRoleNames(roleNames);
            authTokenDetails.setExpirationDate(expirationDate);
        } catch (JwtException ex) {
            log.error(ex.getMessage(), ex);
        }
        return authTokenDetails;
    }

    private String serializeKey(Key key) {
        String encodedKey =
                Base64.getEncoder().encodeToString(key.getEncoded());
        return encodedKey;
    }
}
