package net.shopin.active.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	private String username;
	private String password;
	private String role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	//获取验证信息
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority  = new SimpleGrantedAuthority(role);
		authorities.add(authority);
		return authorities;
	}
	//过期
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//锁定
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	//凭据是否过期
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//是否可用
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
