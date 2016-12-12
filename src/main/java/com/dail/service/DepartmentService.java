package com.dail.service;

import com.dail.model.Department;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
public interface DepartmentService {

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAll();

}
