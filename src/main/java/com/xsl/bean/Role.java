package com.xsl.bean;

import java.util.List;

/**
 * Created by xushilong on 2017/12/10.
 */
public class Role {
    /**
     * 角色id
     */
    private String id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;

    private List<Permission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
