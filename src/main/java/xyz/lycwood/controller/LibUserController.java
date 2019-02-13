package xyz.lycwood.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.entity.LibUser;
import xyz.lycwood.service.LibUserService;

/*import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;*/
@RequestMapping("/user/")
@Controller
public class LibUserController {
	@Autowired
	private LibUserService libUserService;

	@RequestMapping("doUserListUI")
	public String doUserListUI(){
		return "sys/user_list";
	}
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "sys/user_edit";
	}
	

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		return new JsonResult(
				libUserService.findObjectById(id));
	}
	
	

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,
			Integer valid){
		libUserService.validById(id, valid);
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent){
		return new JsonResult(
				libUserService.findObject(username, pageCurrent));
	}
	
	@RequiresLog("用户删除书籍")
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer id){
		libUserService.deleteObjectByUserId(id);
		return new JsonResult("delete ok");
	}

	
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password){
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return new JsonResult("login ok");
	}
	
	@RequestMapping("doSearchUser")
	@ResponseBody
	public JsonResult doSearchUser(String username){
		return new JsonResult(libUserService.findByUsername(username));
	}
	
	@RequestMapping("doUpdateUserMessById")
	@ResponseBody
	public JsonResult doUpdateUserMessById(Integer id,String username,String phone,String hobby){
		libUserService.updateUserMessById(id, username, phone, hobby);
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doFindObjectByUsername")
	@ResponseBody
	public JsonResult doFindObjectByUsername(String username){
		LibUser obj = libUserService.findObjectByUsername(username);
		return new JsonResult(obj);
	}
	
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(String username,String password,String gender,String[] check_val,String tel,String cardId){
		//System.out.println("156354");
		libUserService.insertObject(username,password,gender,check_val,tel,cardId);
		return new JsonResult("注册成功!");
	}
	
	@RequestMapping("doCheckUsername")
	@ResponseBody
	public JsonResult doCheckUsername(String username){
		JsonResult acd = libUserService.doCheckUsername(username);
		//System.out.println(acd.getState());
		if(acd.getState()==0)
			return acd;
		else
		return new JsonResult("该用户名可用!");
	}

}
