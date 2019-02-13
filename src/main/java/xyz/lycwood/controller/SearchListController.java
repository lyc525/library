package xyz.lycwood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.LibBook;
import xyz.lycwood.service.LibBookService;
import xyz.lycwood.service.LibUserBookSercive;

@Controller
@RequestMapping("/search/")
public class SearchListController {
	
	@RequestMapping("doSearchList")
	public String doSearchList(){
		return "lib/search_list";
	}
	@Autowired
	LibBookService libBookService;
	@Autowired
	LibUserBookSercive libUserBookSercive;
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,String name,String category){
		//System.out.println(name+pageCurrent+category);
		PageObject<LibBook> list = libBookService.searchPageObjects(name,pageCurrent,category);
		List<LibBook> ll = list.getRecords();
		for (LibBook b : ll) {
			if(b.getId() == 0){
				JsonResult jss = new JsonResult();
				jss.setState(0);
				jss.setMessage("查不到该书籍信息!");
				return jss;
			}
		}
		return new JsonResult(list);
	}
	@RequestMapping("doUserBook")
	@ResponseBody
	public JsonResult douserBook(Integer bookId,String username){
		
		//System.out.println("借阅"+bookId+username);
		libUserBookSercive.borrowBook(bookId, username);
		return new JsonResult("借阅成功");
	}
	@RequestMapping("doSearchByCate")
	@ResponseBody
	public JsonResult doSearchByCate(String category){
		//System.out.println(category);
		PageObject<LibBook> list = libBookService.findBookByCategory(category);
		
		return new JsonResult(list);
	}
}
