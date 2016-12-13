package com.dail.dao;

import com.dail.model.ProjectPeopleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPeopleMapper {
    int deleteByPrimaryKey(ProjectPeopleKey key);

    int insert(ProjectPeopleKey record);

    int insertSelective(ProjectPeopleKey record);

    int deleteByProjectId(Integer projectId);
}