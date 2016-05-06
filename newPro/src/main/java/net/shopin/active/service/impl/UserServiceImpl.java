package net.shopin.active.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.shopin.active.mapper.UserMapper;
import net.shopin.active.model.User;
import net.shopin.active.service.IUserService;

@Service("IUserService")
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;
	public List<User> findUserByName() {
		List<User> userList = userMapper.select();
		return userList;
	}

}
