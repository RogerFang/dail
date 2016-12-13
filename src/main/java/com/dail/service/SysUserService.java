package com.dail.service;


import com.dail.model.People;
import com.dail.model.SysUser;

import java.util.Set;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SysUserService {

    SysUser selectById(Integer userId);

    SysUser selectByUsername(String username);

    int register(SysUser user);

    Set<SysUser> selectAllGenerals();

    Set<SysUser> selectAllAdmins();

    int bind(Integer userId, Integer peopleId);

    int bind(SysUser sysUser, People people);

    SysUser selectByIdWithPeople(Integer id);

    SysUser selectByIdWithPeopleInfo(Integer id);
}
