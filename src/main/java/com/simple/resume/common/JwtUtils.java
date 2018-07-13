package com.simple.resume.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

//用来生成token
@Repository
public class JwtUtils {

	public static final String BASE64_SECURITY = "simple";
			
    /**
     * jwt解密，需要密钥和token，如果解密失败，说明token无效
     * @param jsonWebToken
     * @param
     * @return
     */
    public Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(BASE64_SECURITY))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 创建token
     *
     * @param
     * @param
     * @param
     * @return
     */
    public String createJWT(int userID, String userName, int TTL_HOURS) {
        TTL_HOURS = TTL_HOURS * 60 * 60 *1000;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥 就是一个base64加密后的字符串？
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(BASE64_SECURITY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        //创建时间
        //主题，也差不多是个人的一些信息，为了好的移植，采用了map放个人信息，而没有采用JSON
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
        		.claim("userID", userID)
                .claim("userName", userName)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);  //估计是第三段密钥
        //添加Token过期时间
        if (TTL_HOURS >= 0) {
            // 过期时间
            long expMillis = nowMillis + TTL_HOURS;
            // 现在是什么时间
            Date exp = new Date(expMillis);
            // 系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }
}
