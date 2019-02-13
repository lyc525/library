package xyz.lycwood.service;

import java.util.Map;

import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.LibAdmin;

/**
 * 员工管理业务层数据接口
 * @author Administrator
 *
 */
public interface LibAdminService {
	/**
	 * 查询员工信息
	 * @param adminname
	 * @param pageCurrent
	 * @return
	 */
	PageObject<LibAdmin> findPageObjects(String adminname,Integer pageCurrent);
	/**
	 * 保存员工信息
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int insertObject(LibAdmin entity,Integer[] roleIds,Integer deptId);
	/**
	 * 显示员工信息
	 * @param id
	 * @return
	 */
	Map<String,Object> findObjectById(Integer id);
	/**
	 * 更新员工信息
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateObject(LibAdmin entity,Integer[] roleIds);
	/**
	 * 删除员工信息及员工及角色的关联信息
	 * @param id
	 * @param roleIds
	 * @return
	 */
	int deleteObject(Integer id);
}
