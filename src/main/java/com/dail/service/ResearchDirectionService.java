package com.dail.service;

import com.dail.model.ResearchDirection;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
public interface ResearchDirectionService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ResearchDirection record);

    ResearchDirection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResearchDirection record);

    List<ResearchDirection> selectAll();
}
