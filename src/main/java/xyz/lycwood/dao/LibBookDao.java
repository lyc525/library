package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.lycwood.entity.LibBook;

public interface LibBookDao {
	// 根据书名查询书的数据信息进行分页处理
	List<LibBook> findPageObjects(@Param("name") String name);

	int getRowCount(@Param("name") String name);

	// 删除图书信息
	int deleteObject(@Param("ids") Integer... ids);

	/**
	 * 添加图书信息
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(LibBook entity);

	/**
	 * 根据id查询图书信息
	 * 
	 * @param id
	 * @return
	 */
	LibBook findBookById(Integer id);

	/**
	 * 
	 * @param
	 * @return entity
	 */

	List<LibBook> findBookByCategory(String category);

	List<LibBook> findBookByAuthor(@Param("author") String author);

	/**
	 * 根据id进行更新图书信息
	 * 
	 * @param entity
	 * @return
	 */
	int updateObject(LibBook entity);

	/**
	 * 根据id进行更新状态码
	 * 
	 * @param id
	 * @return
	 */
	int updateValid(@Param("id") Integer id, @Param("valid") Integer valid);

	int updateNumById(@Param("id") Integer id, @Param("oddNum") Integer oddNum);
}
