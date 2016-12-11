package com.dail.dao;

import com.dail.model.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    SysRole selectByName(String name);

    List<SysRole> selectAll();

    Set<SysRole> selectByUserId(Integer userId);
}