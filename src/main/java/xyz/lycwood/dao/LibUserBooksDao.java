package xyz.lycwood.dao;


import org.apache.ibatis.annotations.Param;


public interface LibUserBooksDao {

	
	/**
	 * 基于会员id删除会员id与书籍id关联信息
	 * @param id
	 * @return
	 */
	int deleteObjectByUserId(@Param("id") Integer id);
	
	int insertObjects(@Param("userId")Integer userId,
			  @Param("bookId")Integer bookId);
}
