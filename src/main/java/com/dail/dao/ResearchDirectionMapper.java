package com.dail.dao;

import com.dail.model.ResearchDirection;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchDirectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResearchDirection record);

    int insertSelective(ResearchDirection record);

    ResearchDirection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResearchDirection record);

    int updateByPrimaryKeyWithBLOBs(ResearchDirection record);

    int updateByPrimaryKey(ResearchDirection record);
}