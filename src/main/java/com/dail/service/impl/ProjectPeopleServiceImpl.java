package com.dail.service.impl;

import com.dail.dao.ProjectPeopleMapper;
import com.dail.model.ProjectPeopleKey;
import com.dail.service.ProjectPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/13.
 */
@Service
public class ProjectPeopleServiceImpl implements ProjectPeopleService{

    @Autowired
    private ProjectPeopleMapper projectPeopleMapper;

    @Override
    public int deleteByPrimaryKey(ProjectPeopleKey key) {
        return projectPeopleMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(ProjectPeopleKey record) {
        return projectPeopleMapper.insert(record);
    }

    @Override
    public int deleteByProjectId(Integer projectId) {
        return projectPeopleMapper.deleteByProjectId(projectId);
    }
}
