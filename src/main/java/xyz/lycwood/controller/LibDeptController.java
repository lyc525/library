package xyz.lycwood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.entity.LibDept;
import xyz.lycwood.service.LibDeptService;

@Controller
@RequestMapping("/dept/")
public class LibDeptController {
	
	@RequestMapping("doDeptListUI")
	public String doDeptListUI(){
		return "sys/dept_list";
	}
	
	@RequestMapping("doDeptEditUI")
	public String doDeptEditUI(){
		return "sys/dept_edit";
	}
	
	@Autowired
	private LibDeptService libDeptService;
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		return new JsonResult(libDeptService.findObjects());
	}
	
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		return new JsonResult(libDeptService.deleteObjects(id));
	}
	
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes(){
		return new JsonResult(libDeptService.findZTreeDeptNodes());
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(LibDept entity){
		libDeptService.insertObjects(entity);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(LibDept entity){
		libDeptService.updateObjects(entity);
		return new JsonResult("update ok");
	}
	
}
