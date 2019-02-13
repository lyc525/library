package xyz.lycwood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.entity.LibMenu;
import xyz.lycwood.service.LibMenuService;

@Controller
@RequestMapping("/menu/")
public class LibMenuController {
	
	@Autowired
	private LibMenuService libMenuService;
	
	@RequestMapping("doMenuListUI")
	public String doMenuListUI() {
		return "sys/menu_list";
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(libMenuService.findObjects());
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		libMenuService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI() {
		return "sys/menu_edit";
	}
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(libMenuService.findZtreeNodes());
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(LibMenu entity) {
		libMenuService.insertObject(entity);
		return new JsonResult("save ok");
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(LibMenu entity) {
		libMenuService.updateObject(entity);
		return new JsonResult("update ok");
	}
}
