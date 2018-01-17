package com.xsl.service;

import com.xsl.bean.LoginUser;
import com.xsl.bean.ResponseBean;
import com.xsl.bean.Role;
import com.xsl.bean.User;

import java.util.List;

/**
 * Created by xushilong on 2018/1/17.
 */
public interface UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 获取用户对应的角色信息
     *
     * @param id
     * @return
     */
    List<Role> getRolesByUserId(String id);

    /**
     * 用户登录
     *
     * @param token
     * @param user
     * @return
     */
    ResponseBean login(String token, LoginUser user);

    /**
     * 获取用户信息
     *
     * @return
     */
    ResponseBean getUserInfo();
}
