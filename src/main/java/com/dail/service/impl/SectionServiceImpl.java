package com.dail.service.impl;

import com.dail.dao.SectionMapper;
import com.dail.model.Section;
import com.dail.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Section record) {
        return sectionMapper.insertSelective(record);
    }

    @Override
    public Section selectByPrimaryKey(Integer id) {
        return sectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Section record) {
        return sectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Section> selectAll() {
        return sectionMapper.selectAll();
    }

    @Override
    public List<Section> selectAllEnabled(Boolean enabled) {
        return sectionMapper.selectAllEnabled(enabled);
    }
}
