package xyz.lycwood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import xyz.lycwood.entity.Lib_logs;

public interface Lib_logsDao {
	
	List<Lib_logs> findObjects(@Param("username") String username);
	
	int deleteObjects(@Param("ids") Integer... ids);
	
	int insertObject(Lib_logs entity);
}
