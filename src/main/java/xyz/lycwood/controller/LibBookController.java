package xyz.lycwood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.LibBook;
import xyz.lycwood.service.LibBookService;

@Controller
@RequestMapping("/book/")
public class LibBookController {

	@Autowired
	private LibBookService libBookService;
	
	//跳转到图书查询页面
	@RequestMapping("doBookListUI")
	public String doBookListUI(){
		return "sys/book_list";
	}
	//跳转到图书 添加 页面
	@RequestMapping("doBookEditUI")
	public String doBookEditUI(){
		return "sys/book_edit";
	}
	 @RequestMapping("doFindPageObjects")
     @ResponseBody
	  public JsonResult doFindPageObjects(String name, Integer pageCurrent){
		// System.out.println("图书显示");
		  PageObject<LibBook> pageObject = libBookService.findPageObjects(name, pageCurrent);
		  
		  //System.out.println(pageObject);
		  return new JsonResult(pageObject);
	  }
	//根据图书id执行删除操作
		@RequestMapping("doDeleteObject")
		 @ResponseBody
		 public JsonResult doDeleteObject(Integer... ids){
			libBookService.deleteObject(ids);
			 return new JsonResult("delete OK");
		 }
		
		@RequestMapping("doSaveObject")
		@ResponseBody
		public JsonResult doSaveObject(LibBook entity){
			libBookService.insertObjects(entity);
			return new JsonResult("save ok");
		}
		
		@RequestMapping("doFindObjectById")
		@ResponseBody
		public JsonResult doFindObjectById(Integer id){
			return new JsonResult(libBookService.findBookById(id));
		} 
		
		@RequestMapping("doUpdateObject")
		@ResponseBody
		public JsonResult doUpdateObject(LibBook entity){
			libBookService.updateObject(entity);
			return new JsonResult("update ok");
		}
		
		@RequestMapping("doValidById")
		@ResponseBody
		public JsonResult doValidById(Integer id,Integer valid){
			libBookService.updateValid(id, valid);
			return new JsonResult("update ok");
		}
}
