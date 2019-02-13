package xyz.lycwood.service;

import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.LibBook;


/**
 * 图书业务层模块
 * @author Administrator
 *
 */
public interface LibBookService {
	/**
	 * 查找图书 
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	 PageObject<LibBook> searchPageObjects(String name,Integer pageCurrent,String category);
	 /**
	  * 基于图书id进行删除图书信息
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer... id);
	 /**
	  * 添加图书信息
	  * @param entity
	  * @return
	  */
	 int insertObjects(LibBook entity);
	 /**
	  * 基于图书id进行查询图示信息
	  * @param id
	  * @return
	  */
	 LibBook findBookById(Integer id);
	 
	 
	 
	 PageObject<LibBook> findBookByCategory(String category);
	 
	
	 /**
	  * 基于图书id进行更新图书信息
	  * @param entity
	  * @return
	  */
	 int updateObject(LibBook entity);
	 /**
	  * 更新valid的状态码
	  * @param id
	  * @return
	  */
	 int updateValid(Integer id,Integer valid);


	 

/**
 * 图书业务层模块
 * @author Administrator
 *
 */

	/**
	 * 查找图书 
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	 PageObject<LibBook> findPageObjects(String name,Integer pageCurrent);

}
