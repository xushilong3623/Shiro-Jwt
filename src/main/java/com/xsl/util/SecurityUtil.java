package com.xsl.util;

import com.xsl.bean.ShiroUser;
import com.xsl.bean.User;
import org.apache.shiro.subject.Subject;

/**
 * Created by xushilong on 2018/1/17.
 */
public class SecurityUtil {

    public static User getLoginUser() {
        return getShiroUser().getUser();
    }

    public static ShiroUser getShiroUser() {
        Subject subject = getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        return shiroUser;
    }


    public static String getUserId() {
        Subject subject = getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        if (!ValidateUtil.isNullOrEmpty(shiroUser.getUser())) {
            return shiroUser.getUser().getId();
        }
        return null;
    }

    public static Subject getSubject() {
        return org.apache.shiro.SecurityUtils.getSubject();
    }

    public static String decodePassword(String password, String key) {
        return Base64Util.getFromBase64(XORUtil.getInstance().decode(password, key));
    }
}
