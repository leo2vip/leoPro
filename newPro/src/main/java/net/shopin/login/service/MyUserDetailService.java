package net.shopin.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.shopin.active.mapper.UserMapper;
import net.shopin.active.model.User;
/***
 * 
 * @author leo2v
 * @description spring security 登陆验证，对应spring_security.xml配置 
 */
@Service
public class MyUserDetailService implements UserDetailsService{
	@Autowired
	private UserMapper userMapper;
	
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		List<User> userList = userMapper.select();
		System.out.println(userList.size());
		return userList.get(0);
	}

}
