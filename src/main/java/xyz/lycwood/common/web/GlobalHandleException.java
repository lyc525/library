package xyz.lycwood.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.lycwood.common.vo.JsonResult;

@ControllerAdvice
public class GlobalHandleException {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandlerRuntimeException(RuntimeException e){
		System.out.println("用户名"+e.getMessage());
		e.printStackTrace();
		return new JsonResult(e.getMessage());
	}

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult shiroExceptionHandler(ShiroException e){
		JsonResult result = new JsonResult();
		result.setState(0);
		if(e instanceof IncorrectCredentialsException){
			result.setMessage("密码不正确");
		}else if(e instanceof UnknownAccountException){
			result.setMessage("此账户不存在");
		}else if(e instanceof LockedAccountException){
			result.setMessage("账户被禁用了");
		}else if(e instanceof AuthorizationException){
			result.setMessage("没有此权限");
		}else{
			e.printStackTrace();
		}
		return result;
	}
}
