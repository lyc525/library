package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.lycwood.entity.LibUser;

public interface LibUserDao {

	/**
	 * 显示会员信息在异步加载页面
	 * @return
	 */
	List<LibUser> findObject(@Param("username") String username);
	/**
	 * 基于用户名进行模糊查询
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username") String username);
	/**
	 * 基于会员id删除用户信息
	 * @param id
	 * @return
	 */
	int deleteObjectByUserId(@Param("id") Integer id);
	/**
	 * 基于会员ID对会员启用禁用信息进行修改
	 * @param id
	 * @param valid
	 * @return
	 */
	int validById(@Param("id")Integer id,
			@Param("valid")Integer valid);
	/**
	 * 基于会员id查询会员信息,做会员信息修改页面的原始数据呈现
	 * @param id
	 * @return
	 */
	LibUser findObjectById(@Param("id")Integer id);
	
	/**
	 * 用于会员注册信息的添加操作
	 * @param entity
	 * @return
	 */
	int insertObject(LibUser entity);
	/**
	 * 基于用户名查询用户所有信息
	 * @param username
	 * @return
	 */
	LibUser findUserByUsername(String username);
	
	int updateById(@Param("id")Integer id,@Param("bookNum")Integer bookNum,@Param("borNum")Integer borNum);
	
	/**
	 * 基于用户id进行更新用户数据
	 * @param id
	 * @param username
	 * @param phone
	 * @param hobby
	 * @return
	 */
	int updateUserMessById(@Param("id") Integer id,
						   @Param("username") String username,
						   @Param("phone") String phone,
						   @Param("hobby") String hobby);
	/**
	 * 用户注册时验证用户名是否存在
	 * @param username
	 * @return
	 */
	LibUser findObjectByUsername(@Param("username")String username);
	
	/**
	 * 查询会员数量,用于设置会员Id
	 * @return
	 */
	int findObjects();
	/**
	 * 查询会员数量,用于验证用户名是否可用
	 * @return
	 */
	int findObjectCountByUsername(@Param("username")String username);
}
