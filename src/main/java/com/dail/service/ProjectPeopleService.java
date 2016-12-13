package com.dail.service;

import com.dail.model.ProjectPeopleKey;

/**
 * Created by Roger on 2016/12/13.
 */
public interface ProjectPeopleService {

    int deleteByPrimaryKey(ProjectPeopleKey key);

    int insert(ProjectPeopleKey record);

    int deleteByProjectId(Integer projectId);
}
