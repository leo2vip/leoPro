package net.shopin.active.controller;

import javax.servlet.http.HttpServletRequest;

import net.shopin.active.service.IfirstService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/first")
public class promotionController {
	@Autowired
	private IfirstService firstService;
	/**@author qutengfei
	 * @description 登陆
	 * @Date 20160401
	 */
	@RequestMapping("/login")
	public void login(){
		firstService.first();
		System.out.println("success1");
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
