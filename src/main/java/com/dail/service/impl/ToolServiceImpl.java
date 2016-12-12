package com.dail.service.impl;

import com.dail.dao.ToolMapper;
import com.dail.model.Tool;
import com.dail.service.ToolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolMapper toolMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return toolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Tool record) {
        return toolMapper.insertSelective(record);
    }

    @Override
    public Tool selectByPrimaryKey(Integer id) {
        return toolMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Tool record) {
        return toolMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Tool> page(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Tool> tools = toolMapper.selectAllBaseInfo();
        return new PageInfo<>(tools);
    }

    @Override
    public Tool selectByIdWithInfo(Integer id) {
        return toolMapper.selectByIdWithInfo(id);
    }
}
