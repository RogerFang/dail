package com.dail.service;

import com.dail.model.People;
import com.github.pagehelper.PageInfo;

/**
 * Created by Roger on 2016/12/10.
 */
public interface PeopleService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(People record);

    PageInfo<People> pageWithInfo(int pageNumber, int pageSize);

    PageInfo<People> pageWithDetail(int pageNumber, int pageSize);

    People selectByIdWithDetail(Integer id);
}
