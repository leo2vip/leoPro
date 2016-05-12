package net.shopin.active.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.shopin.active.service.IUserService;
import net.shopin.active.service.IfirstService;

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
	public ModelAndView login(){
		System.out.println("登陆成功=============================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/index");
		return modelAndView;
	}
	/***
	 * @author qutengfei 
	 * @description 失败返回页
	 */
	@RequestMapping("/erLogin")
	public ModelAndView erLogin(HttpServletRequest request){
		System.out.println("success to menu");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../login");
		modelAndView.addObject("error", "登陆信息错误");
		return modelAndView;
	}
	/***
	 * @author qutengfei 
	 * @description 退出重新登陆
	 */
	@RequestMapping("/reLogin")
	public ModelAndView reLogin(HttpServletRequest request){
		System.out.println("success to menu");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../login");
		//modelAndView.addObject("error", true);
		return modelAndView;
	}
}
