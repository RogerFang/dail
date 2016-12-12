package com.dail.dao;

import com.dail.model.ProjectPeopleKey;

public interface ProjectPeopleMapper {
    int deleteByPrimaryKey(ProjectPeopleKey key);

    int insert(ProjectPeopleKey record);

    int insertSelective(ProjectPeopleKey record);
}