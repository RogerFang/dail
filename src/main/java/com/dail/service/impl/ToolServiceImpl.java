package com.dail.service.impl;

import com.dail.dao.ToolMapper;
import com.dail.model.Tool;
import com.dail.service.ToolService;
import com.dail.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class ToolServiceImpl implements ToolService {
    private static int MAX_LENGTH = 600;

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
    public PageInfo<Tool> pageByUid(int pageNumber, int pageSize, Integer uid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Tool> tools = toolMapper.selectAllBaseInfoByUid(uid);
        return new PageInfo<>(tools);
    }

    @Override
    public Tool selectByIdWithInfo(Integer id) {
        return toolMapper.selectByIdWithInfo(id);
    }

    @Override
    public Tool selectByIdWithInfoUser(Integer id) {
        return toolMapper.selectByIdWithInfoUser(id);
    }

    @Override
    public int insert(Tool record) {
        record.setDescription(StrUtil.getShortContent(record.getContent(), MAX_LENGTH));
        record.setCreateTime(new Date());
        return toolMapper.insertSelective(record);
    }

    @Override
    public int update(Tool record) {
        record.setDescription(StrUtil.getShortContent(record.getContent(), MAX_LENGTH));
        return toolMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Tool> search(int pageNumber, int pageSize, String query) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Tool> tools = toolMapper.searchAllBaseInfo(StrUtil.filtersql(query));
        return new PageInfo<>(tools);
    }
}
