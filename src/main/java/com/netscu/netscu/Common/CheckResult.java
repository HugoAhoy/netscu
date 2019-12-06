package com.netscu.netscu.Common;

import io.jsonwebtoken.Claims;

/**
 * @author HugoAhoy
 * @date 2019/12/6 0006
 * @time 1:13
 */
public class CheckResult {
    private Boolean success;
    private Claims claims;
    private String errCode;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
