package com.dail.service;

import com.dail.model.ProjectDirectionKey;

/**
 * Created by Roger on 2016/12/13.
 */
public interface ProjectDirectionService {

    int deleteByPrimaryKey(ProjectDirectionKey key);

    int insert(ProjectDirectionKey record);

    int deleteByProjectId(Integer projectId);
}
