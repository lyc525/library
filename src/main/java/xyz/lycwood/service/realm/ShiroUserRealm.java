package xyz.lycwood.service.realm;



import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lycwood.dao.LibAdminRolesDao;
import xyz.lycwood.dao.LibMenuDao;
import xyz.lycwood.dao.LibRoleMenusDao;
import xyz.lycwood.dao.LibUserDao;
import xyz.lycwood.entity.LibUser;
/**
 * 员工管理员登录认证及授权模块
 * @author Administrator
 *
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	
	@Autowired
	private LibUserDao libUserDao;
	
	
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//1.构建凭证匹配器对象
		HashedCredentialsMatcher hcm = new HashedCredentialsMatcher("MD5");
		//2.设置加密次数
		hcm.setHashIterations(1);
		super.setCredentialsMatcher(hcm);
	}
	/**
	 * 认证模块
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken newToken = (UsernamePasswordToken) token;
		String username = newToken.getUsername();
		LibUser user = libUserDao.findUserByUsername(username);
		if(user == null ){
			throw new UnknownAccountException();
		}
		if(user.getValid() == 0){
			throw new LockedAccountException();
		}
		ByteSource newSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo Info = new SimpleAuthenticationInfo(user,user.getPassword(), newSalt, getName());
		return Info;
	}
	/**
	 * 授权模块
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

}
