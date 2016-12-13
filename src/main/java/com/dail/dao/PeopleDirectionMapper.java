package com.dail.dao;

import com.dail.model.PeopleDirectionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleDirectionMapper {
    int deleteByPrimaryKey(PeopleDirectionKey key);

    int insert(PeopleDirectionKey record);

    int insertSelective(PeopleDirectionKey record);

    int deleteByPeopleId(Integer peopleId);
}