package xyz.lycwood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

	/**
	 * 图书馆首页地址
	 * @return
	 */
	@RequestMapping("doIndex")
	public String doLibIndex(){
		return "index";
	}
	/**
	 * 图书馆后台首页地址
	 * @return
	 */
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "starter";
	}
	/**
	 * 图书馆后台分页界面地址
	 * @return
	 */
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
	/**
	 * 图书馆后台登录界面
	 * @return
	 */
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}
	
	@RequestMapping("doWe")
	public String doWe(){
		return "we";
	}
	
}
