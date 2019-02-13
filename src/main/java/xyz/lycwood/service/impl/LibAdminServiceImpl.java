package xyz.lycwood.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.exception.ServiceException;
import xyz.lycwood.common.vo.CheckBox;
import xyz.lycwood.common.vo.Node;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.dao.LibAdminDao;
import xyz.lycwood.dao.LibAdminDeptsDao;
import xyz.lycwood.dao.LibAdminRolesDao;
import xyz.lycwood.dao.LibRoleDao;
import xyz.lycwood.entity.LibAdmin;
import xyz.lycwood.entity.Lib_logs;
import xyz.lycwood.service.LibAdminService;
import xyz.lycwood.util.PageUtil;
import xyz.lycwood.vo.AdminVo;

@Service
@Transactional(timeout=30,rollbackFor=RuntimeException.class)
public class LibAdminServiceImpl implements LibAdminService {

	@Autowired
	private LibAdminDao libAdminDao;
	@Autowired
	private LibAdminRolesDao libAdminRolesDao;
	@Autowired
	private LibAdminDeptsDao libAdminDeptsDao;
	
	@Transactional(readOnly=true)
	@Override
	public PageObject<LibAdmin> findPageObjects(String adminname, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1){
			throw new IllegalArgumentException("页码值异常!");
		}
		int rowCount = libAdminDao.getRowCount(adminname);
		if(rowCount == 0){
			throw new ServiceException("查不到该员工的信息!");
		}
		/*int startIndex = (pageCurrent - 1) * pageSize;
		int pageCount = 0;*/
		
		Integer pageSize = 10;
		
		List<LibAdmin> list = new ArrayList<>();//创建一个结果集对象
		
		PageHelper.startPage(pageCurrent,pageSize);//用分页查询插件进行分页
		
		list = libAdminDao.findpageObjects(adminname);//正常的select查询
		
		PageObject<LibAdmin> po = PageUtil.page(list,pageSize); //调用工具类封装数据
		
		
		//List<LibAdmin> records = libAdminDao.findpageObjects(adminname, startIndex, pageSize);
		/*PageObject<LibAdmin> po = new PageObject<>();
		po.setRecords(records);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		po.setRowCount(rowCount);
		po.setPageCount(pageCount);*/
		return po;
	}
	
	@RequiresLog("员工保存")
	@RequiresPermissions("admin:save")
	@Override
	public int insertObject(LibAdmin entity, Integer[] roleIds,Integer deptId) {
		if(entity == null){
			throw new IllegalArgumentException("保存对象不能为空!");
		}
		if(StringUtils.isEmpty(entity.getAdminname())){
			throw new ServiceException("用户名不能为空!");
		}
		if(StringUtils.isEmpty(entity.getPassword())){
			throw new ServiceException("密码不能为空!");
		}
		if(StringUtils.isEmpty(entity.getDeptId())){
			throw new ServiceException("员工所在部门不能为空!");
		}
		if(StringUtils.isEmpty(entity.getPhone())){
			throw new ServiceException("手机号不能为空!");
		}
		if(roleIds == null ||roleIds.length == 0){
			throw new ServiceException("需要给用户分配角色!");
		}
		if(deptId==null ||deptId < 1){
			throw new ServiceException("用户所在部门不能为空!");
		}
		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		SimpleHash sHash = new SimpleHash("MD5", entity.getPassword(), salt);
		entity.setPassword(sHash.toString());
		int rows = libAdminDao.insertObject(entity);
		if(rows == 0){
			throw new ServiceException("保存失败!");
		}
		libAdminRolesDao.insertObject(entity.getId(), roleIds);
		libAdminDeptsDao.insertObject(entity.getId(), deptId);
		/*if(row > 0){
			throw new ServiceException("我是故意抛的异常!哈哈哈哈");
		}*/
		return rows;
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		if(id == null || id < 1){
			throw new IllegalArgumentException("id值异常!");
		}
		AdminVo user = libAdminDao.findObjectById(id);
		if(user == null){
			throw new ServiceException("此用户已经不存在了!");
		}
		List<Integer> roleIds = libAdminRolesDao.findObjectById(id);
		Map<String,Object> map = new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}

	@RequiresLog("员工修改")
	@RequiresPermissions("admin:update")
	@Override
	public int updateObject(LibAdmin entity, Integer[] roleIds) {
		if(entity == null){
			throw new IllegalArgumentException("保存对象不能为空!");
		}
		if(StringUtils.isEmpty(entity.getAdminname())){
			throw new ServiceException("用户名不能为空!");
		}
		if(StringUtils.isEmpty(entity.getDeptId())){
			throw new ServiceException("员工所在部门不能为空!");
		}
		if(StringUtils.isEmpty(entity.getPhone())){
			throw new ServiceException("手机号不能为空!");
		}
		if(	roleIds == null|| roleIds.length == 0 ){
			throw new ServiceException("角色不能为空");
		}
		int rows = libAdminDao.updateObject(entity);
		if(rows ==0 ){
			throw new ServiceException("更新失败!");
		}
		libAdminRolesDao.deleteObject(entity.getId());
		libAdminRolesDao.insertObject(entity.getId(), roleIds);
		return rows;
	}

	@RequiresLog("员工删除")
	@RequiresPermissions("admin:delete")
	@Override
	public int deleteObject(Integer id) {
		if(id == null || id < 1){
			throw new IllegalArgumentException("id值异常!");
		}
		int rows = libAdminDao.deleteObject(id);
		if(rows == 0 ){
			throw new ServiceException("删除失败!");
		}
		libAdminRolesDao.deleteObject(id);
		return rows;
	}

}
