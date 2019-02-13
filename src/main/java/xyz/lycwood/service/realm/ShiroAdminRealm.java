package xyz.lycwood.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import xyz.lycwood.dao.LibAdminDao;
import xyz.lycwood.dao.LibAdminRolesDao;
import xyz.lycwood.dao.LibMenuDao;
import xyz.lycwood.dao.LibRoleMenusDao;
import xyz.lycwood.entity.LibAdmin;
/**
 * 员工管理员登录认证及授权模块
 * @author Administrator
 *
 */
@Service
public class ShiroAdminRealm extends AuthorizingRealm{
	
	@Autowired
	private LibAdminDao libAdminDao;
	@Autowired
	private LibAdminRolesDao libAdminRolesDao;
	@Autowired
	private LibRoleMenusDao libRoleMenusDao;
	@Autowired
	private LibMenuDao libMenuDao;
	
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
		UsernamePasswordToken newToken = (UsernamePasswordToken)token;
		String adminname = newToken.getUsername();
		LibAdmin admin= libAdminDao.findObject(adminname);
		if(admin==null){
			throw new UnknownAccountException(); 
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(admin.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin, admin.getPassword(),credentialsSalt,getName());
		return info;
	}
	/**
	 * 授权模块
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LibAdmin admin = (LibAdmin)principals.getPrimaryPrincipal();
		List<Integer> roleIds = libAdminRolesDao.findObjectById(admin.getId());
		if(roleIds == null || roleIds.size() == 0){
			throw new AuthorizationException();
		}
		List<Integer> menuIds = libRoleMenusDao.findMenuIdByRoleIds(roleIds.toArray(new Integer[] {}));
		if(menuIds == null || menuIds.size() == 0){
			throw new AuthorizationException();
		}
		List<String> permissions = libMenuDao.findPermissions(menuIds.toArray(new Integer[]{}));
		if(permissions == null || permissions.size() == 0){
			throw new AuthorizationException();
		}
		SimpleAuthorizationInfo Info = new SimpleAuthorizationInfo();
		Set<String> set = new HashSet<>();
		for (String per : permissions) {
			if(!StringUtils.isEmpty(per)){
				set.add(per);
			}
		}
		Info.setStringPermissions(set);
		return Info;
	}

}
