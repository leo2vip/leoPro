package net.shopin.active.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import bsh.StringUtil;
import net.shopin.active.model.security.user;
import net.shopin.active.service.IUserService;

@Controller
@RequestMapping("/userManagement")
public class UserManagementController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private IUserService userService;
	
	/**@author qutengfei
	 * @description 获取所有用户
	 * @Date 20160401
	 */
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request){
		user user = new user();
		ModelAndView modelAndView = new ModelAndView();
//		JsonObject obj = new JsonObject();
		
		String username = request.getParameter("username");
		if (null != user && !"".equals(username)) {
			user.setUsername(username);
		}
		
		List<user> userList = userService.findUserByModel(user);
		log.info(""+userList.size());
		modelAndView.addObject("list", userList);
		return null;
		
	}
}
