package com.dail.service.impl;

import com.dail.dao.PeopleMapper;
import com.dail.model.People;
import com.dail.service.PeopleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<People> pageWithInfo(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<People> peopleList = peopleMapper.selectAllBaseWithInfo();
        return new PageInfo<>(peopleList);
    }

    @Override
    public PageInfo<People> pageWithDetail(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<People> peopleList = peopleMapper.selectAllBaseWithDetail();
        return new PageInfo<>(peopleList);
    }

    @Override
    public People selectByIdWithDetail(Integer id) {
        return peopleMapper.selectByIdWithDetail(id);
    }
}
