package com.dail.service.impl;

import com.dail.dao.ProjectMapper;
import com.dail.model.Project;
import com.dail.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Project record) {
        return projectMapper.insertSelective(record);
    }

    @Override
    public Project selectByPrimaryKey(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return projectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Project> page(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Project> projects = projectMapper.selectAllBaseInfo();
        return new PageInfo<>(projects);
    }

    @Override
    public Project selectByIdWithInfo(Integer id) {
        return projectMapper.selectByIdWithInfo(id);
    }
}
