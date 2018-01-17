package com.xsl.service.impl;

import com.xsl.bean.*;
import com.xsl.mapper.UserMapper;
import com.xsl.service.UserService;
import com.xsl.shiro.StatelessAuthenticationToken;
import com.xsl.util.JsonWebTokenUtil;
import com.xsl.util.ValidateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xushilong on 2018/1/17.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Value("${jwt.key}")
    private String jwtKey;

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public List<Role> getRolesByUserId(String id) {
        return userMapper.getRolesByUserId(id);
    }

    @Override
    public ResponseBean login(String token, LoginUser user) {
        ResponseBean response = new ResponseBean();
        if (ValidateUtil.isNullOrEmpty(user.getUserName())) {
            response.setCode(ResponseBean.CODE_NOTVALIDATE);
        } else {
            Map<String, Object> result = new HashMap<>();
            Integer expire = 120;
            if (!ValidateUtil.isNullOrEmpty(user.getRememberMe()) && user.getRememberMe()) {
                expire = 43200;
            }
            String jwt = new JsonWebTokenUtil(jwtKey).getJWTString(user.getUserName(), expire);
            StatelessAuthenticationToken authenticationToken = new StatelessAuthenticationToken(user.getUserName(), token, jwt);
            try {
                SecurityUtils.getSubject().login(authenticationToken);
                result.put("pass", "ok");
                result.put("token", jwt);
                response.setResult(result);
                response.setCode(ResponseBean.CODE_SUCCESS);
            } catch (Exception e) {
                result.put("pass", "error");
                response.setResult(result);
                response.setCode(ResponseBean.CODE_SUCCESS);
            }
        }
        return response;
    }

    @Override
    public ResponseBean getUserInfo() {
        ResponseBean response=new ResponseBean();
        Map<String,Object> result=new HashMap<>();
        UserBean user=userMapper.getUserInfo();
        response.setCode(ResponseBean.CODE_SUCCESS);
        result.put("user",user);
        response.setResult(result);
        return response;
    }
}
