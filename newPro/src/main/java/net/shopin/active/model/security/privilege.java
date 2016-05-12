package net.shopin.active.model.security;

import java.io.Serializable;

public class privilege implements Serializable {
    private Integer sid;

    private String privilege;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }
}