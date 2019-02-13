package xyz.lycwood.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lycwood.common.annotation.RequiresLog;
import xyz.lycwood.common.utils.IPUtils;
import xyz.lycwood.dao.Lib_logsDao;
import xyz.lycwood.entity.LibAdmin;
import xyz.lycwood.entity.LibUser;
import xyz.lycwood.entity.Lib_logs;

@Aspect
@Service
public class LibLogAspect {
	
	@Autowired
	private Lib_logsDao lib_logsDao;
	
	@Pointcut("@annotation(xyz.lycwood.common.annotation.RequiresLog)")
	public void doLog(){}
	
	@Around("doLog()")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		long t1 = System.currentTimeMillis();
		Object obj = jp.proceed();
		long t2 = System.currentTimeMillis();
		doinsertDataBase(jp,(t2-t1));
		return obj;
	}

	private void doinsertDataBase(ProceedingJoinPoint jp, long time)throws Throwable {
		Class<?> targetCls = jp.getTarget().getClass();
		MethodSignature ms = (MethodSignature)jp.getSignature();
		Method method = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		String methodName = targetCls.getName() + method.getName();
		Object[] args = jp.getArgs();
		RequiresLog requiresLog = method.getDeclaredAnnotation(RequiresLog.class);
		String operation = requiresLog.value();
		LibAdmin admin = (LibAdmin)SecurityUtils.getSubject().getPrincipal();
		String username = admin.getAdminname();
		String ip = IPUtils.getIpAddr();
		Lib_logs log = new Lib_logs();
		log.setUsername(username);
		log.setOperation(operation);
		log.setMethod(methodName);
		log.setParams(Arrays.toString(args));
		log.setIp(ip);
		log.setTime(time);
		log.setCreatedTime(new Date());
		//System.out.println(log);
		//System.out.println(log);
		//将日志写入数据库
		lib_logsDao.insertObject(log);
	}
}
