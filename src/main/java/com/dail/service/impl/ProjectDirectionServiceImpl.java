package com.dail.service.impl;

import com.dail.dao.ProjectDirectionMapper;
import com.dail.model.ProjectDirectionKey;
import com.dail.service.ProjectDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/13.
 */
@Service
public class ProjectDirectionServiceImpl implements ProjectDirectionService {

    @Autowired
    private ProjectDirectionMapper projectDirectionMapper;

    @Override
    public int deleteByPrimaryKey(ProjectDirectionKey key) {
        return projectDirectionMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(ProjectDirectionKey record) {
        return projectDirectionMapper.insert(record);
    }

    @Override
    public int deleteByProjectId(Integer projectId) {
        return projectDirectionMapper.deleteByProjectId(projectId);
    }
}
