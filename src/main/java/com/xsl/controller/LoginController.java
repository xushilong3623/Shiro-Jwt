package com.xsl.controller;

import com.xsl.bean.LoginUser;
import com.xsl.bean.ResponseBean;
import com.xsl.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xushilong on 2018/1/17.
 */

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean login(@RequestHeader(value = "Authorization") String token,
                              @RequestBody LoginUser user){
        return userService.login(token,user);
    }

    @RequestMapping(value = "/user/info",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("User:view")
    public ResponseBean getUserInfo(){
        return userService.getUserInfo();
    }
}
