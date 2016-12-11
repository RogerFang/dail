package com.dail.service.impl;

import com.dail.dao.SysRoleMapper;
import com.dail.model.SysRole;
import com.dail.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }

    @Override
    public Set<SysRole> selectByUserId(Integer userId) {
        return roleMapper.selectByUserId(userId);
    }
}
