package xyz.lycwood.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.exception.ServiceException;
import xyz.lycwood.common.vo.Node;
import xyz.lycwood.dao.LibAdminDao;
import xyz.lycwood.dao.LibDeptDao;
import xyz.lycwood.entity.LibDept;
import xyz.lycwood.service.LibDeptService;
/**
 * 部门模块业务层的实现类
 * @author Administrator
 *
 */

@Service
public class LibDeptServiceImpl implements LibDeptService{
	
	@Autowired
	private LibDeptDao libDeptDao;
	@Autowired
	private LibAdminDao libAdminDao;

	@Override
	public List<Map<String,Object>> findObjects() {
		return libDeptDao.findObjects();
	}

	@RequiresLog("部门删除")
	@RequiresPermissions("dept:delete")
	@Override
	public int deleteObjects(Integer id) {
		if(id == null || id < 1){
			throw new IllegalArgumentException("id值存在异常!");
		}
		int row = libDeptDao.selectChildDeptByDeptId(id);
		if(row > 0){
			throw new ServiceException("该部门下存在有子部门,不能进行删除操作!");
		}
		int rows = libAdminDao.selectUserByDeptId(id);
		if(rows > 0){
			throw new ServiceException("该部门下存在员工信息,不能进行删除操作!");
		}
		int rowss = libDeptDao.deleteObjects(id);
		if(rowss == 0){
			throw new ServiceException("删除部门信息失败!");
		}
		return rowss;
	}

	@Override
	public List<Node> findZTreeDeptNodes() {
		return libDeptDao.findZTreeDeptNodes();
	}

	@RequiresLog("部门保存")
	@RequiresPermissions("dept:save")
	@Override
	public int insertObjects(LibDept entity) {
		if(entity == null){
			throw new ServiceException("保存对象不能为空!");
		}
		if(StringUtils.isEmpty(entity.getName())){
			throw new ServiceException("部门名称不能为空!");
		}
		if(StringUtils.isEmpty(entity.getNote())){
			throw new ServiceException("部门备注不能为空!");
		}
		if(StringUtils.isEmpty(entity.getSort())){
			throw new ServiceException("部门排序号部门为空!");
		}
		if(StringUtils.isEmpty(entity.getParentId())){
			throw new ServiceException("上级部门不能为空!");
		}
		int rows = libDeptDao.insertObjects(entity);
		if(rows == 0){
			throw new ServiceException("保存失败!");
		}
		return rows;
	}

	@RequiresLog("部门修改")
	@RequiresPermissions("dept:update")
	@Override
	public int updateObjects(LibDept entity) {
		if(entity == null){
			throw new ServiceException("保存对象不能为空!");
		}
		if(StringUtils.isEmpty(entity.getName())){
			throw new ServiceException("部门名称不能为空!");
		}
		if(StringUtils.isEmpty(entity.getNote())){
			throw new ServiceException("部门备注不能为空!");
		}
		if(StringUtils.isEmpty(entity.getSort())){
			throw new ServiceException("部门排序号部门为空!");
		}
		if(StringUtils.isEmpty(entity.getParentId())){
			throw new ServiceException("上级部门不能为空!");
		}
		int rows = libDeptDao.updateObects(entity);
		if(rows == 0){
			throw new ServiceException("更新失败!");
		}
		return rows;
	}
}
