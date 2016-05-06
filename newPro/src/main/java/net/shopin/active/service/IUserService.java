package net.shopin.active.service;

import java.util.List;

import net.shopin.active.model.User;

public interface IUserService {
	public List<User> findUserByName();
}
