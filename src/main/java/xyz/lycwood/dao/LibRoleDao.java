package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.lycwood.common.vo.CheckBox;
import xyz.lycwood.entity.LibRole;
import xyz.lycwood.vo.RoleVo;



public interface LibRoleDao {
	/**
	 * 从数据库查询角色信息以显示
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<LibRole> findPageObjects(@Param("name")String name);
	
	/**
	 * 根据名字获取总记录数
	 * @param name
	 * @return
	 */
	int getRowCount(String name);
	
	/**
	 * 向数据库插入角色
	 * @param eneiey
	 * @return
	 */
	int insertObject(LibRole eneiey);
	
	/**
	 * 根据角色id删除角色信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	/**
	 * 根据角色id查询角色以及角色对应的菜单信息
	 * @param id
	 * @return
	 */
	RoleVo findObjectById(Integer id);
	/**
	 * 根据角色信息修改角色
	 * @param entity
	 * @return
	 */
	int updateObject(LibRole entity);
	/**
	 * 查询所有的角色
	 * @return
	 */
	List<CheckBox> findObjects();
		
}
