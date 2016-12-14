package com.dail.service.impl;

import com.dail.constant.RoleEnum;
import com.dail.dao.SysUserRoleMapper;
import com.dail.model.SysUser;
import com.dail.model.SysUserRoleKey;
import com.dail.service.SysRoleService;
import com.dail.service.SysUserRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleService roleService;

    @Override
    public int insert(SysUserRoleKey record) {
        return userRoleMapper.insert(record);
    }

    @Override
    public int deleteByUserId(Integer userId) {
        return userRoleMapper.deleteByUserId(userId);
    }

    @Transactional
    @Override
    public int changeRole(Integer userId, Integer roleId) {
        deleteByUserId(userId);
        SysUserRoleKey key = new SysUserRoleKey(userId, roleId);
        return insert(key);
    }

    @Override
    public void changeToAdmin(Integer userId) {
        changeRole(userId, RoleEnum.ADMIN);
    }

    @Override
    public void changeToGeneral(Integer userId) {
        changeRole(userId, RoleEnum.GENERAL);
    }

    @Transactional
    private void changeRole(Integer userId, RoleEnum roleEnum){
        deleteByUserId(userId);
        Integer roleId = roleService.selectByName(roleEnum.name()).getId();
        SysUserRoleKey key = new SysUserRoleKey(userId, roleId);
        insert(key);
    }
}
