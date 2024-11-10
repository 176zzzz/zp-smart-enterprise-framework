
package com.zp.sef.common.utils;

import com.alibaba.fastjson.JSON;
import com.zp.sef.common.auth.authentication.JwtUsernamePasswordAuthenticationToken;
import com.zp.sef.common.model.constant.SecurityConstants;
import com.zp.sef.common.model.user.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class JwtTokenUtil {

    // 签名算法 family为RSA的
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS512;

    private static final String AUTHORITIES_KEY = "auth";

    private static final String USER_DETAIL_KEY = "userDetail";

    // 设置RS256私钥
    private static final String JWT_PRIVATE_KEY = "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";

    // 设置RS256公钥
    private static final String JWT_PUBLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAm5gNfg6RX8Yg6S0Vx+ACc73a5Fi98LbDOAYJWpK+wQgYsPmRq5jqGGmTm+fu17c9u+rQt6jaDgKuxtD2hRAVqFaCxixtBIfb07KPcZ63c5FlRwHCSFR42uFfNzWYf2KWyVUb51CyQus9lKtOqkwJWbBCB6IwPJlCV++39dZIqoGXla89d9UTAjyDo9Hn1h8LrEVnvYqMvCrU3qIHuQAeTnU4+rBxPf/y+sh6rAF1ED1X568R3jCKHcSLNOga8ZrwnQ7on+GZU3YZ8OfceHJiphsNp4J1+rg5xDlz7mgRbigF4SMB0Q2rWC2F95HMEeDzHqdjK8csjYxbGb3OIjc7lEoOrtoeNOlNkow/XOaP/iFqvKK1PVg4ZdxpQZlIPMblgNmQMrWJT8P8JqHKZKVAVxHOXkrhTpAHHNJxCwXd8gGhUjKl1UoufkKWlrfgfYRQ2RBNco0j088ea1pLf8i8I+7a1RCCakEiJV1ZYsTKLJ3Vv09q15hDKtjRFdTf0er9KNwjn4oADFCWN7F5tdSt8QNo60NpSypXvMrHCAbOXLbIq5InT+2Gle80xaHFMs4IL5Ob7elg+tGJhpM9JDZNbjjuGt0KPSbv7gXiR3C/ZY32ShaIIJohoAUwU5aPabDS3B/e+PhIfxCRUf7Pyq70p0UTkcpyk3ptvnC2cI3licECAwEAAQ==";


    /**
     * createRsaJWT
     *
     * @param subject 对应用户id
     * @param expireTime 过期时间
     * @param claimsMap 权限，用户详情类等
     * @return String
     */
    public static String createRsaJWT(String subject, Long expireTime, Map<String, Object> claimsMap)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        long nowMillis = System.currentTimeMillis();
        //签发时间
        Date now = new Date(nowMillis);
        //expireTime判断用户是否需要设置token有效时间
        if (expireTime == null) {
            //如果为空，将默认有效期赋值给exprieTime
            expireTime = SecurityConstants.EXPIRATION_TIME;
        }
        //结束时间 = 当前时间 + 设定的有效期时间
        long expiresTime = nowMillis + expireTime;
        //转换为date数据类型
        Date expirationDate = new Date(expiresTime);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", signatureAlgorithm.getValue())
                .setClaims(claimsMap)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, generalPrivateKey())
                .setExpiration(expirationDate);

        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    /**
     * validate token.
     *
     * @param token token
     */
    public static void validateToken(String token) throws GeneralSecurityException {
        try {
            // 尝试解析和验证JWT
            getClaims(token);
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
            throw e;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid token: " + e.getMessage());
            throw e;
        }
    }


    public static Claims getClaims(String jwt) throws GeneralSecurityException {
        return Jwts.parser().setSigningKey(generalPublicKey())
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static LoginUser getUserDetails(String jwt) throws GeneralSecurityException {
        return JSON.parseObject((String) getClaims(jwt).get(USER_DETAIL_KEY), LoginUser.class);
    }

    /**
     * Get auth Info.
     *
     * @param token token
     * @return auth info
     */
    public static Authentication getAuthentication(String token) throws GeneralSecurityException {
        Claims claims = getClaims(token);
        List<GrantedAuthority> authorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES_KEY));
        LoginUser principal = (LoginUser) claims.get(USER_DETAIL_KEY);
        return new JwtUsernamePasswordAuthenticationToken(authorities, principal, principal.getPassword());
    }

    public static String createToken(Authentication authentication)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (authentication == null) {
            throw new IllegalArgumentException("Authentication cannot be null");
        }
        if (authentication.getPrincipal() == null) {
            throw new IllegalArgumentException("Principal cannot be null");
        }
        if (!authentication.isAuthenticated()) {
            throw new SecurityException("User not authenticated");
        }
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        String principalString = JSON.toJSONString(principal);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(AUTHORITIES_KEY, principal.getAuthorities());
        claimsMap.put(USER_DETAIL_KEY, principalString);
        return createRsaJWT(principal.getId(), null, claimsMap);
    }

    /**
     * 加载私钥
     */
    private static PrivateKey generalPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clear = Base64.getDecoder().decode(JWT_PRIVATE_KEY);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        return fact.generatePrivate(keySpec);
    }

    /**
     * 加载公钥
     */
    private static PublicKey generalPublicKey() throws GeneralSecurityException {
        byte[] data = Base64.getDecoder().decode(JWT_PUBLIC_KEY);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        return fact.generatePublic(spec);
    }
}
