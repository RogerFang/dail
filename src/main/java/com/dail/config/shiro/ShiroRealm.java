package com.dail.config.shiro;

import com.dail.model.SysRole;
import com.dail.model.SysUser;
import com.dail.service.SysRoleService;
import com.dail.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roger on 2016/12/11.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

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
        authorizationInfo.addRoles(getRoleStrSet(sysRoleService.selectByUserId(user.getId())));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) authenticationToken.getPrincipal();
        SysUser user = sysUserService.selectByUsername(username);
        if (user == null){
            // 没找到账号
            throw new UnknownAccountException();
        }

        if (Boolean.TRUE.equals(user.getEnabled())){
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

    private Set<String> getRoleStrSet(Set<SysRole> roles){
        Set<String> set = new HashSet<>();
        for (SysRole role: roles){
            set.add(role.getName());
        }
        return set;
    }
}
