package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LibRoleMenusDao {
	/**
	 * 插入角色时根据角色id向中间表插入数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObject(@Param("roleId")Integer roleId,@Param("menuIds")Integer [] menuIds);
	/**
	 * 根据角色id删除中间表数据
	 * @param id
	 * @return
	 */
	int deleteObjectByRoleId(Integer id);
	
	List<Integer> findMenuIdByRoleIds(@Param("ids") Integer[] ids);
}
