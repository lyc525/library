package xyz.lycwood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.vo.CheckBox;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.dao.LibRoleDao;
import xyz.lycwood.dao.LibRoleMenusDao;
import xyz.lycwood.entity.LibRole;
import xyz.lycwood.entity.Lib_logs;
import xyz.lycwood.service.LibRoleService;
import xyz.lycwood.util.PageUtil;
import xyz.lycwood.vo.RoleVo;

@Service
@Transactional(timeout=30,rollbackFor=RuntimeException.class)
public class LibRoleServiceImpl implements LibRoleService {

	@Autowired
	private LibRoleDao libRoleDao;
	@Autowired
	private LibRoleMenusDao libRoleMenusDao;
	
	
	@Transactional(readOnly=true)
	@RequiresPermissions("role:find")
	@Override
	public PageObject<LibRole> findObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<=0){
			throw new RuntimeException("请输入合法的页码"); 
		}
		int pageSize = 10;
		/*int startIndex = (pageCurrent - 1) * pageSize;
		List<LibRole> list = libRoleDao.findPageObjects(name, startIndex, pageSize);
		int rowCount = libRoleDao.getRowCount(name);
		int pageCount = (int)Math.ceil((double)rowCount/(double)pageSize);
		PageObject<LibRole> obj = new PageObject<>();
		obj.setPageCount(pageCount);
		obj.setPageCurrent(pageCurrent);
		obj.setPageSize(pageSize);
		obj.setRecords(list);
		obj.setRowCount(rowCount);*/
		
		List<LibRole> list = new ArrayList<>();//创建一个结果集对象
		
		PageHelper.startPage(pageCurrent,pageSize);//用分页查询插件进行分页
		
		list = libRoleDao.findPageObjects(name);//正常的select查询
		
		PageObject<LibRole> po = PageUtil.page(list,pageSize); //调用工具类封装数据
		return po;
	}

	@RequiresLog("保存角色")
	@Override
	public int insertObject(LibRole entity,Integer [] menuIds) {
		if(entity==null){
			throw new RuntimeException("角色信息不能为空");
		}
		if(entity.getName()==null||entity.getName()==""){
			throw new RuntimeException("请输入角色信息");
		}
		if(menuIds==null||menuIds.length==0){
			throw new RuntimeException("请为角色赋予权限");
		}
		int row = libRoleDao.insertObject(entity);
		if(row==0)throw new RuntimeException("插入失败");
		libRoleMenusDao.insertObject(entity.getId(), menuIds);
		return row;
	}
	@RequiresLog("删除角色")
	@Override
	public int deleteObject(Integer id) {
		if(id<1||id==null){
			throw new RuntimeException("请输入合法的id值");
		}
		int row = libRoleDao.deleteObject(id);
		if(row==0){
			throw new RuntimeException("该记录可能已经不存在");
		}
		libRoleMenusDao.deleteObjectByRoleId(id);
		return row;
	}

	@Transactional(readOnly=true)
	@Override
	public RoleVo findObjectById(Integer id) {
		if(id==null||id<1){
			throw new IllegalArgumentException("id值不能为空");
		}
		RoleVo srVo = libRoleDao.findObjectById(id);
		if(srVo==null){
			throw new RuntimeException("此记录可能已经不存在");
		}
		
		return srVo;
	}

	@RequiresLog("角色修改")
	@Override
	public int updateObject(LibRole entity, Integer[] menuIds) {
		if(entity==null){
			throw new RuntimeException("角色信息不能为空");
		}
		if(entity.getName()==null||entity.getName()==""){
			throw new RuntimeException("请输入角色信息");
		}
		if(menuIds==null||menuIds.length==0){
			throw new RuntimeException("请为角色赋予权限");
		}
		int row = libRoleDao.updateObject(entity);
		if(row==0){
			throw new RuntimeException("记录可能已经不存在");
		}
		libRoleMenusDao.deleteObjectByRoleId(entity.getId());
		libRoleMenusDao.insertObject(entity.getId(), menuIds);
		return row;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<CheckBox> findObjects() {
		return libRoleDao.findObjects();
	}
}
