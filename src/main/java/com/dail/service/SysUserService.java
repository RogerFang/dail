package com.dail.service;

import com.dail.entity.SysUser;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SysUserService {

    SysUser findByUsername(String username);

    SysUser register(SysUser user);
}
