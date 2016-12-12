package com.dail.service.impl;

import com.dail.dao.InstitutionMapper;
import com.dail.model.Institution;
import com.dail.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionMapper institutionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return institutionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Institution record) {
        return institutionMapper.insertSelective(record);
    }

    @Override
    public Institution selectByPrimaryKey(Integer id) {
        return institutionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Institution record) {
        return institutionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Institution> selectAll() {
        return institutionMapper.selectAll();
    }
}
