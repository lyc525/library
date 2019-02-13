package xyz.lycwood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.lycwood.common.exception.ServiceException;
import xyz.lycwood.dao.LibBookDao;
import xyz.lycwood.dao.LibUserBooksDao;
import xyz.lycwood.dao.LibUserDao;
import xyz.lycwood.dao.LibUserhistoryDao;
import xyz.lycwood.entity.LibBook;
import xyz.lycwood.entity.LibUser;
import xyz.lycwood.service.LibUserBookSercive;
@Service
@Transactional(timeout=30,rollbackFor=RuntimeException.class)
public class LibUserBookSerciveImpl implements LibUserBookSercive{
	@Autowired
	LibBookDao libBookDao;
	@Autowired
	LibUserDao libUserDao;
	@Autowired
	LibUserhistoryDao libUserhistoryDao;
	@Autowired
	LibUserBooksDao libUserBooksDao;
	
	@Override
	public String borrowBook(Integer bookId ,String username ) {
		if(bookId==null||bookId<1)
			throw new ServiceException("id值不合法");
		LibBook book = libBookDao.findBookById(bookId);
		Integer oddNum = book.getOddNum();
		if(oddNum<1)
			throw new ServiceException("此书已借完");
		Integer valid = book.getValid();
		if(valid==null||valid==0)
			throw new ServiceException("此书目前无法借阅");
		LibUser user = libUserDao.findUserByUsername(username);
		
		Integer borNum = user.getBorNum();
		
		if(borNum<1)
			throw new ServiceException("可借数量为0");
		borNum--;
		Integer bookNum = user.getBookNum();
		bookNum++;
		Integer userId = user.getId();
		libUserDao.updateById(userId, bookNum, borNum);
		oddNum--;
		libBookDao.updateNumById(bookId, oddNum);
		int rows = libUserhistoryDao.insertObjects(userId, bookId);
		System.out.println("关系表"+rows);
		libUserBooksDao.insertObjects(userId, bookId);
		return null;
	}

}
