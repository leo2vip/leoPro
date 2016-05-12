package net.shopin.active.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import net.shopin.active.mapper.privilegeMapper;
import net.shopin.active.mapper.roleMapper;
import net.shopin.active.mapper.role_privilegeMapper;
import net.shopin.active.mapper.userMapper;
import net.shopin.active.mapper.user_roleMapper;
import net.shopin.active.model.security.privilege;
import net.shopin.active.model.security.role;
import net.shopin.active.model.security.role_privilege;
import net.shopin.active.model.security.user;
import net.shopin.active.model.security.user_role;
import net.shopin.active.service.IUserService;


@Service
public class UserServiceImpl implements IUserService{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private userMapper userMapper;
	@Autowired
	private user_roleMapper user_roleMappers;
	@Autowired
	private roleMapper roleMapper;
	@Autowired
	private role_privilegeMapper role_privilegeMapper;
	@Autowired
	private privilegeMapper privilegeMapper;
	
	public user findUserByName() {
		user user = userMapper.selectByPrimaryKey(1);
		return user;
	}

	public user_role findUserRoleBySid(Integer sid) {
		user_role user_role = user_roleMappers.selectByPrimaryKey(sid);
		return user_role;
	}

	public List<user_role> findUserRoleByModel(user_role userRole) {
		List<user_role> list = user_roleMappers.selectByModel(userRole);
		return list;
	}

	public role findRoleBySid(Integer sid) {
		role role = roleMapper.selectByPrimaryKey(sid);
		return role;
	}

	public role_privilege findRolePrivilegeBySid(Integer sid) {
		role_privilege role_privilege = role_privilegeMapper.selectByPrimaryKey(sid);
		return role_privilege;
	}

	public List<role_privilege> findRolePrivilegeByModel(role_privilege rolePrivilege) {
		List<role_privilege> list = role_privilegeMapper.selectByModel(rolePrivilege);
		return list;
	}

	public privilege findPrivilegeBySid(Integer sid) {
		privilege privilege = privilegeMapper.selectByPrimaryKey(sid);
		return privilege;
	}

	public List<user> findUserByModel(user user) {
		List<user> list = userMapper.selectByModel(user);	
		return list;
	}
	/***
	 * @description 根据用户名获取所有权限信息
	 */
	public Map findUserRolePrivilege(String username) {
		log.info("调用findUserRolePrivilege接口开始，传入参数为：username="+username);
		Map returnMap = new HashMap();
		user user = new user();
		List<role> roleList = new ArrayList<role>();
		List<privilege> privilegeList = new ArrayList<privilege>();
		user_role userRole = new user_role();
		Set<SimpleGrantedAuthority> auths = new HashSet<SimpleGrantedAuthority>();
		StringBuffer logMsgRole = new StringBuffer("用户名："+username+"，角色信息为[");
		StringBuffer logMsgPrivilege = new StringBuffer(",拥有的权限为[");
		//获取用户
		user.setUsername(username);
		List<user> userList = this.findUserByModel(user);
		if (userList.size()==0) {
			log.info("系统中无此用户："+username);
			returnMap.put("msg", "系统中无此用户："+username);
			return returnMap;
		}
		//根据用户获取角色
		userRole.setUser_sid(userList.get(0).getSid());
		List<user_role> userRoleList = this.findUserRoleByModel(userRole);
		if (userRoleList.size()==0) {
			log.info("此用户无任何角色信息："+username);
			returnMap.put("msg", "此用户无任何角色信息："+username);
			return returnMap;
		}
		for (int i = 0; i < userRoleList.size(); i++) {
			//获取角色
			role role = this.findRoleBySid(userRoleList.get(i).getRole_sid());
			logMsgRole.append(role.getRole()+",");
			roleList.add(role);
			role_privilege rolePrivilege = new role_privilege();
			rolePrivilege.setRole_sid(role.getSid());
			List<role_privilege> rolePrivilegeList = this.findRolePrivilegeByModel(rolePrivilege);
			
			for (int j = 0; j < rolePrivilegeList.size(); j++) {
				//获取权限
				privilege privilege = this.findPrivilegeBySid(rolePrivilegeList.get(j).getPrivilege_sid());
				logMsgPrivilege.append(privilege.getPrivilege()+",");
				privilegeList.add(privilege);

				SimpleGrantedAuthority ga = new SimpleGrantedAuthority(privilege.getPrivilege());
				auths.add(ga);
			}
		}
		
		if (privilegeList.size()==0) {
			log.info("此用户无任何权限："+username);
			returnMap.put("msg", "此用户无任何权限："+username);
			return returnMap;
		}
		
		returnMap.put("Set<SimpleGrantedAuthority>", auths);
		returnMap.put("userList", userList);
		returnMap.put("roleList", roleList);
		returnMap.put("privilegeList", privilegeList);
		log.info(""+logMsgRole.append("]"+logMsgPrivilege+"]"));
		return returnMap;
	}


}
