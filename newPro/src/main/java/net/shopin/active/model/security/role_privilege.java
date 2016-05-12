package net.shopin.active.model.security;

import java.io.Serializable;

public class role_privilege implements Serializable {
    private Integer sid;

    private Integer role_sid;

    private Integer privilege_sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getRole_sid() {
        return role_sid;
    }

    public void setRole_sid(Integer role_sid) {
        this.role_sid = role_sid;
    }

    public Integer getPrivilege_sid() {
        return privilege_sid;
    }

    public void setPrivilege_sid(Integer privilege_sid) {
        this.privilege_sid = privilege_sid;
    }
}