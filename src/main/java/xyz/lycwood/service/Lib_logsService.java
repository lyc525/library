package xyz.lycwood.service;

import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.entity.Lib_logs;

public interface Lib_logsService {

	PageObject<Lib_logs> findObjects(String username,Integer strtPage);
	
	int deleteObjects(Integer... ids);
}
