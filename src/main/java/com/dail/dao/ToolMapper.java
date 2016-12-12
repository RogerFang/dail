package com.dail.dao;

import com.dail.model.Tool;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tool record);

    int insertSelective(Tool record);

    Tool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tool record);

    int updateByPrimaryKeyWithBLOBs(Tool record);

    int updateByPrimaryKey(Tool record);

    List<Tool> selectAllBaseInfo();

    List<Tool> selectAllWithBlobInfo();

    Tool selectByIdWithInfo(Integer id);
}