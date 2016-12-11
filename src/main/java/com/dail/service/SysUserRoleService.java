package com.dail.service;

import com.dail.model.SysUserRoleKey;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SysUserRoleService {

    int insert(SysUserRoleKey record);

    int deleteByUserId(Integer userId);

    int changeRole(Integer userId, Integer roleId);

    void changeToAdmin(Integer userId);

    void changeToGeneral(Integer userId);
}
