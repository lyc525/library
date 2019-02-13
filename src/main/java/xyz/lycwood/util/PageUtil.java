package xyz.lycwood.util;

import java.util.List;

import com.github.pagehelper.PageInfo;

import xyz.lycwood.common.vo.PageObject;


public class PageUtil {

	public static PageObject page(List<?> list,Integer pageSize){
		
		
		PageInfo<?> pi = new PageInfo<>(list);
		PageObject po = new PageObject<>();
		po.setPageCount(pi.getPages());
		po.setPageCurrent(pi.getPageNum());
		po.setPageSize(pageSize);
		po.setRecords(pi.getList());
		po.setRowCount((int)pi.getTotal());
		return po;
	}
}
