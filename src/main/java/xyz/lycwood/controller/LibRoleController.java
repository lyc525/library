package xyz.lycwood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.entity.LibRole;
import xyz.lycwood.service.LibRoleService;

@Controller
@RequestMapping("/role/")
public class LibRoleController {

	@Autowired
	private LibRoleService libRoleService;
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		return new JsonResult(libRoleService.findObjects(name, pageCurrent));
	}
	
	@RequestMapping("doRoleListUI")
	public String doRoleListUI(){
		return "sys/role_list";
	}
	
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI(){
		return "sys/role_edit";
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(LibRole entity,Integer [] menuIds){
		libRoleService.insertObject(entity, menuIds);
		return new JsonResult("save OK");
	}
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		libRoleService.deleteObject(id);
		return new JsonResult("delete OK");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		return new JsonResult(libRoleService.findObjectById(id));
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(LibRole entity,Integer [] menuIds){
		libRoleService.updateObject(entity, menuIds);
		return new JsonResult("update OK");
	}
	
	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindRoles(){
		return new JsonResult(libRoleService.findObjects());
	}
}
