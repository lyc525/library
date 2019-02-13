package xyz.lycwood.service.impl;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.exception.ServiceException;
import xyz.lycwood.common.vo.JsonResult;
import xyz.lycwood.common.vo.PageObject;
import xyz.lycwood.dao.LibUserBooksDao;
import xyz.lycwood.dao.LibUserDao;
import xyz.lycwood.dao.LibUserhistoryDao;
import xyz.lycwood.entity.LibUser;
import xyz.lycwood.entity.Lib_logs;
import xyz.lycwood.service.LibUserService;
import xyz.lycwood.util.PageUtil;

@Service
@Transactional(timeout=30,rollbackFor=RuntimeException.class)
public class LibUserServiceImpl implements LibUserService {

	@Autowired
	private LibUserDao libUserDao;

	@Autowired
	private LibUserBooksDao libUserBooksDao;

	@Autowired
	private LibUserhistoryDao libUserhistoryDao;

	@Transactional(readOnly=true)
	@RequiresPermissions("user:find")
	@Override
	public PageObject<LibUser> findObject(String username, Integer pageCurrent) {
		// 1.数据合法性验证
		if (pageCurrent == null || pageCurrent <= 0)
			throw new ServiceException("参数不合法");
		// 2.依据条件获取总记录数
		int rowCount = libUserDao.getRowCount(username);
		if (rowCount == 0)
			throw new ServiceException("记录不存在");
		// 3.计算startIndex的值
		int pageSize = 10;
		
		List<LibUser> list = new ArrayList<>();//创建一个结果集对象
		
		PageHelper.startPage(pageCurrent,pageSize);//用分页查询插件进行分页
		
		list = libUserDao.findObject(username);//正常的select查询
		
		PageObject<LibUser> po = PageUtil.page(list,pageSize); //调用工具类封装数据
		
		return po;

	}

	@RequiresLog("会员删除")
	@RequiresPermissions("user:delete")
	@Override
	public int deleteObjectByUserId(Integer id) {
		int rows = libUserDao.deleteObjectByUserId(id);
		libUserBooksDao.deleteObjectByUserId(id);
		libUserhistoryDao.deleteObjectByUserId(id);
		return rows;
	}

	@RequiresLog("会员禁用启用")
	@RequiresPermissions("user:valid")
	@Override
	public int validById(Integer id, Integer valid) {

		// 1.参数有效性校验
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");
		if (valid == null || (valid != 1 && valid != 0))
			throw new IllegalArgumentException("valid值不正确");
		// 2.禁用启用操作
		int rows = libUserDao.validById(id, valid);
		if (rows == 0)
			throw new ServiceException("记录可能已经不存在");
		// 3.返回结果
		return rows;
	}

	@Transactional(readOnly=true)
	@Override
	public LibUser findObjectById(Integer id) {
		LibUser record = libUserDao.findObjectById(id);
		return record;
	}

	@Override
	public LibUser findByUsername(String username) {
		return libUserDao.findUserByUsername(username);
	}
	
	@Override
	public int updateUserMessById(Integer id, String username, String phone, String hobby) {
		return libUserDao.updateUserMessById(id, username, phone, hobby);
	}

	@Override
	public int insertObject(String username, String password, String gender, String[] check_val, String tel,
			String cardId) {
		//生成会员userNum
				String userNum = getUserNum();
				//生成盐值salt
				String salt=UUID.randomUUID().toString();
				//对密码加密
				String sHash=new SimpleHash("MD5",password,salt).toHex();
				//获取爱好类
				String hobby = getHobby(check_val);
				//新建实体,储存数据
				LibUser entity = new LibUser();
				entity.setUserNum(userNum);
				entity.setUsername(username);
				entity.setPassword(sHash);
				entity.setSalt(salt);
				entity.setGender(gender);
				entity.setPhone(tel);
				entity.setCardId(cardId);
				entity.setVip(0);
				entity.setHobby(hobby);
				entity.setValid(1);
				entity.setCreatedTime(new Date());
				entity.setLastTime(new Date());
				entity.setBookNum(0);
				entity.setOverNum(0);
				entity.setBorNum(10);
				int rows = libUserDao.insertObject(entity);
				return rows;
	}
	
	/**
	 * 将爱好类String数组转换为字符串
	 * @param object
	 * @return
	 */
	private String getHobby(String[] object) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < object.length; i++){
		 sb. append(object[i]);
		}
		String hobby = sb.toString();
		return hobby;
	}
	
	/**
	 * 生成userNum
	 * @return
	 */
	private String getUserNum() {
		//获取注册年月日
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		//获取数据库中已有会员数量
		Integer nums = libUserDao.findObjects();
		//设置新会员的会员编码(未加日期)
        Format f1 = new DecimalFormat("00000");
        String userId = f1.format(nums+1);
        //与日期拼接
        String userNum = date+userId;
        System.out.println(userNum);
		return userNum;
	}
	
	@Override
	public LibUser findObjectByUsername(String username) {
		LibUser obj = libUserDao.findObjectByUsername(username);
		return obj;
	}

	@Override
	public JsonResult doCheckUsername(String username) {
		//System.out.println("username"+username);
		int rows = libUserDao.findObjectCountByUsername(username);
		
		if(rows>=1){
			//System.out.println("进没进");
			JsonResult asd = new JsonResult();
			asd.setState(0);
			asd.setMessage("用户已存在!");
			return asd;
			//throw new ServiceException("该用户名已经存在");
		}else{
			return null;
		}
	}


}
