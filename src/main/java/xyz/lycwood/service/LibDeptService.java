package xyz.lycwood.service;

import java.util.List;
import java.util.Map;

import xyz.lycwood.common.vo.Node;
import xyz.lycwood.entity.LibDept;

/**
 * 业务层接口
 * @author Administrator
 *
 */

public interface LibDeptService {
	/**
	 * 呈现所有的部门信息
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	/**
	 * 基于部门id进行删除部门
	 * @param id
	 * @return
	 */
	int deleteObjects(Integer id);
	/**
	 * 查询部门的ZTree树结构
	 * @return
	 */
	List<Node> findZTreeDeptNodes();
	/**
	 * 添加部门数据信息
	 * @param entity
	 * @return
	 */
	int insertObjects(LibDept entity);
	/**
	 * 更新部门数据信息
	 * @param entity
	 * @return
	 */
	int updateObjects(LibDept entity);
}
