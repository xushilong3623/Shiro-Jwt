package com.xsl.bean;

/**
 * Created by xushilong on 2017/12/10.
 */
public class LoginUser {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 是否记住我
     */
    private Boolean rememberMe;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
