package com.dail.dao;

import com.dail.model.ResearchDirection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchDirectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResearchDirection record);

    int insertSelective(ResearchDirection record);

    ResearchDirection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResearchDirection record);

    int updateByPrimaryKey(ResearchDirection record);

    List<ResearchDirection> selectAll();

    List<ResearchDirection> selectByPeopleId(Integer peopleId);

    List<ResearchDirection> selectByProjectId(Integer projectId);
}