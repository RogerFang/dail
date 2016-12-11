package com.dail.dao;

import com.dail.model.Logo;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logo record);

    int insertSelective(Logo record);

    Logo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logo record);

    int updateByPrimaryKey(Logo record);
}