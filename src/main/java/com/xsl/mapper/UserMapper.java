package com.xsl.mapper;

import com.xsl.bean.Role;
import com.xsl.bean.User;
import com.xsl.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xushilong on 2018/1/17.
 */
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @Select("SELECT id,username AS userName,realname AS realName,telephone,password,adduser AS addUser,createtime AS createTime,`status`" +
            " FROM `user` WHERE username=#{userName}")
    User getUserByUserName(@Param("userName") String userName);

    /**
     * 获取用户的角色信息
     *
     * @param id
     * @return
     */
    List<Role> getRolesByUserId(@Param("id") String id);

    @Select("SELECT * FROM user LIMIT 1")
    UserBean getUserInfo();
}
