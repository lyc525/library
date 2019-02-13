package xyz.lycwood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.dao.Lib_logsDao;
import xyz.lycwood.entity.Lib_logs;
import xyz.lycwood.service.Lib_logsService;
import xyz.lycwood.util.PageUtil;
@Service
public class Lib_logsServiceImpl implements Lib_logsService{
	@Autowired
	private Lib_logsDao lib_logsDao;
	
	@RequiresPermissions("log:find")
	@Override
	public PageObject<Lib_logs> findObjects(String username,Integer pageCurrent) {
		
		if(pageCurrent == null || pageCurrent < 1){
			throw new IllegalArgumentException("页码值异常!");
		}
		Integer pageSize = 10;
		
		List<Lib_logs> list = new ArrayList<>();//创建一个结果集对象
		
		PageHelper.startPage(pageCurrent,pageSize);//用分页查询插件进行分页
		
		list = lib_logsDao.findObjects(username);//正常的select查询
		
		PageObject<Lib_logs> po = PageUtil.page(list,pageSize); //调用工具类封装数据
		
		
		return po;
	}

	@RequiresPermissions("log:delete")
	@Override
	public int deleteObjects(Integer... ids) {
		
		int flag = lib_logsDao.deleteObjects(ids);
		return flag;
	}
	

}
