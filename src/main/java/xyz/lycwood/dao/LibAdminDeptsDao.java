package xyz.lycwood.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 员工与部门的中间表
 * @author Administrator
 *
 */
public interface LibAdminDeptsDao {
	int insertObject(@Param("adminId") Integer adminId,@Param("deptId") Integer deptId );
}
