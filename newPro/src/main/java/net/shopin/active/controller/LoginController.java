package net.shopin.active.controller;

import javax.servlet.http.HttpServletRequest;

import net.shopin.active.service.IfirstService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD:newPro/src/main/java/net/shopin/active/controller/promotionController.java
=======
import net.shopin.active.service.IUserService;
import net.shopin.active.service.IfirstService;

>>>>>>> 8d52e7f... leo add spring security easy:newPro/src/main/java/net/shopin/active/controller/LoginController.java
@Controller
@RequestMapping("/html/mydesktop")
public class LoginController {
	@Autowired
	private IfirstService firstService;
	/**@author qutengfei
	 * @description 登陆成功
	 * @Date 20160401
	 */
	@RequestMapping("/login")
<<<<<<< HEAD:newPro/src/main/java/net/shopin/active/controller/promotionController.java
	public void login(){
		firstService.first();
		System.out.println("success1");
=======
	public ModelAndView login(){
		System.out.println("登陆成功=============================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/index");
		return modelAndView;
>>>>>>> 8d52e7f... leo add spring security easy:newPro/src/main/java/net/shopin/active/controller/LoginController.java
	}
	/***
	 * @author qutengfei 
	 * @description 失败返回页
	 */
	@RequestMapping("/index")
	public ModelAndView menu(HttpServletRequest request){
		System.out.println("success to menu");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/login");
		modelAndView.addObject("error", true);
		return modelAndView;
	}
}
