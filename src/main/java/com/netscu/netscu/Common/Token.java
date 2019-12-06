package com.netscu.netscu.Common;

import com.auth0.jwt.algorithms.Algorithm;
import com.netscu.netscu.Entity.User;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author HugoAhoy
 * @date 2019/12/6 0006
 * @time 0:28
 */
public class Token {
    public static String createJWT(User user){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date Now = new Date(nowMillis);
        Date expDate = new Date(SystemConstant.JWT_TTL);
        SecretKey secretKey = generalKey();
        JwtBuilder token = Jwts.builder()
                .setId(user.getId())
                .setIssuedAt(Now)//设置签发日期
                .setExpiration(expDate)
                .signWith(signatureAlgorithm, secretKey);
        return token.compact();
    }

    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(SystemConstant.JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    private static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
