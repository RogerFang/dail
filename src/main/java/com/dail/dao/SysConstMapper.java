package com.dail.dao;

import com.dail.model.SysConst;
import org.springframework.stereotype.Repository;

@Repository
public interface SysConstMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConst record);

    int insertSelective(SysConst record);

    SysConst selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConst record);

    int updateByPrimaryKey(SysConst record);
}