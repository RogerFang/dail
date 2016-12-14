package com.dail.service.impl;

import com.dail.dao.PeopleMapper;
import com.dail.model.People;
import com.dail.model.PeopleDirectionKey;
import com.dail.service.PeopleDirectionService;
import com.dail.service.PeopleService;
import com.dail.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roger on 2016/12/10.
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    private static final int MAX_LENGTH= 650;

    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private PeopleDirectionService peopleDirectionService;

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
    public PageInfo<People> pageWithInfoUser(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<People> peopleList = peopleMapper.selectAllBaseWithInfoUser();
        return new PageInfo<>(peopleList);
    }

    @Override
    public People selectByIdWithDetail(Integer id) {
        return peopleMapper.selectByIdWithDetail(id);
    }

    @Override
    public People selectByIdWithInfoUser(Integer id) {
        return peopleMapper.selectByIdWithInfoUser(id);
    }

    @Override
    public List<People> selectAllNotParticipants(List<People> list) {
        List<Integer> ids = Lists.newArrayList();
        for (People people: list){
            ids.add(people.getId());
        }
        return peopleMapper.selectAllNotParticipants(ids);
    }

    @Override
    public List<People> selectAll() {
        return peopleMapper.selectAllBaseWithInfo();
    }

    @Transactional
    @Override
    public void update(People record) {
        record.setPureDesc(StrUtil.getShortContent(record.getDescription(), MAX_LENGTH));
        peopleMapper.updateByPrimaryKeySelective(record);
        List<Integer> directionIds = record.getResearchDirectionIds();
        if (directionIds != null && directionIds.size() > 0){
            peopleDirectionService.deleteByPeopleId(record.getId());
            for (Integer did: directionIds){
                PeopleDirectionKey key = new PeopleDirectionKey();
                key.setPeopleId(record.getId());
                key.setResearchDirectionsId(did);
                peopleDirectionService.insert(key);
            }
        }
    }

    @Transactional
    @Override
    public void insert(People record) {
        record.setPureDesc(StrUtil.getShortContent(record.getDescription(), MAX_LENGTH));
        peopleMapper.insertSelective(record);
        List<Integer> directionIds = record.getResearchDirectionIds();
        if (directionIds != null && directionIds.size() > 0){
            for (Integer did: directionIds){
                PeopleDirectionKey key = new PeopleDirectionKey();
                key.setPeopleId(record.getId());
                key.setResearchDirectionsId(did);
                peopleDirectionService.insert(key);
            }
        }
    }
}
