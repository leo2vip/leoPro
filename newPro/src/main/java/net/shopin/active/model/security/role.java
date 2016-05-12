package net.shopin.active.model.security;

import java.io.Serializable;

public class role implements Serializable {
    private Integer sid;

    private String role;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}