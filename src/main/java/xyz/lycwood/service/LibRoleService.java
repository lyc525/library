package xyz.lycwood.service;

import java.util.List;

import xyz.lycwood.common.vo.CheckBox;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.LibRole;
import xyz.lycwood.vo.RoleVo;

public interface LibRoleService {

    PageObject<LibRole> findObjects(String name,Integer pageCurrent);
	
	int insertObject(LibRole entity,Integer [] menuIds);
	
	int deleteObject(Integer id);
	
	RoleVo findObjectById(Integer id);
	
	int updateObject(LibRole entity,Integer [] menuIds);
	
	List<CheckBox> findObjects();
}
