package com.dail.service.impl;

import com.dail.dao.PeopleDirectionMapper;
import com.dail.model.PeopleDirectionKey;
import com.dail.service.PeopleDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/14.
 */
@Service
public class PeopleDirectionServiceImpl implements PeopleDirectionService {

    @Autowired
    private PeopleDirectionMapper peopleDirectionMapper;

    @Override
    public int deleteByPrimaryKey(PeopleDirectionKey key) {
        return peopleDirectionMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(PeopleDirectionKey record) {
        return peopleDirectionMapper.insert(record);
    }

    @Override
    public int deleteByPeopleId(Integer peopleId) {
        return peopleDirectionMapper.deleteByPeopleId(peopleId);
    }
}
