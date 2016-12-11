package com.dail.dao;

import com.dail.model.SysUserRoleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);

    int deleteByUserId(Integer userId);
}