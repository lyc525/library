package xyz.lycwood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.Lib_logs;
import xyz.lycwood.service.Lib_logsService;

@Controller
@RequestMapping("/log/")
public class Lib_logsController {
	@Autowired
	Lib_logsService lib_logsService;
	@RequestMapping("doLogListUI")
	public String doLogListUI(){
		return "sys/log_list";
	}
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
		PageObject<Lib_logs> po =lib_logsService.findObjects(username,pageCurrent);
		return new JsonResult(po);
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids){
		lib_logsService.deleteObjects(ids);
		return new JsonResult("delete OK");
	}
}
