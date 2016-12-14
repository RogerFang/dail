package com.dail.service;

import com.dail.model.PeopleDirectionKey;

/**
 * Created by Roger on 2016/12/13.
 */
public interface PeopleDirectionService {

    int deleteByPrimaryKey(PeopleDirectionKey key);

    int insert(PeopleDirectionKey record);

    int deleteByPeopleId(Integer peopleId);
}
