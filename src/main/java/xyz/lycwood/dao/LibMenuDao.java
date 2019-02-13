package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.lycwood.common.vo.Node;
import xyz.lycwood.entity.LibMenu;

public interface LibMenuDao {
	
	List<LibMenu>findObjects();
	
	int deleteObject(@Param("id")Integer id);
	
	int getChildCount(@Param("id")Integer id);
	
	int deleteObjectsByMenuId(@Param("id")Integer id);
	
	int insertObjects();
	
	List<Node>findZtreeNodes();
	
	int insertObject(LibMenu entity);
	
	int updateObject(LibMenu entity);
	
	List<String> findPermissions(@Param("menuIds") Integer[] menuIds);
}
