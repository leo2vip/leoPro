package net.shopin.active.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.shopin.active.model.User;
import net.shopin.active.service.IUserService;
import net.shopin.active.service.IfirstService;

@Controller
@RequestMapping("/first")
public class promotionController {
	@Autowired
	private IfirstService firstService;
	@Autowired
	private IUserService userService;
	/**@author qutengfei
	 * @description 登陆
	 * @Date 20160401
	 */
	@RequestMapping("/login")
	public void login(){
//		firstService.first();
		List<User> userList = userService.findUserByName();
		System.out.println(userList);
	}
	/***
	 * @author qutengfei 
	 * @description 主菜单
	 */
	@RequestMapping("/menu")
	public ModelAndView menu(HttpServletRequest request){
		System.out.println("success to menu");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/desktop/index");
		return modelAndView;
	}
}
