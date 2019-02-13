package xyz.lycwood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.exception.ServiceException;
import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.dao.LibBookDao;
import xyz.lycwood.entity.LibBook;
import xyz.lycwood.entity.LibUser;
import xyz.lycwood.service.LibBookService;
import xyz.lycwood.util.PageUtil;

@Service
@Transactional(timeout=30,rollbackFor=RuntimeException.class)
public class LibBookServiceImpl implements LibBookService {

	@Autowired
	private LibBookDao libBookDao;

	@Transactional(readOnly=true)
	//@RequiresLog("图书查看")
	@RequiresPermissions("book:find")
	@Override
	public PageObject<LibBook> findPageObjects(String name, Integer pageCurrent) {
		// 1.验证参数合法性
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = libBookDao.getRowCount(name);
		//System.out.println("结果行数"+rowCount);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0)
			throw new ServiceException("系统没有查到对应记录");
		// 3.基于条件查询当前页记录(pageSize定义为2)
		// 3.1)定义pageSize
		int pageSize = 10;

		List<LibBook> list = new ArrayList<>();// 创建一个结果集对象
		
		PageHelper.startPage(pageCurrent, pageSize);// 用分页查询插件进行分页

		list = libBookDao.findPageObjects(name);
		/*for(LibBook i:list){
			System.out.println(i.toString());
		}*/
		// 正常的select查询
		PageObject<LibBook> po = PageUtil.page(list, pageSize); // 调用工具类封装数据
		
		return po;

	}

	@RequiresLog("图书删除")
	@RequiresPermissions("book:delete")
	@Override
	public int deleteObject(Integer... ids) {
		if (ids == null || ids.length == 0)
			throw new ServiceException("数据不合法,请重新输入");
		List<LibBook> list = new ArrayList<>();
		for (Integer id : ids) {
			LibBook book = libBookDao.findBookById(id);
			list.add(book);
		}
		for (LibBook book : list) {
			if (book.getTotalNum() != book.getOddNum()) {
				libBookDao.updateValid(book.getId(), 0);
				throw new ServiceException("书籍总数量与剩余数量不匹配,不能进行删除操作!");
			}
		}
		int rows = libBookDao.deleteObject(ids);
		if (rows == 0)
			throw new ServiceException("此信息可能已经不存在");
		return rows;
	}

	@RequiresLog("图书保存")
	@RequiresPermissions("book:save")
	@Override
	public int insertObjects(LibBook entity) {
		return libBookDao.insertObject(entity);
	}

	@Override
	public LibBook findBookById(Integer id) {
		return libBookDao.findBookById(id);
	}

	@RequiresLog("图书修改")
	@RequiresPermissions("book:update")
	@Override
	public int updateObject(LibBook entity) {
		return libBookDao.updateObject(entity);
	}

	@RequiresLog("图书禁用启用")
	@RequiresPermissions("book:valid")
	@Override
	public int updateValid(Integer id, Integer valid) {
		if (id == null || id < 1) {
			throw new IllegalArgumentException("id值异常!");
		}
		if (valid != 0 && valid != 1) {
			throw new ServiceException("状态码异常!");
		}
		int rows = libBookDao.updateValid(id, valid);
		if (rows == 0) {
			throw new ServiceException("更新状态码失败!");
		}
		return rows;
	}

	@Override
	public PageObject<LibBook> searchPageObjects(String name, Integer pageCurrent, String category) {
		// 1.验证参数合法性
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		if (!category.equals("Z")) {
			int rowCount = libBookDao.getRowCount(name);
			// 2.2) 验证查询结果，假如结果为0不再执行如下操作
			if (rowCount == 0){
				LibBook a = new LibBook();
				List<LibBook> bb = new ArrayList<>();
				a.setId(0);
				bb.add(a);
				PageObject<LibBook> po = new PageObject<LibBook>();
				po.setRecords(bb);
				return po;
			}
				//throw new ServiceException("系统没有查到对应记录");
		}

		Integer pageSize = 3;

		List<LibBook> list = new ArrayList<>();// 创建一个结果集对象

		PageHelper.startPage(pageCurrent, pageSize);// 用分页查询插件进行分页
		if (category.equals("S")) {
			list = libBookDao.findPageObjects(name);// 正常的select查询
		} else {
			list = libBookDao.findBookByAuthor(name);// 正常的select查询
		}
		PageObject<LibBook> po = PageUtil.page(list, pageSize); // 调用工具类封装数据

		return po;
	}

	@Override
	public PageObject<LibBook> findBookByCategory(String category) {
		if (category == null)
			throw new ServiceException("分类参数异常");
		Integer pageSize = 4;

		List<LibBook> list = new ArrayList<>();// 创建一个结果集对象

		PageHelper.startPage(1, pageSize);// 用分页查询插件进行分页

		list = libBookDao.findBookByCategory(category);// 正常的select查询

		PageObject<LibBook> po = PageUtil.page(list, pageSize);

		return po;
	}

}
