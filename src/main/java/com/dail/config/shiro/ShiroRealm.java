package com.dail.config.shiro;

import com.dail.entity.SysUser;
import com.dail.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Roger on 2016/12/11.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        authorizationInfo.addRoles(user.getRoleStrs());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) authenticationToken.getPrincipal();
        SysUser user = sysUserService.findByUsername(username);
        if (user == null){
            // 没找到账号
            throw new UnknownAccountException();
        }

        if (Boolean.TRUE.equals(user.isEnabled())){
            // 账号锁定
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialSalt()),//salt=salt+salt
                getName()  //realm name
        );
    }
}
