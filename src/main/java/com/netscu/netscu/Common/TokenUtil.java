package com.netscu.netscu.Common;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HugoAhoy
 * @date 2019/12/8 0008
 * @time 1:16
 */
/*
 * @author MRC
 * @date 2019年4月5日 下午1:14:53
 * @version 1.0
 */
public class TokenUtil {

    public static String getTokenUserId() {
        String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
        String userId = Token.validateJWT(token).getClaims().getSubject();
        return userId;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}