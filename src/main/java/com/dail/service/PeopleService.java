package com.dail.service;

import com.dail.model.People;

/**
 * Created by Roger on 2016/12/10.
 */
public interface PeopleService {

    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKeyWithBLOBs(People record);

    int updateByPrimaryKey(People record);
}
