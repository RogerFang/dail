package com.dail.dao;

import com.dail.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKeyWithBLOBs(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> selectAllBaseInfo();

    List<Project> selectAllBaseInfoByUid(Integer uid);

    List<Project> selectAllWithBlobInfo();

    Project selectByIdWithInfo(Integer id);

    Project selectByIdWithDetail(Integer id);
}