package com.dail.dao;

import com.dail.model.Tool;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tool record);

    int insertSelective(Tool record);

    Tool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tool record);

    int updateByPrimaryKeyWithBLOBs(Tool record);

    int updateByPrimaryKey(Tool record);
}