package com.xsl.shiro;

/**
 * Created by betty77 on 2017/7/31.
 */

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 用于授权的Token对象：
 * 用户身份即用户名；
 * 凭证即客户端传入的消息摘要。
 */
public class StatelessAuthenticationToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String username;//用户身份即用户名；
    private String password;//密码
    private boolean rememberMe;
    private String host;
    private String token; //jwt


    public StatelessAuthenticationToken(String username, String token) {
        super();
        this.username = username;
        this.token = token;
    }


    public StatelessAuthenticationToken(String username, String password, String token) {
        super();
        this.username = username;
        this.password = password;
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
