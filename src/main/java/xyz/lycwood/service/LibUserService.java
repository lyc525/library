package xyz.lycwood.service;

import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.LibUser;

public interface LibUserService {
	PageObject<LibUser> findObject(
			String username,
			Integer pageCurrent);
	
	int deleteObjectByUserId(Integer id);
	/**
	 * 设置用户禁用启用修改,数据更新
	 * @param id
	 * @param valid
	 * @return
	 */
	int validById(Integer id,Integer valid);
	/**
	 * 根据会员Id查询会员信息,做修改页面数据呈现
	 * @param id
	 * @return
	 */
	LibUser findObjectById(Integer id);
	
	/**
	 * 用于会员注册信息的添加操作
	 * @param entity
	 * @return
	 *//*
	int insertObject(LibUser entity);*/
	
	
	/**
	 * 根据用户名查询用户所有信息
	 * @param username
	 * @return
	 */
	LibUser findByUsername(String username);
	
	/**
	 * 根据用户名id进行更新用户信息
	 * @param id
	 * @param username
	 * @param phone
	 * @param hobby
	 * @return
	 */
	int updateUserMessById(Integer id,String username,String phone,String hobby);
	
	/**
	 * 用于会员注册信息的添加操作
	 * @param entity
	 * @return
	 */
	int insertObject(String username,String password,String gender,String[] check_val,String tel,String cardId);
	
	/**
	 * 用户注册时验证用户名是否存在
	 * @param username
	 * @return
	 */
	LibUser findObjectByUsername(String username);
	/**
	 * 查询用户名是否存在
	 * @param username
	 * @return
	 */
	JsonResult doCheckUsername(String username);

}

