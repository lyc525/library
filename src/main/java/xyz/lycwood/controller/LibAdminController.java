package xyz.lycwood.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.entity.LibAdmin;
import xyz.lycwood.service.LibAdminService;

@Controller
@RequestMapping("/admin/")
public class LibAdminController {

	@RequestMapping("doAdminListUI")
	public String doAdminListUI(){
		return "sys/admin_list";
	}
	
	@RequestMapping("doAdminEditUI")
	public String doAdminEditUI(){
		return "sys/admin_edit";
	}
	
	@Autowired
	private LibAdminService libAdminService;
	
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password){
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return new JsonResult("login ok");
	}
	
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String adminname,Integer pageCurrent){
		return new JsonResult(libAdminService.findPageObjects(adminname, pageCurrent));
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(LibAdmin entity,Integer[] roleIds,Integer deptId){
		libAdminService.insertObject(entity, roleIds,deptId);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		return new JsonResult(libAdminService.findObjectById(id));
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(LibAdmin entity,Integer[] roleIds){
		libAdminService.updateObject(entity, roleIds);
		return new JsonResult("update ok");
	}
	
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		libAdminService.deleteObject(id);
		return new JsonResult("delete ok");
	}
}
