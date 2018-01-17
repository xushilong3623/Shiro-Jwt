package com.xsl.bean;

/**
 * Created by xushilong on 2017/12/10.
 */
public class Permission {
    /**
     * 权限id
     */
    private String id;
    /**
     * 备注
     */
    private String remark;
    /**
     * 名称
     */
    private String name;
    /**
     * 标识
     */
    private String sign;
    /**
     * 所属功能模块
     */
    private Module module;

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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
