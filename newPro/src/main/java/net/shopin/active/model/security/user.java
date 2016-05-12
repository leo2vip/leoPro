package net.shopin.active.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.shopin.active.mapper.roleMapper;
import net.shopin.active.mapper.user_roleMapper;

@Service
public class user implements Serializable {
	
	private static final long serialVersionUID = 3419768526893806079L;
	
    private Integer sid;

    private String username;

    private String password;

    private user_role userRole;
    
//    private List<role> roleList;
//    
//    private List<role_privilege> role_privilegeList;
//    
//    private List<privilege> privilegeList;
    
    
	public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
////		GrantedAuthority authority  = new SimpleGrantedAuthority(role);
////		authorities.add(authority);
////		return authorities;
//		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//		user_role user_role = new user_role();
//		user_role.setUser_sid(sid);
//		List<user_role> user_roleList = user_roleMapper.selectByModel(user_role);
//		for (int i = 0; i < user_roleList.size(); i++) {
//			role role = new role();
//			role.setSid(user_roleList.get(i).getRole_sid());
//			//获取权限
//			List<role> rolelist= roleMapper.selectByModel(role);
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rolelist.get(0).getRole());
//			auths.add(grantedAuthority);
//		}
//		return auths;
//	}
//
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
}