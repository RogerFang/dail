package com.dail.service.impl;

import com.dail.dao.ResearchDirectionMapper;
import com.dail.model.ResearchDirection;
import com.dail.service.ResearchDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class ResearchDirectionServiceImpl implements ResearchDirectionService {

    @Autowired
    private ResearchDirectionMapper researchDirectionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return researchDirectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(ResearchDirection record) {
        return researchDirectionMapper.insertSelective(record);
    }

    @Override
    public ResearchDirection selectByPrimaryKey(Integer id) {
        return researchDirectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ResearchDirection record) {
        return researchDirectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ResearchDirection> selectAll() {
        return researchDirectionMapper.selectAll();
    }
}
