package xyz.lycwood.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.exception.ServiceException;
import xyz.lycwood.common.vo.Node;
import xyz.lycwood.dao.LibMenuDao;
import xyz.lycwood.entity.LibMenu;
import xyz.lycwood.service.LibMenuService;
@Service
public class LibMenuServiceImpl implements LibMenuService{
	
	@Autowired
	private LibMenuDao libMenuDao;
	
	@RequiresLog("菜单查看")
	@RequiresPermissions("menu:find")
	@Override
	public List<LibMenu> findObjects() {
		List<LibMenu> list = libMenuDao.findObjects();
		return list;
	}

	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("必须先选择");
		int count = libMenuDao.getChildCount(id);
		if(count!=0)
			throw new ServiceException("请先删除子菜单");
		int rows = libMenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		libMenuDao.deleteObjectsByMenuId(id);
		return rows;
	}

	@Override
	public List<Node> findZtreeNodes() {
		List<Node> list = libMenuDao.findZtreeNodes();
		if(list==null||list.size()==0)
			throw new ServiceException("没有查询到菜单信息");
		return list;
	}

	@Override
	public int insertObject(LibMenu entity) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名字不能为空");
		if(StringUtils.isEmpty(entity.getUrl()))
			throw new IllegalArgumentException("菜单url不能为空");
		if(StringUtils.isEmpty(entity.getPermission()))
			throw new IllegalArgumentException("授权标示不能为空");
		int rows;
		try {
			rows = libMenuDao.insertObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存对象失败!");
		}
		return rows;
	}

	@Override
	public int updateObject(LibMenu entity) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名字不能为空");
		int	rows = libMenuDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在了");
		return rows;
	}


}
