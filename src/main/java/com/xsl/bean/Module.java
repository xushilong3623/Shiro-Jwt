package com.xsl.bean;

import java.util.List;

/**
 * Created by xushilong on 2017/12/10.
 */
public class Module {
    /**
     * 模块id
     */
    private String id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 标识
     */
    private String sign;

    /**
     * 父类id
     */
    private String parentId;

    /**
     * 权限列表
     */
    private List<Permission> permissions;

    /**
     * id列表 用于批量删除
     */
    private List<String> ids;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
