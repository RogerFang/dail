package com.dail.service.impl;

import com.dail.dao.PeopleMapper;
import com.dail.model.People;
import com.dail.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/10.
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return peopleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(People record) {
        return peopleMapper.insert(record);
    }

    @Override
    public int insertSelective(People record) {
        return peopleMapper.insertSelective(record);
    }

    @Override
    public People selectByPrimaryKey(Integer id) {
        return peopleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(People record) {
        return peopleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(People record) {
        return peopleMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(People record) {
        return peopleMapper.updateByPrimaryKey(record);
    }
}
