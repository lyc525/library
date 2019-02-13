package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.lycwood.entity.LibAdmin;
import xyz.lycwood.vo.AdminVo;

public interface LibAdminDao {
	/**
	 * 基于部门Id进行查询部门下方是否存在员工信息
	 * @param id
	 * @return
	 */
	int selectUserByDeptId(Integer id);
	/**
	 * 查询员工信息
	 * @return
	 */
	List<LibAdmin> findpageObjects(@Param("adminname") String adminname);
	/**
	 * 查询所有员工信息
	 * @param adminname
	 * @return
	 */
	int getRowCount(@Param("adminname") String adminname);
	/**
	 * 保存员工信息至数据库
	 * @param entity
	 * @return
	 */
	int insertObject(LibAdmin entity);
	/**
	 *根据id进行修改用户信息
	 * @param id
	 * @return
	 */
	AdminVo findObjectById(Integer id);
	/**
	 * 根据id进行修改用户信息
	 * @param entity
	 * @return
	 */
	int updateObject(LibAdmin entity);
	/**
	 * 基于id进行删除员工
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 根据员工姓名查询相关员工信息
	 * @param adminname
	 * @return
	 */
	LibAdmin findObject(@Param("adminname") String adminname);
	
}
