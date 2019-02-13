package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LibAdminRolesDao {
	/**
	 * 将员工与角色相关的数据表写入数据库
	 * @param adminId
	 * @param roleId
	 * @return
	 */
	int insertObject(@Param("adminId") Integer adminId,@Param("roleIds") Integer[] roleIds );
	/**
	 * 根据id进行查找相关员工信息
	 * @param id
	 * @return
	 */
	List<Integer> findObjectById(Integer id);
	/**
	 * 根据用户id进行删除相关角色信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
}
