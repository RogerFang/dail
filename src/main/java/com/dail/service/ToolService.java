package com.dail.service;

import com.dail.model.Tool;
import com.github.pagehelper.PageInfo;

/**
 * Created by Roger on 2016/12/11.
 */
public interface ToolService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Tool record);

    Tool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tool record);

    PageInfo<Tool> page(int pageNumber, int pageSize);

    Tool selectByIdWithInfo(Integer id);
}
