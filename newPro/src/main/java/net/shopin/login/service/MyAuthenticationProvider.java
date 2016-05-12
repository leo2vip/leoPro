package net.shopin.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import net.shopin.active.model.security.privilege;
import net.shopin.active.model.security.role;
import net.shopin.active.model.security.user;
import net.shopin.active.service.IUserService;


/***
 * 
 * @author leo2v
 *
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private IUserService userService;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class,
					authentication,
					"Only UsernamePasswordAuthenticationToken is supported");
			String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
					: authentication.getName();
			
			Map returnMap = userService.findUserRolePrivilege(username);
			Set<SimpleGrantedAuthority> auths = (Set)returnMap.get("Set<SimpleGrantedAuthority>");
			List<user> userList = (ArrayList)returnMap.get("userList");
			List<role> roleList = (ArrayList)returnMap.get("roleList");
			List<privilege> privilegeList = (ArrayList)returnMap.get("privilegeList");
			
			if (privilegeList == null || privilegeList.isEmpty()) {
				log.info("本地权限无此用户ID:" + username + ",或者用户权限为空");
			}
			String passwordInDb = "";
			if(null!=userList && userList.size()>0) passwordInDb = userList.get(0).getPassword();
			Object password = authentication.getCredentials();
			Object principal = authentication.getPrincipal();
			
			UsernamePasswordAuthenticationToken upAuthentication = new UsernamePasswordAuthenticationToken(
					principal, password, auths);
			log.info("authentication get:" + upAuthentication);
			
			if (!passwordInDb.equals(password)) {
				log.info("用户名密码不匹配");
				upAuthentication.setAuthenticated(false);
			}
			return upAuthentication;
			
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
