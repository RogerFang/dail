package com.dail.dao;

import com.dail.model.People;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKeyWithBLOBs(People record);

    int updateByPrimaryKey(People record);
}