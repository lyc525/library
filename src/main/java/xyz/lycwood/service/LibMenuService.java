package xyz.lycwood.service;

import java.util.List;

import xyz.lycwood.common.vo.Node;
import xyz.lycwood.entity.LibMenu;

public interface LibMenuService {
	List<LibMenu> findObjects();
	
	int deleteObject(Integer id);
	
	List<Node>findZtreeNodes();
	
	int insertObject(LibMenu entity);
	
	int updateObject(LibMenu entity);
}
