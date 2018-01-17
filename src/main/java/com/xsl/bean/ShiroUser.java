/**
 *
 */
package com.xsl.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1748602382963711884L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 用户信息
     */
    private User user;
    /**
     * jwt凭证
     */
    private String jwt;


    /**
     * 加入更多的自定义参数
     */
    private Map<String, Object> attribute = new HashMap<String, Object>();

    public ShiroUser() {

    }

    public ShiroUser(String loginName) {
        this.loginName = loginName;
    }

    public ShiroUser(String id, String loginName, String jwt) {
        this.id = id;
        this.loginName = loginName;
        this.jwt = jwt;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public void setAttribute(String name, Object value) {
        attribute.put(name, value);
    }

    /**
     * @param name
     * @return
     */
    public Object getAttribute(String name) {
        return attribute.get(name);
    }

    public Object removeAttribute(String name) {
        return attribute.remove(name);
    }

    public Map<String, Object> getAttributes() {
        return this.attribute;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}