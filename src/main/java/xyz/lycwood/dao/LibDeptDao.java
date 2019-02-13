package xyz.lycwood.dao;

import java.util.List;
import java.util.Map;

import xyz.lycwood.common.vo.Node;
import xyz.lycwood.entity.LibDept;


/**
 * 部门表相关接口
 * @author Administrator
 *
 */
public interface LibDeptDao {
	/**
	 * 查询所有的部门信息
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	/**
	 * 基于部门id查询该部门下是否存在子部门
	 */
	int selectChildDeptByDeptId(Integer id);
	/**
	 * 基于部门id进行删除操作
	 * @param id
	 * @return
	 */
	int deleteObjects(Integer id);
	/**
	 * 查询部门树结构信息
	 * @return
	 */
	List<Node> findZTreeDeptNodes();
	/**
	 * 保存部门数据信息到数据库
	 * @param entity
	 * @return
	 */
	int insertObjects(LibDept entity);
	/**
	 * 更新部门数据信息到数据库
	 * @param entity
	 * @return
	 */
	int updateObects(LibDept entity);
}
