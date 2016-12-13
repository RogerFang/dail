package com.dail.dao;

import com.dail.model.ProjectDirectionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDirectionMapper {
    int deleteByPrimaryKey(ProjectDirectionKey key);

    int insert(ProjectDirectionKey record);

    int insertSelective(ProjectDirectionKey record);

    int deleteByProjectId(Integer projectId);
}