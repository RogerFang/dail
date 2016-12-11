package com.dail.service.impl;

import com.dail.dao.SlideMapper;
import com.dail.model.Slide;
import com.dail.service.SlideService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private SlideMapper slideMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return slideMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Slide record) {
        return slideMapper.insertSelective(record);
    }

    @Override
    public Slide selectByPrimaryKey(Integer id) {
        return slideMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Slide record) {
        return slideMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Slide> selectAllEnabled(Boolean enabled) {
        return slideMapper.selectAllEnabled(enabled);
    }

    @Override
    public List<Slide> selectAll() {
        return slideMapper.selectAll();
    }

    @Override
    public PageInfo<Slide> pageEnabled(Boolean enabled, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Slide> slides = slideMapper.selectAllEnabled(enabled);
        return new PageInfo<>(slides);
    }
}
