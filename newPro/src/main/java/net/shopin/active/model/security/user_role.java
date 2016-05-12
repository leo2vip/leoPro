package net.shopin.active.model.security;

import java.io.Serializable;

public class user_role implements Serializable {
    private Integer sid;

    private Integer user_sid;

    private Integer role_sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUser_sid() {
        return user_sid;
    }

    public void setUser_sid(Integer user_sid) {
        this.user_sid = user_sid;
    }

    public Integer getRole_sid() {
        return role_sid;
    }

    public void setRole_sid(Integer role_sid) {
        this.role_sid = role_sid;
    }
}