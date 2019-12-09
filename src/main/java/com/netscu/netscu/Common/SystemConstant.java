package com.netscu.netscu.Common;

/**
 * @author HugoAhoy
 * @date 2019/12/6 0006
 * @time 1:00
 */
public class SystemConstant {
    public static final String JWT_SECRET = "SECRET";
    //测试的时候吧超时时长调大
    public static final long JWT_TTL = 1000*60*60*5;
    public static final String JWT_ERRCODE_EXPIRE ="408";
    public static final String JWT_ERRCODE_FAIL="500";
}
