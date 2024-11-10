import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zp.sef.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

public class JwtTest {

    private static final Long JWT_DEFAULT_EXPIRES = 3600000L; // 1 hour
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

    @Test
    public void testCreateAndParseJwt() throws Exception {
        // 创建测试数据
        String subject = "testSubject";
        Long expireTime = 10 * 60 * 1000L; // 10 minute
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("testClaim", "testValue");

        // 创建 JWT
        String jwt = JwtTokenUtil.createRsaJWT(subject, null, claimsMap);
        System.out.println(jwt);
        assertNotNull(jwt, "The created JWT should not be null");

        // 解析 JWT
        Claims claims = JwtTokenUtil.getClaims(jwt);
        System.out.println("Claims Set:");
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        assertNotNull(claims, "The parsed claims should not be null");
        // 创建一个 SimpleDateFormat 对象，并设置为中国时区
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置为中国时区

        // 获取签发时间，并转换为中国时间格式
        Date issuedAtDate = new Date(claims.getIssuedAt().getTime());
        System.out.println("签发时间: " + sdf.format(issuedAtDate));

        // 获取过期时间，并转换为中国时间格式
        Date expirationDate = new Date(claims.getExpiration().getTime());
        System.out.println("过期时间: " + sdf.format(expirationDate));
        // 验证 JWT 内容
        assertEquals(subject, claims.getSubject(), "The subject should match");
        assertEquals(claimsMap.get("testClaim"), claims.get("testClaim"), "The claim should match");
        assertTrue(claims.getIssuedAt().before(claims.getExpiration()), "IssuedAt should be before expiration");

        JwtTokenUtil.validateToken(jwt);

    }

    @Test
    public void testTokenValidation() throws Exception {
        // 创建测试数据
        String subject = "testSubject";
        Long expireTime = 60000L; // 1 minute
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("testClaim", "testValue");

        // 创建 JWT
        String jwt = JwtTokenUtil.createRsaJWT(subject, expireTime, claimsMap);

        // 验证 JWT
        JwtTokenUtil.validateToken(jwt);
    }

}