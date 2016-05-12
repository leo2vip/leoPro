package net.shopin.active.service;

import java.util.List;
import java.util.Map;

import net.shopin.active.model.security.privilege;
import net.shopin.active.model.security.role;
import net.shopin.active.model.security.role_privilege;
import net.shopin.active.model.security.user;
import net.shopin.active.model.security.user_role;

//import net.shopin.active.model.User;

public interface IUserService {
	public user findUserByName();
	
	public List<user> findUserByModel(user user);
	//用户、角色
	public user_role findUserRoleBySid(Integer sid);
	
	public List<user_role> findUserRoleByModel(user_role userRole);
	//角色
	public role findRoleBySid(Integer sid);
	//角色、资源
	public role_privilege findRolePrivilegeBySid(Integer sid);
	
	public List<role_privilege> findRolePrivilegeByModel(role_privilege rolePrivilege);
	//资源
	public privilege findPrivilegeBySid(Integer sid);
	//根据用户名获取角色和权限
	public Map findUserRolePrivilege(String username);
}
