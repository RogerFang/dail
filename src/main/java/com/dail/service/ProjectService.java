package com.dail.service;

import com.dail.model.Project;
import com.github.pagehelper.PageInfo;

/**
 * Created by Roger on 2016/12/11.
 */
public interface ProjectService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    PageInfo<Project> page(int pageNumber, int pageSize);

    PageInfo<Project> pageByUid(Integer uid, int pageNumber, int pageSize);

    Project selectByIdWithInfo(Integer id);

    Project selectByIdWithDetail(Integer id);

    void update(Project record);
}
