package xyz.lycwood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/")
public class IndexController {

	@RequestMapping("doIndexList")
	public String doIndexList(){
		return "lib/index_list";
	}
	
	@RequestMapping("doLoginList")
	public String doLoginList(){
		return "lib/login_list";
	}
	
	@RequestMapping("doRegisterList")
	public String doRegisterList(){
		return "lib/register_list";
	}
	
	@RequestMapping("doSearchList")
	public String doSearchList(){
		return "lib/search_list";
	}
	
	@RequestMapping("doSearchUserMess")
	public String doSearchUserMess(){
		return "lib/search_user_mess";
	}
	
	@RequestMapping("doUpdateUserMess")
	public String doUpdateUserMess(){
		return "lib/update_user_mess";
	}
	
}
